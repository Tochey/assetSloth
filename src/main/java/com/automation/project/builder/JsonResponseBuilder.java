package com.automation.project.builder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class JsonResponseBuilder {

    public ResponseEntity<Object> responseBuilder(String message, HttpStatus status, Object responseObj) {

        Map<String, Object> map = new HashMap<String, Object>();
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);
        map.put("timestamp", formattedDate);
        return new ResponseEntity<Object>(map, status);

    }
}
