package com.scc.model;

public class Ticket {
    private int id;
    private String customerName;
    private String movieTitle;
    private String seatNo;
    private double price;

    public Ticket() {}

    public Ticket(String customerName, String movieTitle, String seatNo, double price) {
        this.customerName = customerName;
        this.movieTitle = movieTitle;
        this.seatNo = seatNo;
        this.price = price;
    }

    public Ticket(int id, String customerName, String movieTitle, String seatNo, double price) {
        this.id = id;
        this.customerName = customerName;
        this.movieTitle = movieTitle;
        this.seatNo = seatNo;
        this.price = price;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getMovieTitle() { return movieTitle; }
    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }

    public String getSeatNo() { return seatNo; }
    public void setSeatNo(String seatNo) { this.seatNo = seatNo; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}