package uk.co.o2.facewall.data.dto;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.test.TestGraphDatabaseFactory;

import java.util.HashMap;

import static uk.co.o2.facewall.data.dao.MockNodeFactory.createMockNodeWithProperties;
import static uk.co.o2.facewall.data.datatype.TeamId.newTeamId;
import static uk.co.o2.facewall.data.dto.TeamInformation.noTeamInformation;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TeamInformationMapperTest {

    protected GraphDatabaseService graphDb;
    private final TeamInformationMapper teamInformationMapper = new TeamInformationMapper();

    @Before
    public void prepareTestDatabase() {
        graphDb = new TestGraphDatabaseFactory().newImpermanentDatabase();
    }

    @After
    public void destroyTestDatabase() {
        graphDb.shutdown();
    }

    @Test
    public void map_id() throws Exception {
        Transaction tx = graphDb.beginTx();
        Node mockNode = null;
        try {
            mockNode = graphDb.createNode();
            mockNode.setProperty("id", "some-id");
            tx.success();
        } finally {
            tx.close();
        }

        TeamInformation result = teamInformationMapper.map(mockNode);
        assertThat(result.getId(), is(newTeamId("some-id")));
    }

    @Test
    public void map_name() throws Exception {
        Transaction tx = graphDb.beginTx();
        Node mockNode = null;
        try {
            mockNode = graphDb.createNode();
            mockNode.setProperty("name", "blinky's team");
            tx.success();
        } finally {
            tx.close();
        }

        TeamInformation result = teamInformationMapper.map(mockNode);
        assertThat(result.getName(), is("blinky's team"));
    }

    @Test
    public void map_picture() throws Exception {
        Transaction tx = graphDb.beginTx();
        Node mockNode = null;
        try {
            mockNode = graphDb.createNode();
            mockNode.setProperty("colour", "blue");
            tx.success();
        } finally {
            tx.close();
        }

        TeamInformation result = teamInformationMapper.map(mockNode);
        assertThat(result.getColour(), is("blue"));
    }

    @Test
    public void map_null_to_no_team_information() throws Exception {
        TeamInformation result = teamInformationMapper.map(null);

        assertThat(result, is(sameInstance(noTeamInformation())));
    }
}
