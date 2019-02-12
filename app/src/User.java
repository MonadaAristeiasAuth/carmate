package com.carmate.android.carmate;

/**
 * Created by brandt on 4/2/2019.
 */

public class User {
    private String id,name,surname,email,password,isDriver,routesGoing,routesInterested,routesDriving;

    public User(String id, String name, String surname, String email, String password, String isDriver, String routesGoing, String routesInterested, String routesDriving) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.isDriver = isDriver;
        this.routesGoing = routesGoing;
        this.routesInterested = routesInterested;
        this.routesDriving = routesDriving;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsDriver(String isDriver) {
        this.isDriver = isDriver;
    }

    public void setRoutesGoing(String routesGoing) {
        this.routesGoing = routesGoing;
    }

    public void setRoutesInterested(String routesInterested) {
        this.routesInterested = routesInterested;
    }

    public void setRoutesDriving(String routesDriving) {
        this.routesDriving = routesDriving;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getIsDriver() {
        return isDriver;
    }

    public String getRoutesGoing() {
        return routesGoing;
    }

    public String getRoutesInterested() {
        return routesInterested;
    }

    public String getRoutesDriving() {
        return routesDriving;
    }
}
