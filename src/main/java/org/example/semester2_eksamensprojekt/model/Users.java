package org.example.semester2_eksamensprojekt.model;
// Vi er klar over Java konventionen er f.eks, = buyingPrice og ikke buying_price.
// Vi har valgt at beholde det sådan, da vi ikke kan refactor det hele om ift. html siderne. Det er ikke tiden værd for os at rette manuelt alle steder.

public class Users {
    private int id;
    private String username;
    private String password;
    private String role; // Admin, data registration, mechanic, business_developer. Kunne også gøres til ENUM.

    public Users(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
