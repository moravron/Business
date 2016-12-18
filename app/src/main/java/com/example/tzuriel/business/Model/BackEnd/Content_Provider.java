package com.example.tzuriel.business.Model.BackEnd;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.tzuriel.business.Model.Entities.Attraction;
import com.example.tzuriel.business.Model.Entities.Business;
import com.example.tzuriel.business.Model.Entities.User;

import java.util.ArrayList;

import javax.sql.DataSource;

/**
 * Created by tzuriel on 18/12/2016.
 */
    public class Content_Provider extends ContentProvider {
    DataBaseInterface instance = new BackEndFactory().getInstance();
    public static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static final String CP_TAG= "entertaimentContent";

    static {
        sUriMatcher.addURI("com.example.tzuriel.business", "Users", 1);
        sUriMatcher.addURI("com.example.tzuriel.business", "Business", 2);
        sUriMatcher.addURI("com.example.tzuriel.business", "Attractions", 3);
    }

    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        try {
            int uriCode = sUriMatcher.match(uri);
            switch (uriCode) {
                case 1: // users
                    ArrayList<User> users = instance.getAllUsers();
                    MatrixCursor userMatrix = new MatrixCursor(new String[] { "mail", "password","idUser"});
                    for ( User user : users) {
                        userMatrix.addRow(new Object[]{ user.getMail(), user.getPassword(),user.get_userId()});
                    }
                    return userMatrix;



                case 2: // business
                    ArrayList<Business> businesses = instance.getAllBusiness();
                    //        Business business = new Business(contentBusiness.getAsInteger("ID"), contentBusiness.getAsString("CompanyName"), contentBusiness.getAsString("Address"), contentBusiness.getAsString("Mail"), contentBusiness.getAsString("PhoneNumber"), contentBusiness.getAsInteger("UserId"));

                    MatrixCursor businessMatrix = new MatrixCursor(new String[]{"ID", "CompanyName", "Address","PhoneNumber", "UserID"});
                    for (Business business : businesses) {
                        businessMatrix.addRow(new Object[]{business.getId(), business.getCompany_name(), business.getAddress(), business.getPhone_number(), business.getUserId()});
                    }
                    return businessMatrix;


                case 3: // attraction
                    ArrayList<Attraction> attractions = instance.getAllAttractions();
                    MatrixCursor AttractionMatrix = new MatrixCursor(new String[] {"BusinessId", "Name", "Cost", "Description", "Type", "Country","BeginDate","EndDate"});
                    for ( Attraction attraction : instance.getAllAttractions()) {
                        AttractionMatrix.addRow(new Object[]{attraction.getBusinessId(),attraction.getName(),attraction.getCost(),attraction.getDescription(),attraction.getType(),attraction.getCuntry(),attraction.getBegin_date(),attraction.getEnd_date()});
                    }


                    return AttractionMatrix;
                default:
                    throw new IllegalArgumentException("invalid query, no such path.");
            }
        }
        catch (Exception ex) {
            Log.d(CP_TAG, ex.getMessage());
            return null;
        }
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
            int uriCode = sUriMatcher.match(uri);//1 user 2 business 3 attraction
            switch (uriCode) {
                case 1:
                    instance.add_user(values);
                    return null;
                case 2:
                    instance.add_business(values);
                    return null;
                case 3:
                    instance.add_attraction(values);
                    return null;
                default:
                    throw new IllegalArgumentException("invalid query, no such path.");
            }
        }



    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}







