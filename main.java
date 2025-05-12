import java.util.*;

abstract class User {
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean checkPassword(String input) {
        return password.equals(input);
    }

    public String getUsername() {
        return username;
    }

    public abstract void showMenu(Scanner scanner, SystemData data);
}

class Customer extends User {
    private List<Booking> bookings = new ArrayList<>();

    public Customer(String username, String password) {
        super(username, password);
    }

    @Override
    public void showMenu(Scanner scanner, SystemData data) {
        while (true) {
            System.out.println("\n[Меню клиента]");
            System.out.println("1. Посмотреть доступные авто");
            System.out.println("2. Забронировать авто");
            System.out.println("3. Посмотреть мои брони");
            System.out.println("4. Оплатить бронь");
            System.out.println("0. Выйти");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    data.showAvailableCars();
                    break;
                case "2":
                    data.showAvailableCars();
                    System.out.print("Введите ID машины для брони: ");
                    int carId = Integer.parseInt(scanner.nextLine());
                    Car car = data.getCarById(carId);
                    if (car != null && car.isAvailable()) {
                        Booking booking = new Booking(car, this);
                        bookings.add(booking);
                        data.bookings.add(booking);
                        car.setAvailable(false);
                        System.out.println("Машина успешно забронирована.");
                    } else {
                        System.out.println("Машина недоступна.");
                    }
                    break;
                case "3":
                    for (Booking b : bookings) {
                        System.out.println(b);
                    }
                    break;
                case "4":
                    for (int i = 0; i < bookings.size(); i++) {
                        Booking b = bookings.get(i);
                        if (!b.isPaid()) {
                            System.out.println(i + 1 + ". " + b);
                        }
                    }
                    System.out.print("Выберите номер брони для оплаты: ");
                    int idx = Integer.parseInt(scanner.nextLine()) - 1;
                    if (idx >= 0 && idx < bookings.size() && !bookings.get(idx).isPaid()) {
                        bookings.get(idx).pay();
                        System.out.println("Оплата прошла успешно!");
                    } else {
                        System.out.println("Неверный выбор.");
                    }
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }
}

class Owner extends User {
    private List<Car> myCars = new ArrayList<>();

    public Owner(String username, String password) {
        super(username, password);
    }

    @Override
    public void showMenu(Scanner scanner, SystemData data) {
        while (true) {
            System.out.println("\n[Меню арендодателя]");
            System.out.println("1. Добавить машину");
            System.out.println("2. Просмотреть мои машины");
            System.out.println("3. Просмотреть бронирования");
            System.out.println("0. Выйти");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Модель: ");
                    String model = scanner.nextLine();
                    System.out.print("Локация: ");
                    String location = scanner.nextLine();
                    System.out.print("Цена за день: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    Car car = new Car(model, location, price, this);
                    myCars.add(car);
                    data.cars.add(car);
                    System.out.println("Машина добавлена.");
                    break;
                case "2":
                    for (Car c : myCars) {
                        System.out.println(c);
                    }
                    break;
                case "3":
                    for (Booking b : data.bookings) {
                        if (myCars.contains(b.getCar())) {
                            System.out.println(b);
                        }
                    }
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }
}

class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public void showMenu(Scanner scanner, SystemData data) {
        while (true) {
            System.out.println("\n[Меню администратора]");
            System.out.println("1. Просмотреть все машины");
            System.out.println("2. Изменить цену машины");
            System.out.println("0. Выйти");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    for (Car c : data.cars) {
                        System.out.println(c);
                    }
                    break;
                case "2":
                    for (Car c : data.cars) {
                        System.out.println(c);
                    }
                    System.out.print("Введите ID машины: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Car car = data.getCarById(id);
                    if (car != null) {
                        System.out.print("Введите новую цену: ");
                        double newPrice = Double.parseDouble(scanner.nextLine());
                        car.setPrice(newPrice);
                        System.out.println("Цена обновлена.");
                    } else {
                        System.out.println("Машина не найдена.");
                    }
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }
}

class Car {
    private static int nextId = 1;
    private int id;
    private String model;
    private String location;
    private double price;
    private boolean available;
    private Owner owner;

    public Car(String model, String location, double price, Owner owner) {
        this.id = nextId++;
        this.model = model;
        this.location = location;
        this.price = price;
        this.available = true;
        this.owner = owner;
    }

    public int getId() { return id; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return id + " | " + model + " | " + location + " | $" + price + (available ? " | Доступен" : " | Забронирован");
    }
}

class Booking {
    private Car car;
    private Customer customer;
    private boolean paid;

    public Booking(Car car, Customer customer) {
        this.car = car;
        this.customer = customer;
        this.paid = false;
    }

    public Car getCar() {
        return car;
    }

    public boolean isPaid() {
        return paid;
    }

    public void pay() {
        this.paid = true;
    }

    @Override
    public String toString() {
        return "Машина: " + car + " | Клиент: " + customer.getUsername() + " | Оплачено: " + paid;
    }
}

class SystemData {
    public List<User> users = new ArrayList<>();
    public List<Car> cars = new ArrayList<>();
    public List<Booking> bookings = new ArrayList<>();

    public Car getCarById(int id) {
        for (Car c : cars) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    public void showAvailableCars() {
        for (Car c : cars) {
            if (c.isAvailable()) {
                System.out.println(c);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SystemData data = new SystemData();

        // Тестовые пользователи
        data.users.add(new Customer("client1", "123"));
        data.users.add(new Owner("owner1", "123"));
        data.users.add(new Admin("admin1", "123"));

        while (true) {
            System.out.println("\n=== Система аренды авто ===");
            System.out.print("Логин: ");
            String username = scanner.nextLine();
            System.out.print("Пароль: ");
            String password = scanner.nextLine();

            User user = null;
            for (User u : data.users) {
                if (u.getUsername().equals(username) && u.checkPassword(password)) {
                    user = u;
                    break;
                }
            }

            if (user != null) {
                System.out.println("Успешный вход как " + user.getClass().getSimpleName());
                user.showMenu(scanner, data);
            } else {
                System.out.println("Неверный логин или пароль.");
            }
        }
    }
}
