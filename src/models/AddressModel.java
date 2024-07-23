package models;

public class AddressModel {
    private String userId;
    private String cityName;
    private String houseNo;
    private String email;
    private String phoneNumber;
    private Location location;

    public AddressModel(){
        //
        userId = null;
        cityName = "";
        houseNo = "";
        location = new Location();
    }

    public AddressModel(String userId, String city, String houseNo, Location location){
        this.userId = userId;
        this.cityName = city;
        this.houseNo = houseNo;
        this.location = location;
    }
}
