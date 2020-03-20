package org.java.jdbc.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable, Cloneable {
    private Long id;

    public Entity() {
    }

    public Entity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
