package models;

public class Location {
    private String longitude;
    private String latitute;


    public Location(){
        // default location
    }

    public Location(String longitute, String latitute){
        this.longitude = longitute;
        this.latitute = latitute;
    }

    public String getLatitude(){
        return this.latitute;
    }
    public void setLatitude(String lat) {
        this.latitute = lat;
    }

    public String getLongitude(){
        return this.longitude;
    }

    public void setLongitude(String longitude){
        this.longitude = longitude;
    }
}
