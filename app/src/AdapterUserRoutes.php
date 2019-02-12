package com.carmate.android.carmate;

import android.support.v7.widget.RecyclerView;

/**
 * Created by brandt on 5/2/2019.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by brandt on 10/1/2019.
 */

public class AdapterUserRoutes extends RecyclerView.Adapter<AdapterUserRoutes.MyViewHolderUser> {
    private ArrayList<Route> mDataset;
    Route route;
    Context context;
    String id;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolderUser extends RecyclerView.ViewHolder {
        private TextView tvFrom,tvTo,tvDriver,tvDate;
        private Button btnPeople;

        // each data item is just a string in this case
        public View mView;
        public AdapterView.OnItemClickListener listener;
        public CardView mCardView;

        public MyViewHolderUser(View v) {
            super(v);
            mView = v;
            tvDate = (TextView) itemView.findViewById(R.id.date);
            tvFrom = (TextView) itemView.findViewById(R.id.fromLoc);
            tvTo = (TextView) itemView.findViewById(R.id.toLoc);
            tvDriver = (TextView) itemView.findViewById(R.id.driver);

            btnPeople = (Button) itemView.findViewById(R.id.btn_people);


        }

       /* public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
            this.listener = listener;
        }*/
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterUserRoutes(ArrayList<Route> myDataset) {
        mDataset = new ArrayList<>();
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterUserRoutes.MyViewHolderUser onCreateViewHolder(ViewGroup parent,
                                                                    int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_user_routes, parent, false);
        context = parent.getContext();
        MyViewHolderUser vh = new MyViewHolderUser(itemView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolderUser holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        SharedPreferences preferences;
        preferences = context.getSharedPreferences("com.carmate.android.carmate", Context.MODE_PRIVATE);
        id = preferences.getString("userId","-1");


        //holder.mTextView.setText(mDataset[position]);
        route = mDataset.get(position);

        holder.tvDate.setText(route.getDateInfo());
        holder.tvFrom.setText(route.getFromData());
        holder.tvTo.setText(route.getToData());

       holder.btnPeople.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               showPopup(view);
           }
       });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void showPopup(View view) {
        View popupView = LayoutInflater.from(context).inflate(R.layout.popup_window, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.MATCH_PARENT);

        //popupWindow.showAsDropDown(popupView,Gravity.CENTER, 0, 0);
        popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);

    }


}

