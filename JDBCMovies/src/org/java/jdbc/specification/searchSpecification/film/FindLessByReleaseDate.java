package org.java.jdbc.specification.searchSpecification.film;

import org.java.jdbc.specification.Specification;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

public class FindLessByReleaseDate implements Specification {
    private int year;

    public FindLessByReleaseDate(int year) {
        this.year = year;
    }

    @Override
    public String toSql() {
        return "WHERE releasedate < ?";
    }

    @Override
    public List<Object> getParameters() {
        int currentYear = new java.util.Date().getYear();
        return Collections.singletonList(new Date(currentYear-year, 1, 1));
    }
}
