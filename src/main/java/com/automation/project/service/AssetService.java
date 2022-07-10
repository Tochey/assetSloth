package com.automation.project.service;

import com.automation.project.devicefarm.DeviceFarm;
import com.automation.project.user.User;
import com.automation.project.builder.JsonResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AssetService {

    @Autowired
    private final DeviceFarm driver;
    private final JsonResponseBuilder json = new JsonResponseBuilder();

    public AssetService(DeviceFarm driver) {
        this.driver = driver;
    }

    public ResponseEntity<?> addAndassign(User user) throws IOException, InterruptedException, ParseException {
        if (user.getFname() == null) {
            return new ResponseEntity<>("First name cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (user.getLname() == null) {
            return new ResponseEntity<>("Last name cannot be null", HttpStatus.BAD_REQUEST);
        }
        // if(user.getNumber() == null){
        // return new ResponseEntity<>("Phone number cannot be null",
        // HttpStatus.BAD_REQUEST);
        // }
        if (user.getPackageNumber() == 0) {
            return new ResponseEntity<>("WFH Number is null or not valid", HttpStatus.BAD_REQUEST);
        }
        if (user.getPurchaseDate() == null) {
            return new ResponseEntity<>("Purchase date cannot be null", HttpStatus.BAD_REQUEST);
        } else if (!validateDate(user.getPurchaseDate())) {
            return new ResponseEntity<>("Purchase date format is not valid", HttpStatus.BAD_REQUEST);
        }


        driver.addAndassign(user);
        return new ResponseEntity<>(user.getFname() + " " + user.getLname() + "'s assets are added", HttpStatus.OK);

    }

    public boolean validateDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");

        try {
            Date date1 = formatter.parse(date);
            String dat = formatter.format(date1);
            System.out.println(dat);
        } catch (Exception e) {

            return false;
        }

        return true;

    }

}
