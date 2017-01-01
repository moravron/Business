package com.example.tzuriel.business.Controler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tzuriel.business.R;

public class AddBusinessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business);

        findViewById(R.id.add_business_button).setOnClickListener(new View.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(View view) {
                                                                            Intent add_business_button = new Intent(AddBusinessActivity.this, homeUserActivity.class);
                                                                            startActivity(add_business_button);
                                                                        }

                                                                    }
        );


    }
}
