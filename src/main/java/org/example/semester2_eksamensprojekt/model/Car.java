package org.example.semester2_eksamensprojekt.model;
// Vi er klar over Java konventionen er f.eksk = buyingPrice og ikke buying_price.
// Vi har valgt at beholde det sådan, da vi ikke kan refactor det hele om ift. html siderne. Det er ikke tiden værd for os at rette manuelt alle steder.

public class Car {
    private int id; //Vognnummer
    private String framenumber; //stelnummer
    private String color;
    private String brand;
    private String model;
    private int equipment_level; // equipment_level 1-4.
    private double steel_price;
    private double registration_fee;
    private double CO2_emissions;
    private boolean limited; //Ift. abonnement.
    private String status; //Bilens tilgængelighed. - Kunne også være ENUMS, men indtil videre bruger vi string.
    private String img;

    public Car(int id, String framenumber, String color, String brand, String model, int equipment_level, double steel_price, double registrationFee, double CO2_emissions, boolean limited, String status, String img) {
        this.id = id;
        this.framenumber = framenumber;
        this.color = color;
        this.brand = brand;
        this.model = model;
        this.equipment_level = equipment_level;
        this.steel_price = steel_price;
        this.registration_fee = registrationFee;
        this.CO2_emissions = CO2_emissions;
        this.limited = limited;
        this.status = status;
        this.img = img;
    }

    public Car(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFramenumber() {
        return framenumber;
    }

    public void setFramenumber(String framenumber) {
        this.framenumber = framenumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getEquipment_level() {
        return equipment_level;
    }

    public void setEquipment_level(int equipment_level) {
        this.equipment_level = equipment_level;
    }

    public double getSteel_price() {
        return steel_price;
    }

    public void setSteel_price(double steel_price) {
        this.steel_price = steel_price;
    }

    public double getRegistration_fee() {
        return registration_fee;
    }

    public void setRegistration_fee(double registration_fee) {
        this.registration_fee = registration_fee;
    }

    public double getCO2_emissions() {
        return CO2_emissions;
    }

    public void setCO2_emissions(double CO2_emissions) {
        this.CO2_emissions = CO2_emissions;
    }

    public boolean isLimited() {
        return limited;
    }

    public void setLimited(boolean limited) {
        this.limited = limited;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    // instanceof ElectricCar = Refererer til subklassen
    // Returnerer true with Objektet er en elbil.
    // Hjælper med polymorfi.
    public boolean isElectric() {
        return this instanceof ElectricCar;
    }
}

