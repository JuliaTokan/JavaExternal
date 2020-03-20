package org.java.jdbc.dao;

import org.java.jdbc.db.ConnectorDB;
import org.java.jdbc.dao.impl.ActorDAO;
import org.java.jdbc.dao.impl.FilmDAO;

import java.sql.SQLException;

public class DaoFactory {
    public static FilmDAO createFilmDAO() throws SQLException {
        return new FilmDAO(ConnectorDB.getConnection());
    }

    public static ActorDAO createActorDAO() throws SQLException {
        return new ActorDAO(ConnectorDB.getConnection());
    }
}
