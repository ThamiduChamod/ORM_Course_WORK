package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.BOFactory;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.TherapistBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.PatientDTO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.TherapistDTO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.view.tdm.PatientTM;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.view.tdm.TherapistTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TherapistController implements Initializable {
    public Label terapistIdlbl;
    public TextField nameText;
    public TextField nitText;
    public TextField emailText;
    public TextField phoneText;
    public TableView therapistTable;
    public TableColumn tableId;
    public TableColumn tableName;
    public TableColumn tableNic;
    public TableColumn tableEmail;
    public TableColumn tablePhone;

    TherapistBO therapistBO = (TherapistBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPIST);

    public void saveBtnOnAction(ActionEvent event) {

        String name = nameText.getText();
        String nic = nitText.getText();
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
            nitText.setStyle(nitText.getStyle() + ";-fx-text-fill: red;");
        }else  nitText.setStyle(nitText.getStyle() + ";-fx-text-fill: black;");
        if (!isValidPhone){
            phoneText.setStyle(phoneText.getStyle() + ";-fx-text-fill: red;");
        }else phoneText.setStyle(phoneText.getStyle() + ";-fx-text-fill: black;");
        if (!isValidEmail) {
            emailText.setStyle(emailText.getStyle() + ";-fx-text-fill: red;");
        }else emailText.setStyle(emailText.getStyle() + ";-fx-text-fill: black;");




        if (isValidName && isValidEmail && isValidPhone && isValidNic ){
            boolean save = therapistBO.save(new TherapistDTO(
                    terapistIdlbl.getText(),
                    nameText.getText(),
                    nitText.getText(),
                    emailText.getText(),
                    phoneText.getText()
            ));
            if (save){
                lodNextId();
                clearText();
                new Alert(Alert.AlertType.INFORMATION,"Therapist Save Successfully").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Therapist Save Failed").show();
            }
        }

    }


    public void lodNextId () {
        terapistIdlbl.setText(therapistBO.getNextId());
    }
    private void clearText(){
        nameText.clear();
        nitText.clear();
        emailText.clear();
        phoneText.clear();

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clearText();
        lodNextId();
        setCellValues();
        loadTable();
    }
    public void setCellValues() {
        tableId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        tableEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tablePhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }
    public void loadTable(){
        List<TherapistDTO> patientDTOS = therapistBO.loadTable();

        ObservableList<TherapistTM> patientTMS = FXCollections.observableArrayList();
        for (TherapistDTO patientDTO : patientDTOS) {

            TherapistTM patientTM = new TherapistTM(
                    patientDTO.getId(),
                    patientDTO.getName(),
                    patientDTO.getNic(),
                    patientDTO.getEmail(),
                    patientDTO.getPhone()
            );
            patientTMS.add(patientTM);
        }
        therapistTable.setItems(patientTMS);
    }

    public void therapistTableOnAction(MouseEvent mouseEvent) {
        
    }


}
