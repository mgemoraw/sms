package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class Database {
    static Connection connection;
    private static final String jdbcUrl = "jdbc:sqlite:src/storage/database.db";
    

    // getter method for database url
    public static String getUrl(){
        return jdbcUrl;
    }

    // returns connection object
    public static Connection connect(String jdbcUrl){
        try {
            connection  = DriverManager.getConnection(jdbcUrl);
            
        }catch(SQLException e){
            //
            System.out.println("Database connection error: ");
            e.printStackTrace();
            return null;
        }
        return connection;
    }

    public static void close(Connection conn) throws SQLException{
        conn.close();
    }
    // create database tables
    public static void createTables(){
        // create connection to the databse
        Connection conn = connect(jdbcUrl);

        if (conn != null){
            try {
                  /** USER ROLES
                 * user groups are guest, admin, student, teacher, and staff
                 * user roles are database read and write access roles
                 * user role 'user': only read access to user page contents
                 * user role 'admin': is super user
                 * user role 'student': has read access amd write access on student username and password
                 * user role 'teacher':
                 * user role 'advisor':
                 * user role 'principal':
                 * user role 'registrar':
                 * 
                 * TABLES
                 * users: userId, username, password, group, role='user'
                 * students: stdId, firstname, lastname, email, dob, grade, subField, section="A", address, "username="firstname", password="firstname", 
                 * teachers: tid, firstname, lastname, email, dob, major, minor, dateEmployed, address, username="firstname", password="name"
                 * 
                 * classrooms: roomId, section, numberOfStudents, grade
                 * subject: sid, name, description, chapters
                 * parent: pid, name, email, phone, address, username, password
                 * principal: managerId, firstname, lastname, email, username, password, phone
                 * 
                 */ 
                
                Statement statement = conn.createStatement();
                
                System.out.println("creating database tables...");


                // creating address table
                String sql = """
                        CREATE TABLE IF NOT EXISTS address(
                        id int auto_increment primary key,
                        city varchar(100),
                        houseNumber varchar(20),
                        homePhone varchar(20))
                        """;
                statement.execute(sql);
                // System.out.println("Address table created successfully");

                // creating subjects table
                sql = """
                        CREATE TABLE IF NOT EXISTS subjects(
                        id int auto_increment primary key,
                        code varchar(20),
                        name varchar(100),
                        descriptoin varchar(255),
                        gradeLevel int,
                        chapters int)
                        """;
                statement.execute(sql);
                // System.out.println("Subject table created successfully");


                // create users table
                sql = """
                        CREATE TABLE IF NOT EXISTS users(
                        id int auto_increment primary key,
                        userId varchar(20) unique not null, 
                        username varchar(100) not null, 
                        password varchar(100) not null, 
                        userGroup varchar(20), 
                        role varchar(50) default 'user')
                        """;
                
                // statement.execute("drop table if exists users");
                // System.out.println("Database users droped");

                statement.execute(sql);
                // System.out.println("Database users created");

                // create school manager table
                // sql = "drop table if exists managers";
                // statement.execute(sql);

                sql = """
                    CREATE TABLE IF NOT EXISTS managers(
                    managerId int auto_increment primary key, 
                    staffId varchar(50), 
                    firstname varchar(100) not null, 
                    lastname varchar(100) not null, 
                    email varchar(100) not null, 
                    phone varchar(20),
                    foreign key(staffId) references teachers(staffId))
                    """; 

                statement.execute(sql);
                // System.out.println("Principal table created");

                // create teachers table
                // sql = "drop table if exists teachers";
                // statement.execute(sql);

                sql = """
                    CREATE TABLE IF NOT EXISTS teachers(
                    staffId varchar(50) primary key, 
                    firstname varchar(100) not null, 
                    lastname varchar(100) not null, 
                    email varchar(100), dob Date, 
                    educationLevel varchar(100), 
                    major varchar(100), 
                    dateEmployed Date, 
                    minor varchar(100), 
                    phone varchar(20),
                    addressId int,
                    foreign key(addressId) references address(id));
                    """;

                statement.execute(sql);
                // System.out.println("teachers table created");


                // // create families table
                // sql = "drop table if exists families";
                // statement.execute(sql);

                sql = """
                    CREATE TABLE IF NOT EXISTS families(
                    userId varchar(50) primary key, 
                    firstname varchar(100) not null, 
                    lastname varchar(100) not null, 
                    email varchar(100) , 
                    phone varchar(20), 
                    addressId int,
                    foreign key(addressId) references address(id))
                    """;
                statement.execute(sql);
                // System.out.println("families table created");


                // create students table
                // sql = "drop table if exists students";
                // statement.execute(sql);

                sql = """
                    CREATE TABLE IF NOT EXISTS students(
                    studentId varchar(50) primary key, 
                    firstname varchar(100) not null, 
                    lastname varchar(100) not null, 
                    email varchar(100) , 
                    dob Date, 
                    gradeLevel int, 
                    studyField varchar(100), 
                    phone varchar(20),
                    classId int,
                    familyId,
                    foreign key(classId) references classrooms(roomId),
                    foreign key(familyId) references families(userId))
                    """;
                statement.execute(sql);
                // System.out.println("students table created");

                
                 // create staff table
                // sql = "drop table if exists staff";
                // statement.execute(sql);

                sql = """
                    CREATE TABLE IF NOT EXISTS staff(
                    staffId varchar(50) primary key, 
                    firstname varchar(100) not null, 
                    lastname varchar(100) not null, 
                    email varchar(100) , 
                    dob Date, 
                    dateEmployed date,
                    education varchar(255),
                    department varchar(100),
                    phone varchar(20),
                    addressId int,
                    foreign key(addressId) references address(id));
                 """;
                         
                statement.execute(sql);
                // System.out.println("staff table created");

                // create classroom
                // sql = "drop table if exists classrooms";
                // statement.execute(sql);

                sql = """
                    CREATE TABLE IF NOT EXISTS classrooms(
                    roomId int auto_increment primary key, 
                    grade int not null, 
                    numberOfStudents int not null, 
                    section varchar(10), 
                    building varchar(100));
                    """;
                statement.execute(sql);
                // System.out.println("classrooms table created");

                // notice 
                System.out.println("...all database tables created successfully");


                // inserting test data after database tables are created
                System.out.println("Inserting test data...");
                
                insertTestData();

                // close connection;
                close(conn);

            } catch(SQLException e){
                // return;
                System.out.println("Error: ");
                e.printStackTrace();
            }
        } 
    }


    private static void insertTestData() {
        Connection conn = connect(Database.getUrl());
                     
        if (conn!=null){
            // PreparedStatement statement = conn.pre
            try {
                
                // inserting test data for users
                String sql ="""
                    INSERT INTO users(userId, username, password, userGroup) values
                    ('THSE1001','admin','Admin123', 'admin'),
                    ('THSE1002', 'user', 'sgetme', 'admin'),
                    ('THSE1003', 'abebe', 'abebe123', 'advisor' ),
                    ('THSE1004', 'abel', 'abel123', 'teacher'),
                    ('THSE1005', 'abera', 'abera123', 'staff'),
                    ('THSS1001', 'feven', 'feven123', 'student'),
                    ('THSS1002', 'girma', 'girma123', 'student'),
                    ('THSS1004', 'selam', 'selam123', 'student')
                            
                    """;

                Statement statement = conn.createStatement();
                statement.execute(sql);
                
    

                // insertint student table data
                sql = """
                Insert into students(studentId, firstname, lastname, email, gradeLevel, studyField) values
                ('THSS1001', 'Feven', 'Ali', 'feven123@gmail.com', 12, 'Natural'),('THSS1002', 'Girma', 'Dagnaw', 'girmadan@gmail.com', 11, 'Social'),
                ('THSS1003', 'Abdu', 'Yimer', 'abdu2@gmail.com', 9, 'null'),
                ('THSS1004', 'selamawit', 'belete', 'selam@gmail.com', 12, 'Natural')
                
                """;

                statement.execute(sql);


                // insertint teachers table data
                sql = """
                Insert into teachers(staffId, firstname, lastname, email, major, minor) values
                ('THSE1001', 'Moges', 'Ali', 'moges22@gmail.com', 'Physics', 'Maths'),
                ('THSE1002', 'Mengist', 'Akele', 'sgetme@gmail.com', 'Chemistry', 'Biology'),
                ('THSS1003', 'Abebe', 'Haimanot', 'abem@gmail.com', 'Sport', 'Biology'),
                ('THSS1004', 'Abel', 'Kebede', 'abelk@yahoo.com', 'Geography', 'Economics'),
                ('THSS1005', 'Abera', 'Shawul', 'shawulab@yahoo.com', 'Economics', 'Accounting')
                
                """;
                statement.execute(sql);
                
                // insertint staff table data
                sql = """
                Insert into staff(staffId, firstname, lastname, email, department) values
                ('THSS1005', 'Abera', 'Shawul', 'shawulab@yahoo.com', 'finance')
                """;
                statement.execute(sql);

                // insertint staff table data
                sql = """
                Insert into managers(managerId, staffId, firstname, lastname, email, phone) values
                (1, 'THSE1001', 'Moges', 'Ali', 'moges22@gmail.com',  '251919666'),
                (2, 'THSE1002', 'Mengist', 'Akele', 'sgetme@gmail.com', '+251919666')
                """;
                statement.execute(sql);

                System.out.println("Test Data inserted successfully");

                conn.close();
            } catch(SQLException exp){
                System.out.println("Data already inserted");
                // exp.printStackTrace();
            }  
        }
   
    }


    // delete table
    private void deleteTable(String tablename){
        // 
    }
    

    public static List<Student> fetchStudents(String sid, String name) throws SQLException{
        Connection conn = Database.connect(Database.getUrl());

        List<Student> students = new ArrayList<Student>();
                
        String sql = """
                Select * from students where studentId=?;
                """;
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, "studentId");

        preparedStatement.executeQuery();
        ResultSet result = preparedStatement.executeQuery();
        
        while(result.next()){
            Student student = new Student();
            student.setFirstName(result.getString("firstname"));
            student.setLastName(result.getString("lastname"));
            students.add(student);
        }
        // conn.close();
        return students;
    }

    public static HashMap<String, String> fetchUser(String username, String password) throws SQLException{

        Connection conn = connect(getUrl());
        // String dbUserName=null, dbPassword=null, dbUserGroup=null;


        HashMap<String, String> user = new HashMap<String, String>();
        String query = "select * from users where username=? and password=?";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        // Statement statement = conn.createStatement();
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet result = preparedStatement.executeQuery();
         
        while (result.next()){
            user.put("username",  result.getString("username")); 
            user.put("password", result.getString("password"));
            user.put("userGroup", result.getString("userGroup"));
        }

        conn.close();

        return user;
    }

    public static void insertNewStudent(Student student)  {
        String query = "INSERT INTO students(studentId, firstname, lastname, gradeLevel) VALUES(?, ?, ?, ?)";

        // String query2 = "INSERT INTO students(studentId, firstname, lastname, gradeLevel) VALUES()";

        try {
            Connection conn = Database.connect(getUrl());
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, student.getId());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setInt(4, student.getGradeLevel());
            statement.executeUpdate();


            System.out.println("\nStudent added to the database successfully");
            
            conn.close();

        } catch(SQLException e){
            System.out.println("Database Connection Error");
            e.printStackTrace();
        }

    }

    public static void insertNewStaff(Staff st){
        String query = "INSERT INTO staff(userId, firstname, lastname, department) VALUES(?, ?, ?, ?)";
        try {
            Connection conn = Database.connect(getUrl());
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, st.getId());
            statement.setString(2, st.getFirstName());
            statement.setString(3, st.getLastName());
            statement.setString(4, st.getDepartment());
           
            statement.executeUpdate();

            System.out.println("\n Staff added to the database successfully");

            conn.close();
        } catch(SQLException e){
            System.out.println("Database Connection Error");
            e.getStackTrace();
        }
    }
    public static void insertNewTeacher(Teacher t)  {
        String query = "INSERT INTO teachers(userId, firstname, lastname, major,minor) VALUES(?, ?, ?, ?, ?)";

        try {
            Connection conn = Database.connect(getUrl());
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, t.getId());
            statement.setString(2, t.getFirstName());
            statement.setString(3, t.getLastName());
            statement.setString(4, t.getMajor());
            statement.setString(5, t.getMinor());
            statement.executeUpdate();

            System.out.println("\n Teacher added to the database successfully");
            // adding username and password
            
            conn.close();

        } catch(SQLException e){
            System.out.println("Database Connection Error");
            e.getStackTrace();
        }

    }

    public static void insertClassroom(Classroom room)  {
        String query = "INSERT INTO classrooms(roomId, grade, section, building, numberOfStudents) values(?, ?, ?, ?, ?)";

        try {
            Connection conn = Database.connect(getUrl());
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, room.getRoomId());
            statement.setInt(2, room.getGradeLevel());
            statement.setString(3, room.getSection());
            statement.setString(4, room.getbuildingNo());
            statement.setInt(5, room.getNumberOfStudents());
            statement.executeUpdate();

            System.out.println("\n Classroom created Successfully");
            // adding username and password
            
            conn.close();

        } catch(SQLException e){
            System.out.println("Writing data to database failed");
            e.printStackTrace();
        }

    }
    public static void insertNewUser(String userId, String userName, String password, String userGroup) {
        String query = "INSERT INTO users(userId, username, password, userGroup) VALUES(?, ?, ?, ?)";
        
        try {
        Connection conn = Database.connect(getUrl());
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, userName);
        statement.setString(3, password);
        statement.setString(4, userGroup);
        
        statement.executeUpdate();
        System.out.println("User Added Successfully");
        conn.close();
        } catch(SQLException e) {
            System.out.println("Database Connection Error");
            e.printStackTrace();
        }
    }


    public static HashMap<String, String> getStudentDetails(String userId) throws SQLException{
        Connection conn = Database.connect(getUrl());
        HashMap<String, String> std = new HashMap<String, String>();

        String query = "select * from students where studentId=?";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        // Statement statement = conn.createStatement();
        preparedStatement.setString(1, userId);
    
        ResultSet result = preparedStatement.executeQuery();
        
        while (result.next()){
            std.put("firstName", result.getString("firstname"));
            std.put("lastName", result.getString("lastname"));
            std.put("gradeLevel", ""+result.getInt("gradeLevel"));
        }
        conn.close();

        return std;
    }

    public static HashMap<String, String> getTeacherDetails(String userId) throws SQLException{
        String query = "select * from teachers where staffId=?";
        Connection conn = Database.connect(getUrl());
        HashMap<String, String> data = new HashMap<String, String>();

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        // Statement statement = conn.createStatement();
        preparedStatement.setString(1, userId);
    
        ResultSet result = preparedStatement.executeQuery();
        
        while (result.next()){
            data.put("firstName", result.getString("firstname"));
            data.put("lastName", result.getString("lastname"));
            data.put("major", ""+result.getString("major"));
        }
        conn.close();

        return data;
    }

    public static ResultSet getStaffDetails(String userId) throws SQLException{
        String query = "select * from staff where userId=?";
        Connection conn = Database.connect(getUrl());
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        // Statement statement = conn.createStatement();
        preparedStatement.setString(1, userId);
    
        ResultSet staff = preparedStatement.executeQuery();
        
        conn.close();
        return staff;
    }


    // get list of students
    public static List<HashMap<String, String>> getStudents(){

        Connection conn;;
        List<HashMap<String, String>> students = new ArrayList<HashMap<String, String>>();
        try {
            conn = connect(getUrl());
            String sql = """
                    select * from students;
                    """;
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
        
            while (result.next()){

                HashMap<String, String>std = new HashMap<String, String>();
                std.put("studentId", result.getString("studentId"));
                std.put("firstName", result.getString("firstname"));
                std.put("lastName", result.getString("lastname"));
                std.put("email", result.getString("email"));
                std.put("dob", result.getString("dob"));
                std.put("gradeLevel", "" + result.getInt("gradeLevel"));
                std.put("studyField", "" + result.getString("studyField"));
                std.put("phone", "" + result.getString("phone"));
                students.add(std);
            }

            // finally close connection
            conn.close();
        } catch(SQLException e){
            System.out.println("Database Connection Error");
            e.printStackTrace();
        } 

        
       // return
       return students;
    }

    // get list of teachers
    public static List<HashMap<String, String>> getTeachers(){

        Connection conn;;
        List<HashMap<String, String>> teachers = new ArrayList<HashMap<String, String>>();
        try {
            conn = connect(getUrl());
            String sql = """
                    select * from teachers;
                    """;
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
        
            while (result.next()){

                HashMap<String, String> row = new HashMap<String, String>();
                row.put("staffId", result.getString("staffId"));
                row.put("firstName", result.getString("firstname"));
                row.put("lastName", result.getString("lastname"));
                row.put("email", result.getString("email"));
                row.put("dob", result.getString("dob"));
                row.put("educationLevel", result.getString("educationLevel"));
                row.put("major", "" + result.getString("major"));
                row.put("minor", "" + result.getString("minor"));
                row.put("phone", "" + result.getString("phone"));
                row.put("dateEmployed", result.getString("dateEmployed"));
                teachers.add(row);
            }

            // finally close connection
            conn.close();
        } catch(SQLException e){
            System.out.println("Database Connection Error");
            e.printStackTrace();
        } 

        
       // return
       return teachers;
    }

    // get list of staffs
    public static List<HashMap<String, String>> getStaffs(){

        Connection conn;;
        List<HashMap<String, String>> staffs = new ArrayList<HashMap<String, String>>();
        try {
            conn = connect(getUrl());
            String sql = """
                    select * from staff;
                    """;
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
        
            while (result.next()){

                HashMap<String, String> row = new HashMap<String, String>();
                row.put("staffId", result.getString("staffId"));
                row.put("firstName", result.getString("firstname"));
                row.put("lastName", result.getString("lastname"));
                row.put("email", result.getString("email"));
                row.put("dob", result.getString("dob"));
                row.put("dateEmployed", result.getString("dateEmployed"));

                row.put("department", result.getString("department"));
                row.put("educationLevel", "" + result.getString("educationLevel"));
                
                row.put("phone", "" + result.getString("phone"));
                staffs.add(row);
            }

            // finally close connection
            conn.close();
        } catch(SQLException e){
            System.out.println("Database Connection Error");
            e.printStackTrace();
        } 
        
       // return
       return staffs;
    }

    // get list of managers
    public static List<HashMap<String, String>> getManagers(){

        Connection conn;;
        List<HashMap<String, String>> managers = new ArrayList<HashMap<String, String>>();
        try {
            conn = connect(getUrl());
            String sql = """
                    select * from managers;
                    """;
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
        
            while (result.next()){

                HashMap<String, String> row = new HashMap<String, String>();
                row.put("staffId", result.getString("staffId"));
                row.put("firstName", result.getString("firstname"));
                row.put("lastName", result.getString("lastname"));
                row.put("email", result.getString("email"));
                
                row.put("phone", "" + result.getString("phone"));
                managers.add(row);
            }

            // finally close connection
            conn.close();
        } catch(SQLException e){
            System.out.println("Database Connection Error");
            e.printStackTrace();
        } 

        
       // return
       return managers;
    }


    // get list of classrooms
    public static List<HashMap<String, String>> getClassrooms(){

        Connection conn;;
        List<HashMap<String, String>> rooms = new ArrayList<HashMap<String, String>>();
        try {
            conn = connect(getUrl());
            String sql = """
                    select * from classrooms;
                    """;
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
        
            while (result.next()){

                HashMap<String, String> row = new HashMap<String, String>();
                row.put("roomId", "" + result.getInt("roomId"));
                row.put("grade", "" + result.getInt("grade"));
                row.put("numberOfStudents",  "" + result.getString("numberOfStudents"));
                row.put("section", result.getString("section"));
                
                row.put("building", "" + result.getString("building"));
                rooms.add(row);
            }

            // finally close connection
            conn.close();
        } catch(SQLException e){
            System.out.println("Database Connection Error");
            e.printStackTrace();
        } 

        
       // return
       return rooms;
    }

    // get list of classrooms
    public static List<HashMap<String, String>> getAllUsers(){

        Connection conn;;
        List<HashMap<String, String>> users = new ArrayList<HashMap<String, String>>();
        try {
            conn = connect(getUrl());
            String sql = """
                    select * from users;
                    """;
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
        
            while (result.next()){

                HashMap<String, String> row = new HashMap<String, String>();
                row.put("userId", result.getString("userId"));
                row.put("username", result.getString("username"));
                row.put("password", result.getString("password"));
                row.put("userGroup", result.getString("userGroup"));
                
                row.put("role", result.getString("role"));
                users.add(row);
            }

            // finally close connection
            conn.close();
        } catch(SQLException e){
            System.out.println("Database Connection Error");
            e.printStackTrace();
        } 

        
       // return
       return users;
    }


    public static boolean changePassword(String userId, String password) {
        String sql = """
                update users set password=? from users where userId=?;
                """;

        try {
            Connection conn = connect(getUrl());
            PreparedStatement statement = conn.prepareStatement(sql);
    
    
            statement.setString(1, userId);
            statement.setString(2, password);
    
            statement.executeUpdate();
            conn.close();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
       


        return false;
    }
}

