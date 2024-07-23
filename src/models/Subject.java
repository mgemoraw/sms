package models;

public class Subject {
    private String id;
    private String code;
    private String name;
    private String description;
    private int chapters;
    private int grade;

    protected Subject(){

    }

    protected Subject(String id, String code, String name, String desc, int chapters, int grade){
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = desc;
        this.chapters = chapters;
        this.grade = grade;
    }

    public  void takeCourse(){
        
    }

}
