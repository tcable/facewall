package uk.co.o2.facewall.model;

public class UserModel {
    public String email;
    public String password;
    public String imgUrl;
    public String name;
    public String role;
    public String location;
    public String officeLocation;
    public String team;
    public String scrum;
    public String details;

    // FIXME - Charlie :  refactor me so I only use one constructor?
    public UserModel() {
    }

    public UserModel(String email, String password, String imgUrl, String name, String role, String location, String officeLocation, String team, String scrum, String details) {
        this.email = email;
        this.password = password;
        this.imgUrl = imgUrl;
        this.name = name;
        this.role = role;
        this.location = location;
        this.officeLocation = officeLocation;
        this.team = team;
        this.scrum = scrum;
        this.details = details;
    }
}
