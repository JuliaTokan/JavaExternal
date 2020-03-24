package org.java.jdbc;

import org.java.jdbc.controller.MoviesDaoController;
import org.java.jdbc.dao.DaoFactory;
import org.java.jdbc.dao.impl.ActorDAO;
import org.java.jdbc.dao.impl.FilmDAO;
import org.java.jdbc.view.MoviesView;

import java.sql.SQLException;

public class DemoDaoMovies {

    public static void main(String[] args) throws SQLException {
        FilmDAO filmDAO = DaoFactory.createFilmDAO();
        ActorDAO actorDAO = DaoFactory.createActorDAO();
        MoviesView moviesView = new MoviesView();

        MoviesDaoController moviesDaoController = new MoviesDaoController(filmDAO, actorDAO, moviesView);
        moviesDaoController.start();
    }
}
