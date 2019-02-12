package com.carmate.android.carmate;

/**
 * Created by brandt on 11/1/2019.
 */

public class Route {
    private String id,driverId,dateInfo,fromData,toData,peopleData,capacity;

    public Route(String id,String fromData,String toData,String driverId,
                 String peopleData,String capacity,String dateInfo){
        this.id = id;
        this.driverId = driverId;
        this.dateInfo = dateInfo;
        this.fromData = fromData;
        this.toData = toData;
        this.peopleData = peopleData;
        this.capacity = capacity;
    }

    public String getId(){return this.id;} //routeId

    public String getDriverId(){
        return this.driverId;
    }

    public String getDateInfo(){
        return this.dateInfo;
    }

    public String getFromData(){
        return this.fromData;
    }

    public String getToData(){
        return this.toData;
    }

    public String getPeopleData(){
        return this.peopleData;
    }

    public String getCapacity(){
        return this.capacity;
    }

    public void setId(String id){this.id = id; }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public void setDateInfo(String dateInfo) {
        this.dateInfo = dateInfo;
    }

    public void setFromData(String fromData) {
        this.fromData = fromData;
    }

    public void setToData(String toData) {
        this.toData = toData;
    }

    public void setPeopleData(String peopleData) {
        this.peopleData = peopleData;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}
