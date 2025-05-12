package src.models;

import src.SystemData;
import java.util.Scanner;

public class Admin extends User {
    public Admin(String name, String login, String password) {
        super(name, login, password);
    }

    @Override
    public void menu(Scanner scanner) {
        while (true) {
            System.out.println("\n1. Показать всех пользователей");
            System.out.println("2. Назначить цену на машину");
            System.out.println("3. Выйти");
            System.out.print("Выбор: ");
            int choice = scanner.nextInt(); scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Клиенты:");
                    SystemData.customers.forEach(c -> System.out.println("- " + c.name));
                    System.out.println("Владельцы:");
                    SystemData.owners.forEach(o -> System.out.println("- " + o.name));
                }
                case 2 -> {
                    System.out.print("Введите модель машины: ");
                    String model = scanner.nextLine();
                    Car car = SystemData.cars.stream().filter(c -> c.getModel().equalsIgnoreCase(model)).findFirst().orElse(null);
                    if (car != null) {
                        System.out.print("Новая цена: ");
                        double price = scanner.nextDouble(); scanner.nextLine();
                        car.setPrice(price);
                        System.out.println("Цена обновлена.");
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
