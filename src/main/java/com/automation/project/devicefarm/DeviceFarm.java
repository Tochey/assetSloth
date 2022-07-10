package com.automation.project.devicefarm;

import com.automation.project.credentials.Credentials;
import com.automation.project.asset.Asset;
import com.automation.project.user.User;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.devicefarm.DeviceFarmClient;
import software.amazon.awssdk.services.devicefarm.model.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;


@Component
public class DeviceFarm {

    ChromeOptions options;
    Credentials credentials;

    WebDriver driver;
    Boolean isActive = false;

    public DeviceFarm(){


    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public void login() throws InterruptedException, MalformedURLException {
        WebElement element;

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        this.credentials = new Credentials();

        //messgaing queue
        while (isActive) {

            System.out.println("Gotta wait : sleeping");
            Thread.sleep(1000);
        }

        isActive = true;

        System.setProperty("aws.accessKeyId", "YOUR_AWS_ACCESS_KEY");
        System.setProperty("aws.secretAccessKey", "YOUR_SECRET_ACCESS_KEY");
        String myProjectARN = "YOUR_PROJECT_ARN";
        DeviceFarmClient client = DeviceFarmClient.builder().region(Region.US_WEST_2).build();
        CreateTestGridUrlRequest request = CreateTestGridUrlRequest.builder().expiresInSeconds(300).projectArn(myProjectARN).build();
        CreateTestGridUrlResponse response = client.createTestGridUrl(request);
        URL testGridUrl =  new URL(response.url());

        driver = new RemoteWebDriver(testGridUrl, DesiredCapabilities.firefox());



        System.out.println("Im active and logging in ");

//        System.out.println(testGridUrl);
//        this.driver = new ChromeDriver();
        driver.get("https://login.assetpanda.com/users/sign_in");
//
        element = driver.findElement(By.id("user_email"));
        element.sendKeys(credentials.getEmail());
        element = driver.findElement(By.id("asset-panda-pwfield"));
        element.sendKeys(credentials.getPassword());
        element = driver.findElement(By.className("login-button"));
        element.click();


    }

    public void addEmployee(User user) throws MalformedURLException, InterruptedException {
//        login();
        WebElement element;
        try {
            System.out.println("adding emplpoyee");
            User newUser = new User(user.getFname(), user.getLname(), user.getPackageNumber(), user.getPurchaseDate());
            element = driver.findElement(By.linkText("Employees"));
            element.click();
//            wait(2000);
            element = driver.findElement(By.linkText("Add New"));
            element.click();
            element = driver.findElement(By.id("values_field_1"));
            element.sendKeys(newUser.getFname() + " " + newUser.getLname());

            element = driver.findElement(By.id("values_field_2"));
            element.sendKeys(newUser.getEmail());

            element = driver.findElement(By.xpath("//*[@id=\"entity_object_fields\"]/div[7]/div/div/div/div[1]"));
            element.click();
            wait(1000);
            element = driver.findElement(By.xpath("//*[@id=\"entity_object_fields\"]/div[7]/div/div/div/div[2]/div/div[4]"));
            element.click();
            element = driver.findElement(By.id("create-object-btn"));
            element.click();


            element = driver.findElement(By.cssSelector("#asset-menu > li:nth-child(1) > a"));
            element.click();


        } catch (NoSuchElementException e) {
            System.out.println(e.getLocalizedMessage());
            driver.quit();
//            return -1;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public void addAndassign(@NotNull User user) throws IOException, InterruptedException, ParseException {

        WebElement element;

        login();

        addEmployee(user);

        User newUser = new User(user.getFname(), user.getLname(), user.getPackageNumber(), user.getPurchaseDate());

        for (Asset as : newUser.getAssetList()) {
            System.out.println("adding assets");
            element = driver.findElement(By.linkText("Add New"));
            element.click();

            element = driver.findElement(By.id("value_ids_field_18-selectized"));
            element.sendKeys(newUser.getFname() + " " + newUser.getLname());
            element.sendKeys(Keys.ENTER);

            element = driver.findElement(By.id("value_ids_field_15-selectized"));
            element.sendKeys(as.getType());
            element.sendKeys(Keys.ENTER);

            element = driver.findElement(By.name("values[field_41]"));
            element.sendKeys(as.getPurchaseDate());
            element.sendKeys(Keys.ENTER);
//
            element = driver.findElement(By.name("values[field_130]"));
            element.sendKeys(as.getDeployDate());
            element.sendKeys(Keys.ENTER);

            element = driver.findElement(By.id("values_field_142"));
            element.sendKeys(as.getPrice());

            Select select = new Select(driver.findElement(By.id("values_field_8")));
            select.selectByVisibleText(as.getModel());


            select = new Select(driver.findElement(By.id("values_field_6")));
            select.selectByVisibleText(as.getSource());

            element = driver.findElement(By.id("create-object-btn"));
            element.click();
//             wait(2000);
            element = driver.findElement(By.cssSelector("#asset-menu > li:nth-child(1) > a"));
            element.click();
//        }
        }
            driver.quit();
            isActive = false;
            System.out.println("Im done");
//        return 0;
        }



}
