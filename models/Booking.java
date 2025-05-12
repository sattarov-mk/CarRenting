package src.models;

public class Booking {
    private Customer customer;
    private Car car;
    private boolean confirmed;

    public Booking(Customer customer, Car car) {
        this.customer = customer;
        this.car = car;
        this.confirmed = false;
    }

    public Car getCar() { return car; }
    public void setConfirmed(boolean confirmed) { this.confirmed = confirmed; }

    @Override
    public String toString() {
        return car.getModel() + " забронирован клиентом " + customer.name + " | Подтвержден: " + confirmed;
    }
}
