package org.example.proglab8;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import Enums.AstartesCategory;
import Enums.MeleeWeapon;
import Enums.Weapon;
import MainClasses.*;
import Network.Client;
import Network.Request;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import Commands.*;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TableManager {
    Client client = ApplicationClient.getClient();

    Locale locale = MainPage.locale;

    Vector<SpaceMarine> marines =client.sendRequest(new Request(new Marines(), MainPage.user)).getMarines();;


     ObservableList<SpaceMarine> list = FXCollections.observableList(marines);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<SpaceMarine, AstartesCategory> CATEGORY;

    @FXML
    private TableColumn<SpaceMarine, Double> HEALTH;

    @FXML
    private TableColumn<SpaceMarine, Long> Id;

    @FXML
    private TableColumn<SpaceMarine, Integer> MarinesCount;

    @FXML
    private TableColumn<SpaceMarine, String> MarinesName;

    @FXML
    private TableColumn<SpaceMarine, String> NAME;

    @FXML
    private TableColumn<SpaceMarine, Enums.Weapon> WEAPONTYPE;

    @FXML
    private TableColumn<SpaceMarine, MeleeWeapon> Weapon;

    @FXML
    private TableColumn<SpaceMarine, Double> X;

    @FXML
    private TableColumn<SpaceMarine, Long> Y;
    @FXML
    private TableView<SpaceMarine> table;

    @FXML
    private Button delete;

    @FXML
    private TextField Filter;

    public TableManager() throws InterruptedException {
    }


    @FXML
    void initialize() {

    Filter.setPromptText(resources.getString("Filter"));
    delete.setText(resources.getString("Delete_Row"));



        initializeTables(list);
        Id.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            list.stream().filter(spaceMarine1 -> spaceMarine1.getId() < spaceMarine1.getId()).collect(Vector<SpaceMarine>::new, Vector<SpaceMarine>::add, Vector<SpaceMarine>::addAll);
            table.setItems(list);
            table.refresh();
        });
        NAME.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            list.stream().filter(spaceMarine1 -> spaceMarine1.getName().length() > spaceMarine1.getName().length())
                    .collect(Vector<SpaceMarine>::new, Vector<SpaceMarine>::add, Vector<SpaceMarine>::addAll);
            table.setItems(list);
            table.refresh();
        });
        HEALTH.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            list.stream().filter(spaceMarine1 -> spaceMarine1.getHealth() > spaceMarine1.getHealth())
                    .collect(Vector<SpaceMarine>::new, Vector<SpaceMarine>::add, Vector<SpaceMarine>::addAll);
            table.setItems(list);
            table.refresh();
        });
        CATEGORY.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            list.stream().filter(spaceMarine1 -> spaceMarine1.getCategoryAstartes().getLvl() > spaceMarine1.getCategoryAstartes().getLvl())
                    .collect(Vector<SpaceMarine>::new, Vector<SpaceMarine>::add, Vector<SpaceMarine>::addAll);
            table.setItems(list);
            table.refresh();
        });
        WEAPONTYPE.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            list.stream().filter(spaceMarine1 -> spaceMarine1.getWeapon().ordinal() > spaceMarine1.getWeapon().ordinal())
                    .collect(Vector<SpaceMarine>::new, Vector<SpaceMarine>::add, Vector<SpaceMarine>::addAll);
            table.setItems(list);
            table.refresh();
        });
        Weapon.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            list.stream().filter(spaceMarine1 -> spaceMarine1.getMeleeWeapon().ordinal() > spaceMarine1.getMeleeWeapon().ordinal())
                    .collect(Vector<SpaceMarine>::new, Vector<SpaceMarine>::add, Vector<SpaceMarine>::addAll);
            table.setItems(list);
            table.refresh();
        });
        X.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            list.stream().filter(spaceMarine1 -> spaceMarine1.getX() > spaceMarine1.getX())
                    .collect(Vector<SpaceMarine>::new, Vector<SpaceMarine>::add, Vector<SpaceMarine>::addAll);
            table.setItems(list);
            table.refresh();
        });
        Y.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            list.stream().filter(spaceMarine1 -> spaceMarine1.getY() > spaceMarine1.getY())
                    .collect(Vector<SpaceMarine>::new, Vector<SpaceMarine>::add, Vector<SpaceMarine>::addAll);
            table.setItems(list);
            table.refresh();
        });
        MarinesName.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            list.stream().filter(spaceMarine1 -> spaceMarine1.getChapter().getName().length() > spaceMarine1.getChapter().getName().length())
                    .collect(Vector<SpaceMarine>::new, Vector<SpaceMarine>::add, Vector<SpaceMarine>::addAll);
            table.setItems(list);
            table.refresh();
        });
        MarinesCount.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            list.stream().filter(spaceMarine1 -> spaceMarine1.getMarinesCount() > spaceMarine1.getMarinesCount())
                    .collect(Vector<SpaceMarine>::new, Vector<SpaceMarine>::add, Vector<SpaceMarine>::addAll);
            table.setItems(list);
            table.refresh();
        });

        table.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            if(event.getClickCount() == 2) {
                SpaceMarine spaceMarine = table.getSelectionModel().getSelectedItem();
                if (spaceMarine != null) {
                    UpdateManager.spaceMarine = spaceMarine;
                    UpdateManager.id = spaceMarine.getId();
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(TableManager.class.getResource("ObjectUpdater.fxml"));
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
                    stage.showAndWait();
                    try {
                            marines = client.sendRequest(new Request(new Marines(), MainPage.user)).getMarines();
                            list = FXCollections.observableArrayList(marines);
                            table.setItems(list);
                            table.refresh();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);

                        }
                    }
                }

        });
        table.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {

        });
        table.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {

        });
        delete.setOnAction(event -> {

            SpaceMarine spaceMarine = table.getSelectionModel().getSelectedItem();
            if (spaceMarine != null) {
                try {
                    client.sendRequest(new Request(new Remove(), spaceMarine.getId(), MainPage.user));
                    Vector<SpaceMarine> updatedList = client.sendRequest(new Request(new Marines(), MainPage.user)).getMarines();
                    list = FXCollections.observableArrayList(updatedList);
                    table.setItems(list);
                    table.refresh();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        FilteredList<SpaceMarine> filteredList = new FilteredList<>(list, b -> true);
        Filter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(spaceMarine -> {

                String Y = String.valueOf(spaceMarine.getY());
                String X = String.valueOf(spaceMarine.getX());
                String marinesCount = String.valueOf(spaceMarine.getMarinesCount());
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (spaceMarine.getId().toString().contains(lowerCaseFilter)) {
                    return true;
                } else if (spaceMarine.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (spaceMarine.getWeapon().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (spaceMarine.getCategoryAstartes().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (spaceMarine.getMeleeWeapon().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (spaceMarine.getChapter().getName().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (spaceMarine.getX().toString().contains(lowerCaseFilter)) {
                    return true;
                } else if (Y.contains(lowerCaseFilter)) {
                    return true;
                } else if (marinesCount.contains(lowerCaseFilter)) {
                    return true;
                }
                else if(spaceMarine.getHealth().toString().contains(lowerCaseFilter)) {
                    return true;
                }

                return false;
            });
            SortedList<SpaceMarine> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedList);
            table.refresh();
        });


    }


    private void initializeTables(ObservableList<SpaceMarine> list) {
        Id.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));
        NAME.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getName()));
        WEAPONTYPE.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getWeapon()));
        CATEGORY.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getCategoryAstartes()));
        X.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getX()));
        Y.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getY()));
        MarinesCount.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getMarinesCount()));
        MarinesName.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getChapter().getName()));
        HEALTH.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getHealth()));
        Weapon.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getMeleeWeapon()));

        table.setItems(list);
    }



}
