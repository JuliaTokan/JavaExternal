package org.java.jdbc;

import org.java.jdbc.controller.MoviesController;
import org.java.jdbc.dao.DaoFactory;
import org.java.jdbc.dao.impl.ActorDAO;
import org.java.jdbc.dao.impl.FilmDAO;
import org.java.jdbc.entity.Film;
import org.java.jdbc.view.MoviesView;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class DemoMovies {

    public static void main(String[] args) throws SQLException {
        FilmDAO filmDAO = DaoFactory.createFilmDAO();
        ActorDAO actorDAO = DaoFactory.createActorDAO();
        MoviesView moviesView = new MoviesView();

        MoviesController moviesController = new MoviesController(filmDAO, actorDAO, moviesView);
        moviesController.start();
    }
}
