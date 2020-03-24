package org.java.jdbc;

import org.java.jdbc.controller.MoviesRepositoryController;
import org.java.jdbc.db.ConnectorDB;
import org.java.jdbc.repository.ActorRepository;
import org.java.jdbc.repository.FilmRepository;
import org.java.jdbc.view.MoviesView;

import java.sql.SQLException;

public class DemoRepositoryMovies {
    public static void main(String[] args) throws SQLException {
        FilmRepository filmRepository = new FilmRepository(ConnectorDB.getConnection());
        ActorRepository actorRepository = new ActorRepository(ConnectorDB.getConnection());

        MoviesView moviesView = new MoviesView();

        MoviesRepositoryController moviesController = new MoviesRepositoryController(filmRepository, actorRepository, moviesView);
        moviesController.start();
    }
}
