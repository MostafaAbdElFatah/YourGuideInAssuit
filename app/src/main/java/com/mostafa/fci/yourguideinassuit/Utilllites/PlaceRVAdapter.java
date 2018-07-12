package com.mostafa.fci.yourguideinassuit.Utilllites;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mostafa.fci.yourguideinassuit.Constants.ItemClicked;
import com.mostafa.fci.yourguideinassuit.Constants.JSONKeysNames;
import com.mostafa.fci.yourguideinassuit.Constants.PlaceType;
import com.mostafa.fci.yourguideinassuit.R;
import com.mostafa.fci.yourguideinassuit.classes.Place;

import java.util.ArrayList;

public class PlaceRVAdapter extends RecyclerView.Adapter<PlaceRVAdapter.RVHolder> {


    Context context;
    ArrayList<Place> arrayList;
    ItemClicked itemClicked;

    public PlaceRVAdapter(Context context, ArrayList<Place> arrayList , ItemClicked clicked) {
        this.arrayList = arrayList;
        this.context = context;
        this.itemClicked = clicked;
    }


    @Override
    public RVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RVHolder holder;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item, parent, false);
        holder = new RVHolder(view,itemClicked);
        return holder;

    }


    @Override
    public void onBindViewHolder(RVHolder holder, int position) {

        Place place = arrayList.get(position);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"fonts/AD-Rsail.otf");
        holder.name.setTypeface(typeface);
        Typeface typeface2 = Typeface.createFromAsset(context.getAssets(),"fonts/Neckar Font Regular.ttf");
        holder.address.setTypeface(typeface2);

        if (place.getType().equals(JSONKeysNames.CLINICS)) {
            holder.name.setText("عيادة"+ place.getName());
            holder.address.setText(place.getAddress());
        }else {
            holder.name.setText(place.getName());
            holder.address.setText(place.getAddress());
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    /**
     * RecyclerView.ViewHolder
     **/

    public static class RVHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        TextView name , address;
        ItemClicked itemClicked;
        public RVHolder(View itemView , ItemClicked clicked) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.itemClicked = clicked;
            name = itemView.findViewById(R.id.namePlaceChildItem);
            address = itemView.findViewById(R.id.addressPlaceChildItem);
        }

        @Override
        public void onClick(View view) {
            if(itemClicked != null)
                this.itemClicked.onClicked(this.getLayoutPosition());
        }
    }
}


