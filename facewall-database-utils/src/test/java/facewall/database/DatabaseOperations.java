package facewall.database;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.IndexManager;

import static facewall.database.config.FacewallDatabaseConfiguration.IndexConfiguration.Persons_Id;
import static facewall.database.config.FacewallDatabaseConfiguration.IndexConfiguration.Teams_Id;

abstract class DatabaseOperations {

    private DatabaseOperations() {}

    public static final DatabaseOperation clearDatabaseOperation = new DatabaseOperation() {
        @Override public void performOperation(GraphDatabaseService db) {

            //This is deprecated on the GraphDatabaseService interface,
            // but the alternative is not supported by implementation (RestGraphDatabase)
            for (Node node : db.getAllNodes()) {
                for (Relationship relationship : node.getRelationships()) {
                    relationship.delete();
                }

                boolean notTheRootNode = node.getId() != 0;
                if (notTheRootNode) {
                    node.delete();
                }
            }

            IndexManager indexManager = db.index();
            for (String indexName : indexManager.nodeIndexNames()) {
                indexManager.forNodes(indexName).delete();
            }

            for (String indexName : indexManager.relationshipIndexNames()) {
                indexManager.forRelationships(indexName).delete();
            }
        }
    };

    public static final DatabaseOperation initialiseDatabaseOperation = new DatabaseOperation() {
        @Override protected void performOperation(GraphDatabaseService db) {
            IndexManager indexManager = db.index();

            indexManager.forNodes(Persons_Id.indexName);
            indexManager.forNodes(Teams_Id.indexName);
        }
    };
}