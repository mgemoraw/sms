
import java.util.*;

import models.Database;

public class Display {
    

    private static void displayStudentHeader(){
       
        String str = String.format("# %-5s %-15s %-15s %-15s %-10s %-15s", "No.", "Student ID.", "First Name", "Last Name", "Grade", "Section");
        System.out.println(str);
    }

    public static void displayAllStuents(){
    
        int index = 0;

        displayStudentHeader();
        List<HashMap<String, String>> students = Database.getStudents();

        for (HashMap<String, String> std: students){
            index++;
            String studentId = std.get("studentId");
            String firstName = std.get("firstName");
            String lastName = std.get("lastName");
            String grade = std.get("gradeLevel");
            String section = std.get("section");
            String row = String.format("# %-5d %-15s %-15s %-15s %-10s %-15s", index, studentId, firstName, lastName, grade, section);
            System.out.println(row);
        }
    }

    public static void displayAllTeachers(){
    
        int index = 0;

        String str = String.format("# %-5s %-15s %-15s %-15s %-15s %-15s", "No.", "Staff ID.", "First Name", "Last Name", "Major Subject", "Minor Subject");
        System.out.println(str);
        List<HashMap<String, String>> teachers = Database.getTeachers();

        for (HashMap<String, String> teacher: teachers){
            index++;
            String studentId = teacher.get("staffId");
            String firstName = teacher.get("firstName");
            String lastName = teacher.get("lastName");
            String major = teacher.get("Major Subject");
            String minor = teacher.get("Minor Subject");
            String row = String.format("# %-5d %-15s %-15s %-15s %-15s %-15s", index, studentId, firstName, lastName, major, minor);
            System.out.println(row);
        }
    }

    public static void displayAllUsers(){
        int index = 0;

        String str = String.format("# %-5s %-15s %-15s %-15s %-15s %-10s", "No.", "user Id", "username", "Password", "User Group ", "Role");
        System.out.println(str);
        List<HashMap<String, String>> users = Database.getAllUsers();

        for (HashMap<String, String> user: users){
            index++;
            String userId = user.get("userId");
            String username = user.get("username");
            String password = user.get("password");
            String userGroup = user.get("userGroup");
            String role = user.get("role");
            String row = String.format("# %-5d %-15s %-15s %-15s %-15s %-10s", index, userId, username, password, userGroup, role);
            System.out.println(row);
        }
    }

    public static void displayClassrooms(){

    }
}
