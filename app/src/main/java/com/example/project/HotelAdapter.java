package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import android.content.ContentResolver;
import android.widget.Toast;

import java.util.ArrayList;

public class HotelAdapter extends ArrayAdapter<Hotel> {
    private ArrayList<Hotel> Hotels;
    private Context context;
    public HotelAdapter(Context context, int resource, ArrayList<Hotel> s){
        super(context,resource,s);
        this.Hotels = s;
        this.context = context;
    }
    @Nullable
    @Override
    public Hotel getItem(int position) {
        return Hotels.get(position);
    }

    @Override
    public int getCount(){
        return Hotels.size();
    }

    @Override
    public View getView(int index, View convertView,ViewGroup parent) {
        final Hotel hotel = getItem(index);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.onehotel,parent,false);
        }
        TextView slot1 = convertView.findViewById(R.id.hotelname);
        slot1.setText(hotel.getName());
        Button btn = convertView.findViewById(R.id.hotelbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("STAY_HOTEL",hotel.getName());
                final Session session = new Session(context);
                try {
                    int uri = context.getContentResolver().update(MyContentProvider.CONTENT_URI1,values,"USER_NAME = "+"'"+session.getusername()+"'",null);
                    Toast.makeText(context,hotel.getName()+" has been booked",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(context,"Unable to Book hotel",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;
    }
}
