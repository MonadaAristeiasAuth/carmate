package com.carmate.android.carmate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by brandt on 4/2/2019.
 */

public class UserProfile extends AppCompatActivity{
    private User currentUser;
    private SharedPreferences preferences;
    private String id;
    private TextView tvName,tvSurname,tvEmail;
    private ImageButton btnRoutesInterested, btnRoutesGoing,btnRoutesDriving;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        preferences = this.getSharedPreferences("com.carmate.android.carmate", Context.MODE_PRIVATE);
        id = preferences.getString("userId","-1");

        tvName = (TextView) findViewById(R.id.name);
        tvSurname = (TextView) findViewById(R.id.surname);
        tvEmail = (TextView) findViewById(R.id.email);

        btnRoutesInterested = (ImageButton) findViewById(R.id.btnRoutesInterested);
        btnRoutesInterested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfile.this,ShowRoutes.class);
                startActivity(intent);
            }
        });

        btnRoutesDriving = (ImageButton) findViewById(R.id.btn_routes_driving);
        btnRoutesDriving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(UserProfile.this,ActivityRoutesDriving.class);
                //startActivity(intent);
            }
        });

        SyncUserData syncUserData = new SyncUserData();
        syncUserData.execute();
    }


    //AsyncTask class to fetch user's data from remote database
    private class SyncUserData extends AsyncTask<String, String, User> {
        String msg = "Internet/DB_Credentials/Windows_FireWall_TurnOn Error, See Android Monitor in the bottom For details!";
        ProgressDialog progress;



        @Override
        protected void onPreExecute() //Starts the progress dailog
        {
            progress = ProgressDialog.show(UserProfile.this, "Loading...",
                    "", true);
        }

        @Override
        protected User doInBackground(String... strings)  // Connect to the database, write query and add items to array list
        {

            OkHttpClient client = new OkHttpClient();
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url("http://carmate.gr.138-201-126-35.linuxzone125.grserver.gr/getUserInfo.php?id=" + id)
                    .build();
            try {
                Response response = client.newCall(request).execute();

                String responseString = response.body().string();
                String[] res = responseString.split("/");
                currentUser = new User(id,res[0],res[1],res[2],"*",res[3],res[4],res[5],res[6]);


            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //return null;
            return currentUser;
        }

        @Override
        protected void onPostExecute(User fetchedData)
        {
            progress.dismiss();
            tvName.setText("Name: " + fetchedData.getName());
            tvSurname.setText("Surname: " + fetchedData.getSurname());
            tvEmail.setText("Email: " + fetchedData.getEmail());
        }
    }
}
