package src.models;

import src.SystemData;
import java.util.Scanner;

public class Owner extends User {
    public Owner(String name, String login, String password) {
        super(name, login, password);
    }

    @Override
    public void menu(Scanner scanner) {
        while (true) {
            System.out.println("\n1. Добавить машину");
            System.out.println("2. Подтвердить брони");
            System.out.println("3. Выйти");
            System.out.print("Выбор: ");
            int choice = scanner.nextInt(); scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Модель: ");
                    String model = scanner.nextLine();
                    System.out.print("Город: ");
                    String location = scanner.nextLine();
                    System.out.print("Цена: ");
                    double price = scanner.nextDouble(); scanner.nextLine();
                    Car car = new Car(model, location, price, this);
                    SystemData.cars.add(car);
                    System.out.println("Машина добавлена.");
                }
                case 2 -> {
                    SystemData.bookings.stream().filter(b -> b.getCar().getOwner().equals(this)).forEach(b -> {
                        System.out.println("Бронь: " + b);
                        b.setConfirmed(true);
                    });
                    System.out.println("Все брони подтверждены.");
                }
                case 3 -> { return; }
                default -> System.out.println("Неверный ввод.");
            }
        }
    }
}
