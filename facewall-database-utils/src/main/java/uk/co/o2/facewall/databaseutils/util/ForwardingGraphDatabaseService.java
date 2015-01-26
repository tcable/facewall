package uk.co.o2.facewall.databaseutils.util;

import org.neo4j.graphdb.*;
import org.neo4j.graphdb.event.KernelEventHandler;
import org.neo4j.graphdb.event.TransactionEventHandler;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.graphdb.schema.Schema;
import org.neo4j.graphdb.traversal.BidirectionalTraversalDescription;
import org.neo4j.graphdb.traversal.TraversalDescription;

public abstract class ForwardingGraphDatabaseService implements GraphDatabaseService {

    protected final GraphDatabaseService backingDatabase;

    protected ForwardingGraphDatabaseService(GraphDatabaseService backingDatabase) {
        this.backingDatabase = backingDatabase;
    }

    @Override final public Node createNode() {
        return backingDatabase.createNode();
    }

    @Override final public Node getNodeById(long id) {
        return backingDatabase.getNodeById(id);
    }

    @Override public Relationship getRelationshipById(long id) {
        return backingDatabase.getRelationshipById(id);
    }

    @Override public Iterable<Node> getAllNodes() {
        return backingDatabase.getAllNodes();
    }

    @Override public Iterable<RelationshipType> getRelationshipTypes() {
        return backingDatabase.getRelationshipTypes();
    }

    @Override public boolean isAvailable(long timeout) {
        return backingDatabase.isAvailable(timeout);
    }

    @Override public void shutdown() {
        backingDatabase.shutdown();
    }

    @Override public Transaction beginTx() {
        return backingDatabase.beginTx();
    }

    @Override
    public <T> TransactionEventHandler<T> registerTransactionEventHandler(TransactionEventHandler<T> handler) {
        return backingDatabase.registerTransactionEventHandler(handler);
    }

    @Override
    public <T> TransactionEventHandler<T> unregisterTransactionEventHandler(TransactionEventHandler<T> handler) {
        return backingDatabase.unregisterTransactionEventHandler(handler);
    }

    @Override public KernelEventHandler registerKernelEventHandler(KernelEventHandler handler) {
        return backingDatabase.registerKernelEventHandler(handler);
    }

    @Override public KernelEventHandler unregisterKernelEventHandler(KernelEventHandler handler) {
        return backingDatabase.unregisterKernelEventHandler(handler);
    }

    @Override public IndexManager index() {
        return backingDatabase.index();
    }

    @Override
    public Node createNode(Label... labels) {
        return backingDatabase.createNode(labels);
    }

    @Override
    public ResourceIterable<Node> findNodesByLabelAndProperty(Label label, String key, Object value) {
        return backingDatabase.findNodesByLabelAndProperty(label,key,value);
    }

    @Override
    public Schema schema() {
        return backingDatabase.schema();
    }

    @Override
    public TraversalDescription traversalDescription() {
        return backingDatabase.traversalDescription();
    }

    @Override
    public BidirectionalTraversalDescription bidirectionalTraversalDescription() {
        return backingDatabase.bidirectionalTraversalDescription();
    }
}
