package uk.co.o2.facewall.data.dto;

import java.util.TreeMap;

import static uk.co.o2.facewall.data.dto.TeamInformation.newTeamInformation;
import static uk.co.o2.facewall.data.dto.TeamInformation.noTeamInformation;

public class TeamInformationMapper {

    public TeamInformation map(TreeMap teamNode) {
        TeamInformation result = noTeamInformation();

        if (teamNode != null) {
            TeamInformation.Builder teamInformation = newTeamInformation();

            String id = (String) teamNode.get("id");
            if (id != null) {
                teamInformation.withId(id);
            }

            String name = (String) teamNode.get("name");
            if (name != null) {
                teamInformation.named(name);
            }

            String colour = (String) teamNode.get("colour");
            if (colour != null) {
                teamInformation.coloured(colour);
            }

            result = teamInformation.build();
        }
        return result;
    }
}
