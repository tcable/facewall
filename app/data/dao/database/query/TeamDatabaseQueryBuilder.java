package data.dao.database.query;

import data.datatype.TeamId;
import domain.datatype.QueryString;

import java.util.HashMap;
import java.util.Map;

public class TeamDatabaseQueryBuilder implements DatabaseQueryBuilder {

    private final FacewallQueryResultsMapper queryResultsMapper;
    private String id = "*";
    private Map<String, String> propertyCriteria = new HashMap<>();

    TeamDatabaseQueryBuilder(FacewallQueryResultsMapper queryResultsMapper) {
        this.queryResultsMapper = queryResultsMapper;
    }

    public DatabaseQuery build() {
        return new TeamDatabaseQuery(queryResultsMapper, id, propertyCriteria);
    }

    public TeamDatabaseQueryBuilder withId(TeamId id) {
        this.id = id.value;
        return this;
    }

    public TeamDatabaseQueryBuilder named(QueryString queryString) {
        propertyCriteria.put("name", queryString.value);
        return this;
    }
}
