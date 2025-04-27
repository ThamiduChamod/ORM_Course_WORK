package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.controller;

import com.mysql.cj.BindValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.BOFactory;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.ScheduleBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.ProgramDTO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.ScheduleDTO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.view.tdm.ProgramTM;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.view.tdm.ScheduleTM;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.sql.Date;
import java.sql.Time;

public class ScheduleController implements Initializable {
    public TableView<ProgramTM> programTable;
    public TableColumn tableId;
    public TableColumn tabaleName;
    public TableColumn tableDuration;
    public TableColumn tableFee;
    public Label sessionIdLbl;
    public DatePicker datePiker;
    public TextField sTimeText;
    public ComboBox<String> am1cmb;
    public TextField eTimeText;
    public ComboBox<String> am2Cmb;
    public ComboBox<String> therapistIdCmb;
    public Label therapistNameLbl;
    public TableView sheduleTime;
    public TableColumn tableSiD;
    public TableColumn tablePId;
    public TableColumn tabaleTId;
    public TableColumn tableDate;
    public TableColumn tableETime;
    public TableColumn tableSTime;
    public TableView sheduleTable;


    ProgramTM selectedProgram = new ProgramTM();
    ScheduleBO scheduleBO = (ScheduleBO) BOFactory.getInstance().getBO(BOFactory.BOType.SCHEDULE);


    public void saveBtnOnAction(ActionEvent event) {

        String startTime = sTimeText.getText()+am1cmb.getSelectionModel().getSelectedItem();
        String endTime = eTimeText.getText()+am2Cmb.getSelectionModel().getSelectedItem();


        boolean save = scheduleBO.save(new ScheduleDTO(
                        sessionIdLbl.getText(),
                        Date.valueOf(datePiker.getValue()),
                        startTime,
                        endTime),
                selectedProgram.getProgram_id(),
                therapistIdCmb.getSelectionModel().getSelectedItem()

        );

        if (save){
            new Alert(Alert.AlertType.INFORMATION, "Schedule Saved", ButtonType.OK).show();
        }else new Alert(Alert.AlertType.ERROR, "Schedule Saved Fail", ButtonType.OK).show();


    }
    public void loadTherapist(){

        therapistIdCmb.setItems(scheduleBO.loadTherapistID());


    }

    public void tableOnAction(MouseEvent mouseEvent) {
        selectedProgram = programTable.getSelectionModel().getSelectedItem();
        if (selectedProgram != null) {
            tableId.setText(selectedProgram.getProgram_id());
            tabaleName.setText(selectedProgram.getProgram_id());
            tableDuration.setText(selectedProgram.getDuration().toString());
            tableId.setText(String.valueOf(selectedProgram.getProgram_id()));
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValues();
        loadTable();
        setCmbValues();
        loadTherapist();
        lodNextId();
        loadScheduleTable();
    }

    public void lodNextId(){
        sessionIdLbl.setText(scheduleBO.getNextId());
    }

    public void setCellValues() {
        tableId.setCellValueFactory(new PropertyValueFactory<>("program_id"));
        tabaleName.setCellValueFactory(new PropertyValueFactory<>("program_name"));
        tableDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        tableFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        tableSiD.setCellValueFactory(new PropertyValueFactory<>("id"));
        tablePId.setCellValueFactory(new PropertyValueFactory<>("program_id"));
        tabaleTId.setCellValueFactory(new PropertyValueFactory<>("therapist_id"));
        tableDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableETime.setCellValueFactory(new PropertyValueFactory<>("sTime"));
        tableSTime.setCellValueFactory(new PropertyValueFactory<>("eTime"));

    }
    public void setCmbValues() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("am");
        observableList.add("pm");
        am1cmb.setItems(observableList);
        am2Cmb.setItems(observableList);

    }

    public void loadTable(){

        List<ProgramDTO> programDTOS = scheduleBO.loadTable();

        ObservableList<ProgramTM> programTMS = FXCollections.observableArrayList();
        for (ProgramDTO programDto : programDTOS) {

            ProgramTM programTM = new ProgramTM(
                    programDto.getProgram_id(),
                    programDto.getProgram_name(),
                    programDto.getDuration(),
                    programDto.getFee()
            );
            programTMS.add(programTM);
        }
        programTable.setItems(programTMS);
    }

    public void loadScheduleTable (){
        List<ScheduleTM> scheduleTMS = scheduleBO.loadScheduleTable();

        ObservableList<ScheduleTM> scheduleTMS1 = FXCollections.observableArrayList();

        scheduleTMS1.addAll(scheduleTMS);
        sheduleTable.setItems(scheduleTMS1);
    }

    public void therapistNameOnAction(ActionEvent event) {
        String selectedItem = therapistIdCmb.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        setTherapistName(selectedItem);
    }

    public void setTherapistName (String selectedItem) {
        therapistNameLbl.setText( scheduleBO.findTherapistName(selectedItem));
    }

    public void scheduleTableOnAction(MouseEvent mouseEvent) {
        
    }
}
