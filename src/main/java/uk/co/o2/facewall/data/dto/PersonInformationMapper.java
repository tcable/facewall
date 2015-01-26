package uk.co.o2.facewall.data.dto;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.NotFoundException;
import org.neo4j.graphdb.Transaction;

import static uk.co.o2.facewall.data.dto.PersonInformation.newPersonInformation;
import static uk.co.o2.facewall.data.dto.PersonInformation.noPersonInformation;

public class PersonInformationMapper {

    public PersonInformation map(Node personNode) {
        PersonInformation result = noPersonInformation();

        if (personNode != null) {
            PersonInformation.Builder personInformation = newPersonInformation();

            Transaction tx = personNode.getGraphDatabase().beginTx();
            try {
                String id = (String) personNode.getProperty("id");
                personInformation.withId(id);
                tx.success();
            } catch (NotFoundException e) {
                tx.failure();
            } finally {
                tx.close();
            }

            tx = personNode.getGraphDatabase().beginTx();
            try {
                String name = (String) personNode.getProperty("name");
                personInformation.named(name);
                tx.success();
            } catch (NotFoundException e) {
                tx.failure();
            } finally {
                tx.close();
            }

            tx = personNode.getGraphDatabase().beginTx();
            try {
                String picture = (String) personNode.getProperty("picture");
                personInformation.withPicture(picture);
                tx.success();
            } catch(NotFoundException e) {
                tx.failure();
            } finally {
                tx.close();
            }

            tx = personNode.getGraphDatabase().beginTx();
            try {
                String email = (String) personNode.getProperty("email");
                personInformation.withEmail(email);
                tx.success();
            } catch(NotFoundException e) {
                tx.failure();
            } finally {
                tx.close();
            }

            tx = personNode.getGraphDatabase().beginTx();
            try {
                String role = (String) personNode.getProperty("role");
                personInformation.withRole(role);
                tx.success();
            } catch(NotFoundException e) {
                tx.failure();
            } finally {
                tx.close();
            }
            result = personInformation.build();
        }
        return result;
    }
}
