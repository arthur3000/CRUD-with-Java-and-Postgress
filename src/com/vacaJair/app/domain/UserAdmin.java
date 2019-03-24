package com.vacaJair.app.domain;

import com.vacaJair.app.dao.AppUsersDbImplementation;

import java.util.List;

public class UserAdmin {
    private AppUsersDbImplementation appUsersDb = new AppUsersDbImplementation();

    public UserAdmin() {
        appUsersDb.connect();
        appUsersDb.createTable();
        appUsersDb.setup();
    }

    public void exit(){
        appUsersDb.disconnect();
    }

    public boolean addUser(String firstName, String lastName, String email, String password){
        appUsersDb.insertUser(firstName, lastName, email, password);
        return true; //Recommended to do more validations
    }

    public List getUserList(){
        return appUsersDb.selectAllProducts();
    }

    public boolean modifyUser(int id, String firstName, String lastName, String email, String password){
        appUsersDb.updateUser(id, firstName, lastName, email, password);
        return true;
    }

    public boolean deleteUser(int userId){
        appUsersDb.deleteUser(userId);
        return true;
    }
}


