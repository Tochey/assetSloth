package com.automation.project.asset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Asset {
     private String assignedUser;
     private  String type;
     private  String price;
     private  String model;
     private  String purchaseDate;
     private String deployDate;
     private  String source;






}
