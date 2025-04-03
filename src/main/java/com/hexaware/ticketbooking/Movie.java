package com.hexaware.ticketbooking;

import java.time.LocalDate;
import java.time.LocalTime;

public class Movie extends Event {
    private String genre;

    public Movie(String eventName, LocalDate eventDate, LocalTime eventTime, String venueName, int totalSeats, double ticketPrice, String genre) {
        super(eventName, eventDate, eventTime, venueName, totalSeats, ticketPrice, EventType.MOVIE);
        this.genre = genre;
    }

    @Override
    public void displayEventTypeDetails() {
        System.out.println("Movie Genre: " + genre);
    }
}
