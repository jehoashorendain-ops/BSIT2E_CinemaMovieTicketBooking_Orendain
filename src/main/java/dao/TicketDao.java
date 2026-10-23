package com.scc.dao;

import com.scc.model.Ticket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDao {

    public void addTicket(Ticket ticket) {
        String sql = "INSERT INTO tickets(customer_name, movie_title, seat_no, price) VALUES(?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ticket.getCustomerName());
            pstmt.setString(2, ticket.getMovieTitle());
            pstmt.setString(3, ticket.getSeatNo());
            pstmt.setDouble(4, ticket.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting ticket: " + e.getMessage());
        }
    }

    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT id, customer_name, movie_title, seat_no, price FROM tickets";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Ticket ticket = new Ticket(
                        rs.getInt("id"),
                        rs.getString("customer_name"),
                        rs.getString("movie_title"),
                        rs.getString("seat_no"),
                        rs.getDouble("price")
                );
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching tickets: " + e.getMessage());
        }
        return tickets;
    }
}