package uk.co.o2.facewall.databaseutils.util;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import uk.co.o2.facewall.data.QueryEngine;

import java.util.Iterator;
import java.util.Map;

public class QueryEngineAdaptor implements QueryEngine {

    private final ExecutionEngine engine;

    private QueryEngineAdaptor(ExecutionEngine engine) {
        this.engine = engine;
    }

    public static QueryEngine createQueryEngineAdaptor(ExecutionEngine executionEngine) {
        return new QueryEngineAdaptor(executionEngine);
    }

    @Override public Iterator<Map<String, Object>> query(String statement, Map<String, Object> params) {
        ExecutionResult result = engine.execute(statement, params);

        return result.iterator();
    }
}
