package uk.co.o2.facewall.model;

public class PersonDetailsModel implements SearchResultsModel {
    public String name;
    public String teamName;
    public String picture;
    public String email;
    public String password;
    public String role;
    public String scrum;
    public String details;
    public String location;
    public String officeLocation;

    public PersonDetailsModel(String name, String teamName, String picture, String email, String password, String role, String scrum, String details, String location, String officeLocation) {
        this.name = name;
        this.teamName = teamName;
        this.picture = picture;
        this.email = email;
        this.password = password;
        this.role = role;
        this.scrum = scrum;
        this.details = details;
        this.location = location;
        this.officeLocation = officeLocation;
    }
}
