package models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

// base model
public abstract class BaseModel implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private Date createdAt;
    private Date updatedAt;
    public BaseModel(){
        // default constructor
        createdAt = new Date();
        updatedAt = new Date();
    }

    public BaseModel(String id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        createdAt = new Date();
        updatedAt = new Date();
    }

    // ID getter method
    public String getId(){
        return this.id;
    }

    // ID setter method
    public void setId(String id){
        this.id = id;
    }

    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public void setFirstName(String name){this.firstName = name;}
    public void setLastName(String name){this.lastName = name;}

    public Date getCreatedAt(){return this.createdAt;}
    public Date getUpdatedAt(){return this.updatedAt;}

    @Override
    public String toString(){
        return this.id;
    }


    // returning json string
    public String toJson(){
        // returns json formatted string respresentation of BaseModel
        return "{\"id\": \"" + id + "\",\"name\": \""+ firstName + "\",\"created_at\": \"" + createdAt + "\", \"updated_at\": \"" + updatedAt + "\"}";
    }

    // converts to hasmap and returns
    public HashMap<String, String> toMap(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        map.put("name", firstName);
        map.put("created_at", createdAt.toString());
        map.put("updated_at", updatedAt.toString());
        return  map;
    }

    public abstract void login(String userId, String password);
    public abstract void logout(String userId, String password);
}

