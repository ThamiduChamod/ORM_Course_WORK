package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import at.favre.lib.crypto.bcrypt.BCrypt;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.BOFactory;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.AccountBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.LoginpageBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.DAOFactory;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.AccountDAO;


import java.io.IOException;

public class LoginController {
    public AnchorPane loginAnchorpPane;
    public TextField userNameText;
    public PasswordField passwordText;

    LoginpageBO loginpageBO = (LoginpageBO) BOFactory.getInstance().getBO(BOFactory.BOType.LOGIN);


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

        String username = userNameText.getText();
        String enteredPassword = passwordText.getText();



        // Step 1: Check if the username exists
        if (loginpageBO.searchUser(username)) {
            System.out.println("bbbbb");

            String jobRollByUsername = loginpageBO.getJobRollByUsername(username);
            System.out.println(jobRollByUsername);
            // Step 2: Check if the entered password matches the stored password


//            String job =loginpageBO.checkPassword(enteredPassword,username);
//            System.out.println(job);
            if (loginpageBO.checkPassword(enteredPassword,username) ) {
                System.out.println("aaaaa");
                try {
//                    AnchorPane load = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
//                    loginAnchorpPane.getChildren().clear();
//                    loginAnchorpPane.getChildren().add(load);
//                    MainPageController controller = loader.getController();
//                    controller.setData(jobRollByUsername);

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));
                    AnchorPane load = loader.load();
                    loginAnchorpPane.getChildren().clear();
                    loginAnchorpPane.getChildren().add(load);
                    MainPageController controller = loader.getController();
                    controller.setData(jobRollByUsername);



//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainLayout.fxml"));
//                    Parent load = loader.load(); // Load and get the root node
//
//                    MainPageController controller = loader.getController();
//                    controller.setData(jobRollByUsername);
//
//                    loginAnchorpPane.getChildren().clear();
//                    loginAnchorpPane.getChildren().add(load);

                } catch (IOException e) {
                    new Alert(Alert.AlertType.ERROR, "Can't load Main page").showAndWait();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid password!").showAndWait();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "User not found!").showAndWait();
        }


    }
}
