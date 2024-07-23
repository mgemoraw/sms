package models;

public class Classroom {
    private static int roomId = 0;
    private int numberOfStudents = 0;
    private int gradeLevel;
    private String section;
    private String buildingNo;

    public Classroom(){
        // default constructor
        roomId += 1;
    }

    public Classroom(int grade, String sec, int students, String building){
        roomId += 1;
        this.gradeLevel = grade;
        this.section = sec;
        this.numberOfStudents = students;
        this.buildingNo =building;
    }

    public int getRoomId(){
        return roomId;
    }

    public int getGradeLevel(){return this.gradeLevel;}
    public void setGradeLevel(int grade){
        this.gradeLevel = grade;
    }

    public int getNumberOfStudents(){
        return this.numberOfStudents;
    }
    public void setNumberOfStudents(int n){
        this.numberOfStudents = n;
    }

    public String getSection(){
        return this.section;
    }
    public void setSection(String sec){
        this.section = sec;
    }

    public String getbuildingNo(){
        return this.buildingNo;
    }
    public void setbuildingNo(String building){
        this.buildingNo = building;
    }
}
