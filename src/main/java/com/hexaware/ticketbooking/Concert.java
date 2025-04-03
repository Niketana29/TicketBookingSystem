package com.hexaware.ticketbooking;

import java.time.LocalDate;
import java.time.LocalTime;

public class Concert extends Event {
    private String artistName;

    public Concert(String eventName, LocalDate eventDate, LocalTime eventTime, String venueName, int totalSeats, double ticketPrice, String artistName) {
        super(eventName, eventDate, eventTime, venueName, totalSeats, ticketPrice, EventType.CONCERT);
        this.artistName = artistName;
    }

    @Override
    public void displayEventTypeDetails() {
        System.out.println("Concert Artist: " + artistName);
    }
}
