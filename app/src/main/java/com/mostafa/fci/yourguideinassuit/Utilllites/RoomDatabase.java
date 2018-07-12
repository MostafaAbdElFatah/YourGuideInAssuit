package com.mostafa.fci.yourguideinassuit.Utilllites;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.content.Context;
import com.mostafa.fci.yourguideinassuit.classes.Department;
import com.mostafa.fci.yourguideinassuit.classes.Place;


@Database(entities = {Department.class, Place.class}, version = 1)
public abstract class RoomDatabase extends android.arch.persistence.room.RoomDatabase {

    public abstract DaoDatabase daoDatabase();

    private static RoomDatabase INSTANCE;


    public static RoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            RoomDatabase.class, "flower_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}