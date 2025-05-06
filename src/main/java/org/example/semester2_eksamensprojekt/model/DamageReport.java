package org.example.semester2_eksamensprojekt.model;

import java.sql.Date;
import java.time.LocalDate;


public class DamageReport {
    private int id;
    private int car_id;
    private LocalDate date;

    public DamageReport(int id, int car_id, LocalDate date) {
        this.id = id;
        this.car_id = car_id;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
