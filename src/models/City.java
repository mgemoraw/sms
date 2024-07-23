package models;

public class City {
    public String name;
    public Location location;

    public City(){
        name = null;
        location = null;
    }

    public City(String name, Location loc){
        this.name = name;
        this.location = loc;
    }

}
