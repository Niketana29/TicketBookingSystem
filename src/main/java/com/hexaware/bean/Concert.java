package com.hexaware.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class Concert extends Event {
    private String artistName;
    private String concertType;

    public Concert() {
        super(null, null, null, null, 0, 0.0, EventType.CONCERT);
    }

    public Concert(String eventName, LocalDate eventDate, LocalTime eventTime, Venue venue, int totalSeats, double ticketPrice, String artistName, String concertType) {
        super(eventName, eventDate, eventTime, venue, totalSeats, ticketPrice, EventType.CONCERT);
        this.artistName = artistName;
        this.concertType = concertType;
    }

    @Override
    public void displayEventTypeDetails() {
        System.out.println("Concert Artist: " + artistName);
        System.out.println("Concert Type: " + concertType);
    }
}
