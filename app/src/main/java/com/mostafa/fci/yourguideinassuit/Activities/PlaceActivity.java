package com.mostafa.fci.yourguideinassuit.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mostafa.fci.yourguideinassuit.Constants.ItemClicked;
import com.mostafa.fci.yourguideinassuit.Constants.JSONKeysNames;
import com.mostafa.fci.yourguideinassuit.R;
import com.mostafa.fci.yourguideinassuit.Utilllites.PlaceRVAdapter;
import com.mostafa.fci.yourguideinassuit.Utilllites.ReadData;
import com.mostafa.fci.yourguideinassuit.Utilllites.RoomDatabase;
import com.mostafa.fci.yourguideinassuit.classes.Place;

import java.util.ArrayList;
import java.util.List;

public class PlaceActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Place> placesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        recyclerView = findViewById(R.id.placeRecyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        Intent intent = getIntent();
        int index = intent.getIntExtra("placeId",-1);
        String type = intent.getStringExtra("placeType");
        if (index != -1 || type != "")
            if (type.equals(JSONKeysNames.CLINICS)) {
                List<Place> list = ReadData.readClinics(PlaceActivity.this,index);
                placesList.addAll(list);
            }else {
                List<Place> list = RoomDatabase.getDatabase(PlaceActivity.this)
                        .daoDatabase().fetchbyPlaceType(type);
                placesList.addAll(list);
            }


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new PlaceRVAdapter(PlaceActivity.this, placesList, new ItemClicked() {
            @Override
            public void onClicked(int position) {
                Place  place = placesList.get(position);
                Intent intent = new Intent(PlaceActivity.this , DetailsActivity.class);
                intent.putExtra("place",place);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);


    }

}
