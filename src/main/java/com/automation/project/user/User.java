package com.automation.project.user;

import com.automation.project.asset.Asset;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Data
@NoArgsConstructor
public class User {
    private String fname;
    private String lname;
    private String email;
    private int packageNumber;
    private String purchaseDate;
    private String deployDate;
    private List<Asset> assetList;

    public User(String fname, String lname, int packageNumber, String purchaseDate) throws ParseException {
        this.fname = fname;
        this.lname = lname;
        this.email = fname.toLowerCase().charAt(0) + lname.toLowerCase() + "@flagshippioneering.com";
        this.packageNumber = packageNumber;
        SimpleDateFormat formatter= new SimpleDateFormat("MM/dd/yy");
        SimpleDateFormat formatter1= new SimpleDateFormat("MMM dd, yyyy");
        Date date1= formatter.parse(purchaseDate);
        System.out.println(date1);
        purchaseDate = formatter1.format(date1);
        this.purchaseDate = purchaseDate;

        System.out.println(this.purchaseDate);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        this.deployDate = formatter1.format(cal.getTime());




        this.assetList = new ArrayList<>();
        List<Asset> anotherList;
        switch (packageNumber) {
            case 1:
                anotherList = Arrays.asList(
                        new Asset(this.fname + " " + this.lname, "Laptop MAC", "$2500", "14\" Macbook Pro, M1 Pro, 16GB Ram, 512GB", purchaseDate, deployDate, "Zehntek"),
                        new Asset(this.fname + " " + this.lname, "Monitor 24\"", "$255", "24\" LG 24BL650C-B 1080", purchaseDate, deployDate, "Zehntek"),
                        new Asset(this.fname + " " + this.lname, "Monitor 24\"", "$255", "24\" LG 24BL650C-B 1080", purchaseDate, deployDate, "Zehntek"),
                        new Asset(this.fname + " " + this.lname, "StarTech Dock", "$321", "StarTech Thunderbolt 3 USB-C Dual 4k", purchaseDate, deployDate, "Zehntek"),
                      new Asset(this.fname + " " + this.lname, "Apple Keyboard", "$110", "Apple Magic Keyboard Full", purchaseDate, deployDate, "Zehntek"),
                        new Asset(this.fname + " " + this.lname, "Apple Mouse", "$85", "Apple Magic Mouse", purchaseDate, deployDate, "Zehntek"),
                        new Asset(this.fname + " " + this.lname, "Adapters", "$75", "Apple USB Type-C Digital AV Multipart Adapter", purchaseDate, deployDate, "Zehntek"));

                assetList.addAll(anotherList);
                break;
            case 2:
                anotherList = Arrays.asList(
                        new Asset(this.fname + " " + this.lname, "Laptop MAC", "$2500", "14\" Macbook Pro, M1 Pro, 16GB Ram, 512GB", purchaseDate, deployDate, "Zehntek"),
                        new Asset(this.fname + " " + this.lname, "Monitor 32\"", "$485", "32\" LG 32BN67U-B 4k", purchaseDate, deployDate, "Zehntek"),
                        new Asset(this.fname + " " + this.lname, "StarTech Dock", "$321", "StarTech Thunderbolt 3 USB-C Dual 4k", purchaseDate, deployDate, "Zehntek"),
                        new Asset(this.fname + " " + this.lname, "Apple Keyboard", "$110", "Apple Magic Keyboard Full", purchaseDate, deployDate, "Zehntek"),
                        new Asset(this.fname + " " + this.lname, "Apple Mouse", "$85", "Apple Magic Mouse", purchaseDate, deployDate, "Zehntek"),
                        new Asset(this.fname + " " + this.lname, "Adapters", "$75", "Apple USB Type-C Digital AV Multipart Adapter", purchaseDate, deployDate, "Zehntek"));
                assetList.addAll(anotherList);
                break;
            case 3:
                anotherList = Arrays.asList(
                        new Asset(this.fname + " " + this.lname, "Laptop PC", "$2500", "14\" X1 Carbon, 16GB RAM, 512GB SSD, GHz i7. (8th gen)", purchaseDate, deployDate, "Zehntek"),
                        new Asset(this.fname + " " + this.lname, "Monitor 24\"", "$255", "24\" LG 24BL650C-B 1080", purchaseDate, deployDate, "Zehntek"),
                        new Asset(this.fname + " " + this.lname, "Monitor 24\"", "$255", "24\" LG 24BL650C-B 1080", purchaseDate, deployDate, "Zehntek"),
                        new Asset(this.fname + " " + this.lname, "StarTech Dock", "$321", "StarTech Thunderbolt 3 USB-C Dual 4k", purchaseDate, deployDate, "Zehntek"),
                        new Asset(this.fname + " " + this.lname, "Keyboard/Mouse Combo (PC)", "$65", "Logitech MK 540 (combo)", purchaseDate, deployDate, "Zehntek"));
                assetList.addAll(anotherList);
                break;
            case 4:
                anotherList = Arrays.asList(
                        new Asset(this.fname + " " + this.lname, "Laptop PC", "$2500", "14\" X1 Carbon, 16GB RAM, 512GB SSD, GHz i7. (8th gen)", purchaseDate, deployDate, "Zehntek"),
                        new Asset(this.fname + " " + this.lname, "Monitor 32\"", "$485", "32\" LG 32BN67U-B 4k", purchaseDate, deployDate, "Zehntek"),
                        new Asset(this.fname + " " + this.lname, "StarTech Dock", "$321", "StarTech Thunderbolt 3 USB-C Dual 4k", purchaseDate, deployDate, "Zehntek"),
                        new Asset(this.fname + " " + this.lname, "Keyboard/Mouse Combo (PC)", "$65", "Logitech MK 540 (combo)", purchaseDate, deployDate, "Zehntek"));
                assetList.addAll(anotherList);
                break;
        }
    }


}

