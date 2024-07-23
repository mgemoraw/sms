package models;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Teacher extends BaseModel implements Teaching, Advising {
    private String major;
    private String minor;
    private Date dateOfBirth;
    private int experience;
    private int age;
    private Classroom classroom;
    private ArrayList<Session> sessions;


    public Teacher(){
        //
    }
    public Teacher(String userId, String firstName, String lastName, String major, String minor){
        super(userId, firstName, lastName);
        this.major = major;
        this.minor = minor;
    }
    public void setAge(int age){
        this.age = age;
    }

    public String getMajor(){return this.major;}
    public String getMinor(){return this.minor;}
    public void setMajor(String sub){
        this.major = sub;
    }
    public void setMinor(String sub){
        this.minor = sub;
    }
    public void setClassroom(Classroom room){
        this.classroom = room;
    }

    public void setSessons(ArrayList<Session> sessions){
        this.sessions = sessions;
    }

    @Override
    public void login(String username, String password){

    }
    @Override
    public void logout(String username, String password){
    }

    public static void showInfo() throws SQLException{
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your staff ID: ");
        String staffId = scanner.next();
        HashMap<String, String> result = Database.getTeacherDetails(staffId);

        
        String str = String.format(" %-15s %-15s %-15s %-15s %-15s", "ID No", "First Name", "Last Name", "Major", "Minor");
        System.out.println(str);

        str = String.format(" %-15s %-15s %-15s %-15s %-15s", result.get("staffId"), result.get("firstName"), result.get("lastName"), result.get("major"), result.get("minor"));
        System.out.println(str);
    }

    public static void changePassword(){
        
    }

    
    @Override
    public void teachingCourse(String courseId) {
        // TODO Auto-generated method stub

        
    }
    @Override
    public float gradeStudents(String studentId) {
        // TODO Auto-generated method stub

        return 0;
    }
    @Override
    public String adviseSection(String roomId, int grade, String section) {
        // TODO Auto-generated method stub
        return null;
    }

    
}
