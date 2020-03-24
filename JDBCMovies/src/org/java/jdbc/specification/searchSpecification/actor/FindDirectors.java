package org.java.jdbc.specification.searchSpecification.actor;

import org.java.jdbc.specification.Specification;

import java.util.Collections;
import java.util.List;

public class FindDirectors implements Specification {
    @Override
    public String toSql() {
        return "INNER JOIN film_actor ON film_actor.id_actor = actor.id WHERE film_actor.is_director = true";
    }

    @Override
    public List<Object> getParameters() {
        return Collections.emptyList();
    }
}
