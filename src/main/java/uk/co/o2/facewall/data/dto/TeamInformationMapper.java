package uk.co.o2.facewall.data.dto;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.NotFoundException;
import org.neo4j.graphdb.Transaction;

import static uk.co.o2.facewall.data.dto.TeamInformation.newTeamInformation;
import static uk.co.o2.facewall.data.dto.TeamInformation.noTeamInformation;

public class TeamInformationMapper {

    public TeamInformation map(Node teamNode) {
        TeamInformation result = noTeamInformation();

        if (teamNode != null) {
            TeamInformation.Builder teamInformation = newTeamInformation();

            Transaction tx = teamNode.getGraphDatabase().beginTx();
            try {
                String id = (String) teamNode.getProperty("id");
                teamInformation.withId(id);
                tx.success();
            } catch (NotFoundException e) {
                tx.failure();
            } finally {
                tx.close();
            }

            tx = teamNode.getGraphDatabase().beginTx();
            try {
                String name = (String) teamNode.getProperty("name");
                teamInformation.named(name);
                tx.success();
            } catch (NotFoundException e) {
                tx.failure();
            } finally {
                tx.close();
            }

            tx = teamNode.getGraphDatabase().beginTx();
            try {
                String colour = (String) teamNode.getProperty("colour");
                teamInformation.coloured(colour);
                tx.success();
            } catch (NotFoundException e) {
                tx.failure();
            } finally {
                tx.close();
            }

            result = teamInformation.build();
        }
        return result;
    }
}
