package org.java.jdbc.specification.searchSpecification.film;

import org.java.jdbc.specification.Specification;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class FindByReleaseDateDiapason implements Specification {
    private int fromYear;
    private int toYear;

    public FindByReleaseDateDiapason(int fromYear, int toYear) {
        this.fromYear = fromYear;
        this.toYear = toYear;
    }

    @Override
    public String toSql() {
        return "WHERE releasedate BETWEEN ? AND ?";
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(new Date(fromYear, 1, 1), new Date(toYear, 12, 31));
    }
}
