package org.example.proglab8;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import Commands.Login;
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

public class LoginWindow {

    Client client = ApplicationClient.getClient();

    public static User user;

    static Locale locale;

    @FXML
    private TextField Logintitle;

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
    private PasswordField PasswordField;

    @FXML
    private Button RegisterButton;

    @FXML
    void initialize() {

       LoginButton.setOnAction(actionEvent -> {
           String login = LoginField.getText();
           String password = PasswordField.getText();

               try {
                   var answer = login(login, password);
                   if(answer.getLoginError() == LoginError.LOGIN_ERROR || login.isEmpty() || password.isEmpty()){
                       Shake shake = new Shake(LoginField);
                       Shake shake2 = new Shake(PasswordField);
                   }else{
                       RegisterStatus.isLoggin = true;
                       LoginButton.getScene().getWindow().hide();
                       FXMLLoader fxmlLoader = new FXMLLoader();
                       fxmlLoader.setLocation(LoginWindow.class.getResource("Main.fxml"));
                       fxmlLoader.setResources(ResourceBundle.getBundle("org.example.proglab8.l"));
                       try {
                           fxmlLoader.load();
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                       Parent root = fxmlLoader.getRoot();
                       Scene scene= new Scene(root);
                       Stage stage = new Stage();
                       stage.setScene(scene);
                       stage.setResizable(false);
                       stage.show();

                   }


               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }


       });



       RegisterButton.setOnAction(actionEvent -> {
           RegisterButton.getScene().getWindow().hide();
           FXMLLoader fxmlLoader = new FXMLLoader();
           fxmlLoader.setLocation(LoginWindow.class.getResource("Register.fxml"));
           fxmlLoader.setResources(ResourceBundle.getBundle("org.example.proglab8.l"));
           try {
               fxmlLoader.load();
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
           Parent root = fxmlLoader.getRoot();
           Scene scene= new Scene(root);
           Stage stage = new Stage();
           stage.setScene(scene);
           stage.setResizable(false);
           stage.showAndWait();


       });

       English.setOnAction(actionEvent -> {
           locale = new Locale("en", "EN");
           resources = ResourceBundle.getBundle("org/example/proglab8/l", locale);
           LoginButton.setText(resources.getString("Login_button"));
           PasswordField.setPromptText(resources.getString("Password_Field"));
           LoginField.setPromptText(resources.getString("Login_Field"));
           RegisterButton.setText(resources.getString("Register_Button"));
           Logintitle.setText(resources.getString("Login_Title"));
       });

       Russian.setOnAction(actionEvent -> {
           locale = new Locale("ru", "RU");
           resources = ResourceBundle.getBundle("org/example/proglab8/l", locale);
           LoginButton.setText(resources.getString("Login_button"));
           PasswordField.setPromptText(resources.getString("Password_Field"));
           LoginField.setPromptText(resources.getString("Login_Field"));
           RegisterButton.setText(resources.getString("Register_Button"));
           Logintitle.setText(resources.getString("Login_Title"));
       });

       Islandian.setOnAction(actionEvent -> {
           locale = new Locale("is", "IS");
           resources = ResourceBundle.getBundle("org/example/proglab8/l", locale);
           LoginButton.setText(resources.getString("Login_button"));
           PasswordField.setPromptText(resources.getString("Password_Field"));
           LoginField.setPromptText(resources.getString("Login_Field"));
           RegisterButton.setText(resources.getString("Register_Button"));
           Logintitle.setText(resources.getString("Login_Title"));
       });
       Es.setOnAction(actionEvent -> {
           locale = new Locale("es" ,"ES");
           resources = ResourceBundle.getBundle("org/example/proglab8/l", locale);
           LoginButton.setText(resources.getString("Login_button"));
           PasswordField.setPromptText(resources.getString("Password_Field"));
           LoginField.setPromptText(resources.getString("Login_Field"));
           RegisterButton.setText(resources.getString("Register_Button"));
           Logintitle.setText(resources.getString("Login_Title"));
       });

    }


    private Response login(String login, String password) throws InterruptedException {

        user = new User(login, password);
        var answer = client.sendRequest(new Request(new Login(), user));
        return answer;
    }




}
