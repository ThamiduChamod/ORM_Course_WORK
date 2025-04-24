package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.BOFactory;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.AccountBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.AccountDTO;

public class CreateAccountController {
    public Label userIdLbl;
    public TextField nameText;
    public TextField nicText;
    public TextField emailText;
    public TextField phoneText;
    public PasswordField passwordText;
    public PasswordField confirmPassswordText;
    public Button registerBtn;
    public ComboBox jobCmb;


    AccountBO accountBO = (AccountBO) BOFactory.getInstance().getBO(BOFactory.BOType.ACCOUNT);

    public void registerBtnClicked(ActionEvent event) {

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

        if (isValidName && isValidEmail && isValidPhone && isValidNic){

            AccountDTO AccountDTO = new AccountDTO(
                    userIdLbl.getText(),
                    nameText.getText(),
                    nicText.getText(),
                    emailText.getText(),
                    phoneText.getText(),
                    jobCmb.getSelectionModel().getSelectedItem().toString(),
                    passwordText.getText()
            );

            try {
                boolean isSave = accountBO.saveUser(AccountDTO);
                if (isSave){
                    new Alert(Alert.AlertType.INFORMATION, "User saved successfully").show();
                }else new Alert(Alert.AlertType.ERROR, "User save failed").show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Fail to save User...!").show();
            }



        }

    }
}
