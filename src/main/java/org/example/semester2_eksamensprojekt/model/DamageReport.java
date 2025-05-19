package org.example.semester2_eksamensprojekt.model;

import java.time.LocalDate;
import java.util.List;

public class DamageReport {
    private int id;
    private int car_id;
    private LocalDate date;
    private List<DamageItem> damageItems;

    public DamageReport(int id, int car_id, LocalDate date) {
        this.id = id;
        this.car_id = car_id;
        this.date = date;
    }

    public DamageReport(int car_id, LocalDate date) {
        this.car_id = car_id;
        this.date = date;
    }

    public DamageReport() {
    }

    private String imageUrl;

    // Getter og Setter
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    // getters og setters

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

    public List<DamageItem> getDamageItems() {
        return damageItems;
    }

    public void setDamageItems(List<DamageItem> damageItems) {
        this.damageItems = damageItems;
    }
}

