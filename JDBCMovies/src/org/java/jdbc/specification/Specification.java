package org.java.jdbc.specification;

import java.util.List;

public interface Specification {
    String toSql();
    List<Object> getParameters();
}
