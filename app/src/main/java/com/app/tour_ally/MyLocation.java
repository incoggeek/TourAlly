package com.app.tour_ally;

//A common blueprint for all location

public class MyLocation {

    //Member fields
    private int mLocationImageId;
    private String mLocationName;
    private String mAddress;

    //Constructor

    public MyLocation(int locationImageId, String locationName, String address)
    {
           mLocationImageId = locationImageId;
           mLocationName = locationName;
           mAddress = address;
    }

    public MyLocation(int locationImageId, String locationName)
    {
        mLocationImageId = locationImageId;
        mLocationName = locationName;
    }


    //Getters and Setters

    public int getmLocationImageId() {
        return mLocationImageId;
    }

    public String getmLocationName() {
        return mLocationName;
    }
    public String getmAddress() {
        return mAddress;
    }
}
