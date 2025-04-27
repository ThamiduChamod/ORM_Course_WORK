package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.BOFactory;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.PatientBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.PatientDTO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.ProgramDTO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.view.tdm.PatientTM;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.view.tdm.ProgramTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {


    public Label patientIdLbl;
    public TextField nameText;
    public TextField nicText;
    public TextField emailTexet;
    public TextField phoneText;
    public ComboBox<String> programCmb;
    public Label dutarionLbl;
    public Label priceLbl;
    public AnchorPane patientAnchorPAane;
    public AnchorPane mainAnchoePane;
    public TableView patientTable;
    public TableColumn tableId;
    public TableColumn tableName;
    public TableColumn tableNic;
    public TableColumn tableEmail;
    public TableColumn tablePhone;
    public Button patientBtn;
    public Button TherapistBtn;
    public Button programBtn;
    public Button scheduleBtn;
    public Button appoinmentBtn;
    public Label joblble;


    PatientBO patientBO = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT);


    public void accountAccess(){

    }

    public void setData(String job){

        System.out.println(job);

        joblble.setText(job);

//        if (job .equals("ADMIN")){
//            patientBtn.setDisable(true);
//            scheduleBtn.setDisable(true);
//            appoinmentBtn.setDisable(true);
//        }else {
//            TherapistBtn.setDisable(true);
//            programBtn.setDisable(true);
//        }

        if (joblble.getText().equals("ADMIN")){
            patientBtn.setDisable(true);
            scheduleBtn.setDisable(true);
            appoinmentBtn.setDisable(true);

            TherapistBtn.setDisable(false);
            programBtn.setDisable(false);
        }else {
            TherapistBtn.setDisable(true);
            programBtn.setDisable(true);

            patientBtn.setDisable(false);
            scheduleBtn.setDisable(false);
            appoinmentBtn.setDisable(false);
        }
    }


    public void programCmbOnAction(ActionEvent event) {
        String selectedItem = programCmb.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
       // setProgramDetails(selectedItem);

    }

    public void setProgramDetails(String selectedItem){
//        List<String> details = patientBO.programDurationByName(selectedItem);
//        List<String> list = patientBO.programFeesByName(selectedItem);
//
//        dutarionLbl.setText(details.getFirst());
//        priceLbl.setText(list.getFirst());

    }

    public void saveBtnClicked(ActionEvent event) {


        String name = nameText.getText();
        String nic = nicText.getText();
        String email = emailTexet.getText();
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
            emailTexet.setStyle(emailTexet.getStyle() + ";-fx-text-fill: red;");
        }else emailTexet.setStyle(emailTexet.getStyle() + ";-fx-text-fill: black;");


        if (isValidName && isValidEmail && isValidPhone && isValidNic ) {

            patientBO.save(new PatientDTO(
                    patientIdLbl.getText(),
                    nameText.getText(),
                    nameText.getText(),
                    emailTexet.getText(),
                    phoneText.getText()

            ),programCmb.getSelectionModel().getSelectedItem());

        }







        }

    public void patientOnAction(ActionEvent event) {
        pageLode("/view/MainView.fxml",mainAnchoePane);

    }

    public void therapistOnAction(ActionEvent event) {
        pageLode("/view/TherapistView.fxml",patientAnchorPAane);

    }

    public void programbtnOnAction(ActionEvent event) {
        pageLode("/view/ProgramView.fxml",patientAnchorPAane);

    }

    public void ssheduleBtnOnAction(ActionEvent event) {
        pageLode("/view/Schedules_therapy_sessions.fxml",patientAnchorPAane);

    }

    public void appoinmentBtnOnAction(ActionEvent event) {
        pageLode("/view/AppoinmentView.fxml",patientAnchorPAane);
    }

    public void pageLode(String link, AnchorPane anchorPane) {
        try {
            AnchorPane load = FXMLLoader.load(getClass().getResource(link));
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(load);
        }catch(IOException e){
            new Alert(Alert.AlertType.ERROR,"Can't load this page").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadNextId();
        loadTable();
        loadTherapyProgramCmb();
        setCellValues();

        if (joblble.getText().equals("ADMIN")){
            patientBtn.setDisable(true);
            scheduleBtn.setDisable(true);
            appoinmentBtn.setDisable(true);

            TherapistBtn.setDisable(false);
            programBtn.setDisable(false);
        }else {
            TherapistBtn.setDisable(true);
            programBtn.setDisable(true);

            patientBtn.setDisable(false);
            scheduleBtn.setDisable(false);
            appoinmentBtn.setDisable(false);
        }
    }

    public void loadNextId(){
        patientIdLbl.setText(patientBO.getNextId());
    }

    public void loadTherapyProgramCmb(){
        programCmb.setItems(patientBO.loadProgramNames());
    }

    public void patientTableOnAction(MouseEvent mouseEvent) {

    }
    public void loadTable(){
        List<PatientDTO> patientDTOS = patientBO.loadTable();

        ObservableList<PatientTM> patientTMS = FXCollections.observableArrayList();
        for (PatientDTO patientDTO : patientDTOS) {

             PatientTM patientTM = new PatientTM(
                    patientDTO.getId(),
                    patientDTO.getName(),
                  patientDTO.getNic(),
                    patientDTO.getEmail(),
                    patientDTO.getPhone()
            );
            patientTMS.add(patientTM);
        }
        patientTable.setItems(patientTMS);
    }

    public void setCellValues() {
        tableId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        tableEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tablePhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    public void logoutOnAction(ActionEvent event) {
        try {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
            mainAnchoePane.getChildren().clear();
            mainAnchoePane.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Can't load this page").showAndWait();
        }
    }
}
