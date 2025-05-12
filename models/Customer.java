package src.models;

import src.SystemData;
import java.util.Scanner;

public class Customer extends User {
    public Customer(String name, String login, String password) {
        super(name, login, password);
    }

    @Override
    public void menu(Scanner scanner) {
        while (true) {
            System.out.println("\n1. Найти машины");
            System.out.println("2. Забронировать машину");
            System.out.println("3. Выйти");
            System.out.print("Выбор: ");
            int choice = scanner.nextInt(); scanner.nextLine();

            switch (choice) {
                case 1 -> SystemData.cars.forEach(System.out::println);
                case 2 -> {
                    System.out.print("Введите модель: ");
                    String model = scanner.nextLine();
                    Car found = SystemData.cars.stream().filter(c -> c.getModel().equalsIgnoreCase(model)).findFirst().orElse(null);
                    if (found != null) {
                        Booking booking = new Booking(this, found);
                        SystemData.bookings.add(booking);
                        System.out.println("Машина забронирована! Воображаемая оплата прошла успешно.");
                    } else {
                        System.out.println("Машина не найдена.");
                    }
                }
                case 3 -> { return; }
                default -> System.out.println("Неверный ввод.");
            }
        }
    }
}
