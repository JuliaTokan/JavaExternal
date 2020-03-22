package org.java.jdbc.dao.impl;

import org.java.jdbc.dao.AbstractDAO;
import org.java.jdbc.dao.IFilmDAO;
import org.java.jdbc.entity.Actor;
import org.java.jdbc.entity.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDAO extends AbstractDAO implements IFilmDAO {
    public static final String SQL_SELECT_ALL_FILMS = "SELECT * FROM film";
    public static final String SQL_INSERT_FILMS = "INSERT INTO film (name, releasedate, country) VALUES(?, ?, ?)";

    public static final String SQL_INSERT_ACTOR_TO_FILM = "INSERT INTO film_actor VALUES(?, ?, ?)";

    public static final String SQL_SELECT_FILMS_BETWEEN_YEARS = "SELECT * FROM film WHERE releasedate BETWEEN ? AND ?";
    public static final String SQL_DELETE_FILMS_EARLIER_YEARS = "DELETE FROM film WHERE releasedate < ?";

    public FilmDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void init() {

    }

    @Override
    public void add(Film film) {
        try {
            try (PreparedStatement st = connection
                    .prepareStatement(SQL_INSERT_FILMS)) {
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
    public void addActorToFilm(Actor actor, Film film, Boolean isDirector) {
        try {
            PreparedStatement st = connection.prepareStatement(SQL_INSERT_ACTOR_TO_FILM);
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

    @Override
    public List<Film> getAll() {
        List<Film> films = new ArrayList<>();
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet resultSet =
                    st.executeQuery(SQL_SELECT_ALL_FILMS);
            films = getListFromResultSet(resultSet);
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return films;
    }

    @Override
    public List<Film> findFilmsBetweenYears(int fromYear, int toYear) {
        List<Film> films = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_SELECT_FILMS_BETWEEN_YEARS);
            st.setDate(1, new Date(fromYear, 1, 1));
            st.setDate(2, new Date(toYear, 12, 31));
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
    public void deleteFilmsReleasedEarlier(int numYears) {
        int year = new java.util.Date().getYear();
        year -= numYears;

        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_DELETE_FILMS_EARLIER_YEARS);
            st.setDate(1, new Date(year, 1, 1));
            st.execute();
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
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
