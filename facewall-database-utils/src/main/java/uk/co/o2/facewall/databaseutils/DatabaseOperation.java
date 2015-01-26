package uk.co.o2.facewall.databaseutils;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

abstract class DatabaseOperation {

    DatabaseOperation() {}

    abstract protected void performOperation(GraphDatabaseService db);

    public final void execute(GraphDatabaseService db) {
        //TODO work out why this messes heroku up
        Transaction tx = db.beginTx();
        try {
            performOperation(db);

            tx.success();
        } finally {
            tx.close();
        }
    }
}
