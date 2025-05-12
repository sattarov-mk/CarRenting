package src.models;

public class Car {
    private String model;
    private String location;
    private double price;
    private Owner owner;

    public Car(String model, String location, double price, Owner owner) {
        this.model = model;
        this.location = location;
        this.price = price;
        this.owner = owner;
    }

    public String getModel() { return model; }
    public double getPrice() { return price; }
    public Owner getOwner() { return owner; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return model + " - " + location + " - " + price + "$ в сутки";
    }
}
