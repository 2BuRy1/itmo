package org.example.proglab8;

import java.net.URL;
import java.util.ResourceBundle;

import Managers.Shake;
import Network.Client;
import Network.Request;
import Network.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import Commands.*;

public class RemoveManager {

    User user = MainPage.user;
    Client client = ApplicationClient.getClient();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Id;

    @FXML
    private TextArea Response;

    @FXML
    private Button SendRequest;

    @FXML
    void initialize() {

        SendRequest.setOnAction(event -> {
            try {
                Response.clear();
                if (Id.getText().isEmpty()) {
                    Shake shake = new Shake(Id);
                }
                else {
                    Long id = Long.parseLong(Id.getText());
                    var response = client.sendRequest(new Request(new Remove(), id, user));
                    Response.setText(response.getResult());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
    }
}
