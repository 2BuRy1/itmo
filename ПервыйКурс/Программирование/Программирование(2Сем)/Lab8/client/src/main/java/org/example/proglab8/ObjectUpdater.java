package org.example.proglab8;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.Vector;

import Enums.AstartesCategory;
import Enums.MeleeWeapon;
import Enums.Weapon;
import MainClasses.Chapter;
import MainClasses.Coordinates;
import MainClasses.SpaceMarine;
import Network.Client;
import Network.Request;
import Network.Response;
import Network.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Commands.Update;
import Commands.Marines;

public class ObjectUpdater {

    Client client = ApplicationClient.getClient();

    Long id = UpdateManager.id;

    User user = MainPage.user;

    SpaceMarine spaceMarine = UpdateManager.spaceMarine;
    ObservableList<AstartesCategory> categories = FXCollections.observableArrayList(AstartesCategory.SCOUT, AstartesCategory.DREADNOUGHT, AstartesCategory.ASSAULT, AstartesCategory.LIBRARIAN, AstartesCategory.APOTHECARY);

    ObservableList<Weapon> weapons = FXCollections.observableArrayList(Weapon.MELTAGUN, Weapon.BOLT_PISTOL, Weapon.HEAVY_FLAMER, Weapon.MULTI_MELTA);

    ObservableList<Enums.MeleeWeapon> meleeweapon = FXCollections.observableArrayList(Enums.MeleeWeapon.MANREAPER, Enums.MeleeWeapon.CHAIN_AXE, Enums.MeleeWeapon.LIGHTING_CLAW, Enums.MeleeWeapon.POWER_BLADE, Enums.MeleeWeapon.POWER_FIST);


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
    private ChoiceBox<Enums.MeleeWeapon> MeleeWeapon;

    @FXML
    private TextField Name;

    @FXML
    private TextArea Response;

    @FXML
    private ChoiceBox<Weapon> WeaponType;

    @FXML
    private TextField X;

    @FXML
    private TextField Y;

    @FXML
    void initialize() {
        Name.setText(spaceMarine.getName());
        ChapterName.setText(spaceMarine.getChapter().getName());
        Health.setText(String.valueOf(spaceMarine.getHealth()));
        MarinesCount.setText(String.valueOf(spaceMarine.getMarinesCount()));
        X.setText(String.valueOf(spaceMarine.getX()));
        Y.setText(String.valueOf(spaceMarine.getY()));
        Category.setItems(categories);
        WeaponType.setItems(weapons);
        MeleeWeapon.setItems(meleeweapon);
        Category.setValue(spaceMarine.getCategoryAstartes());
        WeaponType.setValue(spaceMarine.getWeapon());
        MeleeWeapon.setValue(spaceMarine.getMeleeWeapon());
        Add.setOnAction(actionEvent -> {
            String name = Name.getText();
            String chapterName = ChapterName.getText();
            Double health = Double.parseDouble(Health.getText());
            int count = Integer.parseInt(MarinesCount.getText());
            Double x = Double.parseDouble(X.getText());
            long y = Long.parseLong(Y.getText());
            AstartesCategory astartesCategory = Category.getValue();
            Weapon weapon = WeaponType.getValue();
            Enums.MeleeWeapon meleeWeapon = MeleeWeapon.getValue();
            SpaceMarine spaceMarine = new SpaceMarine(name, new Coordinates(x, y), LocalDateTime.now(), health, astartesCategory, weapon, meleeWeapon, new Chapter(chapterName, count), user.getLogin());
            if(!spaceMarine.validate()){
                Response.setText("Введены невалидные данные");
            }
            else{

                try {
                   var response = client.sendRequest(new Request(new Update(), spaceMarine, id, user));
//                    Vector <SpaceMarine> marines = client.sendRequest(new Request(new Marines(), user)).getMarines();
//                    TableManager.marines = marines;
//                    TableManager.list = FXCollections.observableList(marines);
                    Response.setText(response.getResult());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }


        });
    }

}
