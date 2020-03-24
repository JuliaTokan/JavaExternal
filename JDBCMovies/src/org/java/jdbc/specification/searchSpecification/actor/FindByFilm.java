package org.java.jdbc.specification.searchSpecification.actor;

import org.java.jdbc.specification.Specification;

import java.util.Collections;
import java.util.List;

public class FindByFilm implements Specification {
    private Long film_id;

    public FindByFilm(Long film_id) {
        this.film_id = film_id;
    }

    @Override
    public String toSql() {
        return "INNER JOIN film_actor ON film_actor.id_actor = actor.id WHERE film_actor.id_film = ?";
    }

    @Override
    public List<Object> getParameters() {
        return Collections.singletonList(film_id);
    }
}
