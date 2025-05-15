package org.example.semester2_eksamensprojekt.model;

public class CarSalesInfo {
        private double finalPrice;
        private double exceededKmCost;
        private double totalDamageCost;

        public CarSalesInfo(double finalPrice, double exceededKmCost, double totalDamageCost) {
            this.finalPrice = finalPrice;
            this.exceededKmCost = exceededKmCost;
            this.totalDamageCost= totalDamageCost;
        }

        public double getFinalPrice() {
            return finalPrice;
        }

        public double getExceededKmCost() {
            return exceededKmCost;
        }

        public double getTotalDamageCost() {
            return totalDamageCost;
        }
}
