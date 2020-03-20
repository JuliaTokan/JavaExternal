package org.java.jdbc.controller;

import org.java.jdbc.dao.impl.ActorDAO;
import org.java.jdbc.dao.impl.FilmDAO;
import org.java.jdbc.entity.Actor;
import org.java.jdbc.entity.Film;
import org.java.jdbc.view.MoviesView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

public class MoviesController {
    private FilmDAO filmDAO;
    private ActorDAO actorDAO;
    private MoviesView moviesView;

    public MoviesController(FilmDAO filmDAO, ActorDAO actorDAO, MoviesView moviesView) {
        this.filmDAO = filmDAO;
        this.actorDAO = actorDAO;
        this.moviesView = moviesView;
    }

    public void start() {
        for (; ; ) {
            moviesView.printMenu();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                int action = Integer.parseInt(bufferedReader.readLine());
                switch (action) {
                    case 1:
                        showAllFilms();
                        break;
                    case 2:
                        addFilm();
                        break;
                    case 3:
                        addActorToFilm();
                        break;
                    case 4:
                        findFilmsThisAndLastYear();
                        break;
                    case 5:
                        deleteFilmsReleasedEarlier();
                        break;
                    case 6:
                        showAllActors();
                        break;
                    case 7:
                        addActor();
                        break;
                    case 8:
                        findActorsByFilm();
                        break;
                    case 9:
                        findActorsByNumFilms();
                        break;
                    case 10:
                        System.exit(0);
                        break;
                }
            } catch (IOException e) {
                System.out.println("Input error!");
            } catch (Exception e) {
                //System.out.println("Incorrect input! Try again!");
                e.printStackTrace();
            }
        }
    }

    public void showAllFilms() {
        List<Film> films = filmDAO.getAll();
        moviesView.printList(films);
    }

    public void addFilm() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        moviesView.printMsg("Enter name:");
        String name = bufferedReader.readLine();
        moviesView.printMsg("Enter release date (yyyy/mm/dd) :");
        Date releaseDate = new Date(bufferedReader.readLine());
        moviesView.printMsg("Enter country:");
        String country = bufferedReader.readLine();

        Film film = new Film(null, name, releaseDate, country);
        filmDAO.add(film);
    }

    public void addActorToFilm() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        List<Film> films = filmDAO.getAll();
        moviesView.printListToChoose(films);
        moviesView.printMsg("Choose film:");
        int film = Integer.parseInt(bufferedReader.readLine()) - 1 ;

        List<Actor> actors = actorDAO.getAll();
        moviesView.printListToChoose(actors);
        moviesView.printMsg("Choose actor:");
        int actor = Integer.parseInt(bufferedReader.readLine()) - 1;

        filmDAO.addActorToFilm(actors.get(actor), films.get(film));
    }

    public void findFilmsThisAndLastYear() {
        int thisYear = new Date().getYear();
        int lastYear = thisYear - 1;
        List<Film> films = filmDAO.findFilmsBetweenYears(lastYear, thisYear);
        moviesView.printList(films);
    }

    public void deleteFilmsReleasedEarlier() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        moviesView.printMsg("Enter a number of years:");
        int num = Integer.parseInt(bufferedReader.readLine());
        filmDAO.deleteFilmsReleasedEarlier(num);
    }

    public void showAllActors() {
        List<Actor> actors = actorDAO.getAll();
        moviesView.printList(actors);
    }

    public void addActor() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        moviesView.printMsg("Enter name:");
        String name = bufferedReader.readLine();
        moviesView.printMsg("Enter date of birth (yyyy/mm/dd) :");
        Date birthDate = new Date(bufferedReader.readLine());

        Actor actor = new Actor(null, name, birthDate);
        actorDAO.add(actor);
    }

    public void findActorsByFilm() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<Film> films = filmDAO.getAll();
        moviesView.printListToChoose(films);

        moviesView.printMsg("Choose film:");
        int film = Integer.parseInt(bufferedReader.readLine()) - 1;

        List<Actor> actors = actorDAO.findActorsByFilm(films.get(film));
        moviesView.printList(actors);
    }

    public void findActorsByNumFilms() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        moviesView.printMsg("Enter num:");
        int num = Integer.parseInt(bufferedReader.readLine());
        List<Actor> actors = actorDAO.findActorsThatStarredInFilmsMoreThen(num);
        moviesView.printList(actors);
    }
}
