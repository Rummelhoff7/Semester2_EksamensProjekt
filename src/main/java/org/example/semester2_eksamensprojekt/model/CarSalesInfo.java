package org.example.semester2_eksamensprojekt.model;

public class CarSalesInfo {
        private double finalPrice;
        private double exceededKmCost;

        public CarSalesInfo(double finalPrice, double exceededKmCost) {
            this.finalPrice = finalPrice;
            this.exceededKmCost = exceededKmCost;
        }

        public double getFinalPrice() {
            return finalPrice;
        }

        public double getExceededKmCost() {
            return exceededKmCost;
        }

}
