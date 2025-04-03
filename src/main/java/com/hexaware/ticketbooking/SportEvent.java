package com.hexaware.ticketbooking;

import java.time.LocalDate;
import java.time.LocalTime;

public class SportEvent extends Event {
    private String sportType;

    public SportEvent(String eventName, LocalDate eventDate, LocalTime eventTime, String venueName, int totalSeats, double ticketPrice, String sportType) {
        super(eventName, eventDate, eventTime, venueName, totalSeats, ticketPrice, EventType.SPORTS);
        this.sportType = sportType;
    }

    @Override
    public void displayEventTypeDetails() {
        System.out.println("Sport Type: " + sportType);
    }
}
