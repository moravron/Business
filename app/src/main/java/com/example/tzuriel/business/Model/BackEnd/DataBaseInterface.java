package com.example.tzuriel.business.Model.BackEnd;

import android.content.ContentValues;

import com.example.tzuriel.business.Model.Entities.Attraction;
import com.example.tzuriel.business.Model.Entities.Business;
import com.example.tzuriel.business.Model.Entities.User;

import java.util.ArrayList;

/**
 * Created by tzuriel on 18/12/2016.
 */
public interface DataBaseInterface {



    public void add_user(ContentValues contentProvider);
    public void add_business(ContentValues contentProvider);
    public void add_attractions(ContentValues contentProvider);

    public void delete_business(int businessId);
    public void delete_attraction(int attractionId);



    public ArrayList<User> getAllUsers();
    public ArrayList<Business> getAllBusiness();
    public ArrayList<Attraction> getAllAttractions();


    public ArrayList<Business> get_business_of_user(int userId);
    public ArrayList<Attraction> get_attraction_of_business(int businessId);


    public User validate_and_get_user(String mail,String password);
    public boolean is_user_mail_exsist(String mail);
   // public void check_new_business_activity();



}
