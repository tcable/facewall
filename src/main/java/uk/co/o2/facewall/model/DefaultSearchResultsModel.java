package uk.co.o2.facewall.model;

import java.util.List;

public class DefaultSearchResultsModel implements SearchResultsModel {
    public List<PersonSearchResult> persons;
    public List<TeamSearchResult> teams;

    public DefaultSearchResultsModel(List<PersonSearchResult> persons, List<TeamSearchResult> teams) {
        this.persons = persons;
        this.teams = teams;
    }

    public DefaultSearchResultsModel(List<PersonSearchResult> persons) {
        this.persons = persons;
    }
}