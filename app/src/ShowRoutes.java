package com.carmate.android.carmate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by brandt on 5/2/2019.
 */

public class ShowRoutes extends AppCompatActivity{
    private ArrayList<Route> routesList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_routes);
        mRecyclerView = (RecyclerView) findViewById(R.id.recViewUserRoutes);
        routesList = new ArrayList<>();
        //mToolbar = (Toolbar) findViewById(R.id.user_profile_toolbar);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<Route> myDataset = new ArrayList<>();
        //myDataset.add(new Route("1","3","31/12/2019","Thessaloniki","Athens","3","4"));

        mAdapter = new AdapterUserRoutes(routesList);
        mRecyclerView.setAdapter(mAdapter);

        SharedPreferences preferences;
        preferences = this.getSharedPreferences("com.carmate.android.carmate", Context.MODE_PRIVATE);
        id = preferences.getString("userId","-1");

        ShowRoutes.SyncData syncData = new SyncData();
        syncData.execute();

    }

    //AsyncTask class to fetch route data from remote database
    private class SyncData extends AsyncTask<String, String, ArrayList<Route>> {
        String msg = "Internet/DB_Credentials/Windows_FireWall_TurnOn Error, See Android Monitor in the bottom For details!";
        ProgressDialog progress;



        @Override
        protected void onPreExecute() //Starts the progress dailog
        {
            progress = ProgressDialog.show(ShowRoutes.this, "Loading...",
                    "", true);
        }

        @Override
        protected ArrayList<Route> doInBackground(String... strings)  // Connect to the database, write query and add items to array list
        {



            OkHttpClient client = new OkHttpClient();
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url("http://carmate.gr.138-201-126-35.linuxzone125.grserver.gr/returnRoutesInterested.php" +
                            "?id=" + id)
                    .build();
            try {
                Response response = client.newCall(request).execute();

                String responseString = response.body().string();
                String[] responseArray = responseString.split("//");

                for(int i=0;i<responseArray.length;i++){
                    System.out.println(responseArray[i] + "$$$$$$$$$$$$$$$$\n");
                    String[] res = responseArray[i].split("/");
                    if(res[0] == null) break;
                    Route routeInfoObj = new Route(res[0],res[1],res[2],res[3],res[4],res[5],res[6]);
                    routesList.add(routeInfoObj);
                }



            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routesList;
        }

        @Override
        protected void onPostExecute(ArrayList<Route> fetchedData)
        {
            progress.dismiss();
            mAdapter.notifyDataSetChanged();
        }
    }
}
