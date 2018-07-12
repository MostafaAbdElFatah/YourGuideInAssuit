package com.mostafa.fci.yourguideinassuit.Utilllites;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.mostafa.fci.yourguideinassuit.classes.Department;
import com.mostafa.fci.yourguideinassuit.classes.Place;
import java.util.List;

/**
 * Created by FCI on 2018-06-22.
 */
@Dao
public interface DaoDatabase {

    @Insert
    void insertOnlySinglePlace(Place place);
    @Insert
    void insertMultiplePlaces(List<Place> list);
    @Query ("SELECT * FROM Place WHERE type = :placeType")
    List<Place> fetchbyPlaceType(String placeType);
    @Query ("SELECT * FROM Place WHERE name = :name")
    List<Place> fetchbyPlaceDepID(String name);
    @Query ("SELECT * FROM Place")
    List<Place> fetchAllPlaces();
    @Update
    void updatePlace(Place place);
    @Delete
    void deletePlace(Place place);
    @Query("DELETE FROM Place")
    void deleteAllPlace();

    /**
     * Order methods
     * */

    @Insert
    void insertOnlySingleDepermnet(Department department);
    @Insert
    void insertMultipleDepartments(List<Department> departments);
    @Query ("SELECT * FROM `Department` WHERE id = :id")
    Department fetchbyDeparmentId(String id);
    @Query ("SELECT * FROM `Department`")
    List<Department> fetchAllDeparments();
    @Update
    void updateDepartment(Department department);
    @Delete
    void deleteDeparment(Department department);
    @Query("DELETE FROM `Department`")
    void deleteAllDeparment();

}
