package com.vacaJair.app.dao;

import com.vacaJair.app.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppUsersDbImplementation extends DbAdapter {

    public void createTable() {
        try {
            statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "user_id SERIAL PRIMARY KEY NOT NULL, " +
                    "user_firstName VARCHAR(15) NOT NULL, " +
                    "user_lastName VARCHAR(15) NOT NULL, " +
                    "user_email VARCHAR(80) NOT NULL, " +
                    "user_password VARCHAR(255) NOT NULL)";
            statement.executeUpdate(sql);
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setup() {
        int numberOfRows = -1;
        try {
            PreparedStatement st = connection.prepareStatement(
                    "SELECT  count(*) FROM users");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                numberOfRows = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (numberOfRows == 0) {
            //Do extra setup if needed
            System.out.println("Number of rows: " + numberOfRows);
        }
    }

    public void insertUser(String firstName, String lastName, String email, String password){
        try {
            PreparedStatement st = connection.prepareStatement("INSERT INTO USERS " +
                    "(user_firstName, user_lastName, user_email, user_password) " +
                    "VALUES(?, ?, ?, ?)");
            st.setString(1, firstName);
            st.setString(2, lastName);
            st.setString(3, email);
            st.setString(4, password);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List selectAllProducts(){
        List allUsers = new ArrayList();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM users" +
                    " ORDER BY user_id ASC");
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                int userId = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String email = rs.getString(4);
                String password = rs.getString(5);

                User tempUser = new User(userId, firstName, lastName, email, password);
                allUsers.add(tempUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public void updateUser(int userId, String firstName, String lastName, String email, String password){
        System.out.println("Updating: ");
        try {
            PreparedStatement st = connection.prepareStatement("UPDATE users SET user_firstName=?, " +
                    "user_lastName=?, user_email=?, user_password=? WHERE user_id=?");
            st.setString(1, firstName);
            st.setString(2, lastName);
            st.setString(3, email);
            st.setString(4, password);
            st.setInt(5, userId);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId){
        PreparedStatement st;
        try {
            st = connection.prepareStatement("DELETE FROM users WHERE user_id=?");
            st.setInt(1, userId);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
