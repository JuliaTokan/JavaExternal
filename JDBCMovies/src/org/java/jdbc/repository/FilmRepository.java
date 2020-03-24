package org.java.jdbc.repository;

import org.java.jdbc.entity.Actor;
import org.java.jdbc.entity.Film;
import org.java.jdbc.specification.Specification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.postgresql.util.JdbcBlackHole.close;

public class FilmRepository extends AbstractRepository<Film> {
    private static final String SELECT_QUERY = "SELECT * FROM film ";
    private static final String INSERT_QUERY = "INSERT INTO film (name, releasedate, country) VALUES(?, ?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM film";

    private static final String INSERT_ACTOR_TO_FILM = "INSERT INTO film_actor VALUES(?, ?, ?)";

    public FilmRepository(Connection connection) {
        super(connection);
    }

    @Override
    public void add(Film film) {
        try {
            try (PreparedStatement st = connection
                    .prepareStatement(INSERT_QUERY)) {
                st.setString(1, film.getName());
                st.setDate(2, new java.sql.Date(film.getReleaseDate().getTime()));
                st.setString(3, film.getCountry());
                st.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void remove(Specification specification) {
        String query = DELETE_QUERY + specification.toSql();
        List<Object> params = specification.getParameters();

        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(query);
            st = setParamDate(st, params);
            st.execute();
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
    }

    @Override
    public List<Film> query(Specification specification) {
        String query = SELECT_QUERY + specification.toSql();
        List<Object> params = specification.getParameters();

        List<Film> films = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(query);
            st = setParamDate(st, params);
            ResultSet resultSet =
                    st.executeQuery();
            films = getListFromResultSet(resultSet);
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return films;
    }

    @Override
    public List<Film> getAll() {
        List<Film> films = new ArrayList<>();
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet =
                    st.executeQuery(SELECT_QUERY);
            films = getListFromResultSet(resultSet);
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return films;
    }

    public void addActorToFilm(Actor actor, Film film, Boolean isDirector) {
        try {
            PreparedStatement st = connection.prepareStatement(INSERT_ACTOR_TO_FILM);
            try {
                st.setLong(1, film.getId());
                st.setLong(2, actor.getId());
                st.setBoolean(3, isDirector);
                st.executeUpdate();
            } finally {
                st.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public PreparedStatement setParamDate(PreparedStatement ps, List<Object> params) throws SQLException {
        for (int i = 0; i<params.size(); i++)
            ps.setDate(i+1, (Date) params.get(i));
        return ps;
    }

    private List<Film> getListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Film> films = new ArrayList<>();
        while (resultSet.next()) {
            Film film = new Film();
            film.setId(resultSet.getLong("id"));
            film.setName(resultSet.getString("name"));
            film.setReleaseDate(resultSet.getDate("releaseDate"));
            film.setCountry(resultSet.getString("country"));

            films.add(film);
        }
        return films;
    }
}
