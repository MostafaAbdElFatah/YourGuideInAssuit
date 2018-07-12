package com.mostafa.fci.yourguideinassuit.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.mostafa.fci.yourguideinassuit.Constants.JSONKeysNames;
import com.mostafa.fci.yourguideinassuit.R;
import com.mostafa.fci.yourguideinassuit.Utilllites.ExpAdapter;
import com.mostafa.fci.yourguideinassuit.Utilllites.ReadData;
import com.mostafa.fci.yourguideinassuit.Utilllites.RoomDatabase;
import com.mostafa.fci.yourguideinassuit.classes.Department;
import com.mostafa.fci.yourguideinassuit.classes.Place;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    ExpandableListView expandableListView;
    HashMap<String,List<String>> child_titles = new HashMap<String,List<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = findViewById(R.id.expandable_list_view);

        final List<String> mainTitles = Arrays.asList( getResources().getStringArray(R.array.main_title) );
        List<String> hospitalsTitles = Arrays.asList( getResources().getStringArray(R.array.hospitals_title) );
        List<String> departTitle = new ArrayList<>();
        if ( !(RoomDatabase.getDatabase(MainActivity.this).daoDatabase().fetchAllDeparments().size() > 0 )
                && !(RoomDatabase.getDatabase(MainActivity.this).daoDatabase().fetchAllPlaces().size() > 0) ){

            List<Department> departmentList = ReadData.readDepartment(MainActivity.this);
            List<Place> hospitals = ReadData.readHospitals(MainActivity.this);
            List<Place> specialHospitals = ReadData.readSpecialHospitals(MainActivity.this);
            List<Place> pharmacy = ReadData.readPharmacy(MainActivity.this);
            List<Place> labs = ReadData.readLabs(MainActivity.this);
            List<Place> hotels = ReadData.readHotels(MainActivity.this);
            List<Place> restaurants = ReadData.readRestaurants(MainActivity.this);
            List<Place> semsars = ReadData.readSemsars(MainActivity.this);

            // save in database

            RoomDatabase.getDatabase(MainActivity.this).daoDatabase().insertMultipleDepartments(departmentList);
            RoomDatabase.getDatabase(MainActivity.this).daoDatabase().insertMultiplePlaces(hospitals);
            RoomDatabase.getDatabase(MainActivity.this).daoDatabase().insertMultiplePlaces(specialHospitals);
            RoomDatabase.getDatabase(MainActivity.this).daoDatabase().insertMultiplePlaces(pharmacy);
            RoomDatabase.getDatabase(MainActivity.this).daoDatabase().insertMultiplePlaces(labs);
            RoomDatabase.getDatabase(MainActivity.this).daoDatabase().insertMultiplePlaces(hotels);
            RoomDatabase.getDatabase(MainActivity.this).daoDatabase().insertMultiplePlaces(restaurants);
            RoomDatabase.getDatabase(MainActivity.this).daoDatabase().insertMultiplePlaces(semsars);

                // fill depart array
            for (Department department:departmentList){
                departTitle.add(department.getName());
            }

        }else{
            List<Department> departmentList = RoomDatabase.getDatabase(MainActivity.this)
                    .daoDatabase().fetchAllDeparments();
            for (Department department:departmentList){
                departTitle.add(department.getName());
            }
        }
        // end of if statements

        child_titles.put(mainTitles.get(0),departTitle);
        child_titles.put(mainTitles.get(1),hospitalsTitles);
        child_titles.put(mainTitles.get(2),new ArrayList<String>());
        child_titles.put(mainTitles.get(3),new ArrayList<String>());
        child_titles.put(mainTitles.get(4),new ArrayList<String>());
        child_titles.put(mainTitles.get(5),new ArrayList<String>());
        child_titles.put(mainTitles.get(6),new ArrayList<String>());
        ExpAdapter expAdapter = new ExpAdapter(this,mainTitles,child_titles);
        expandableListView.setAdapter(expAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                Intent intent = new Intent(MainActivity.this , PlaceActivity.class);
                intent.putExtra("placeType",getStringType(groupPosition , childPosition));
                intent.putExtra("placeId",childPosition);
                startActivity(intent);
                return true;
            }
        });
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition > 1 ){
                    Intent intent = new Intent(MainActivity.this , PlaceActivity.class);
                    intent.putExtra("placeType",getStringType(groupPosition,-1));
                    intent.putExtra("placeId",groupPosition);
                    startActivity(intent);
                }
                /////
            }
        });

    }

    private static String getStringType(int parentIndex , int childIndex){
        switch (parentIndex){
            case 0:
                return JSONKeysNames.CLINICS;
            case 1:
                if (childIndex == 0)
                    return JSONKeysNames.HOSPITALS;
                else
                    return JSONKeysNames.S_HOSPITALS;
            case 2:
                return JSONKeysNames.PHARMACY;
            case 3:
                return JSONKeysNames.LABS;
            case 4:
                return JSONKeysNames.HOTELS;
            case 5:
                return JSONKeysNames.RESTAURANTS;
            case 6:
                return JSONKeysNames.SEMSARS;
            default:
                return "";
        }
    }

}
