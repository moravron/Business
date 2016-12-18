package com.example.tzuriel.business.Model.Entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by tzuriel on 18/12/2016.
 */
    public class User implements Serializable
    {
        private  static final long serialVersionUID = 1L;
        public User(String mail, String password, int userId) {
            this.mail = mail;
            this.password = password;
            this.userId = userId;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

       public int get_userId(){return userId;}

        private String mail;
        private String password;
        private int userId;


}
