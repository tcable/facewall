package uk.co.o2.facewall.databaseutils.fixture;

import java.util.ArrayList;
import java.util.List;

import static uk.co.o2.facewall.databaseutils.fixture.PersonDataFactory.defaultPersons;
import static uk.co.o2.facewall.databaseutils.fixture.TeamData.newTeamData;
import static java.lang.Integer.toHexString;
import static java.util.Arrays.asList;
import static java.util.UUID.randomUUID;
import static java.util.concurrent.ThreadLocalRandom.current;

public class TeamDataFactory {

    public static List<TeamData.Builder> defaultTeams(int number) {
        List<TeamData.Builder> result = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            result.add(defaultTeamWithDefaultMembers());
        }
        return result;
    }

    public static TeamData.Builder defaultTeamWithDefaultMembers() {
        return defaultTeam()
            .withMembers(defaultPersons(1 + randomInt(14)));
    }

    public static TeamData.Builder defaultTeam() {
        return newTeamData()
            .withProperty("id", randomUUID().toString())
            .withProperty("name", randomName())
            .withProperty("colour", randomColour());
    }

    private static String randomName() {
        return teamNames.get(randomInt(teamNames.size()));
    }

    private static String randomColour() {
        int randomRange = 0xffffff - 0x999999;
        int randomInt = randomInt(randomRange);

        return toHexString(randomInt + 0x999999);
    }

    private static int randomInt(int bound) {
        return current().nextInt(bound);
    }

    private static final List<String> teamNames = asList(
        "The Lab",
        "Ecommerce",
        "FaceWall",
        "Digital SMB",
        "Deployment",
        "OPP",
        "Portal",
        "Kentucky",
        "Project Wimbledon",
        "Ecom ARS",
        "Devo2",
        "O2 Leadership"
    );
}
