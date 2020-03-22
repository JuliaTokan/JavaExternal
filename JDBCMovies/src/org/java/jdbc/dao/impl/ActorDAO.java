package org.java.jdbc.dao.impl;

import org.java.jdbc.dao.AbstractDAO;
import org.java.jdbc.dao.IActorDAO;
import org.java.jdbc.entity.Actor;
import org.java.jdbc.entity.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorDAO extends AbstractDAO implements IActorDAO {
    public static final String SQL_SELECT_ALL_ACTORS = "SELECT * FROM actor";
    public static final String SQL_INSERT_ACTOR = "INSERT INTO actor (name, birthdate) VALUES(?, ?)";

    public static final String SQL_SELECT_ACTORS_BY_FILM = "SELECT actor.id, actor.name, actor.birthdate FROM film_actor INNER JOIN actor ON film_actor.id_actor = actor.id WHERE film_actor.id_film = ?";
    public static final String SQL_SELECT_ACTORS_STARRED_IN_FILMS_MORE_THAN= "SELECT actor.id, actor.name, actor.birthdate FROM film_actor INNER JOIN actor ON film_actor.id_actor = actor.id GROUP BY actor.id HAVING COUNT(film_actor.id_film) >= ?";
    public static final String SQL_SELECT_ACTORS_ARE_DIRECTORS = "SELECT DISTINCT actor.id, actor.name, actor.birthdate FROM film_actor INNER JOIN actor ON film_actor.id_actor = actor.id WHERE film_actor.is_director = true";

    public ActorDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void init() {

    }

    @Override
    public void add(Actor actor) {
        try {
            try (PreparedStatement st = connection
                    .prepareStatement(SQL_INSERT_ACTOR)) {
                st.setString(1, actor.getName());
                st.setDate(2, new java.sql.Date(actor.getBirthDate().getTime()));
                st.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Actor> getAll() {
        List<Actor> actors = new ArrayList<>();
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet =
                    st.executeQuery(SQL_SELECT_ALL_ACTORS);
            actors = getListFromResultSet(resultSet);
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return actors;
    }

    @Override
    public List<Actor> findActorsByFilm(Film film) {
        List<Actor> actors = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_SELECT_ACTORS_BY_FILM);
            st.setLong(1, film.getId());
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
    public List<Actor> findActorsThatStarredInFilmsMoreThen(int numFilms) {
        List<Actor> actors = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_SELECT_ACTORS_STARRED_IN_FILMS_MORE_THAN);
            st.setInt(1, numFilms);
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
    public List<Actor> findActorsAreDirectors() {
        List<Actor> actors = new ArrayList<>();
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet =
                    st.executeQuery(SQL_SELECT_ACTORS_ARE_DIRECTORS);
            actors = getListFromResultSet(resultSet);
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return actors;
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
