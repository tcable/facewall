package uk.co.o2.facewall.data.dao;

import uk.co.o2.facewall.data.dao.database.FacewallDB;
import uk.co.o2.facewall.data.dao.database.QueryResultRow;
import uk.co.o2.facewall.data.dao.database.query.DatabaseQueryBuilder;
import uk.co.o2.facewall.data.datatype.PersonId;
import uk.co.o2.facewall.data.datatype.TeamId;
import uk.co.o2.facewall.data.dto.PersonDTO;
import uk.co.o2.facewall.data.dto.TeamDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static uk.co.o2.facewall.data.dao.database.QueryResultRowBuilder.blankRow;
import static uk.co.o2.facewall.data.dao.database.QueryResultRowBuilder.defaultRow;
import static uk.co.o2.facewall.data.dao.database.QueryResultsBuilder.results;
import static uk.co.o2.facewall.data.datatype.PersonId.newPersonId;
import static uk.co.o2.facewall.data.datatype.TeamId.newTeamId;
import static uk.co.o2.facewall.data.dto.PersonDTOMatcher.aPersonDTO;
import static uk.co.o2.facewall.data.dto.PersonInformation.newPersonInformation;
import static uk.co.o2.facewall.data.dto.PersonInformationMatcher.aPersonInformation;
import static uk.co.o2.facewall.data.dto.TeamDTOMatcher.aTeamDTO;
import static uk.co.o2.facewall.data.dto.TeamInformation.newTeamInformation;
import static uk.co.o2.facewall.data.dto.TeamInformationMatcher.aTeamInformation;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static uk.co.o2.facewall.util.IterableMatchers.containsExhaustivelyInAnyOrder;

@RunWith(MockitoJUnitRunner.class)
public class QueryingDAOTest {

    private static final TeamId someTeamId = newTeamId("blah");
    private static final PersonId somePersonId = newPersonId("bloom");
    private static final DatabaseQueryBuilder someQuery = mock(DatabaseQueryBuilder.class);

    @Mock FacewallDB mockDb;

    @InjectMocks
    private QueryingDAO facewallDAO;

    @Test
    public void fetch_persons_retrieves_person_nodes() {
        stubDb(results().withRows(
                defaultRow().withPerson(newPersonInformation().withId("jim")),
                defaultRow().withPerson(newPersonInformation().withId("bob"))
        ).build());

        Iterable<PersonDTO> result = facewallDAO.queryPersons(someQuery);
        assertThat(result, containsExhaustivelyInAnyOrder(
                aPersonDTO().withPerson(aPersonInformation().withId("jim")),
                aPersonDTO().withPerson(aPersonInformation().withId("bob"))
        ));
    }

    @Test
    public void fetch_persons_retrieves_team_node() {

        stubDb(results().withRows(
                defaultRow().withTeam(newTeamInformation().withId("reds"))
        ).build());

        Iterable<PersonDTO> result = facewallDAO.queryPersons(someQuery);

        assertThat(result, containsExhaustivelyInAnyOrder(
                aPersonDTO().withTeam(aTeamInformation()
                        .withId("reds")
                )));
    }

    @Test
    public void fetch_teams_retrieves_team_nodes() {
        stubDb(results().withRows(
                defaultRow().withTeam(newTeamInformation().withId("blues")),
                defaultRow().withTeam(newTeamInformation().withId("light blues"))
        ).build());

        Iterable<TeamDTO> result = facewallDAO.queryTeams(someQuery);

        assertThat(result, containsExhaustivelyInAnyOrder(
                aTeamDTO().withTeamInformation(aTeamInformation().withId("blues")),
                aTeamDTO().withTeamInformation(aTeamInformation().withId("light blues"))
        ));
    }

    @Test
    public void fetch_teams_retrieves_members_nodes() {
        stubDb(results().withRows(
                blankRow()
                        .withPerson(newPersonInformation().withId("henry"))
                        .withTeam(newTeamInformation().withId("first team")),
                blankRow()
                        .withPerson(newPersonInformation().withId("percy"))
                        .withTeam(newTeamInformation().withId("first team")),
                blankRow()
                        .withPerson(newPersonInformation().withId("edward"))
                        .withTeam(newTeamInformation().withId("second team"))
        ).build());

        Iterable<TeamDTO> result = facewallDAO.queryTeams(someQuery);

        assertThat(result, containsExhaustivelyInAnyOrder(
                aTeamDTO().withTeamInformation(aTeamInformation().withId("first team"))
                        .whereMemberInformation(containsExhaustivelyInAnyOrder(
                                aPersonInformation().withId("henry"),
                                aPersonInformation().withId("percy")
                        )),
                aTeamDTO().withTeamInformation(aTeamInformation().withId("second team"))
                        .whereMemberInformation(containsExhaustivelyInAnyOrder(
                                aPersonInformation().withId("edward")
                        ))));
    }

    private void stubDb(Iterable<QueryResultRow> queryResults) {
        when(mockDb.query(any(DatabaseQueryBuilder.class)))
                .thenReturn(queryResults);
    }
}
