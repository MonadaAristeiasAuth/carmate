package com.carmate.android.carmate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by brandt on 10/1/2019.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Route> mDataset;
    private Route route;
    private Context context;
    private String id;
    private int positionTemp;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvRouteId,tvDate,tvFrom,tvTo,tvPeople,tvCapacity;
        private Button ibInterested;

        // each data item is just a string in this case
        public View mView;
        public AdapterView.OnItemClickListener listener;
        public CardView mCardView;

        public MyViewHolder(View v) {
            super(v);
            mView = v;
            tvRouteId = (TextView) itemView.findViewById(R.id.routeId);
            tvDate = (TextView) itemView.findViewById(R.id.dateInfo);
            tvFrom = (TextView) itemView.findViewById(R.id.fromData);
            tvTo = (TextView) itemView.findViewById(R.id.toData);
            tvPeople = (TextView) itemView.findViewById(R.id.peopleData);
            tvCapacity = (TextView) itemView.findViewById(R.id.capacityData);
            ibInterested = (Button) itemView.findViewById(R.id.interested_button);
            mCardView = (CardView) itemView.findViewById(R.id.mainScreenCardview);



        }

       /* public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
            this.listener = listener;
        }*/
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Route> myDataset) {
        mDataset = new ArrayList<>();
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);
        context = parent.getContext();
        MyViewHolder vh = new MyViewHolder(itemView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        SharedPreferences preferences;
        preferences = context.getSharedPreferences("com.carmate.android.carmate", Context.MODE_PRIVATE);
        id = preferences.getString("userId","-1");


        //holder.mTextView.setText(mDataset[position]);
        route = mDataset.get(position);
        positionTemp=position;

        holder.tvRouteId.setText(route.getId());
        holder.tvDate.setText(route.getDateInfo());
        holder.tvFrom.setText(route.getFromData());
        holder.tvTo.setText(route.getToData());
        holder.tvPeople.setText(route.getPeopleData());
        holder.tvCapacity.setText(route.getCapacity());

        holder.ibInterested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //modal to inform user that he is interested + button to see routes
                //save route id to routesInterested field on database
                //Toast.makeText(context.getApplicationContext(),"You are interested on this route", Toast.LENGTH_SHORT);
                PostData uploadData = new PostData();
                uploadData.execute();
                view.setVisibility(view.GONE);

            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    //AsyncTask class to fetch route data from remote database
    private class PostData extends AsyncTask<String, String, ArrayList<Route>> {
        String msg = "Internet/DB_Credentials/Windows_FireWall_TurnOn Error, See Android Monitor in the bottom For details!";
        ArrayList<Route> x;

        @Override
        protected ArrayList<Route> doInBackground(String... strings)  // Connect to the database, write query and add items to array list
        {
            x=new ArrayList<>();
            Route r = mDataset.get(positionTemp);
            OkHttpClient client = new OkHttpClient();
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url("http://carmate.gr.138-201-126-35.linuxzone125.grserver.gr/userRouteInterested.php?id="+id+
                            "&routeId="+r.getId())
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
