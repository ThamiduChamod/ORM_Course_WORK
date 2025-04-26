package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainPageController {


    public Label patientIdLbl;
    public TextField nameText;
    public TextField nicText;
    public TextField emailTexet;
    public TextField phoneText;
    public ComboBox programCmb;
    public Label dutarionLbl;
    public Label priceLbl;
    public AnchorPane patientAnchorPAane;
    public AnchorPane mainAnchoePane;

    public void accountAccess(){

    }

    public void setData(String job){
        System.out.println(job);
    }


    public void programCmbOnAction(ActionEvent event) {
    }

    public void saveBtnClicked(ActionEvent event) {
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
}
