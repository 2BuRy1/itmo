package Managers;

import Enums.AstartesCategory;
import Enums.MeleeWeapon;
import Enums.Weapon;
import MainClasses.Chapter;
import MainClasses.Coordinates;
import MainClasses.SpaceMarine;
import Network.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class DataBaseManager {

    String alphabet = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
    private QueryManager queryManager = new QueryManager();


    public Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:9999/studs", "", "");
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Ошибка подключения к базе данных");
            e.printStackTrace();
            ;
        }
        return null;
    }



    public boolean existUser(User user) {
        try {
            PasswordHasherManager passwordHasherManager = new PasswordHasherManager();
            Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.findUser);
            preparedStatement.setString(1, user.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String password = user.getPassword() + resultSet.getString("salt");
                if (resultSet.getString("passwd").equals(passwordHasherManager.hashPassword(password))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка выполнения запроса");
            e.printStackTrace();
            return false;
        }
        return false;
    }


    public void addUser(User user) {
        Connection connection = connect();
        try {
            PasswordHasherManager passwordHasherManager = new PasswordHasherManager();
            String salt = saltGenerator();
            String password = passwordHasherManager.hashPassword(user.getPassword() + salt );

            PreparedStatement pr = connection.prepareStatement(queryManager.addUser);
            pr.setString(1, user.getLogin());
            pr.setString(2, password);
            pr.setString(3, salt);
            pr.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка при выполнении запроса");
            e.printStackTrace();

        }
    }


    public int addmarines(SpaceMarine spaceMarine, User user) {
        Connection connection = connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.addSpaceMarine);
            preparedStatement.setString(1, spaceMarine.getName());
            preparedStatement.setLong(2, spaceMarine.getY());
            preparedStatement.setDouble(3, spaceMarine.getX());
            preparedStatement.setString(4, spaceMarine.getCreationDate());
            preparedStatement.setDouble(5, spaceMarine.getHealth());
            preparedStatement.setString(6, spaceMarine.getCategory());
            preparedStatement.setString(7, spaceMarine.getWeaponType());
            preparedStatement.setString(8, spaceMarine.getMeleeNameWeapon());
            preparedStatement.setString(9, spaceMarine.getChapter().getName());
            preparedStatement.setInt(10, spaceMarine.getChapter().getMarinesCount());
            preparedStatement.setString(11, user.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.err.println("Не удалось добавить объект");
                return -1;
            }
            System.err.println("Объект был успешно добавлен");
            return resultSet.getInt(1);
        } catch (SQLException e) {
            System.err.println("Ошибка при выполнении запроса");
            ;
            return -1;

        }


    }

    public boolean deleteUserObjects(User user, List<Long> ids) {
        Connection connection = connect();
        try {
            for (Long id : ids) {
                PreparedStatement preparedStatement = connection.prepareStatement(queryManager.clearObjects);
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setLong(2, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet.next();
            }
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteObject(Long id, User user) {
        Connection connection = connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.deleteObject);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setLong(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            return false;
        }

    }

    public boolean updateObject(Long id, User user, SpaceMarine spaceMarine) {
        Connection connection = connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.updateObject);
            preparedStatement.setString(1, spaceMarine.getName());
            preparedStatement.setLong(2, spaceMarine.getY());
            preparedStatement.setDouble(3, spaceMarine.getX());
            preparedStatement.setString(4, spaceMarine.getCreationDate());
            preparedStatement.setDouble(5, spaceMarine.getHealth());
            preparedStatement.setString(6, spaceMarine.getCategory());
            preparedStatement.setString(7, spaceMarine.getWeaponType());
            preparedStatement.setString(8, spaceMarine.getMeleeNameWeapon());
            preparedStatement.setString(9, spaceMarine.getChapter().getName());
            preparedStatement.setInt(10, spaceMarine.getChapter().getMarinesCount());
            preparedStatement.setString(11, user.getLogin());
            preparedStatement.setLong(12, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public Vector<SpaceMarine> createCollection() {
        Connection connection = connect();
        Vector<SpaceMarine> marines = new Vector<>();
        try {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(queryManager.addObjects);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {

                    marines.add(new SpaceMarine(resultSet.getLong(1),
                            resultSet.getString(2),
                            new Coordinates(resultSet.getDouble(3), resultSet.getLong(4)),
                            LocalDateTime.parse(resultSet.getString(5)),
                            resultSet.getDouble(6),
                            AstartesCategory.valueOf(resultSet.getString(7)),
                            Weapon.valueOf(resultSet.getString(8)),
                            MeleeWeapon.valueOf(resultSet.getString(9)),
                            new Chapter(resultSet.getString(10), resultSet.getInt(11)),
                            resultSet.getString(12)));


                }
                return marines;


            } catch (SQLException e) {
                System.err.println("Ошибка выполнения запроса");
                return new Vector<>();
            }


        } catch (IllegalArgumentException e) {
            System.err.println("Поля объектов не валидны");
            return new Vector<>();
        }
    }


    private String saltGenerator() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(15);

        for (int i = 0; i < 15; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }


}









