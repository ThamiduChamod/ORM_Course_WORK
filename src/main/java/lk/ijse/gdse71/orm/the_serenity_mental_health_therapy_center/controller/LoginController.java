package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import at.favre.lib.crypto.bcrypt.BCrypt;



import java.io.IOException;

public class LoginController {
    public AnchorPane loginAnchorpPane;
    public TextField userNameText;
    public PasswordField passwordText;

    public void CreateAccountBtnOnAction(ActionEvent event) {

        try {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/CreateAccountView.fxml"));
            loginAnchorpPane.getChildren().clear();
            loginAnchorpPane.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Can't load CREATE ACCOUNT page").showAndWait();
        }

    }

    public void loginPageOnAction(ActionEvent event) {

//        BCrypt
//        if ()
//


        try {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
            loginAnchorpPane.getChildren().clear();
            loginAnchorpPane.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Can't load Main page").showAndWait();
        }
    }
}
