package com.example.tzuriel.business.Model.Entities;

import java.io.Serializable;

/**
 * Created by tzuriel on 18/12/2016.
 */


    public class Attraction implements Serializable {
        int business_id;

        public Attraction(int business_id, String name, double cost, String description, AttractionTypes type, String country, String begin_date, String end_date) {
            this.businessId = business_id;
            this.name = name;
            this.cost = cost;
            this.country = country;
            this.begin_date = begin_date;
            this.end_date = end_date;
            this.description = description;
            this.type = type;
        }

        public int getBusinessId() {
            return business_id;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public String getCuntry() {
            return country;
        }

        public void setCuntry(String cuntry) {
            this.country = cuntry;
        }

        public String getBegin_date() {
            return begin_date;
        }

        public void setBegin_date(String begin_date) {


            this.begin_date = begin_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public AttractionTypes getType() {
            return type;
        }

        public void setType(AttractionTypes type) {
            this.type = type;
        }


     /*   public boolean is_date(String date)
        {
            String[] parts= date.split("/");
            if ((parts[0].length()^ parts[1].length())==2 ^ parts[2].length()==4^ )
        }
        */
        String name;
        double cost;
        String country;
        String begin_date;
        String end_date;
        String description;
        AttractionTypes type;
        int businessId;

}
