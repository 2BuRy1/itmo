package Network;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class User implements Serializable {
    @Serial
    private final static long serialVersionUID = 29L;

    private String login;

    private String password;





    public boolean isLogin(){
        Scanner scanner = new Scanner(System.in);
        String line;
        while(true){
            System.out.println("Вы уже зарагестрированы на сервере? [y/n]");
            line=scanner.nextLine();
            switch (line) {
                case "y", "yes", "Y", "YES", "ДА", "да" -> {
                    return true;
                }
                case "n", "no", "N", "NO", "НЕТ", "нет" -> {
                    return false;
                }
                default -> System.out.println("Неверная команда");


            }

        }

    }


    public User(String login, String password) {
        this.login = login;
        this.password = password;

    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
