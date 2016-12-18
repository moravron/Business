package com.example.tzuriel.business.Model.BackEnd;

/**
 * Created by tzuriel on 18/12/2016.
 */
public class BackEndFactory {


    String database = "lists";

    DataBaseInterface instance = null;

    public DataBaseInterface getInstance() {
        if (database == "list") {
            if (instance==null)
            {
                // instance = new Lists();
            }
        }
            return instance;
    }



}
