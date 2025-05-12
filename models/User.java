package src.models;

import java.util.Scanner;

public abstract class User {
    protected String name;
    protected String login;
    protected String password;

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public String getLogin() { return login; }
    public String getPassword() { return password; }

    public abstract void menu(Scanner scanner);
}
