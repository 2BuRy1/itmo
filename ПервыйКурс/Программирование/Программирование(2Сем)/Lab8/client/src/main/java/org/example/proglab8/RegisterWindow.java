package org.example.proglab8;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import Commands.Login;
import Commands.Register;
import Managers.RegisterStatus;
import Managers.Shake;
import Network.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class RegisterWindow {
    Client client = ApplicationClient.getClient();


    static Locale locale;

    public static User user;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button English;

    @FXML
    private Button Islandian;

    @FXML
    private Button Es;

    @FXML
    private Button Russian;

    @FXML
    private Button LoginButton;

    @FXML
    private TextField LoginField;


    @FXML
    private TextField RegisterTitle;

    @FXML
    private PasswordField PasswordField1;

    @FXML
    private PasswordField PasswordField2;

    @FXML
    private Button RegisterButton;

    @FXML
    void initialize() {

        RegisterButton.setOnAction(actionEvent -> {
            String login = LoginField.getText();
            String password = PasswordField1.getText();
            String password1 = PasswordField2.getText();
            if (!password1.equals(password) || password.isEmpty() || login.isEmpty()) {
                Shake shake = new Shake(PasswordField1);

                Shake shake2 = new Shake(PasswordField2);

                Shake shake1 = new Shake(LoginField);

            } else {
                try {
                    var answer = register(login, password);
                    if (answer.getLoginError() == LoginError.LOGIN_ERROR) {
                        Shake shake = new Shake(LoginField);
                        Shake shake2 = new Shake(PasswordField2);
                        Shake shake1 = new Shake(PasswordField1);

                    } else {
                        RegisterStatus.isLoggin = false;
                        RegisterButton.getScene().getWindow().hide();
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(LoginWindow.class.getResource("Main.fxml"));
                        fxmlLoader.setResources(ResourceBundle.getBundle("org.example.proglab8.l"));
                        try {
                            fxmlLoader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Parent root = fxmlLoader.getRoot();
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();

                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        LoginButton.setOnAction(actionEvent1 -> {
            LoginButton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(LoginWindow.class.getResource("Login.fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle("org.example.proglab8.l"));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = fxmlLoader.getRoot();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();


        });

        English.setOnAction(actionEvent -> {
            locale = new Locale("en", "EN");
            resources = ResourceBundle.getBundle("org/example/proglab8/l", locale);
            LoginButton.setText(resources.getString("Login_button"));
            PasswordField1.setPromptText(resources.getString("Password_Field"));
            PasswordField2.setPromptText(resources.getString("Password_Field"));
            LoginField.setPromptText(resources.getString("Login_Field"));
            RegisterButton.setText(resources.getString("Register_Button"));
            RegisterTitle.setText(resources.getString("Register_Title"));
        });

        Russian.setOnAction(actionEvent -> {
            locale = new Locale("ru", "RU");
            resources = ResourceBundle.getBundle("org/example/proglab8/l", locale);
            LoginButton.setText(resources.getString("Login_button"));
            PasswordField1.setPromptText(resources.getString("Password_Field"));
            PasswordField2.setPromptText(resources.getString("Password_Field"));
            LoginField.setPromptText(resources.getString("Login_Field"));
            RegisterButton.setText(resources.getString("Register_Button"));
            RegisterTitle.setText(resources.getString("Register_Title"));
        });

        Islandian.setOnAction(actionEvent -> {
            locale = new Locale("is", "IS");
            resources = ResourceBundle.getBundle("org/example/proglab8/l", locale);
            LoginButton.setText(resources.getString("Login_button"));
            PasswordField1.setPromptText(resources.getString("Password_Field"));
            PasswordField2.setPromptText(resources.getString("Password_Field"));
            LoginField.setPromptText(resources.getString("Login_Field"));
            RegisterButton.setText(resources.getString("Register_Button"));
            RegisterTitle.setText(resources.getString("Register_Title"));
        });
        Es.setOnAction(actionEvent -> {
            locale = new Locale("es", "ES");
            resources = ResourceBundle.getBundle("org/example/proglab8/l", locale);
            LoginButton.setText(resources.getString("Login_button"));
            PasswordField1.setPromptText(resources.getString("Password_Field"));
            PasswordField2.setPromptText(resources.getString("Password_Field"));
            LoginField.setPromptText(resources.getString("Login_Field"));
            RegisterButton.setText(resources.getString("Register_Button"));
            RegisterTitle.setText(resources.getString("Register_Title"));
        });


    }


    private Response register(String login, String password) throws InterruptedException {
        user = new User(login, password);
        var answer = client.sendRequest(new Request(new Register(), user));
        return answer;
    }
}

