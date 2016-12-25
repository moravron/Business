package com.example.tzuriel.business.Controler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tzuriel.business.Model.BackEnd.BackEndFactory;
import com.example.tzuriel.business.Model.Entities.User;
import com.example.tzuriel.business.R;

public class MainActivity extends AppCompatActivity {
    BackEndFactory instance = new BackEndFactory();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText e_mail= (EditText) findViewById(R.id.login_email);
        final EditText e_password= (EditText) findViewById(R.id.login_password);



        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener()
                                                           {
                                                               @Override
                                                               public void onClick(View view)
                                                               {
                                                                   String mail = e_mail.getText().toString();
                                                                   String password = e_password.getText().toString();
                                                                   User user = instance.getInstance().validate_and_get_user(mail,password);
                                                                   if (user != null)
                                                                   {
                                                                       Intent click_login = new Intent(MainActivity.this, homeUserActivity.class);
                                                                       click_login.putExtra("user",user);
                                                                       startActivity(click_login);
                                                                   }
                                                                   else
                                                                   {
                                                                       Toast.makeText(MainActivity.this,"mail or password incorrect! \n try again!",Toast.LENGTH_LONG).show();

                                                                   }

                                                               }
                                                           }
        );

        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener()
                                                       {
                                                           @Override
                                                           public void onClick(View view)
                                                           {
                                                               Intent click_sign_in = new Intent(MainActivity.this, SignUpActivity.class);
                                                               startActivity(click_sign_in);
                                                           }

                                                       }
        );    }


}
