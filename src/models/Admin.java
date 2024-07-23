package models;

import java.sql.SQLException;
import java.util.Scanner;

public class Admin extends BaseModel {
    private String userId;
    private String password;
    private String email;
    private String phoneNumber;
    static boolean loggedIn = false;

    public Admin(){
        // default constructor
    }

    public Admin(String userId, String password, String email, String phone){
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.phoneNumber = phone;
    }

    @Override
    public void login(String username, String password){

        if (username.equals("admin") && password.equals("Admin")){
            loggedIn = true;
        } else {
            loggedIn = false;
        }

    }

    @Override
    public void logout(String username, String password){
        if (Admin.loggedIn == true){
            Admin.loggedIn = false;
        }

    }

    // adds new student
    public void addNewStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student id: ");
        String stdId = scanner.next();

        System.out.println("Enter student first Name: ");
        String stdFirstName = scanner.next();

        System.out.println("Enter student last Name: ");
        String stdLastName = scanner.next();

        System.out.println("Enter student grade: ");
        int stdGrade = scanner.nextInt();

        System.out.println("Enter student default username: ");
        String stdUserName = scanner.next();

        System.out.println("Enter student default password: ");
        String stdPassword = scanner.next();

        Student std = new Student(stdId, stdFirstName, stdLastName, stdGrade);
        
        System.out.println("...adding record to the database ...");

        // adds data into the database table
        Database.insertNewStudent(std);
        Database.insertNewUser(stdId, stdUserName, stdPassword, "student");
        
    }

    // add new teacher
    public void addNewTeacher(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Staff id: ");
        String userId = scanner.next();

        System.out.println("Enter Staff first Name: ");
        String firstName = scanner.next();

        System.out.println("Enter Staff last Name: ");
        String lastName = scanner.next();

        System.out.println("Enter Teacher's Major subject: ");
        String major = scanner.next();

        System.out.println("Enter Teacher's Minor subject: ");
        String minor = scanner.next();


        System.out.println("Enter teacher default username: ");
        String username = scanner.next();

        System.out.println("Enter teacher default password: ");
        String pwd = scanner.next();

        Teacher teacher = new Teacher(userId, firstName, lastName, major, minor);

        System.out.println("...adding record to the database ...");
        Database.insertNewTeacher(teacher);
        Database.insertNewUser(userId, username, pwd, "teacher");

    }

    public void addClassroom(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Room id: ");
        int roomId = scanner.nextInt();

        System.out.println("Enter Students Grde (integer): ");
        int grade = scanner.nextInt();

        System.out.println("Enter Number of Students: ");
        int number = scanner.nextInt();

        System.out.println("Enter Section Label: ");
        String section = scanner.next();

        System.out.println("Enter Building Label: ");
        String building = scanner.next();

        System.out.println("Adding record into the database...");
        Classroom room = new Classroom(grade, section, number, building);
        Database.insertClassroom(room);
    }

    public void addStaff(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Staff id: ");
        String userId = scanner.next();

        System.out.println("Enter Staff first Name: ");
        String firstName = scanner.next();

        System.out.println("Enter Staff last Name: ");
        String lastName = scanner.next();

        System.out.println("Enter Staff role(Department): ");
        String role = scanner.next();

        System.out.println("Enter Staff username: ");
        String username = scanner.next();

        System.out.println("Enter staff default password: ");
        String pwd = scanner.next();

        Staff staff = new Staff(userId, firstName, lastName, role);

        System.out.println("...adding record to the database ...");
        Database.insertNewStaff(staff);
        Database.insertNewUser(userId, firstName, pwd, "staff");

    }
}


