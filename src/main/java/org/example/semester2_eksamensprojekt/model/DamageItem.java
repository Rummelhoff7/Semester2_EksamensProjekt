package org.example.semester2_eksamensprojekt.model;

public class DamageItem {
    private int id;
    private int dmg_id;
    private String description;
    private double cost;

    // Constructor til at oprette nye damage items (uden id)
    public DamageItem(int dmg_id, String description, double cost) {
        this.dmg_id = dmg_id;
        this.description = description;
        this.cost = cost;
    }

    // Full constructor (bruges fx hvis du henter data fra databasen)
    public DamageItem(int id, int dmg_id, String description, double cost) {
        this.id = id;
        this.dmg_id = dmg_id;
        this.description = description;
        this.cost = cost;
    }

    public DamageItem() {

    }


    // Getters og setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDmg_id() {
        return dmg_id;
    }

    public void setDmg_id(int dmg_id) {
        this.dmg_id = dmg_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
