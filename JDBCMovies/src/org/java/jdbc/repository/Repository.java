package org.java.jdbc.repository;

import org.java.jdbc.specification.Specification;

import java.util.List;

public interface Repository<T> {
    void add(T item);

    //void update(T item);

    //void remove(T item);

    void remove(Specification specification);

    List<T> query(Specification specification);

    List<T> getAll();
}
