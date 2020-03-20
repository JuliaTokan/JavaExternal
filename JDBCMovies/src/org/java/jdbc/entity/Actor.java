package org.java.jdbc.entity;

import java.util.Date;

public class Actor extends Entity {
    private String name;
    private Date birthDate;

    public Actor() {
    }

    public Actor(Long id, String name, Date birthDate) {
        super(id);
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return name+"\n   birthDate: "+birthDate;
    }
}
