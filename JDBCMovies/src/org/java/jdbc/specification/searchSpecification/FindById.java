package org.java.jdbc.specification.searchSpecification;

import org.java.jdbc.specification.Specification;

import java.util.Collections;
import java.util.List;

public class FindById implements Specification {

    private Long id;

    public FindById(Long id) {
        this.id = id;
    }

    @Override
    public String toSql() {
        return "WHERE id = ?";
    }

    @Override
    public List<Object> getParameters() {
        return Collections.singletonList(id);
    }
}
