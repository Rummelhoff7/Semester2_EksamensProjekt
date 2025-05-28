package org.example.semester2_eksamensprojekt.model;
// Vi er klar over Java konventionen er f.eks. = buyingPrice og ikke buying_price.
// Vi har valgt at beholde det sådan, da vi ikke kan refactor det hele om ift. html siderne. Det er ikke tiden værd for os at rette manuelt alle steder.

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
