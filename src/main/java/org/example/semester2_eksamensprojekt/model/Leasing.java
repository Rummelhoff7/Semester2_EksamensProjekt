package org.example.semester2_eksamensprojekt.model;

import java.time.LocalDate;

public class Leasing {
    private int id;
    private int car_id;
    private LocalDate start_date;
    private LocalDate end_date;
    private double price;
    private boolean status; // true = limited, false = unlimited.
    private String customer_info; // for- og efternavn på kunden. obs. ikke personfølsomt.

    public Leasing(int car_id, LocalDate start_date, LocalDate end_date, double price, boolean status, String customer) {
        /*
        if (end_date.isBefore(start_date)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
         */
        this.car_id = car_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.price = price;
        this.status = status;
        this.customer_info = customer;
    }

    public Leasing(int id, int car_id, LocalDate start_date, LocalDate end_date, double price, boolean status, String customer) {
        this.id = id;
        this.car_id = car_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.price = price;
        this.status = status;
        this.customer_info = customer;
    }

    public Leasing() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCustomer_info() {
        return customer_info;
    }

    public void setCustomer_info(String customer_info) {
        this.customer_info = customer_info;
    }
}
