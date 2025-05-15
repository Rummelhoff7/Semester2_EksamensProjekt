package org.example.semester2_eksamensprojekt.model;

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
    private String status; //Bilens tilgængelighed.
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

    // Bilabonnement leaser også Elbiler.
    // Her nedarver ElectricCar fra Car (det er et it-is forhold)
    // ElectricCar har samme attributter som Car, dog med lidt ekstra som giver mening for en elbil.
    public class ElectricCar extends Car{
        private double batteryCapacity; // målt i kWh.
        private double chargingTime; // målt i timer.
        private double rangePerCharge; // målt i km.

        public ElectricCar(double batteryCapacity, double chargingTime, double rangePerCharge) {
            this.batteryCapacity = batteryCapacity;
            this.chargingTime = chargingTime;
            this.rangePerCharge = rangePerCharge;
        }

        public ElectricCar(int id, String framenumber, String color, String brand, String model, int equipment_level, double steel_price, double registrationFee, double CO2_emissions, boolean limited, String status, String img, double batteryCapacity, double chargingTime, double rangePerCharge) {
            super(id, framenumber, color, brand, model, equipment_level, steel_price, registrationFee, CO2_emissions, limited, status, img);
            this.batteryCapacity = batteryCapacity;
            this.chargingTime = chargingTime;
            this.rangePerCharge = rangePerCharge;
        }

        public double getBatteryCapacity() {
            return batteryCapacity;
        }

        public void setBatteryCapacity(double batteryCapacity) {
            this.batteryCapacity = batteryCapacity;
        }

        public double getChargingTime() {
            return chargingTime;
        }

        public void setChargingTime(double chargingTime) {
            this.chargingTime = chargingTime;
        }

        public double getRangePerCharge() {
            return rangePerCharge;
        }

        public void setRangePerCharge(double rangePerCharge) {
            this.rangePerCharge = rangePerCharge;
        }
    }
}

