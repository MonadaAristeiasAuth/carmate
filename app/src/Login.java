package com.carmate.android.carmate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import okhttp3.OkHttpClient;
import okhttp3.Response;

import static java.lang.Math.random;

/**
 * Created by brandt on 4/2/2019.
 */

public class Login extends AppCompatActivity{
    private TextView tvEmail,tvPass,tvName,tvSurname;
    private Button btnSignUp;
    private SharedPreferences preferences;
    private String email,password,name,surname;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        preferences = this.getSharedPreferences("com.carmate.android.carmate", Context.MODE_PRIVATE);
        if(preferences.getBoolean("logged-in",false)){
            Intent intent = new Intent(Login.this,MainScreen.class);
            startActivity(intent);
            Login.this.finish();
        }

        tvEmail = (TextView) findViewById(R.id.user_email);
        tvPass = (TextView) findViewById(R.id.user_password);
        tvName = (TextView) findViewById(R.id.user_name);
        tvSurname = (TextView) findViewById(R.id.user_surname);

        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvEmail.getText()=="" || tvPass.getText()=="" || tvName.getText()=="" || tvSurname.getText()==""){
                    Toast.makeText(Login.this,"You have not filled all the information asked", Toast.LENGTH_SHORT);
                }
                else{
                    preferences.edit().putBoolean("logged-in",true).apply();
                    email = tvEmail.getText().toString();
                    password = tvPass.getText().toString();
                    name = tvEmail.getText().toString();
                    surname = tvSurname.getText().toString();

                    PostUserData uploadUser = new PostUserData();
                    uploadUser.execute();

                    Intent intent = new Intent(Login.this,MainScreen.class);
                    startActivity(intent);
                    Login.this.finish(); //user is not allowed to reach login screen using the back button
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(preferences.getBoolean("logged-in",false)){
            Intent intent = new Intent(Login.this,MainScreen.class);
            startActivity(intent);
            Login.this.finish();
        }

        tvEmail = (TextView) findViewById(R.id.user_email);
        tvPass = (TextView) findViewById(R.id.user_password);
        tvName = (TextView) findViewById(R.id.user_name);
        tvSurname = (TextView) findViewById(R.id.user_surname);

        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvEmail.getText()=="" || tvPass.getText()=="" || tvName.getText()=="" || tvSurname.getText()==""){
                    Toast.makeText(Login.this,"You have not filled all the information asked", Toast.LENGTH_SHORT);
                }
                else{
                    preferences.edit().putBoolean("logged-in",true).apply();
                    email = tvEmail.getText().toString();
                    password = tvPass.getText().toString();
                    name = tvEmail.getText().toString();
                    surname = tvSurname.getText().toString();

                    PostUserData uploadUser = new PostUserData();
                    uploadUser.execute();

                    Intent intent = new Intent(Login.this,MainScreen.class);
                    startActivity(intent);
                    Login.this.finish(); //user is not allowed to reach login screen using the back button
                }
            }
        });
    }

    //AsyncTask class to upload user data
    private class PostUserData extends AsyncTask<String, String, ArrayList<Route>> {
        String msg = "Internet/DB_Credentials/Windows_FireWall_TurnOn Error, See Android Monitor in the bottom For details!";
        ArrayList<Route> x;

        @Override
        protected ArrayList<Route> doInBackground(String... strings)  // Connect to the database, write query and add items to array list
        {
            x=new ArrayList<>();
            int rand = (int)Math.random();
            String uniqueID = "123";//UUID.randomUUID().toString();
            preferences.edit().putString("userId",uniqueID).apply();
            preferences.edit().putString("isDriver","0").apply();

            OkHttpClient client = new OkHttpClient();
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url("http://carmate.gr.138-201-126-35.linuxzone125.grserver.gr/addUser.php?id=" + uniqueID + "&name="+name+
                            "&surname="+surname+"&email=" +email+ "&password=" + password)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                String responseString = response.body().string();
                System.out.println(responseString);


            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return x;
        }
    }
}
