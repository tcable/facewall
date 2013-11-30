package data.factory.team;

import data.dto.TeamDTO;
import data.factory.DefaultMutableTeam;
import data.factory.MembersFactory;
import data.factory.TeamFactory;
import data.mapper.MutableTeam;
import data.mapper.TeamMapper;
import domain.Person;
import domain.Team;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.neo4j.graphdb.Node;

import java.util.Collections;
import java.util.List;

import static data.factory.team.MutableTeamMatcher.aMutableTeam;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static util.CollectionMatcher.contains;

@RunWith(MockitoJUnitRunner.class)
public class TeamFactoryTest {

    @Mock TeamMapper mockTeamMapper;
    @Mock MembersFactory mockMembersFactory;

    private TeamFactory teamFactory;

    @Before
    public void setUp() throws Exception {
        teamFactory = new TeamFactory(mockTeamMapper, mockMembersFactory);
    }

    @Test
    public void create_team_delegates_to_teamMapper() {
        Team expectedTeam1 = mock(Team.class);
        Team expectedTeam2 = mock(Team.class);

        when(mockTeamMapper.map(any(MutableTeam.class), any(Node.class)))
            .thenReturn(expectedTeam1)
            .thenReturn(expectedTeam2);

        List<TeamDTO> dtos = asList(
            mock(TeamDTO.class), mock(TeamDTO.class)
        );
        List<Team> result = teamFactory.createTeams(dtos);

        assertThat(result, contains(
            sameInstance(expectedTeam1),
            sameInstance(expectedTeam2)
        ));
    }

    @Test
    public void create_team_maps_team_details() {
        Node expectedTeamNode1 = mock(Node.class);
        Node expectedTeamNode2 = mock(Node.class);

        List<TeamDTO> dtos = asList(
            new TeamDTO(expectedTeamNode1, Collections.<Node>emptyList()),
            new TeamDTO(expectedTeamNode2, Collections.<Node>emptyList())
        );
        teamFactory.createTeams(dtos);

        verify(mockTeamMapper).map(any(DefaultMutableTeam.class), eq(expectedTeamNode1));
        verify(mockTeamMapper).map(any(DefaultMutableTeam.class), eq(expectedTeamNode2));
    }

    @Test
    public void create_members_delegated_to_member_factory() {
        List<Person> expectedMembers = mock(List.class);
        when(mockMembersFactory.createMembers(any(List.class))).thenReturn(expectedMembers);

        List<TeamDTO> dtos = asList(mock(TeamDTO.class));
        teamFactory.createTeams(dtos);

        verify(mockTeamMapper).map(
            argThat(is(aMutableTeam()
                .whoseMembersAre(
                    sameInstance(expectedMembers)))
            ), any(Node.class)
        );
    }
}