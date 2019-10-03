package com.scottlogic.librarygradproject.model;

public class Movie extends LibraryEntry {

    private String title;

    public Movie(String title) {
        super();
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

}
