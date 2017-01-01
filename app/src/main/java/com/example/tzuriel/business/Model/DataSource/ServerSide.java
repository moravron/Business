package com.example.tzuriel.business.Model.DataSource;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;

import com.example.tzuriel.business.Model.Entities.Attraction;
import com.example.tzuriel.business.Model.Entities.AttractionTypes;
import com.example.tzuriel.business.Model.Entities.Business;
import com.example.tzuriel.business.Model.Entities.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by tzuriel on 01/01/2017.
 */
public class ServerSide {




    private static String GET(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            return response.toString();
        } else {
            return "";
        } }




    private static String POST(String url, Map<String,Object> params) throws IOException {

        //Convert Map<String,Object> into key=value&key=value pairs.
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(postData.toString().getBytes("UTF-8"));
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        }
        else return ""; }



    public Cursor getUserMatrixCursor()throws Exception {///////////user  list

        MatrixCursor userCursor = new MatrixCursor(new String[]{"userId","Mail", "Password"});
        JSONArray array = new JSONObject(GET("http://avron.vlab.jct.ac.il/" + "get_users_list.php")).getJSONArray("User");
        for (int i=0;i<array.length();i++)
        {
            JSONObject user= array.getJSONObject(i);
            userCursor.addRow(new Object[]
            {
                user.getInt("userId"),
                user.getString("Mail"),
                user.getString("Password")
            }
            );}
        return userCursor;
        }

    public Cursor getBusinessMatrixCursor()throws Exception {///////////////////business list

        MatrixCursor businessCursor = new MatrixCursor(new String[]{"BusinessId", "UserId", "CompanyName","Mail", "Address","PhoneNumber"});
        JSONArray array = new JSONObject(GET("http://avron.vlab.jct.ac.il/"+"get_businesss_list.php")).getJSONArray("Business");
        for (int i=0;i<array.length();i++)
        {
            JSONObject business= array.getJSONObject(i);
            businessCursor.addRow(new Object[]
                    {
                            business.getInt("BusinessId"),
                            business.getInt("UserId"),
                            business.getString("CompanyName"),
                            business.getString("Mail"),
                            business.getString("Address"),
                            business.getString("PhoneNumber")
                    }
            );}
        return businessCursor;
    }



    public Cursor getAttractionMatrixCursor()throws Exception {/////////attraction list

        MatrixCursor attractionCursor = new MatrixCursor(new String[]{"BusinessId", "AttractionName", "cost", "AttractionType", "country","beginDate","endDate", "Description"});
        JSONArray array = new JSONObject(GET("http://avron.vlab.jct.ac.il/"+"get_attractions_list.php")).getJSONArray("Attraction");
        for (int i=0;i<array.length();i++)
        {
            JSONObject attraction= array.getJSONObject(i);
            attractionCursor.addRow(new Object[]
                    {
                            attraction.getInt("BusinessId"),
                            attraction.getString("AttractionName"),
                            attraction.getDouble("cost"),
                            attraction.getString("AttractionType"),////////////////////////check
                            attraction.getString("country"),
                            attraction.getString("beginDate"),
                            attraction.getString("endDate"),
                            attraction.getString("Description")
                    }
            );}
        return attractionCursor;
    }

    public ArrayList<Attraction> matrix_to_attraction_list_converter(MatrixCursor mc)
    {

        ArrayList<Attraction> array = new ArrayList<>();

        mc.moveToFirst();
        while(mc.isAfterLast()==false)
        {
            AttractionTypes type=AttractionTypes.valueOf(mc.getString(mc.getColumnIndex("Type")));

            array.add(new Attraction(mc.getInt(mc.getColumnIndex("BusinessId")),mc.getString( mc.getColumnIndex("AttractionName")),mc.getDouble( mc.getColumnIndex("cost")),mc.getString( mc.getColumnIndex("Description")),type,mc.getString( mc.getColumnIndex("country")),mc.getString( mc.getColumnIndex("beginDate")),mc.getString( mc.getColumnIndex("endDate"))));
            mc.moveToNext();

        }
        return array;
    }
    public ArrayList<Business> matrix_to_business_list_converter(MatrixCursor mc)
    {

        ArrayList<Business> array = new ArrayList<>();
        mc.moveToFirst();
        while(mc.isAfterLast()==false)
        {
            array.add(new Business(mc.getInt( mc.getColumnIndex("BusinessId")),mc.getString( mc.getColumnIndex("CompanyName")),mc.getString( mc.getColumnIndex("Address")),mc.getString( mc.getColumnIndex("Mail")),mc.getString( mc.getColumnIndex("PhoneNumber")),mc.getInt(mc.getColumnIndex("UserId"))));
            mc.moveToNext();

        }
        return array;
    }
    public ArrayList<User> matrix_to_users_list_converter(MatrixCursor mc)
    {

        ArrayList<User> array = new ArrayList<>();
        mc.moveToFirst();
        while(mc.isAfterLast()==false)
        {
        array.add(new User(mc.getString( mc.getColumnIndex("Mail")),mc.getString( mc.getColumnIndex("Password")),mc.getInt( mc.getColumnIndex("userId"))));
            mc.moveToNext();

        }
        return array;
    }



    public void addUser(ContentValues user) {
        try {
            Map<String, Object> params = new LinkedHashMap<>();

            params.put("userId", user.getAsInteger("userId"));
            params.put("Mail",user.getAsString("Mail"));
            params.put("Password", user.getAsString("Password"));

            String results = POST("http://avron.vlab.jct.ac.il/addUser.php", params);
            if(results.equals("")){
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 2).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(2));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void updateUser(ContentValues user) {
        try {
            Map<String, Object> params = new LinkedHashMap<>();

            params.put("userId", user.getAsInteger("userId"));
            params.put("Mail",user.getAsString("Mail"));
            params.put("Password", user.getAsString("Password"));

            String results = POST("http://avron.vlab.jct.ac.il/update_user.php", params);
            if(results.equals("")){
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 2).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(2));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void deleteUser(ContentValues user) {
        try {
            Map<String, Object> params = new LinkedHashMap<>();

            params.put("userId", user.getAsInteger("userId"));

            String results = POST("http://avron.vlab.jct.ac.il/delete_user.php", params);
            if(results.equals("")){
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 2).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(2));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void addBusiness(ContentValues business) {
        try {
            Map<String, Object> params = new LinkedHashMap<>();

            params.put("BusinessId", business.getAsInteger("BusinessId"));
            params.put("UserId",business.getAsInteger("UserId"));
            params.put("CompanyName", business.getAsString("CompanyName"));
            params.put("Mail", business.getAsString("Mail"));
            params.put("Address", business.getAsString("Address"));
            params.put("PhoneNumber", business.getAsString("PhoneNumber"));

            String results = POST("http://avron.vlab.jct.ac.il/addBusiness.php", params);
            if(results.equals("")){
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 5).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(5));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void updateBusiness(ContentValues business) {
        try {
            Map<String, Object> params = new LinkedHashMap<>();

            params.put("BusinessId", business.getAsInteger("BusinessId"));
            params.put("UserId",business.getAsInteger("UserId"));
            params.put("CompanyName", business.getAsString("CompanyName"));
            params.put("Mail", business.getAsString("Mail"));
            params.put("Address", business.getAsString("Address"));
            params.put("PhoneNumber", business.getAsString("PhoneNumber"));

            String results = POST("http://avron.vlab.jct.ac.il/update_business.php", params);
            if(results.equals("")){
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 5).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(5));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void deleteBusiness(ContentValues business) {
        try {
            Map<String, Object> params = new LinkedHashMap<>();

            params.put("BusinessId", business.getAsInteger("BusinessId"));

            String results = POST("http://avron.vlab.jct.ac.il/delete_business.php", params);
            if(results.equals("")){
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 5).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(5));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void addAttraction(ContentValues attraction) {
        try {
            Map<String, Object> params = new LinkedHashMap<>();

            params.put("BusinessId", attraction.getAsInteger("BusinessId"));
            params.put("AttractionName",attraction.getAsString("AttractionName"));
            params.put("cost", attraction.getAsDouble("cost"));
            params.put("AttractionType", attraction.getAsString("AttractionType"));/////////type
            params.put("country", attraction.getAsString("country"));
            params.put("beginDate", attraction.getAsString("beginDate"));
            params.put("endDate", attraction.getAsString("endDate"));
            params.put("Description", attraction.getAsString("Description"));

            String results = POST("http://avron.vlab.jct.ac.il/addAttraction.php", params);
            if(results.equals("")){
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 7).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(7));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void updateAttraction(ContentValues attraction) {
        try {
            Map<String, Object> params = new LinkedHashMap<>();

            params.put("BusinessId", attraction.getAsInteger("BusinessId"));
            params.put("AttractionName",attraction.getAsString("AttractionName"));
            params.put("cost", attraction.getAsDouble("cost"));
            params.put("AttractionType", attraction.getAsString("AttractionType"));/////////type
            params.put("country", attraction.getAsString("country"));
            params.put("beginDate", attraction.getAsString("beginDate"));
            params.put("endDate", attraction.getAsString("endDate"));
            params.put("Description", attraction.getAsString("Description"));

            String results = POST("http://avron.vlab.jct.ac.il/update_attraction.php", params);
            if(results.equals("")){
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 7).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(7));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }public void deleteAttraction(ContentValues attraction) {
        try {
            Map<String, Object> params = new LinkedHashMap<>();

            params.put("BusinessId", attraction.getAsInteger("BusinessId"));
            params.put("AttractionName",attraction.getAsString("AttractionName"));

            String results = POST("http://avron.vlab.jct.ac.il/delete_attraction.php", params);
            if(results.equals("")){
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 7).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(7));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}

