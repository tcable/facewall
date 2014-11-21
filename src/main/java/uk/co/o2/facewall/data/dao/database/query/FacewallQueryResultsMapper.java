package uk.co.o2.facewall.data.dao.database.query;

import uk.co.o2.facewall.data.dao.database.QueryResultRow;
import uk.co.o2.facewall.data.dto.PersonInformation;
import uk.co.o2.facewall.data.dto.PersonInformationMapper;
import uk.co.o2.facewall.data.dto.TeamInformation;
import uk.co.o2.facewall.data.dto.TeamInformationMapper;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class FacewallQueryResultsMapper {

    private final PersonInformationMapper personInformationMapper;
    private final TeamInformationMapper teamInformationMapper;

    public FacewallQueryResultsMapper(PersonInformationMapper personInformationMapper, TeamInformationMapper teamInformationMapper) {
        this.personInformationMapper = personInformationMapper;
        this.teamInformationMapper = teamInformationMapper;
    }

    public Iterable<QueryResultRow> map(PersonNodeKey personNodeKey, TeamNodeKey teamNodeKey, Iterator<Map<String, Object>> cypherQueryResults) {
        FacewallQueryResults results = new FacewallQueryResults();

        while ( cypherQueryResults.hasNext() ) {
            Map<String, Object> cypherQueryResultRow = cypherQueryResults.next();
            PersonInformation personInformation = personInformationMapper.map((TreeMap) cypherQueryResultRow.get(personNodeKey.value));
            TeamInformation teamInformation = teamInformationMapper.map((TreeMap) cypherQueryResultRow.get(teamNodeKey.value));

            results.add(personInformation, teamInformation);
        }
        return results;
    }
}
