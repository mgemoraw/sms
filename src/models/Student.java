package models;

import java.util.HashMap;
import java.util.Scanner;
import java.sql.SQLException;

// Student model
public class Student extends BaseModel implements Attend {
    private int age = 0;
    private int gradeLevel;
    private String section;
    

    // default constructor
    public Student(){
        // default constructor

    }
   
    public Student(String id, String firstName, String lastName, int grade){
        super(id, firstName, lastName);
        this.gradeLevel = grade;
    }

    public Student(String id, String firstName, String lastName, int age, int grade, String section){
        super(id, firstName, lastName);
        this.age = age;
        this.gradeLevel = grade;
        this.section = section;
        
    }

    // getter and setter methods
    public int getAge(){
        return this.age;
    }
    public void setAge(int age){
        this.age=age;
    }

    public int getGradeLevel(){return this.gradeLevel;}
    public void setGradeLevel(int grade){this.gradeLevel = grade;}


    public String getSection(){return this.section;}
    public void setSection(String section){this.section = section;}

    @Override
    public void attendClassroom(){
        // attend class room logic 

    }

    // String to HashMap serializer
    @Override
    public HashMap<String, String> toMap(){

        HashMap<String, String> table = super.toMap();
        table.put("age", ""+age);
        table.put("section", ""+section);
        table.put("grade", ""+this.gradeLevel);

        return table;
    }

    @Override
    public String toJson() {
        String str = super.toJson();
        return str;
    }


    @Override
    public String toString(){
        return "[ "+ this.getId() + " " + getFirstName() + " " + this.age + " ]";
    }
    @Override
    public void login(String username, String password){

    }
    @Override
    public void logout(String username, String password){
    }

    public void showInfo(String studentId) throws SQLException{
        //User user = new User(username, password);
        // System.out.println(user.getUserId());
        HashMap<String, String> result = Database.getStudentDetails(studentId);

        String str = String.format(" %-15s %-15s %-15s %-15s %-15s", "ID No", "First Name", "Last Name", "Grade", "Section");
        System.out.println(str);

        str = String.format(" %-15s %-15s %-15s %-15s %-15s", result.get("studentId"), result.get("firstName"), result.get("lastName"), result.get("Grade"), result.get("section"));
        System.out.println(str);
        
    }

    public void changePassword(){
        // 
        System.out.println("Enter your userId");
        Scanner scanner = new Scanner(System.in);
        String userId = scanner.next();

        System.out.println("Enter your old password");
        String oldPassword = scanner.next();

        try {
            HashMap<String, String> user = Database.fetchUser(userId, oldPassword);

            if (user != null){
                System.out.println("Enter Your new password");
                String pwd1 = scanner.next();
                System.out.println("Enter Password again");
                String pwd2 = scanner.next();

                if (pwd1 == pwd2){
                    boolean changed =  Database.changePassword(userId, pwd1);

                    if (changed){
                        System.out.println("Password Changed Successfully!");
                    }
                }
            }

        }catch (SQLException e){
            e.getStackTrace();
            System.out.println("Password Not changed!");
        }

        

    }
}

