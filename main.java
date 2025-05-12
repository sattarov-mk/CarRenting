package src;

import src.models.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SystemData.initializeSampleData();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в систему бронирования автомобилей!");

        System.out.print("Введите логин: ");
        String login = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        User user = SystemData.authenticate(login, password);
        if (user == null) {
            System.out.println("Неверные данные.");
            return;
        }

        user.menu(scanner);
    }
}
