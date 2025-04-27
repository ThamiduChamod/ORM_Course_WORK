package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.BOFactory;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.AppointmentBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.view.tdm.ScheduleTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AppoinmentController implements Initializable {
    public TableView shedualTable;
    public Label appoinmentIdLbl;
    public ComboBox patientsIdCmb;
    public Label patienNameLbl;
    public TableColumn tableSId;
    public TableColumn tableDate;
    public TableColumn tableSTime;
    public TableColumn tableETime;

    AppointmentBO appointmentBO = (AppointmentBO) BOFactory.getInstance().getBO(BOFactory.BOType.APPOINTMENT);

    public void scheduleTableOnaction(MouseEvent mouseEvent) {
        
    }

    public void pationIdOnAction(ActionEvent event) {
    }

    public void saveBtnOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValues();
        loadTable();
        getNextId();
    }

    public void setCellValues() {
        tableSId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableETime.setCellValueFactory(new PropertyValueFactory<>("sTime"));
        tableSTime.setCellValueFactory(new PropertyValueFactory<>("eTime"));
    }

    public void loadTable() {
        List<ScheduleTM> scheduleTMS = appointmentBO.loadScheduleTable();

        ObservableList<ScheduleTM> scheduleTMS1 = FXCollections.observableArrayList();

        scheduleTMS1.addAll(scheduleTMS);
        shedualTable.setItems(scheduleTMS1);
    }

    public void getNextId(){
        appoinmentIdLbl.setText(appointmentBO.getNextId());
    }
}
