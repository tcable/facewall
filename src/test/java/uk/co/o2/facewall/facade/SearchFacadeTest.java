package uk.co.o2.facewall.facade;

import uk.co.o2.facewall.data.PersonRepository;
import uk.co.o2.facewall.data.TeamRepository;
import uk.co.o2.facewall.domain.Person;
import uk.co.o2.facewall.domain.Query;
import uk.co.o2.facewall.domain.Team;
import uk.co.o2.facewall.facade.modelmapper.PersonDetailsModelMapper;
import uk.co.o2.facewall.facade.modelmapper.SearchResultsModelMapper;
import uk.co.o2.facewall.facade.modelmapper.TeamDetailsModelMapper;
import uk.co.o2.facewall.model.DefaultSearchResultsModel;
import uk.co.o2.facewall.model.PersonDetailsModel;
import uk.co.o2.facewall.model.SearchResultsModel;
import uk.co.o2.facewall.model.TeamDetailsModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchFacadeTest {
    @Mock PersonRepository mockPersonRepo;
    @Mock TeamRepository mockTeamRepo;
    @Mock SearchResultsModelMapper mockSearchResultsModelMapper;
    @Mock PersonDetailsModelMapper mockPersonDetailsModelMapper;
    @Mock TeamDetailsModelMapper teamDetailsModelMapper;

    @InjectMocks
    SearchFacade searchFacade;

    @Test
    public void find_persons_and_teams_query_from_web_then_map_to_search_results_model_test() {
        Query query = mock(Query.class);
        Person mockPerson = mock(Person.class);
        Team mockTeam = mock(Team.class);

        when(mockPersonRepo.queryPersons(query)).thenReturn(new ArrayList<>(Arrays.asList(mockPerson, mockPerson)));
        when(mockTeamRepo.queryTeams(query)).thenReturn(new ArrayList<>(Arrays.asList(mockTeam)));

        DefaultSearchResultsModel expectedResult = mock(DefaultSearchResultsModel.class);
        when(mockSearchResultsModelMapper.map(new ArrayList<>(Arrays.asList(mockPerson, mockPerson)), new ArrayList<>(Arrays.asList(mockTeam))))
                .thenReturn(expectedResult);

        SearchResultsModel result = searchFacade.createSearchResultsModel(query);
        assertEquals("Expected: " + expectedResult + "/nGot: " + result + ".", expectedResult, result);

        verify(mockPersonRepo).queryPersons(query);
        verify(mockTeamRepo).queryTeams(query);
        verify(mockSearchResultsModelMapper).map(new ArrayList<>(Arrays.asList(mockPerson, mockPerson)), new ArrayList<>(Arrays.asList(mockTeam)));
    }
}
