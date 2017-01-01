package com.example.tzuriel.business.Controler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tzuriel.business.Model.BackEnd.BackEndFactory;
import com.example.tzuriel.business.Model.BackEnd.DataBaseInterface;
import com.example.tzuriel.business.Model.Entities.User;
import com.example.tzuriel.business.R;

import java.util.ArrayList;

public class homeUserActivity extends AppCompatActivity {
    DataBaseInterface instance = new BackEndFactory().getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intentgetuser = getIntent();
        final User user = (User) intentgetuser.getSerializableExtra("user");////////////////////// need to check
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);
        findViewById(R.id.Choose_business_Button).setOnClickListener(new View.OnClickListener() {
                                                                         @Override
                                                                         public void onClick(View view) {
                                                                             Intent choose_business = new Intent(homeUserActivity.this, ChooseBusinessActivity.class);
                                                                             choose_business.putExtra("business",instance.get_business_of_user(user.get_userId()));
                                                                             startActivity(choose_business);
                                                                         }

                                                                     }
        );
        findViewById(R.id.update_details_button).setOnClickListener(new View.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(View view) {
                                                                            Intent update_details = new Intent(homeUserActivity.this, ChooseBusinessActivity.class);
                                                                            update_details.putExtra("user",user);
                                                                            startActivity(update_details);//// update when screen be ready
                                                                        }

                                                                    }
        );

        findViewById(R.id.add_business_button).setOnClickListener(new View.OnClickListener() {
                                                                      @Override
                                                                      public void onClick(View view) {
                                                                          Intent activity_add_business = new Intent(homeUserActivity.this, AddBusinessActivity.class);
                                                                          startActivity(activity_add_business);//update when
                                                                      }

                                                                  }
        );





    }
}


