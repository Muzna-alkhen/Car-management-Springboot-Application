package com.example.WepApplications.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SellDto {
    private String soldDate;

    private float soldPrice;

    private String customerName;
    public Date getSoldDate() throws ParseException {
        Date date =new SimpleDateFormat("dd/MM/yyyy").parse(this.soldDate);
        return date;



    }

    public void setSoldDate(String soldDate) {
        this.soldDate = soldDate;
    }

    public float getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(float soldPrice) {
        this.soldPrice = soldPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }



}
