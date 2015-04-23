package uk.co.o2.facewall.domain;

import uk.co.o2.facewall.data.datatype.PersonId;

public interface Person {
    String name();
    String picture();
    String email();
    String password();
    Team team();
    PersonId getId();
    String role();
    String scrum();
    String details();
    String location();
    String officeLocation();
}
