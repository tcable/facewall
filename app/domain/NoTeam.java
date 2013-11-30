package domain;

import java.util.List;

import static java.util.Collections.emptyList;

public class NoTeam implements Team {

    private NoTeam() {}

    public static Team noTeam() {
        return new NoTeam();
    }

    @Override public String id() {
        throw new UnsupportedOperationException("id method is no longer supported");
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
}