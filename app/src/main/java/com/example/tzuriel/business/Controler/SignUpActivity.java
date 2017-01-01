package com.example.tzuriel.business.Controler;

import android.app.Activity;
import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tzuriel.business.Model.BackEnd.BackEndFactory;
import com.example.tzuriel.business.Model.BackEnd.DataBaseInterface;
import com.example.tzuriel.business.Model.Entities.User;
import com.example.tzuriel.business.R;

public class SignUpActivity extends AppCompatActivity {
public static int UserGlobalId;
public static int BusinessGlobalId;

    DataBaseInterface instance = new BackEndFactory().getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);




        final EditText e_password = (EditText) findViewById(R.id.login_password);
        final EditText e_confirm_password = (EditText) findViewById(R.id.confirm_password);
        final EditText e_mail = (EditText) findViewById(R.id.mail);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = e_password.getText().toString();
                String confirm_password = e_confirm_password.getText().toString();
                String mail =e_mail.getText().toString();

                try
                {
                    if (check_details(password,confirm_password,mail))
                    {
                        //User new_user = new User(mail,password,++UserGlobalId);
                        ContentValues newUser = new ContentValues();
                        newUser.put("mail",mail);
                        newUser.put("password",password);
                        newUser.put("idUser",++UserGlobalId);

                        //                    MatrixCursor userMatrix = new MatrixCursor(new String[] { "mail", "password","idUser"});

                        //instance.add_user(new_user);
                        // open home user activity of this user




                    }

                }
                catch (Exception e)
                {
                    Toast.makeText(SignUpActivity.this,e.toString(), Toast.LENGTH_LONG).show();
                }


            }



        })
        ;
    }
    public boolean check_details(String password, String confirm_password, String mail) throws Exception {
        /**
         * check sign up details:
         *  - password equal to confirm password
         *  - mail not exists
         *  need to check how send mail to confirm it
         */



        if (!password.equals(confirm_password)) {
            throw new Exception("password is not equal to confirm password!");
        }

        if (instance.is_user_mail_exsist(mail))///////////////////////////////////////////////////
        {
            throw new Exception("this mail already exists");
        }

        return true;
    }




}
