package com.example.tzuriel.business.Model.DataSource;

import android.content.ContentValues;

import com.example.tzuriel.business.Model.BackEnd.Content_Provider;
import com.example.tzuriel.business.Model.BackEnd.DataBaseInterface;
import com.example.tzuriel.business.Model.Entities.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by tzuriel on 18/12/2016.
 */


public class Lists implements DataBaseInterface {
    private ArrayList<User> users_list = new ArrayList<>();
    private ArrayList<Business> business_list = new ArrayList<>();
    private ArrayList<Attraction> attraction_list = new ArrayList<>();


    @Override
    public void add_user(ContentValues contentUser) {
        User user = new User(contentUser.getAsString("Mail"), contentUser.getAsString("Password"), contentUser.getAsInteger("UserId"));
        users_list.add(user);
    }

    @Override
    public void add_business(ContentValues contentBusiness) {
        Business business = new Business(contentBusiness.getAsInteger("ID"), contentBusiness.getAsString("CompanyName"), contentBusiness.getAsString("Address"), contentBusiness.getAsString("Mail"), contentBusiness.getAsString("PhoneNumber"), contentBusiness.getAsInteger("UserId"));
        business_list.add(business);

    }

    @Override
    public void add_attractions(ContentValues contentAttraction) {
        AttractionTypes type=AttractionTypes.valueOf(contentAttraction.getAsString("Type"));

        Attraction attraction = new Attraction(contentAttraction.getAsInteger("businessId"),contentAttraction.getAsString("Name"),contentAttraction.getAsDouble("Cost"),contentAttraction.getAsString("Description"),type,contentAttraction.getAsString("Country"),contentAttraction.getAsString("BeginDate"),contentAttraction.getAsString("EndDate"));
        attraction_list.add(attraction);

    }



    @Override
    public void delete_business(int businessId) {
        for (Attraction attraction : attraction_list) // delete all attraction
        {
            if (attraction.getBusinessId() == businessId) {
                attraction_list.remove(attraction);
            }

        }
        for (Business business : business_list) {
            if (business.getId() == businessId) {
                business_list.remove(business);
            }
        }


    }

    @Override
    public void delete_attraction(int attrationId) {
        for (Attraction attraction : attraction_list) {
            if (attraction.getBusinessId() == attrationId) {
                attraction_list.remove(attraction);
            }
        }
    }

    @Override
    public boolean is_user_mail_exsist(String mail) {
        for (User user : users_list) {
            if (user.getMail().equals(mail)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User validate_and_get_user(String mail, String password) {
        for (User user : users_list) {
            if (user.getMail().equals(mail) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Business> getAllBusiness() {
        return business_list;
    }

    @Override
    public ArrayList<Attraction> getAllAttractions() {return attraction_list;}

    @Override
    public ArrayList<User> getAllUsers() {
        return users_list;
    }


    @Override
    public ArrayList<Business> get_business_of_user(int userId) {
        ArrayList<Business> tmp = new ArrayList<>();
        for (Business business : business_list) {
            if (business.getUserId() == userId) {
                tmp.add(business);
            }
        }
        return tmp;
    }

    @Override
    public ArrayList<Attraction> get_attraction_of_business(int businessId) {
        ArrayList<Attraction> tmp = new ArrayList<>();
        for (Attraction attraction : attraction_list) {
            if (attraction.getBusinessId() == businessId) {
                tmp.add(attraction);
            }
        }
        return tmp;
    }




}



