package com.hexaware.bean;

import com.hexaware.service.*;
import java.sql.*;
import java.util.*;

public class BookingSystemRepositoryImpl implements IBookingSystemRepository {

    @Override
    public Event createEvent(String name, String date, String time, int seats, double price, String type, Venue venue) throws SQLException {
        Connection conn = DBUtil.getDBConn();

        // Insert Venue
        String insertVenue = "INSERT INTO Venue (venue_name, address) VALUES (?, ?)";
        PreparedStatement venueStmt = conn.prepareStatement(insertVenue, Statement.RETURN_GENERATED_KEYS);
        venueStmt.setString(1, venue.getVenueName());
        venueStmt.setString(2, venue.getAddress());
        venueStmt.executeUpdate();

        ResultSet venueKeys = venueStmt.getGeneratedKeys();
        int venueId = -1;
        if (venueKeys.next()) {
            venueId = venueKeys.getInt(1);
        }

        // Insert Event
        String insertEvent = "INSERT INTO Event (event_name, event_date, event_time, venue_id, total_seats, available_seats, ticket_price, event_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement eventStmt = conn.prepareStatement(insertEvent);
        eventStmt.setString(1, name);
        eventStmt.setDate(2, java.sql.Date.valueOf(date));
        eventStmt.setTime(3, java.sql.Time.valueOf(time));
        eventStmt.setInt(4, venueId);
        eventStmt.setInt(5, seats);
        eventStmt.setInt(6, seats);
        eventStmt.setDouble(7, price);
        eventStmt.setString(8, type);
        eventStmt.executeUpdate();

        venueStmt.close();
        eventStmt.close();
        conn.close();

        switch (type.toLowerCase()) {
            case "movie":
                return new Movie(name, java.time.LocalDate.parse(date), java.time.LocalTime.parse(time), venue, seats, price, "Action", "Actor", "Actress");
            case "concert":
                return new Concert(name, java.time.LocalDate.parse(date), java.time.LocalTime.parse(time), venue, seats, price, "Artist", "Type");
            case "sports":
                return new SportEvent(name, java.time.LocalDate.parse(date), java.time.LocalTime.parse(time), venue, seats, price, "Sport", "Team A vs Team B");
            default:
                return null;
        }
    }

    @Override
    public List<Event> getEventDetails() throws SQLException {
        List<Event> events = new ArrayList<>();
        Connection conn = DBUtil.getDBConn();
        String query = "SELECT e.*, v.venue_name, v.address FROM Event e JOIN Venue v ON e.venue_id = v.venue_id";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Venue venue = new Venue(rs.getString("venue_name"), rs.getString("address"));
            String type = rs.getString("event_type").toLowerCase();
            Event event = null;
            switch (type) {
                case "movie":
                    event = new Movie(rs.getString("event_name"), rs.getDate("event_date").toLocalDate(),
                            rs.getTime("event_time").toLocalTime(), venue, rs.getInt("total_seats"),
                            rs.getDouble("ticket_price"), "Action", "Actor", "Actress");
                    break;
                case "concert":
                    event = new Concert(rs.getString("event_name"), rs.getDate("event_date").toLocalDate(),
                            rs.getTime("event_time").toLocalTime(), venue, rs.getInt("total_seats"),
                            rs.getDouble("ticket_price"), "Artist", "Type");
                    break;
                case "sports":
                    event = new SportEvent(rs.getString("event_name"), rs.getDate("event_date").toLocalDate(),
                            rs.getTime("event_time").toLocalTime(), venue, rs.getInt("total_seats"),
                            rs.getDouble("ticket_price"), "Sport", "Team A vs Team B");
                    break;
            }
            if (event != null) events.add(event);
        }

        rs.close();
        stmt.close();
        conn.close();
        return events;
    }

    @Override
    public Event getEventDetails(String eventName) throws SQLException {
        Connection conn = DBUtil.getDBConn();
        String query = "SELECT e.*, v.venue_name, v.address FROM Event e JOIN Venue v ON e.venue_id = v.venue_id WHERE e.event_name = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, eventName);
        ResultSet rs = stmt.executeQuery();

        Event event = null;
        if (rs.next()) {
            Venue venue = new Venue(rs.getString("venue_name"), rs.getString("address"));
            String type = rs.getString("event_type").toLowerCase();
            switch (type) {
                case "movie":
                    event = new Movie(rs.getString("event_name"), rs.getDate("event_date").toLocalDate(),
                            rs.getTime("event_time").toLocalTime(), venue, rs.getInt("total_seats"),
                            rs.getDouble("ticket_price"), "Action", "Actor", "Actress");
                    break;
                case "concert":
                    event = new Concert(rs.getString("event_name"), rs.getDate("event_date").toLocalDate(),
                            rs.getTime("event_time").toLocalTime(), venue, rs.getInt("total_seats"),
                            rs.getDouble("ticket_price"), "Artist", "Type");
                    break;
                case "sports":
                    event = new SportEvent(rs.getString("event_name"), rs.getDate("event_date").toLocalDate(),
                            rs.getTime("event_time").toLocalTime(), venue, rs.getInt("total_seats"),
                            rs.getDouble("ticket_price"), "Sport", "Team A vs Team B");
                    break;
            }
        }

        rs.close();
        stmt.close();
        conn.close();
        return event;
    }

    @Override
    public int getAvailableNoOfTickets(String eventName) throws SQLException {
        Connection conn = DBUtil.getDBConn();
        String query = "SELECT available_seats FROM Event WHERE event_name = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, eventName);
        ResultSet rs = stmt.executeQuery();

        int available = -1;
        if (rs.next()) {
            available = rs.getInt("available_seats");
        }

        rs.close();
        stmt.close();
        conn.close();
        return available;
    }

    @Override
    public double calculateBookingCost(Event event, int numTickets) {
        return event.getTicketPrice() * numTickets;
    }

    @Override
    public boolean bookTickets(String eventName, int numTickets, List<Customer> customers) throws SQLException {
        Connection conn = DBUtil.getDBConn();
        conn.setAutoCommit(false);

        try {
            // Get event details
            String getEventSQL = "SELECT * FROM Event WHERE event_name = ?";
            PreparedStatement getEventStmt = conn.prepareStatement(getEventSQL);
            getEventStmt.setString(1, eventName);
            ResultSet rsEvent = getEventStmt.executeQuery();

            if (!rsEvent.next()) {
                System.out.println("Event not found.");
                conn.rollback();
                return false;
            }

            int eventId = rsEvent.getInt("event_id");
            int availableSeats = rsEvent.getInt("available_seats");
            double ticketPrice = rsEvent.getDouble("ticket_price");

            if (availableSeats < numTickets) {
                System.out.println("Not enough seats available.");
                conn.rollback();
                return false;
            }

            String checkCustomerSQL = "SELECT customer_id FROM Customer WHERE email = ? OR phone_number = ?";
            String insertCustomerSQL = "INSERT INTO Customer (customer_name, email, phone_number) VALUES (?, ?, ?)";
            PreparedStatement checkStmt = conn.prepareStatement(checkCustomerSQL);
            PreparedStatement insertStmt = conn.prepareStatement(insertCustomerSQL, Statement.RETURN_GENERATED_KEYS);

            List<Integer> customerIds = new ArrayList<>();

            for (Customer cust : customers) {
                int customerId = -1;

                checkStmt.setString(1, cust.getEmail());
                checkStmt.setString(2, cust.getPhoneNumber());
                ResultSet rsCust = checkStmt.executeQuery();

                if (rsCust.next()) {
                    customerId = rsCust.getInt("customer_id");
                } else {
                    insertStmt.setString(1, cust.getCustomerName());
                    insertStmt.setString(2, cust.getEmail());
                    insertStmt.setString(3, cust.getPhoneNumber());
                    insertStmt.executeUpdate();
                    ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        customerId = generatedKeys.getInt(1);
                    }
                    generatedKeys.close();
                }

                rsCust.close();
                customerIds.add(customerId);
            }

            String insertBookingSQL = "INSERT INTO Booking (customer_id, event_id, num_tickets, total_cost) VALUES (?, ?, ?, ?)";
            PreparedStatement bookingStmt = conn.prepareStatement(insertBookingSQL);
            for (Integer custId : customerIds) {
                bookingStmt.setInt(1, custId);
                bookingStmt.setInt(2, eventId);
                bookingStmt.setInt(3, 1); // 1 ticket per customer
                bookingStmt.setDouble(4, ticketPrice);
                bookingStmt.executeUpdate();
            }

            String updateSeatsSQL = "UPDATE Event SET available_seats = ? WHERE event_id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSeatsSQL);
            updateStmt.setInt(1, availableSeats - numTickets);
            updateStmt.setInt(2, eventId);
            updateStmt.executeUpdate();

            conn.commit();

            rsEvent.close();
            getEventStmt.close();
            checkStmt.close();
            insertStmt.close();
            bookingStmt.close();
            updateStmt.close();
            conn.close();

            return true;
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
public boolean cancelBooking(int bookingId) throws SQLException {
    Connection conn = DBUtil.getDBConn();

    // Step 1: Get the number of tickets and event_id from the booking
    String selectQuery = "SELECT event_id, num_tickets FROM Booking WHERE booking_id = ?";
    PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
    selectStmt.setInt(1, bookingId);
    ResultSet rs = selectStmt.executeQuery();

    int eventId = -1;
    int numTickets = -1;
    if (rs.next()) {
        eventId = rs.getInt("event_id");
        numTickets = rs.getInt("num_tickets");
    } else {
        rs.close();
        selectStmt.close();
        conn.close();
        return false; // Booking not found
    }

    rs.close();
    selectStmt.close();

    // Step 2: Delete the booking
    String deleteQuery = "DELETE FROM Booking WHERE booking_id = ?";
    PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
    deleteStmt.setInt(1, bookingId);
    int deletedRows = deleteStmt.executeUpdate();
    deleteStmt.close();

    if (deletedRows == 0) {
        conn.close();
        return false;
    }

    // Step 3: Update available seats in Event table
    String updateEventQuery = "UPDATE Event SET available_seats = available_seats + ? WHERE event_id = ?";
    PreparedStatement updateStmt = conn.prepareStatement(updateEventQuery);
    updateStmt.setInt(1, numTickets);
    updateStmt.setInt(2, eventId);
    updateStmt.executeUpdate();
    updateStmt.close();

    conn.close();
    return true;
}


@Override
public Booking getBookingDetails(int bookingId) throws SQLException {
    Connection conn = DBUtil.getDBConn();

    // Query to fetch booking with event and customer details
    String query = "SELECT b.num_tickets, b.total_cost, b.booking_date, " +
                   "e.event_name, e.event_date, e.event_time, e.total_seats, e.ticket_price, e.event_type, " +
                   "v.venue_name, v.address, " +
                   "c.customer_name, c.email, c.phone_number " +
                   "FROM Booking b " +
                   "JOIN Event e ON b.event_id = e.event_id " +
                   "JOIN Venue v ON e.venue_id = v.venue_id " +
                   "JOIN Customer c ON b.customer_id = c.customer_id " +
                   "WHERE b.booking_id = ?";

    PreparedStatement stmt = conn.prepareStatement(query);
    stmt.setInt(1, bookingId);

    ResultSet rs = stmt.executeQuery();

    List<Customer> customers = new ArrayList<>();
    Booking booking = null;

    Event event = null;
    int numTickets = 0;
    double totalCost = 0.0;
    java.time.LocalDateTime bookingDate = null;

    while (rs.next()) {
        // Only initialize event once
        if (event == null) {
            String eventType = rs.getString("event_type").toLowerCase();
            Venue venue = new Venue(rs.getString("venue_name"), rs.getString("address"));
            java.time.LocalDate eventDate = rs.getDate("event_date").toLocalDate();
            java.time.LocalTime eventTime = rs.getTime("event_time").toLocalTime();
            String eventName = rs.getString("event_name");
            int totalSeats = rs.getInt("total_seats");
            double ticketPrice = rs.getDouble("ticket_price");

            switch (eventType) {
                case "movie":
                    event = new Movie(eventName, eventDate, eventTime, venue, totalSeats, ticketPrice, "Action", "Actor", "Actress");
                    break;
                case "concert":
                    event = new Concert(eventName, eventDate, eventTime, venue, totalSeats, ticketPrice, "Artist", "Type");
                    break;
                case "sports":
                    event = new SportEvent(eventName, eventDate, eventTime, venue, totalSeats, ticketPrice, "Sport", "Team A vs Team B");
                    break;
            }

            numTickets = rs.getInt("num_tickets");
            totalCost = rs.getDouble("total_cost");
            bookingDate = rs.getTimestamp("booking_date").toLocalDateTime();
        }

        Customer customer = new Customer(
            rs.getString("customer_name"),
            rs.getString("email"),
            rs.getString("phone_number")
        );
        customers.add(customer);
    }

    rs.close();
    stmt.close();
    conn.close();

    if (event != null && !customers.isEmpty()) {
        booking = new Booking(customers, event, numTickets);
    }

    return booking;
}

}
