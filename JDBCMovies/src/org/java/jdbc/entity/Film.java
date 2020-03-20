package org.java.jdbc.entity;

import java.util.Date;

public class Film extends Entity{
    private String name;
    private Date releaseDate;
    private String country;

    public Film() {
    }

    public Film(Long id, String name, Date releaseDate, String country) {
        super(id);
        this.name = name;
        this.releaseDate = releaseDate;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return name+"\n   releaseDate: "+releaseDate+"\n   country: "+ country;
    }
}
