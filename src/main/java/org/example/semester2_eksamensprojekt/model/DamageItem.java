package org.example.semester2_eksamensprojekt.model;
// Vi er klar over Java konventionen er f.eksk = buyingPrice og ikke buying_price.
// Vi har valgt at beholde det sådan, da vi ikke kan refactor det hele om ift. html siderne. Det er ikke tiden værd for os at rette manuelt alle steder.

public class DamageItem {
    private int id;
    private int dmg_id;
    private String description; // Beskrivelse på skaden.
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
