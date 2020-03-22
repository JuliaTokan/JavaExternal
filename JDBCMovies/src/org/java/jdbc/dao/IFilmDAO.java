package org.java.jdbc.dao;

import org.java.jdbc.entity.Actor;
import org.java.jdbc.entity.Film;

import java.util.List;

public interface IFilmDAO {
    void init();
    void add(Film film);
    void addActorToFilm(Actor actor, Film film, Boolean isDirector);

    List<Film> getAll();

    List<Film> findFilmsBetweenYears(int fromYear, int toYear);
    void deleteFilmsReleasedEarlier(int numYears);
}
