package com.hexaware.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class SportEvent extends Event {
    private String sportName;
    private String teamsName;

    public SportEvent() {
        super(null, null, null, null, 0, 0.0, EventType.SPORTS);
    }

    public SportEvent(String eventName, LocalDate eventDate, LocalTime eventTime, Venue venue, int totalSeats, double ticketPrice, String sportName, String teamsName) {
        super(eventName, eventDate, eventTime, venue, totalSeats, ticketPrice, EventType.SPORTS);
        this.sportName = sportName;
        this.teamsName = teamsName;
    }

    @Override
    public void displayEventTypeDetails() {
        System.out.println("Sport Name: " + sportName);
        System.out.println("Teams: " + teamsName);
    }
}
