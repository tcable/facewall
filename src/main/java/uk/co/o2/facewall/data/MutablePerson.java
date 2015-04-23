package uk.co.o2.facewall.data;

import uk.co.o2.facewall.data.datatype.PersonId;
import uk.co.o2.facewall.data.dto.PersonInformation;
import uk.co.o2.facewall.domain.Person;
import uk.co.o2.facewall.domain.Team;

class MutablePerson implements Person {

    private final PersonInformation personInformation;
    private Team team;

    public MutablePerson(PersonInformation personInformation) {
        this.personInformation = personInformation;
    }

    @Override final public PersonId getId() {
        return personInformation.getId();
    }

    @Override final public String name() {
        return personInformation.getName();
    }

    @Override final public String picture() {
        return personInformation.getPicture();
    }

    @Override final public String email() {
        return personInformation.getEmail();
    }

    @Override final public String password() {
        return personInformation.getPassword();
    }

    @Override final public String role() {
        return personInformation.getRole();
    }

    @Override public Team team() {
        return team;
    }

    @Override final public String scrum() {
        return personInformation.getScrum();
    }

    @Override final public String details() {
        return personInformation.getDetails();
    }

    @Override final public String location() {
        return personInformation.getLocation();
    }

    @Override final public String officeLocation() {
        return personInformation.getOfficeLocation();
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
