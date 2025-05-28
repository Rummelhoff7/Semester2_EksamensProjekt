package org.example.semester2_eksamensprojekt.model;
//Mads
// Bilabonnement leaser også Elbiler.
// Her nedarver ElectricCar fra Car (det er et it-is forhold)
// ElectricCar har samme attributter som Car, dog med lidt ekstra som giver mening for en elbil.
public class ElectricCar extends Car{
    private double batteryCapacity; // målt i kWh.
    private double chargingTime; // målt i timer.
    private double rangePerCharge; // målt i km.

    public ElectricCar(int id, String framenumber, String color, String brand, String model, int equipment_level, double steel_price, double registrationFee, double CO2_emissions, boolean limited, String status, String img, double batteryCapacity, double chargingTime, double rangePerCharge) {
        super(id, framenumber, color, brand, model, equipment_level, steel_price, registrationFee, CO2_emissions, limited, status, img);
        this.batteryCapacity = batteryCapacity;
        this.chargingTime = chargingTime;
        this.rangePerCharge = rangePerCharge;
    }

    public ElectricCar(double batteryCapacity, double chargingTime, double rangePerCharge) {
        super();
        this.batteryCapacity = batteryCapacity;
        this.chargingTime = chargingTime;
        this.rangePerCharge = rangePerCharge;
    }

    public ElectricCar() {
        super();
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

    @Override
    public boolean isElectric() {
        return true;
    }

}