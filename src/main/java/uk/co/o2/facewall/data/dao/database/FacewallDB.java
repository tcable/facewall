package uk.co.o2.facewall.data.dao.database;

import uk.co.o2.facewall.data.QueryEngine;
import uk.co.o2.facewall.data.dao.database.query.DatabaseQuery;
import uk.co.o2.facewall.data.dao.database.query.DatabaseQueryBuilder;

public class FacewallDB {

    private final QueryEngine cypherQueryExecutionEngine;

    public FacewallDB(QueryEngine cypherQueryExecutionEngine) {
        this.cypherQueryExecutionEngine = cypherQueryExecutionEngine;
    }

    public Iterable<QueryResultRow> query(DatabaseQueryBuilder queryBuilder) {
        DatabaseQuery query = queryBuilder.build();
        return query.execute(cypherQueryExecutionEngine);
    }
}