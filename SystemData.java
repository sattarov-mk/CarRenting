package src;

import src.models.*;

import java.util.*;

public class SystemData {
    public static List<Customer> customers = new ArrayList<>();
    public static List<Owner> owners = new ArrayList<>();
    public static List<Admin> admins = new ArrayList<>();
    public static List<Car> cars = new ArrayList<>();
    public static List<Booking> bookings = new ArrayList<>();

    public static void initializeSampleData() {
        // Добавим владельца
        Owner owner = new Owner("Владелец1", "owner1", "123");
        owners.add(owner);
        cars.add(new Car("Toyota", "Bishkek", 500, owner));
        cars.add(new Car("BMW", "Osh", 900, owner));

        // Клиент
        customers.add(new Customer("Клиент1", "client1", "123"));

        // Админ
        admins.add(new Admin("Админ1", "admin1", "123"));
    }

    public static User authenticate(String login, String password) {
        for (Customer c : customers) if (c.getLogin().equals(login) && c.getPassword().equals(password)) return c;
        for (Owner o : owners) if (o.getLogin().equals(login) && o.getPassword().equals(password)) return o;
        for (Admin a : admins) if (a.getLogin().equals(login) && a.getPassword().equals(password)) return a;
        return null;
    }
}
