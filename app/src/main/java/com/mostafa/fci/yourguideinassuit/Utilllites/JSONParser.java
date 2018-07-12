package com.mostafa.fci.yourguideinassuit.Utilllites;


import com.mostafa.fci.yourguideinassuit.Constants.JSONKeysNames;
import com.mostafa.fci.yourguideinassuit.Constants.PlaceType;
import com.mostafa.fci.yourguideinassuit.classes.Department;
import com.mostafa.fci.yourguideinassuit.classes.Place;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Random;


public class JSONParser {

    // get Json Object and return Array of Objects
    public static ArrayList<Place> parse(String content , PlaceType placeType){
        if (content == "" || content == null){
            return null;
        }

        ArrayList<Place> placeList = new ArrayList<>();
        try{
            JSONArray jsonArray = new JSONObject(content).getJSONArray(getStringKey(placeType));
            for(int i=0; i< jsonArray.length() ; i++) {

               JSONObject jsonObject = jsonArray.getJSONObject(i);

               Place place = new Place();
               Random random = new Random();
               int id = random.nextInt(999999999);
               place.setId(id);
               place.setName(jsonObject.getString(JSONKeysNames.NAME));
               place.setAddress(jsonObject.getString(JSONKeysNames.ADDRESS));
               place.setPhone(jsonObject.getString(JSONKeysNames.PHONE));
               place.setType(getStringKey(placeType));
               placeList.add(place);

            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return placeList;

    }

    // get Json Object and return Array of Objects
    public static ArrayList<Department> parse(String content){
        if (content == "" || content == null){
            return null;
        }

        ArrayList<Department> departments = new ArrayList<>();
        try{
            JSONArray jsonArray = new JSONObject(content).getJSONArray(JSONKeysNames.DEPARTMENTS);
            for(int i=0; i<jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Department department = new Department();
                int id = Integer.parseInt(jsonObject.getString(JSONKeysNames.ID));
                department.setId(id) ;
                department.setName(jsonObject.getString(JSONKeysNames.NAME));
                departments.add(department);

            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return departments;

    }

    private static String getStringKey(PlaceType placeType){
        switch (placeType){
            case GOVERNMENTAL_HOSPITALS:
                return JSONKeysNames.HOSPITALS;
            case SPECIAL_HOSPITALS:
                return JSONKeysNames.S_HOSPITALS;
            case CLINICS:
                return JSONKeysNames.CLINICS;
            case LABS:
                return JSONKeysNames.LABS;
            case PHARMACY:
                return JSONKeysNames.PHARMACY;
            case HOTELS:
                return JSONKeysNames.HOTELS;
            case RESTAURANTS:
                return JSONKeysNames.RESTAURANTS;
            case SEMSARS:
                return JSONKeysNames.SEMSARS;
            default:
                return "";
        }
    }

}
