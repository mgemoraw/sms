import models.Admin;
import models.Database;
import models.Student;
import models.Teacher;
import models.User;

import java.util.InputMismatchException;
import java.util.Scanner;


import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.io.IOException;
import java.sql.Connection;


public class TestMain {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        int in = 0;
        boolean exitSystem = false;
        /**
         * Main login menu
         */

        System.out.println("\nCreating Application Database");
        
        Database.createTables();


        System.out.println("\n# Welcome to Our School Managment System #");
        System.out.println("#.........................................#");
        do {
            Menu.loginMenu();
            // in = 0;

            try{
                in = scanner.nextInt();
                switch(in){
                    case 0:
                        System.out.println("\nExiting System...");
                        exitSystem = true;
                        break;
                    case 1:
                        Menu.loginMenu();
                        // in = scanner.nextInt();
                        break;
                    case 2:
                        System.out.print("Enter your username: ");
                        String name = scanner.next(); 
                        System.out.print("Enter password: ");
                        String pwd = scanner.next();
                        
                        User user = new User(name, pwd);
                        try{
                            user.login(name, pwd);
                        } catch(SQLException e) {
                            System.out.println("Database Connection Error");
                            e.printStackTrace();
                        }
                        if(user.isLoggedIn()==true){
                            //
                            System.out.println("You are logged in");

                            if (user.getUserGroup().equals("admin")){
                                // logic to select type of user
                                adminHome(user.getUsername(), user.getPassword());
                            } else if (user.getUserGroup().equals("student")){
                                studentHome(user.getUserId());
                            } else if (user.getUserGroup().equals("teacher")){
                                teacherHome(user.getUserId());
                            } else if (user.getUserGroup() == "family"){
                                familyHome(user.getUsername(), user.getPassword());
                            }
                                
                        }else {
                            System.out.println("You are not logged in");
                        }
                        break;

                    case 3:
                        Menu.showDeveloperInfo();
                        break;

                    default:
                        System.out.println("~~~~~~~Invalid Choice~~~~~~~~ \nPlease Select from available Options.\n");
                        break;
                }
            }
            catch(InputMismatchException e){
                
                System.out.println("Invalid Input. only Integer Numbers [0, 3] are allowed.");
                // e.printStackTrace();
                
                // main(args)

                // restarts main thread after exception
                Thread main = new Thread();
                main.start();
                String input = scanner.next();
                // System.out.println(input);
                // break;
            } 
        } while(!exitSystem);
        
    }



    // home pages
    public static void adminHome(String username, String password){
        /**
         * this page defines admin user page
         * calls Admin class methods
         */
        Admin admin = new Admin();
        int option = 0;

        
        do {
            Menu.adminMenu();
            option = scanner.nextInt();
            switch(option){
                case 0:
                    System.out.println("Exitig from Admin page");
                    break;
                case 1:
                    Menu.adminMenu();
                    break;
                case 2:
                    admin.addNewStudent();
                    break;

                case 3:
                    try {
                        admin.addNewTeacher();
                    } catch(Exception e){
                        System.out.println("Data base connection Error");
                    }
                    break;
                case 4:
                    admin.addClassroom();
                    break;

                case 5:
                    admin.addStaff();
                    break;
                case 6:
                    Display.displayAllUsers();
                    break;
                case 7:
                    Display.displayAllStuents();
                    break;
                case 8:
                    Display.displayAllTeachers();
                    break;
                case 9:
                    Display.displayClassrooms();
                    break;

                default:
                    System.out.println("Choose the right menu");
                    break;
                
            }

            
        } while(option != 0);
        
    }

    public static void studentHome(String studentId){
        Student student = new Student();
        // User user = new User();
        
        int option = 0;
        do {
            Menu.studentMenu();
            option = scanner.nextInt();
            switch(option){
                case 0:
                    System.out.println("Exitig from Student page");
                    break;
                
                case 1:
                    try {
                        System.out.println("\nYour details");
                        
                        student.showInfo(studentId);
                        
                    } catch(SQLException e){
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    student.changePassword();
                    break;
                case 4:
                    System.out.println("\nThis section is under development\n");
                    break;
                default:
                    System.out.println("Invalid input");
                    System.out.println("Exiting System...");
                    break;
            }

            
        } while(option != 0);
        
    }

   

    public static void teacherHome(String staffId){
        Teacher teacher = new Teacher();
        int option = 0;
        do {
            Menu.teacherMenu();
            option = scanner.nextInt();
            switch(option){
                case 0:
                    System.out.println("Exitig from Student page");
                    break;
                
                case 1:
                    try {
                        Teacher.showInfo();
                    } catch(SQLException e){
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    Teacher.changePassword();
                    break;
                case 3:
                    System.out.println("Welcome! Please \nAdding student scores");
                    break;
                case 4:
                    System.out.println("Classrooms\n ");
                    break;
                case 5:
                    System.out.println("See Sessions of the Weak\n");
                    break;
                case 6:
                    System.out.println("Welcome to managing classrooms\n");
                    break;
                default:
                    System.out.println("Invalid input");
                    System.out.println("Exiting System...");
                    break;
            }

            
        } while(option != 0);
        
    }

    public static void familyHome(String username, String password){

    }
    
}
