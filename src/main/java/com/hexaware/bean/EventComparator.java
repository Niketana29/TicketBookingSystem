package com.hexaware.bean;

import java.util.Comparator;

public class EventComparator implements Comparator<Event> {
    @Override
    public int compare(Event e1, Event e2) {
        int nameCompare = e1.getEventName().compareToIgnoreCase(e2.getEventName());
        if (nameCompare == 0) {
            return e1.getVenue().getName().compareToIgnoreCase(e2.getVenue().getName());
        }
        return nameCompare;
    }
}

