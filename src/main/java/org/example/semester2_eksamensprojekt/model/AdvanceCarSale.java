package org.example.semester2_eksamensprojekt.model;
// Vi er klar over Java konventionen er f.eksk = buyingPrice og ikke buying_price.
// Vi har valgt at beholde det sådan, da vi ikke kan refactor det hele om ift. html siderne. Det er ikke tiden værd for os at rette manuelt alle steder.

public class AdvanceCarSale {
    int id;
    int car_id;
    String terms; //Vilkår.
    int exceeded_kilometers;
    double buying_price;
    String collection_point; // Leveringssted.

    public AdvanceCarSale(int car_id, String terms, int exceeded_kilometers, double buying_price, String collection_point) {
        this.car_id = car_id;
        this.terms = terms;
        this.exceeded_kilometers = exceeded_kilometers;
        this.buying_price = buying_price;
        this.collection_point = collection_point;
    }

    public AdvanceCarSale(int car_id, String terms, int exceeded_kilometers, String collection_point) {
        this.car_id = car_id;
        this.terms = terms;
        this.exceeded_kilometers = exceeded_kilometers;
        this.collection_point = collection_point;
    }

    public AdvanceCarSale(int car_id, double buying_price) {
        this.car_id = car_id;
        this.buying_price = buying_price;
    }

    public AdvanceCarSale() {

    }

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

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public int getExceeded_kilometers() {
        return exceeded_kilometers;
    }

    public void setExceeded_kilometers(int exceeded_kilometers) {
        this.exceeded_kilometers = exceeded_kilometers;
    }

    public double getBuying_price() {
        return buying_price;
    }

    public void setBuying_price(double buying_price) {
        this.buying_price = buying_price;
    }

    public String getCollection_point() {
        return collection_point;
    }

    public void setCollection_point(String collection_point) {
        this.collection_point = collection_point;
    }
}
