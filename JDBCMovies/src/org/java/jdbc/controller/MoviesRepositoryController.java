package org.java.jdbc.controller;

import org.java.jdbc.entity.Actor;
import org.java.jdbc.entity.Film;
import org.java.jdbc.repository.ActorRepository;
import org.java.jdbc.repository.FilmRepository;
import org.java.jdbc.specification.searchSpecification.actor.FindByFilm;
import org.java.jdbc.specification.searchSpecification.actor.FindByFilmsNum;
import org.java.jdbc.specification.searchSpecification.actor.FindDirectors;
import org.java.jdbc.specification.searchSpecification.film.FindByReleaseDateDiapason;
import org.java.jdbc.specification.searchSpecification.film.FindLessByReleaseDate;
import org.java.jdbc.view.MoviesView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

public class MoviesRepositoryController {
    FilmRepository filmRepository;
    ActorRepository actorRepository;
    MoviesView moviesView;

    public MoviesRepositoryController(FilmRepository filmRepository, ActorRepository actorRepository, MoviesView moviesView) {
        this.filmRepository = filmRepository;
        this.actorRepository = actorRepository;
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
                        findActorsAreDirectors();
                        break;
                    case 10:
                        findActorsByNumFilms();
                        break;
                    case 11:
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
        List<Film> films = filmRepository.getAll();
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

        Film film = new Film(name, releaseDate, country);
        filmRepository.add(film);
    }

    public void addActorToFilm() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        List<Film> films = filmRepository.getAll();
        moviesView.printListToChoose(films);
        moviesView.printMsg("Choose film:");
        int film = Integer.parseInt(bufferedReader.readLine()) - 1;

        List<Actor> actors = actorRepository.getAll();
        moviesView.printListToChoose(actors);
        moviesView.printMsg("Choose actor:");
        int actor = Integer.parseInt(bufferedReader.readLine()) - 1;

        moviesView.printMsg("If the actor is a director of this film?\n   0 - false;\n   1 - true;");
        Boolean isDirector = new Boolean(bufferedReader.readLine().equals("1") ? true : false);

        filmRepository.addActorToFilm(actors.get(actor), films.get(film), isDirector);
    }

    public void findFilmsThisAndLastYear() {
        int thisYear = new Date().getYear();
        int lastYear = thisYear - 1;
        List<Film> films = filmRepository.query(new FindByReleaseDateDiapason(lastYear, thisYear));
        moviesView.printList(films);
    }

    public void deleteFilmsReleasedEarlier() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        moviesView.printMsg("Enter a number of years:");
        int num = Integer.parseInt(bufferedReader.readLine());
        filmRepository.query(new FindLessByReleaseDate(num));
    }

    public void showAllActors() {
        List<Actor> actors = actorRepository.getAll();
        moviesView.printList(actors);
    }

    public void addActor() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        moviesView.printMsg("Enter name:");
        String name = bufferedReader.readLine();
        moviesView.printMsg("Enter date of birth (yyyy/mm/dd) :");
        Date birthDate = new Date(bufferedReader.readLine());

        Actor actor = new Actor(name, birthDate);
        actorRepository.add(actor);
    }

    public void findActorsByFilm() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<Film> films = filmRepository.getAll();
        moviesView.printListToChoose(films);

        moviesView.printMsg("Choose film:");
        int film = Integer.parseInt(bufferedReader.readLine()) - 1;

        List<Actor> actors = actorRepository.query(new FindByFilm(films.get(film).getId()));
        moviesView.printList(actors);
    }

    public void findActorsByNumFilms() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        moviesView.printMsg("Enter num:");
        int num = Integer.parseInt(bufferedReader.readLine());
        List<Actor> actors = actorRepository.query(new FindByFilmsNum(num));
        moviesView.printList(actors);
    }

    public void findActorsAreDirectors() {
        List<Actor> actors = actorRepository.query(new FindDirectors());
        moviesView.printList(actors);
    }
}
