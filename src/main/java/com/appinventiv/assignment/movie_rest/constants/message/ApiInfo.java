package com.appinventiv.assignment.movie_rest.constants.message;

public interface ApiInfo {
    final String INFO_ADD = "Save a movie and returns payload with generated id";
    final String INFO_FIND = "Finds a specific movie matching provided movie id";
    final String INFO_FIND_ALL = "No input required returns list of all the movies in ascending order by name";
    final String INFO_UPDATE = "Updates a specific movie matching provided movie id";
    final String INFO_DELETE = "Deletes a specific movie matching provided movie id";
}
