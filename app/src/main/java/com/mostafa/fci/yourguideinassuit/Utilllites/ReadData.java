package com.mostafa.fci.yourguideinassuit.Utilllites;

import android.content.Context;
import android.util.Log;
import com.mostafa.fci.yourguideinassuit.Constants.PlaceType;
import com.mostafa.fci.yourguideinassuit.classes.Department;
import com.mostafa.fci.yourguideinassuit.classes.Place;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FCI on 2018-07-10.
 */

public class ReadData {

    public static List<Department> readDepartment(Context context){
        String text = "";
        try {
            InputStream is = context.getAssets().open("assuit/departments.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        } catch (Exception e) {
            Log.d("msg",e.toString());
        }
        List<Department> list = JSONParser.parse(text);
        return list;
    }

    public static ArrayList<Place> readClinics(Context context,int id){
        ArrayList<Place> clincs = new ArrayList<>();
        try {
            String text= "";
            InputStream is = context.getAssets().open("assuit/clinics/clinics"+ id +".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);

            ArrayList<Place> list = JSONParser.parse(text , PlaceType.CLINICS);
            clincs.addAll(list);

        } catch (Exception e) {
            Log.d("msg",e.toString());
        }
        return clincs;
    }

    public static List<Place> readHospitals(Context context){
        String text = "";
        try {
            InputStream is = context.getAssets().open("assuit/hospitals.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        } catch (Exception e) {
            Log.d("msg",e.toString());
        }
        List<Place> list = JSONParser.parse(text , PlaceType.GOVERNMENTAL_HOSPITALS);
        return list;

    }
    public static List<Place> readSpecialHospitals(Context context){
        String text = "";
        try {
            InputStream is = context.getAssets().open("assuit/s_hospitals.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        } catch (Exception e) {
            Log.d("msg",e.toString());
        }

        List<Place> list = JSONParser.parse(text , PlaceType.SPECIAL_HOSPITALS);
        return list;

    }
    public static List<Place> readHotels(Context context){
        String text = "";
        try {
            InputStream is = context.getAssets().open("assuit/hotels.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        } catch (Exception e) {
            Log.d("msg",e.toString());
        }
        List<Place> list = JSONParser.parse(text , PlaceType.HOTELS);
        return list;

    }
    public static List<Place> readLabs(Context context){
        String text = "";
        try {
            InputStream is = context.getAssets().open("assuit/labs.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        } catch (Exception e) {
            Log.d("msg",e.toString());
        }
        List<Place> list = JSONParser.parse(text , PlaceType.LABS);
        return list;

    }
    public static List<Place> readPharmacy(Context context){
        String text = "";
        try {
            InputStream is = context.getAssets().open("assuit/pharmacy.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        } catch (Exception e) {
            Log.d("msg",e.toString());
        }
        List<Place> list = JSONParser.parse(text , PlaceType.PHARMACY);
        return list;

    }
    public static List<Place> readRestaurants(Context context){
        String text = "";
        try {
            InputStream is = context.getAssets().open("assuit/Restaurants.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        } catch (Exception e) {
            Log.d("msg",e.toString());
        }
        List<Place> list = JSONParser.parse(text , PlaceType.RESTAURANTS);
        return list;
    }

    public static List<Place> readSemsars(Context context){
        String text = "";
        try {
            InputStream is = context.getAssets().open("assuit/semsars.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        } catch (Exception e) {
            Log.d("msg",e.toString());
        }
        List<Place> list = JSONParser.parse(text , PlaceType.SEMSARS);
        return list;
    }
}