package uk.co.o2.facewall.data.dto;

import java.util.TreeMap;

import static uk.co.o2.facewall.data.dto.PersonInformation.newPersonInformation;
import static uk.co.o2.facewall.data.dto.PersonInformation.noPersonInformation;

public class PersonInformationMapper {

    public PersonInformation map(TreeMap<String, Object> personNode) {
        PersonInformation result = noPersonInformation();

        if (personNode != null) {
            PersonInformation.Builder personInformation = newPersonInformation();

            String id = (String) personNode.get("id");
            if (id != null) {
                personInformation.withId(id);
            }

            String name = (String) personNode.get("name");
            if (name != null) {
                personInformation.named(name);
            }

            String picture = (String) personNode.get("picture");
            if (picture != null) {
                personInformation.withPicture(picture);
            }

            String email = (String) personNode.get("email");
            if (email != null) {
                personInformation.withEmail(email);
            }

            String role = (String) personNode.get("role");
            if (role != null) {
                personInformation.withRole(role);
            }

            result = personInformation.build();
        }
        return result;
    }
}
