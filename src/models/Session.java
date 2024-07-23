package models;
import java.time.LocalTime;

public class Session {
    private int sessionId;
    private String day;
    private LocalTime time;
    String subject;

    public Session(){

    }
    public Session(int id, String day, LocalTime time, String subject){
        this.sessionId = id;
        this.day = day;
        this.time = time;
        this.subject = subject;
    }

    public int getSessionId(){return this.sessionId;}
    public void setSessionId(int id){
        this.sessionId = id;
    }

    public LocalTime getTime(){
        return this.time;
    }
    public void setTime(LocalTime t){
        this.time = t;
    }

    public String getDay(){
        return this.day;
    }
    public void setDay(String day){
        this.day = day;
    }

    public void setSubject(String sub){
        this.subject = sub;
    }
}
