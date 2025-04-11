package com.hexaware.service;

import com.hexaware.bean.*;

public interface IEventServiceProvider {
    Event createEvent(String name, String date, String time, int seats, double price, String type, Venue venue);
    void getEventDetails(String eventName);
    int getAvailableNoOfTickets(String eventName);
}

