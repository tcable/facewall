package uk.co.o2.facewall.data.dao.database.query;

import uk.co.o2.facewall.data.QueryEngine;
import uk.co.o2.facewall.data.dao.database.QueryResultRow;

public interface DatabaseQuery {

//    Iterable<QueryResultRow> execute(QueryEngine queryEngine);

    Iterable<QueryResultRow> execute(QueryEngine cypherQueryExecutionEngine);

}
