package facade;

import data.PersonRepository;
import domain.PersonStub;
import domain.StubbedTeam;
import domain.Person;
import domain.StubbedTeam;
import domain.Team;
import facade.modelmapper.OverviewModelMapper;
import model.OverviewEntryModel;
import model.OverviewEntryModelMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static data.datatype.PersonId.newPersonId;
import static domain.NoTeam.noTeam;
import static model.OverviewEntryModelMatcher.anOverviewEntryModel;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static util.IterableMatchers.containsExhaustivelyInOrder;

@RunWith(MockitoJUnitRunner.class)
public class OverviewFacadeTest {
    @Mock PersonRepository mockRepo;
    private OverviewFacade overviewFacade;

    @Before
    public void setUp() throws Exception {
        overviewFacade = new OverviewFacade(mockRepo, new OverviewModelMapper());
    }

    @Test
    public void map_repo_to_domain_objects_to_overview_model_test() {
        PersonStub ecom_member1 = new PersonStub(newPersonId("3"), "ecom_member1", "pic1.img", "email1@testemail.com", "BA", null);
        PersonStub ecom_member2 = new PersonStub(newPersonId("4"), "ecom_member2", "pic2.img", "email2@testemail.com", "BA", null);
        PersonStub pr_member    = new PersonStub(newPersonId("5"), "pr_member", "pic3.img", "email3@testemail.com", "BA", null);

        Team ecom = new StubbedTeam("ecom", "blue", new ArrayList<Person>(Arrays.asList(ecom_member1, ecom_member2)));
        Team productResources = new StubbedTeam("productResources", "green", new ArrayList<Person>(Arrays.asList(pr_member)));

        ecom_member1.setTeam(ecom);
        ecom_member2.setTeam(ecom);
        pr_member.setTeam(productResources);

        List<Person> persons = new ArrayList<Person>(Arrays.asList(ecom_member1, ecom_member2, pr_member));
        when(mockRepo.listPersons()).thenReturn(persons);

        OverviewEntryModelMatcher ecom1Matcher = anOverviewEntryModel().withTeamHeader("ecom").named("ecom_member1");
        OverviewEntryModelMatcher ecom2Matcher = anOverviewEntryModel().withTeamHeader("ecom").named("ecom_member2");
        OverviewEntryModelMatcher productResourcesMatcher = anOverviewEntryModel().withTeamHeader("productResources").named("pr_member");

        List<OverviewEntryModel> result = overviewFacade.createOverviewModel();
        assertThat(result, containsExhaustivelyInOrder(ecom1Matcher, ecom2Matcher, productResourcesMatcher));

        verify(mockRepo).listPersons();
    }

    @Test
    public void orders_overview_alphabetically_by_name_when_same_team(){
        PersonStub ecom_member1 = new PersonStub(newPersonId("3"), "bob", "pic1.img", "email1@testemail.com", "BA", null);
        PersonStub ecom_member2 = new PersonStub(newPersonId("4"), "dave", "pic2.img", "email2@testemail.com", "BA", null);
        PersonStub ecom_member3 = new PersonStub(newPersonId("4"), "dave2", "pic2.img", "email3@testemail.com", "BA", null);
        PersonStub ecom_member4 = new PersonStub(newPersonId("7"), "rick", "pic5.img", "email4@testemail.com", "BA", null);

        Team ecom = new StubbedTeam("ecom", "blue", new ArrayList<Person>(Arrays.asList(ecom_member1)));

        ecom_member1.setTeam(ecom);
        ecom_member2.setTeam(ecom);
        ecom_member3.setTeam(ecom);
        ecom_member4.setTeam(ecom);

        List<Person> persons = new ArrayList<Person>(Arrays.asList(ecom_member3, ecom_member2, ecom_member1, ecom_member4));
        when(mockRepo.listPersons()).thenReturn(persons);

        OverviewEntryModelMatcher ecom1Matcher = anOverviewEntryModel().withTeamHeader("ecom").named("bob");
        OverviewEntryModelMatcher ecom2Matcher = anOverviewEntryModel().withTeamHeader("ecom").named("dave");
        OverviewEntryModelMatcher ecom3Matcher = anOverviewEntryModel().withTeamHeader("ecom").named("dave2");
        OverviewEntryModelMatcher ecom4Matcher = anOverviewEntryModel().withTeamHeader("ecom").named("rick");

        List<OverviewEntryModel> result = overviewFacade.createOverviewModel();
        assertThat(result, containsExhaustivelyInOrder(ecom1Matcher, ecom2Matcher, ecom3Matcher, ecom4Matcher));

        verify(mockRepo).listPersons();

    }

    @Test
    public void orders_overviews_alphabetically_by_team_test() {
        PersonStub ecom_member1 = new PersonStub(newPersonId("3"), "ecom_member1", "pic1.img", "email1@testemail.com", "BA", null);
        PersonStub ecom_member2 = new PersonStub(newPersonId("7"), "ecom_member2", "pic5.img", "email2@testemail.com", "BA", null);
        PersonStub pr_member = new PersonStub(newPersonId("4"), "pr_member", "pic2.img", "email3@testemail.com", "BA", null);

        Team ecom = new StubbedTeam("ecom", "blue", new ArrayList<Person>(Arrays.asList(ecom_member1)));
        Team productResources = new StubbedTeam("productResources", "green", new ArrayList<Person>(Arrays.asList(pr_member)));

        ecom_member1.setTeam(ecom);
        ecom_member2.setTeam(ecom);
        pr_member.setTeam(productResources);

        List<Person> persons = new ArrayList<Person>(Arrays.asList(ecom_member2, pr_member, ecom_member1));
        when(mockRepo.listPersons()).thenReturn(persons);

        OverviewEntryModelMatcher ecom1Matcher = anOverviewEntryModel().withTeamHeader("ecom").named("ecom_member1");
        OverviewEntryModelMatcher ecom2Matcher = anOverviewEntryModel().withTeamHeader("ecom").named("ecom_member2");
        OverviewEntryModelMatcher productResourcesMatcher = anOverviewEntryModel().withTeamHeader("productResources").named("pr_member");

        List<OverviewEntryModel> result = overviewFacade.createOverviewModel();
        assertThat(result, containsExhaustivelyInOrder(ecom1Matcher, ecom2Matcher, productResourcesMatcher));

        verify(mockRepo).listPersons();
    }

    @Test
    public void orders_overview_alphabetically_by_team_with_teamless_last_test() {
        PersonStub ecom_member1 = new PersonStub(newPersonId("3"), "ecom_member1", "pic1.img", "email1@testemail.com", "BA", null);
        PersonStub ecom_member2 = new PersonStub(newPersonId("7"), "ecom_member2", "pic5.img", "email2@testemail.com", "BA", null);
        PersonStub pr_member = new PersonStub(newPersonId("4"), "pr_member", "pic2.img", "email3@testemail.com", "BA", null);
        PersonStub teamless_member1 = new PersonStub(newPersonId("5"), "teamless_member1", "pic3.img", "email4@testemail.com", "BA", null);
        PersonStub teamless_member2 = new PersonStub(newPersonId("6"), "teamless_member2", "pic4.img", "email5@testemail.com", "BA", null);

        Team ecom = new StubbedTeam("ecom", "blue", new ArrayList<Person>(Arrays.asList(ecom_member1)));
        Team productResources = new StubbedTeam("productResources", "green", new ArrayList<Person>(Arrays.asList(pr_member)));

        ecom_member1.setTeam(ecom);
        ecom_member2.setTeam(ecom);
        pr_member.setTeam(productResources);
        teamless_member1.setTeam(noTeam());
        teamless_member2.setTeam(noTeam());

        List<Person> persons = new ArrayList<Person>(Arrays.asList(teamless_member1, ecom_member1, teamless_member2, ecom_member2, pr_member));
        when(mockRepo.listPersons()).thenReturn(persons);

        OverviewEntryModelMatcher ecom1Matcher = anOverviewEntryModel().withTeamHeader("ecom").named("ecom_member1");
        OverviewEntryModelMatcher ecom2Matcher = anOverviewEntryModel().withTeamHeader("ecom").named("ecom_member2");
        OverviewEntryModelMatcher productResourcesMatcher = anOverviewEntryModel().withTeamHeader("productResources").named("pr_member");
        OverviewEntryModelMatcher teamless1Matcher = anOverviewEntryModel().withTeamHeader("").named("teamless_member1");
        OverviewEntryModelMatcher teamless2Matcher = anOverviewEntryModel().withTeamHeader("").named("teamless_member2");

        List<OverviewEntryModel> result = overviewFacade.createOverviewModel();
        assertThat(result, containsExhaustivelyInOrder(ecom1Matcher, ecom2Matcher, productResourcesMatcher, teamless1Matcher, teamless2Matcher));

        verify(mockRepo).listPersons();
    }
}