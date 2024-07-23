package models;

import java.util.UUID;
import java.util.Date;
import java.util.HashMap;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    private String userId;
    private String username;
    private String password;
    private String userGroup;
    static int stdIdCounter = 0;
    static int staffIdCounter = 0;
    private boolean loggedIn;
    
    public User(){
        // default constructor
        // this.userId = "THS"+stdIdCounter++ + LocalDate.now().getYear();
        this.userGroup = "user";
        this.loggedIn = false;
    }

    public User(String username, String password){
        // this.userId = "THS"+idCounter++ + LocalDate.now().getYear();
        this.username = username;
        this.password = password;
        this.userGroup = "user";
        this.loggedIn = false;
    }

    public User(String username, String password, String userGroup){
        this.username = username;
        this.password = password;
        this.userGroup = userGroup;
        this.loggedIn = false;
        if (userGroup == "student"){
            this.userId = "THSS"+stdIdCounter++ + LocalDate.now().getYear();
        }else {
            this.userId = "THSE"+stdIdCounter++ + LocalDate.now().getYear();
        }
        
    }

    public void login(String username, String password) throws SQLException{
        // login logic here
        // create database connection
        // Connection conn = Database.connect("jdbc:sqlite:src/storage/database.db");
        
        // Connection conn = Database.connect(Database.getUrl());

        // Statement statement = conn.createStatement();
        // ResultSet result = Database.fetchUser(conn, username, password);

        HashMap<String, String> result = Database.fetchUser(username, password);


        if (result != null){
            // String dbUsername = result.getString("username"); 
            // String dbPassword = result.getString("password");
            // String dbUserGroup = result.getString("userGroup");

            if (username.equals(result.get("username")) && password.equals(result.get("password"))){
                System.out.println("Login Success");
                this.loggedIn = true;
                this.userGroup = result.get("userGroup");
            } else {
                System.out.println("Incorrect Password");
                this.userGroup = null;
                this.loggedIn = false;
            }
        } else {
            System.out.println("User name " + username + " not found");
        }

        // close database connection
        // conn.close();
    }

    public boolean isLoggedIn(){
        return this.loggedIn;
    }
    // get username
    public String getUserId(){
        return this.userId;
    }

    public String getUsername(){
        return this.username;
    }

    public String getUserGroup(){
        return this.userGroup;
    }

    public String getPassword(){return this.password;}

    public void changePassword(String username, String password){
        System.out.println("Password Changed succesfully");
    }

    
}
