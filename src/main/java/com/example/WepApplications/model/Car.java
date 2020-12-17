package com.example.WepApplications.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="car")
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private float price;

    @Column
    private int seats;

    @Column
    private Date soldDate;

    @Column
    private float SoldPrice;

    @Column
    private String customerName;

    @Version
    private  int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Date getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(Date soldDate) {
        this.soldDate = soldDate;
    }

    public float getSoldPrice() {
        return SoldPrice;
    }

    public void setSoldPrice(float soldPrice) {
        SoldPrice = soldPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}