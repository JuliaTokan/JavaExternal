package org.java.jdbc.repository;

import org.java.jdbc.entity.Actor;
import org.java.jdbc.entity.Film;
import org.java.jdbc.specification.Specification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.postgresql.util.JdbcBlackHole.close;

public class ActorRepository extends AbstractRepository<Actor> {
    private static final String SELECT_QUERY = "SELECT * FROM actor ";
    private static final String INSERT_QUERY = "INSERT INTO actor (name, birthdate) VALUES(?, ?)";

    public ActorRepository(Connection connection) {
        super(connection);
    }

    @Override
    public void add(Actor actor) {
        try {
            try (PreparedStatement st = connection
                    .prepareStatement(INSERT_QUERY)) {
                st.setString(1, actor.getName());
                st.setDate(2, new java.sql.Date(actor.getBirthDate().getTime()));
                st.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void remove(Specification specification) {

    }

    @Override
    public List<Actor> query(Specification specification) {
        String query = SELECT_QUERY + specification.toSql();
        List<Object> params = specification.getParameters();

        List<Actor> actors = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(query);
            st = setParam(st, params);
            ResultSet resultSet =
                    st.executeQuery();
            actors = getListFromResultSet(resultSet);
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return actors;
    }

    @Override
    public List<Actor> getAll() {
        List<Actor> actors = new ArrayList<>();
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet =
                    st.executeQuery(SELECT_QUERY);
            actors = getListFromResultSet(resultSet);
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return actors;
    }

    private PreparedStatement setParam(PreparedStatement st, List<Object> params) throws SQLException {
        for (int i = 0; i<params.size(); i++)
            st.setLong(i+1, (Long) params.get(i));
        return st;
    }

    private List<Actor> getListFromResultSet (ResultSet resultSet) throws SQLException {
        List<Actor> actors = new ArrayList<>();

        while (resultSet.next()) {
            Actor actor = new Actor();

            actor.setId(resultSet.getLong("id"));
            actor.setName(resultSet.getString("name"));
            actor.setBirthDate(resultSet.getDate("birthDate"));

            actors.add(actor);
        }

        return actors;
    }
}
