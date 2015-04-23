package uk.co.o2.facewall.data.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import uk.co.o2.facewall.data.datatype.PersonId;

import static uk.co.o2.facewall.data.datatype.PersonId.newPersonId;
import static uk.co.o2.facewall.data.datatype.PersonId.noPersonId;

public class PersonInformation {

    private static final PersonInformation noPersonInformation = newPersonInformation().build();

    private final PersonId id;
    @NotBlank(message = "Name must not be blank")
    @Length(max=50, message = "Length must be less than 50 characters")
    private final String name;
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email is invalid")
    private final String email;
    @NotBlank(message = "password must not be blank")
    @Length(max=25, message = "Length must be less than 25 characters")
    private final String password;
    @NotBlank(message = "Role must not be blank")
    private final String role;
    @NotBlank(message = "Picture must not be blank")
    @URL(message = "The URL is invalid")
    private final String picture;
    @NotBlank(message = "scrum must not be blank")
    private final String scrum;
    @NotBlank(message = "Details must not be blank")
    private final String details;
    @NotBlank(message = "Location must not be blank")
    private final String location;
    @NotBlank(message = "Office Location must not be blank")
    private final String officeLocation;


    private PersonInformation(Builder builder) {
        id = builder.id;
        name = builder.name;
        picture = builder.picture;
        email = builder.email;
        password = builder.password;
        role = builder.role;
        scrum = builder.scrum;
        details = builder.details;
        location = builder.location;
        officeLocation = builder.officeLocation;
    }

    public static Builder newPersonInformation() {
        return new Builder();
    }

    public static PersonInformation noPersonInformation() {
        return noPersonInformation;
    }

    public PersonId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public String getEmail() {
        return email;
    }

    public String getRole(){
        return role;
    }

    public String getScrum(){
        return scrum;
    }

    public String getPassword(){
        return password;
    }

    public String getDetails(){
        return details;
    }

    public String getLocation(){
        return location;
    }

    public String getOfficeLocation(){
        return officeLocation;
    }

    public static class Builder {
        private PersonId id = noPersonId();
        private String name = "";
        private String picture = "";
        private String email = "";
        private String password = "";
        private String role = "";
        private String scrum = "";
        private String details = "";
        private String location = "";
        private String officeLocation = "";

        public PersonInformation build() {
            return new PersonInformation(this);
        }

        public Builder withId(String id) {
            this.id = newPersonId(id);
            return this;
        }

        public Builder named(String name) {
            this.name = name;
            return this;
        }

        public Builder withPicture(String picture) {
            this.picture = picture;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withRole(String role) {
            this.role = role;
            return this;
        }

        public Builder withScrum(String scrum) {
            this.scrum = scrum;
            return this;
        }

        public Builder withDetails(String details) {
            this.details = details;
            return this;
        }

        public Builder withLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder withOfficeLocation(String officeLocation) {
            this.officeLocation = officeLocation;
            return this;
        }
    }
}
