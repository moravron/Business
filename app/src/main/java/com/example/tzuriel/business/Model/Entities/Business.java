package com.example.tzuriel.business.Model.Entities;

import java.io.Serializable;

/**
 * Created by tzuriel on 18/12/2016.
 */
public class Business implements Serializable {
    public Business(int businessId, String company_name, String address, String mail, String phone_number, int userId) {
        this.businessId = businessId;
        this.company_name = company_name;
        this.mail = mail;
        this.address = address;
        this.phone_number = phone_number;
        this.userId = userId;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }




    public int getId() {
        return businessId;
    }


    public int getUserId() {
        return userId;
    }


    private int businessId;
    private String company_name;
    private String mail;
    private String address;
    private String phone_number;
    private int userId;
}