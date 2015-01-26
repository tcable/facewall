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
import static uk.co.o2.facewall.data.datatype.PersonId.newPersonId;
import static uk.co.o2.facewall.data.dto.PersonInformation.noPersonInformation;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PersonInformationMapperTest {

    protected GraphDatabaseService graphDb;
    private PersonInformation result;
    private final PersonInformationMapper personInformationMapper = new PersonInformationMapper();

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

        result = personInformationMapper.map(mockNode);
        assertThat(result.getId(), is(newPersonId("some-id")));
    }

    @Test
    public void map_name() throws Exception {
        Transaction tx = graphDb.beginTx();
        Node mockNode = null;
        try{
            mockNode = graphDb.createNode();
            mockNode.setProperty("name", "blinky");
            tx.success();
        } finally {
            tx.close();
        }

        result = personInformationMapper.map(mockNode);
        assertThat(result.getName(), is("blinky"));
    }

    @Test
    public void map_picture() throws Exception {
        Transaction tx = graphDb.beginTx();
        Node mockNode = null;
        try{
            mockNode = graphDb.createNode();
            mockNode.setProperty("picture", "blinky.img");
            tx.success();
        } finally {
            tx.close();
        }

        result = personInformationMapper.map(mockNode);
        assertThat(result.getPicture(), is("blinky.img"));
    }

    @Test
    public void map_email() throws Exception {
        Transaction tx = graphDb.beginTx();
        Node mockNode = null;
        try{
            mockNode = graphDb.createNode();
            mockNode.setProperty("email", "email@testemail.com");
            tx.success();
        } finally {
            tx.close();
        }

        result = personInformationMapper.map(mockNode);
        assertThat(result.getEmail(), is("email@testemail.com"));
    }

    @Test
    public void map_role() throws Exception {
        Transaction tx = graphDb.beginTx();
        Node mockNode = null;
        try{
            mockNode = graphDb.createNode();
            mockNode.setProperty("role", "baker");
            tx.success();
        } finally {
            tx.close();
        }

        result = personInformationMapper.map(mockNode);
        assertThat(result.getRole(), is("baker"));
    }

    @Test
    public void map_null_to_no_person_information() throws Exception {
        result = personInformationMapper.map(null);

        assertThat(result, is(sameInstance(noPersonInformation())));
    }
}
