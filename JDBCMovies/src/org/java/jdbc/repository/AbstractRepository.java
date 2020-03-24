package org.java.jdbc.repository;

import org.java.jdbc.entity.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class AbstractRepository<T extends Entity> implements Repository<T> {

    Connection connection;

    AbstractRepository(Connection connection) {
        this.connection = connection;
    }
}