package org.example.proglab8;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.regex.PatternSyntaxException;

import Enums.AstartesCategory;
import Enums.MeleeWeapon;
import Enums.Weapon;
import MainClasses.Chapter;
import MainClasses.Coordinates;
import MainClasses.SpaceMarine;
import Network.Client;
import Network.Request;
import Network.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Commands.*;

public class MarinesCreation {

    ObservableList<AstartesCategory> categories = FXCollections.observableArrayList(AstartesCategory.SCOUT, AstartesCategory.DREADNOUGHT, AstartesCategory.ASSAULT, AstartesCategory.LIBRARIAN, AstartesCategory.APOTHECARY);

    ObservableList<Weapon> weapons = FXCollections.observableArrayList(Weapon.MELTAGUN, Weapon.BOLT_PISTOL, Weapon.HEAVY_FLAMER, Weapon.MULTI_MELTA);

    ObservableList<Enums.MeleeWeapon> meleeweapon = FXCollections.observableArrayList(Enums.MeleeWeapon.MANREAPER, Enums.MeleeWeapon.CHAIN_AXE, Enums.MeleeWeapon.LIGHTING_CLAW, Enums.MeleeWeapon.POWER_BLADE, Enums.MeleeWeapon.POWER_FIST);

    User user = MainPage.user;
    Client client = ApplicationClient.getClient();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Add;

    @FXML
    private ChoiceBox<AstartesCategory> Category;

    @FXML
    private TextField ChapterName;

    @FXML
    private TextField Health;

    @FXML
    private TextField MarinesCount;

    @FXML
    private ChoiceBox<MeleeWeapon> MeleeWeapon;

    @FXML
    private TextField Name;

    @FXML
    private ChoiceBox<Weapon> WeaponType;

    @FXML
    private TextField X;

    @FXML
    private TextArea Response;

    @FXML
    private TextField Y;

    @FXML
    void initialize() {
        Category.setItems(categories);
        WeaponType.setItems(weapons);
        MeleeWeapon.setItems(meleeweapon);
        Add.setOnAction(actionEvent -> {
            try {
                String name = Name.getText();
                String chapterName = ChapterName.getText();
                Double health = Double.parseDouble(Health.getText());
                int count = Integer.parseInt(MarinesCount.getText());
                Double x = Double.parseDouble(X.getText());
                long y = Long.parseLong(Y.getText());
                AstartesCategory astartesCategory = Category.getValue();
                Weapon weapon = WeaponType.getValue();
                MeleeWeapon meleeWeapon = MeleeWeapon.getValue();
                SpaceMarine spaceMarine = new SpaceMarine(name, new Coordinates(x, y), LocalDateTime.now(), health, astartesCategory, weapon, meleeWeapon, new Chapter(chapterName, count), user.getLogin());
                if(!spaceMarine.validate()){
                    Response.setText("Введены невалидные данные");
                }
                else{
                    var response = client.sendRequest(new Request(Commands.isAdd == true ? new Add() : new AddIfMin(), spaceMarine, user));
                    Response.setText(response.getResult());
                }
            } catch (NumberFormatException e) {
                Response.setText("Нужно вводить числа, а не буквоки");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

    }


}
