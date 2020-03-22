package org.java.jdbc.dao;


import org.java.jdbc.entity.Actor;
import org.java.jdbc.entity.Film;

import java.util.List;

public interface IActorDAO {
    void init();
    void add(Actor actor);
    List<Actor> getAll();

    List<Actor> findActorsByFilm(Film film);
    List<Actor> findActorsThatStarredInFilmsMoreThen(int numFilms);
    List<Actor> findActorsAreDirectors();
}
