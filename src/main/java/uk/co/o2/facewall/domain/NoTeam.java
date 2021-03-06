package uk.co.o2.facewall.domain;

import java.util.List;

import static java.util.Collections.emptyList;

public class NoTeam implements Team {

    private static final NoTeam noTeam = new NoTeam();

    private NoTeam() {}

    public static Team noTeam() {
        return noTeam;
    }

    @Override public String name() {
        return "";
    }

    @Override public String colour() {
        return "";
    }

    @Override public List<Person> members() {
        return emptyList();
    }

    @Override public void addMember(Person member) {
        throw new UnsupportedOperationException();
    }
}
