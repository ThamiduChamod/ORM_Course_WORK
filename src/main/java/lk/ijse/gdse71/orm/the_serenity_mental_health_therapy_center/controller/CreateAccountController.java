package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.BOFactory;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.AccountBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.AccountDTO;
import at.favre.lib.crypto.bcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {
    public Label userIdLbl;
    public TextField nameText;
    public TextField nicText;
    public TextField emailText;
    public TextField phoneText;
    public PasswordField passwordText;
    public PasswordField confirmPassswordText;
    public Button registerBtn;
    public ComboBox<String> jobCmb;
    public AnchorPane createAcountAnchorPane;
    public RadioButton adminRBtn;
    public RadioButton userRBtn;
    public String jobrool;


    AccountBO accountBO = (AccountBO) BOFactory.getInstance().getBO(BOFactory.BOType.ACCOUNT);



    public void registerBtnClicked(ActionEvent event) {

        String password = BCrypt.withDefaults().hashToString(13, passwordText.getText().toCharArray());


        String name = nameText.getText();
        String nic = nicText.getText();
        String email = emailText.getText();
        String phone = phoneText.getText();


        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
        String emailPattern = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        String phonePattern = "^07\\d{8}$";


        boolean isValidName = name.matches(namePattern);
        boolean isValidNic =nic.matches(nicPattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);
        //boolean isSame = passwordText.getText().equals(confirmPassswordText);


        if (!isValidName){
            nameText.setStyle(nameText.getStyle() + ";-fx-text-fill: red;");
        }else nameText.setStyle(nameText.getStyle() + ";-fx-text-fill: black;");
        if (!isValidNic){
            nicText.setStyle(nicText.getStyle() + ";-fx-text-fill: red;");
        }else  nicText.setStyle(nicText.getStyle() + ";-fx-text-fill: black;");
        if (!isValidPhone){
            phoneText.setStyle(phoneText.getStyle() + ";-fx-text-fill: red;");
        }else phoneText.setStyle(phoneText.getStyle() + ";-fx-text-fill: black;");
        if (!isValidEmail) {
            emailText.setStyle(emailText.getStyle() + ";-fx-text-fill: red;");
        }else emailText.setStyle(emailText.getStyle() + ";-fx-text-fill: black;");

        if (checkPassword()){
            confirmPassswordText.setStyle(confirmPassswordText.getStyle() + ";-fx-text-fill: black;");
        }else confirmPassswordText.setStyle(confirmPassswordText.getStyle() + ";-fx-text-fill: red;");

        if (isValidName && isValidEmail && isValidPhone && isValidNic && checkPassword()){
//            String pass = BCrypt.withDefaults().hashToString(5, passwordText.getText().toCharArray());
//            System.out.println(pass);

            System.out.println( jobCmb.getSelectionModel().getSelectedItem());

            AccountDTO AccountDTO = new AccountDTO(
                    userIdLbl.getText(),
                    nameText.getText(),
                    nicText.getText(),
                    emailText.getText(),
                    phoneText.getText(),
                    jobCmb.getSelectionModel().getSelectedItem(),
                    password
            );


            try {
                boolean isSave = accountBO.saveUser(AccountDTO);
                if (isSave){
                   loadPage("/view/loginView.fxml",createAcountAnchorPane);
                    new Alert(Alert.AlertType.INFORMATION, "User saved successfully").show();
                }else new Alert(Alert.AlertType.ERROR, "User save failed").show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Fail to save User...!").show();
            }



        }

    }

    public void loginPageOnAction(ActionEvent event) {
        loadPage("/view/loginView.fxml",createAcountAnchorPane);
    }

    private void loadPage (String link , AnchorPane anchorPane) {
        try {
            AnchorPane load = FXMLLoader.load(getClass().getResource(link));
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Can't load this page").showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadNextId();
        clearTxet();
        getValue();


    }

    public void getValue (){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("ADMIN");
        observableList.add("USER");
        jobCmb.setItems(observableList);


    }
    private void clearTxet(){
        nameText.clear();
        nicText.clear();
        emailText.clear();
        phoneText.clear();
        passwordText.clear();
        confirmPassswordText.clear();
        jobCmb.getSelectionModel().clearSelection();

    }
    public void loadNextId () {
        System.out.println(accountBO.nextId());
        userIdLbl.setText(accountBO.nextId());
    }



    public void jobOnAction(ActionEvent event) {

    }
    public boolean checkPassword (){
        return (passwordText.getText()) .equals(confirmPassswordText.getText());
    }
}
