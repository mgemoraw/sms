package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Staff extends BaseModel {
    private String department;
    private Date dateOfBirth;
    private int experience;
    private int age;

    public Staff(){
        //
    }
    public Staff(String userId, String firstName, String lastName, String department){
        super(userId, firstName, lastName);
        this.department = department;
       
    }
    public void setAge(int age){
        this.age = age;
    }

    public String getDepartment(){
        return this.department;
    }
    public void setDepartment(String dep){
        this.department = dep;
    }

    @Override
    public void login(String username, String password){

    }
    @Override
    public void logout(String username, String password){
    }

    public void showInfo(String username, String password) throws SQLException{
        User user = new User(username, password);
        ResultSet result = Database.getStaffDetails(user.getUserId());

        while (result.next()){
            System.out.println("First name: " + result.getString("firstName"));
            System.out.println("Last name: " + result.getString("lastName"));
            System.out.println("subject: " + result.getString("subject"));
        }
    }
}
