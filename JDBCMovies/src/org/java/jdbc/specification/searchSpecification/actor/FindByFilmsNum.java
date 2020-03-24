package org.java.jdbc.specification.searchSpecification.actor;

import org.java.jdbc.specification.Specification;

import java.util.Collections;
import java.util.List;

public class FindByFilmsNum implements Specification {
    int num;

    public FindByFilmsNum(int num) {
        this.num = num;
    }

    @Override
    public String toSql() {
        return "INNER JOIN film_actor ON film_actor.id_actor = actor.id GROUP BY actor.id HAVING COUNT(film_actor.id_film) >= ?";
    }

    @Override
    public List<Object> getParameters() {
        return Collections.singletonList(num);
    }
}
