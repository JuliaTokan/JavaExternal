package org.java.jdbc.dao;

import java.sql.*;

public class AbstractDAO {
    protected Connection connection;
    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            // лог о невозможности закрытия Statement
        }
    }
}
