package com.hexaware.bean;

import com.hexaware.service.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class EventServiceProviderImpl implements IEventServiceProvider {
    protected Map<String, Event> events = new HashMap<>();
    protected Set<Event> eventSet = new TreeSet<>(new EventComparator()); // 🔹 New TreeSet

    @Override
    public Event createEvent(String name, String date, String time, int seats, double price, String type, Venue venue) {
        LocalDate eventDate = LocalDate.parse(date);
        LocalTime eventTime = LocalTime.parse(time);
        Event event = null;

        switch (type.toLowerCase()) {
            case "movie":
                event = new Movie(name, eventDate, eventTime, venue, seats, price, "Action", "Actor", "Actress");
                break;
            case "concert":
                event = new Concert(name, eventDate, eventTime, venue, seats, price, "Artist", "Type");
                break;
            case "sports":
                event = new SportEvent(name, eventDate, eventTime, venue, seats, price, "Sport", "Team A vs Team B");
                break;
        }

        if (event != null) {
            events.put(name.toLowerCase(), event);
            eventSet.add(event); // 🔹 Also add to TreeSet
        }
        return event;
    }

    @Override
    public void getEventDetails(String eventName) {
        Event event = events.get(eventName.toLowerCase());
        if (event != null) {
            event.displayEventDetails();
            event.displayEventTypeDetails();
            event.getVenue().displayVenueDetails();
        } else {
            System.out.println("Event not found.");
        }
    }

    @Override
    public int getAvailableNoOfTickets(String eventName) {
        Event event = events.get(eventName.toLowerCase());
        return event != null ? event.getAvailableSeats() : -1;
    }

    public Event getEventByName(String eventName) {
        return events.get(eventName.toLowerCase());
    }

    // 🔹 NEW METHOD to show sorted list of events
    public void displayAllEventsSorted() {
        if (eventSet.isEmpty()) {
            System.out.println("No events found.");
            return;
        }

        System.out.println("----- Sorted Events -----");
        for (Event event : eventSet) {
            event.displayEventDetails();
            System.out.println();
        }
    }
}
