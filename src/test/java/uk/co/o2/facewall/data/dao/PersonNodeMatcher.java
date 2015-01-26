package uk.co.o2.facewall.data.dao;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import uk.co.o2.facewall.data.dao.database.RelationshipTypes;
import uk.co.o2.facewall.util.CompositeMatcher;

public class PersonNodeMatcher extends CompositeMatcher<Node>{
    private PersonNodeMatcher(){
        super();
    }

    public static PersonNodeMatcher aPersonNode() {
        return new PersonNodeMatcher();
    }

    public PersonNodeMatcher withId(final String id) {
        add(new TypeSafeMatcher<Node>() {

            @Override
            public boolean matchesSafely(Node target) {
                return id.equals(getProp(target, "id"));
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with an id of ").appendValue(id);
            }
        });
        return this;
    }

    public PersonNodeMatcher withName(final String name) {
        add(new TypeSafeMatcher<Node>() {

            @Override
            public boolean matchesSafely(Node target) {
                return name.equals(getProp(target, "name"));
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("named ").appendValue(name);
            }
        });
        return this;
    }

    public PersonNodeMatcher withPicture(final String picture) {
        add(new TypeSafeMatcher<Node>() {

            @Override
            public boolean matchesSafely(Node target) {
                return picture.equals(getProp(target, "picture"));
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("whose picture is at ").appendValue(picture);
            }
        });
        return this;
    }

    public PersonNodeMatcher withEmail(final String email) {
        add(new TypeSafeMatcher<Node>() {

            @Override
            public boolean matchesSafely(Node target) {
                return email.equals(getProp(target, "email"));
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("whose email is at ").appendValue(email);
            }
        });
        return this;
    }

    public PersonNodeMatcher locatedIn(final String location) {
        add(new TypeSafeMatcher<Node>() {

            @Override
            public boolean matchesSafely(Node target) {
                return location.equals(getProp(target, "location"));
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("located in ").appendValue(location);
            }
        });
        return this;
    }

    public PersonNodeMatcher relatedToTeamNode(final Matcher<Node> teamNode) {
        add(new TypeSafeMatcher<Node>() {

            @Override
            public boolean matchesSafely(Node target) {
                Transaction tx = target.getGraphDatabase().beginTx();
                try {
                    Relationship teamMemberRelationship = target.getSingleRelationship(RelationshipTypes.TEAMMEMBER_OF, Direction.OUTGOING);
                    Node otherNode = teamMemberRelationship.getOtherNode(target);
                    tx.success();
                    return teamNode.matches(otherNode);
                } finally {
                    tx.close();
                }
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("in the team: ").appendDescriptionOf(teamNode);
            }
        });
        return this;
    }

    private Object getProp(Node target, String property) {
        Transaction tx = target.getGraphDatabase().beginTx();
        try {
            Object obj = target.getProperty(property);
            tx.success();
            return obj;
        } finally {
            tx.close();
        }
    }
}
