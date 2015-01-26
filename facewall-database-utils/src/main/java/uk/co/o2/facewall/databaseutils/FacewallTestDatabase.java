package uk.co.o2.facewall.databaseutils;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.graphdb.index.IndexManager;
import uk.co.o2.facewall.data.QueryEngine;
import uk.co.o2.facewall.databaseutils.fixture.Fixtures;
import uk.co.o2.facewall.databaseutils.fixture.PersonData;
import uk.co.o2.facewall.databaseutils.fixture.TeamData;
import uk.co.o2.facewall.databaseutils.util.ForwardingGraphDatabaseService;

import java.util.Map;
import java.util.NoSuchElementException;

import static uk.co.o2.facewall.databaseutils.DatabaseOperations.clearDatabaseOperation;
import static uk.co.o2.facewall.databaseutils.DatabaseOperations.initialiseDatabaseOperation;
import static uk.co.o2.facewall.databaseutils.config.FacewallDatabaseConfiguration.IndexConfiguration.Persons;
import static uk.co.o2.facewall.databaseutils.config.FacewallDatabaseConfiguration.IndexConfiguration.Teams;
import static uk.co.o2.facewall.databaseutils.config.FacewallDatabaseConfiguration.MEMBER_OF;
import static uk.co.o2.facewall.databaseutils.util.QueryEngineAdaptor.createQueryEngineAdaptor;

public class FacewallTestDatabase extends ForwardingGraphDatabaseService {

    FacewallTestDatabase(GraphDatabaseService backingDatabase) {
        super(backingDatabase);
    }

    public void clear() {
        clearDatabaseOperation.execute(this);
    }

    public void initialise() {
        initialiseDatabaseOperation.execute(this);
    }

    public void seedFixtures(final Fixtures.Builder fixturesBuilder) {
        DatabaseOperation seedFixturesOperation = new DatabaseOperation() {
            @Override protected void performOperation(GraphDatabaseService db) {
                Fixtures fixtures = fixturesBuilder.build();

                IndexManager indexManager = db.index();
                Index<Node> personIdIndex = indexManager.forNodes(Persons.indexName);
                Index<Node> teamIdIndex = indexManager.forNodes(Teams.indexName);

                for (TeamData teamData : fixtures.teams) {
                    Node teamNode = db.createNode();
                    copyData(teamData, teamNode);

                    teamIdIndex.add(teamNode, Teams.key, teamData.get(Teams.key));

                    for (PersonData personData : teamData.members) {
                        Node personNode = db.createNode();
                        copyData(personData, personNode);

                        personNode.createRelationshipTo(teamNode, MEMBER_OF);
                        personIdIndex.add(personNode, Persons.key, personData.get(Persons.key));
                    }
                }

                for (PersonData personData : fixtures.teamlessPersons) {
                    Node personNode = db.createNode();
                    copyData(personData, personNode);

                    personIdIndex.add(personNode, Persons.key, personData.get(Persons.key));
                }
            }

            private void copyData(Map<String, Object> data, Node node) {
                for (Map.Entry<String, Object> entry : data.entrySet()) {
                    node.setProperty(entry.getKey(), entry.getValue());
                }
            }
        };

        seedFixturesOperation.execute(this);
    }

    public QueryEngine createQueryEngine() {
        ExecutionEngine executionEngine = new ExecutionEngine(this.backingDatabase);
        return createQueryEngineAdaptor(executionEngine);
    }

    public Node findPersonById(String personId) {
        Transaction tx = this.beginTx();
        try {
            IndexHits<Node> hits = index().forNodes(Persons.indexName).query(Persons.key, personId);
            Node personNode = hits.getSingle();
            if (personNode != null) {
                tx.success();
                return personNode;
            } else {
                throw new NoSuchElementException("no person with that id");
            }
        } finally {
            tx.close();
        }
    }
}
