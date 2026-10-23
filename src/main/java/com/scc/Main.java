package com.scc;

import com.scc.dao.DatabaseConnection;
import com.scc.dao.TicketDao;
import com.scc.model.Ticket;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize database table on startup
        DatabaseConnection.initializeDatabase();
        TicketDao ticketDao = new TicketDao();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("==================================================");
            System.out.println("           CINEMA MOVIE TICKET BOOKING            ");
            System.out.println("==================================================");
            System.out.println("[1] Book Movie Ticket");
            System.out.println("[2] View Booked Tickets");
            System.out.println("[3] Exit System");
            System.out.println("==================================================");
            System.out.print("Enter choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline buffer[cite: 1]
            } else {
                System.out.println("Invalid input. Please enter a valid number.\n");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n--------------------------------------------------");
                    System.out.println("              BOOK A MOVIE TICKET                 ");
                    System.out.println("--------------------------------------------------");
                    System.out.print("Enter Customer Name: ");
                    String customerName = scanner.nextLine();

                    System.out.print("Enter Movie Title: ");
                    String movieTitle = scanner.nextLine();

                    System.out.print("Enter Seat No (e.g., F-12): ");
                    String seatNo = scanner.nextLine();

                    System.out.print("Enter Ticket Price: ");
                    double price = 0.0;
                    if (scanner.hasNextDouble()) {
                        price = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline buffer[cite: 1]
                    } else {
                        System.out.println("Invalid price format. Defaulting to 0.0");
                        scanner.nextLine();
                    }

                    Ticket newTicket = new Ticket(customerName, movieTitle, seatNo, price);
                    ticketDao.addTicket(newTicket);
                    System.out.println("\n[Success] Ticket successfully booked and saved!\n");
                    break;

                case 2:
                    List<Ticket> tickets = ticketDao.getAllTickets();
                    System.out.println("\n==================================================");
                    System.out.println("               BOOKED MOVIE TICKETS               ");
                    System.out.println("==================================================");
                    System.out.println();
                    System.out.println("+---+-------------------------+-------------------------+-----------+-----------+");
                    System.out.println("| ID| CUSTOMER NAME           | MOVIE TITLE             | SEAT NO   | PRICE     |");
                    System.out.println("+---+-------------------------+-------------------------+-----------+-----------+");

                    for (Ticket t : tickets) {
                        System.out.printf("| %-2d | %-23s | %-23s | %-9s | %9.2f |\n",
                                t.getId(),
                                t.getCustomerName(),
                                t.getMovieTitle(),
                                t.getSeatNo(),
                                t.getPrice()
                        );
                    }
                    System.out.println("+---+-------------------------+-------------------------+-----------+-----------+");
                    System.out.println("Total Records Displayed: " + tickets.size());
                    System.out.println("\n");
                    break;

                case 3:
                    System.out.println("\nExiting system... Thank you!");
                    break;

                default:
                    System.out.println("\nInvalid choice! Please select between 1 and 3.\n");
            }

        } while (choice != 3);

        scanner.close();
    }
}