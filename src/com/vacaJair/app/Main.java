package com.vacaJair.app;

import com.vacaJair.app.domain.UserAdmin;
import com.vacaJair.app.useful.*;

import java.util.List;

public class Main {
    static UserAdmin dbAdmin = new UserAdmin();

    public static void main(String[] args) {
        Useful.printTitle("WELCOME TO MY GENERIC APP", "*");
        boolean keepGoing = true;
        do {
            switch (mainMenu()) {
                case Consts.CREATE_USER: createUser(); break;
                case Consts.READ_ALL_USERS: showAllUsers(); break;
                case Consts.UPDATE_USER: updateUser(); break;
                case Consts.DELETE_USER: deleteUser(); break;
                case Consts.SALIR: keepGoing = false; break;
                default: System.out.println("Option is not valid!"); break;
            }
            if (keepGoing) Useful.pause();
        } while (keepGoing);
        dbAdmin.exit();
    }

    private static int mainMenu() {
        int option;
        System.out.println("1. Add user" + "\n" +
                "2. Show all users" + "\n" +
                "3. Modify user" + "\n" +
                "4. Delete user" + "\n" +
                "0. Exit" + "\n");
        System.out.print("Choose an option: ");
        option = ScanF.readInt();
        return option;
    }

    public static void createUser() {
        String lastName;
        String firstName;
        String email;
        String password;
        boolean success;

        Useful.printTitle("ADD USER", "-");
        System.out.println("Please enter the information below");
        System.out.print("First Name: ");
        firstName = ScanF.readString();
        System.out.print("Last Name: ");
        lastName = ScanF.readString();
        System.out.print("Email: ");
        email = ScanF.readString();
        System.out.print("Password: ");
        password = ScanF.readString();

        success = dbAdmin.addUser(firstName, lastName, email, password);
        if(success)
            System.out.println("User added successfully");
        else
            System.out.println("Adding this user was not possible");
    }

    public static void showAllUsers() {
        Useful.printTitle("SHOW ALL USERS", "-");
        List allUsers = dbAdmin.getUserList();
        int size = allUsers.size();
        for (int i = 0; i < size; i++) {
            System.out.println(allUsers.get(i));
        }
    }

    public static void updateUser() {
        Useful.printTitle("MODIFY A USER", "-");
        System.out.println("Please enter the ID of the user to modify");
        int id = ScanF.readInt();
        String lastName;
        String firstName;
        String email;
        String password;
        boolean success;

        System.out.println("Please enter the NEW information for the user below");
        System.out.print("First Name: ");
        firstName = ScanF.readString();
        System.out.print("Last Name: ");
        lastName = ScanF.readString();
        System.out.print("Email: ");
        email = ScanF.readString();
        System.out.print("Password: ");
        password = ScanF.readString();

        success = dbAdmin.modifyUser(id, firstName, lastName, email, password);
        if(success)
            System.out.println("User updated successfully!");
        else
            System.out.println("Updating this user was not possible");
    }

    public static void deleteUser() {
        Useful.printTitle("DELETE A USER", "+");
        System.out.println("Please enter the ID of the user to delete");
        int id = ScanF.readInt();
        boolean success = dbAdmin.deleteUser(id);
        if(success)
            System.out.println("User deleted successfully");
        else
            System.out.println("Deleting this user was not possible");
    }
}
