package com.hexaware.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class Movie extends Event {
    private String genre;
    private String actorName;
    private String actressName;

    public Movie() {
        super(null, null, null, null, 0, 0.0, EventType.MOVIE);
    }

    public Movie(String eventName, LocalDate eventDate, LocalTime eventTime, Venue venue, int totalSeats, double ticketPrice, String genre, String actorName, String actressName) {
        super(eventName, eventDate, eventTime, venue, totalSeats, ticketPrice, EventType.MOVIE);
        this.genre = genre;
        this.actorName = actorName;
        this.actressName = actressName;
    }

    @Override
    public void displayEventTypeDetails() {
        System.out.println("Movie Genre: " + genre);
        System.out.println("Actor: " + actorName);
        System.out.println("Actress: " + actressName);
    }
}
