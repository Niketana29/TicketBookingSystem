package com.hexaware.ticketbooking;

import java.time.LocalDate;
import java.time.LocalTime;

public class Concert extends Event {
    public Concert(String eventName, String date, String time, String venueName, int totalSeats, double ticketPrice) {
        super(eventName, LocalDate.parse(date), LocalTime.parse(time), venueName, totalSeats, ticketPrice, EventType.CONCERT);
    }
}
