package com.mostafa.fci.yourguideinassuit.Constants;

/**
 * Created by FCI on 2018-07-10.
 */
//governmental
public enum PlaceType {
    CLINICS(0),
    GOVERNMENTAL_HOSPITALS(1),
    PHARMACY(2),
    LABS(3),
    HOTELS(4),
    RESTAURANTS(5),
    SEMSARS(6),
    SPECIAL_HOSPITALS(7);


    private final int value;
    PlaceType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
