package com.example.WepApplications.model;
import javax.persistence.*;
import java.util.Set;

@Entity
public class Parameter {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column (name = "k")
    private String k;

    @Column
    private int value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
