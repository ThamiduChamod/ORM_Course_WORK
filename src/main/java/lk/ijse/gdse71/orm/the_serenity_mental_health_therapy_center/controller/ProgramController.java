package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.BOFactory;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.ProgramBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.ProgramDTO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Program;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.view.tdm.ProgramTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProgramController implements Initializable {
    public Label programIdLbl;
    public TextField nameText;
    public TextField durationText;
    public TextField feeText;
    public TableView programTable;
    public TableColumn thableId;
    public TableColumn tableName;
    public TableColumn tableDuration;
    public TableColumn tableFee;

    ProgramBO programBO = (ProgramBO) BOFactory.getInstance().getBO(BOFactory.BOType.PROGRAM);

    public void saveBtnOnAction(ActionEvent event) {
        boolean save = programBO.save(
                new ProgramDTO(
                        programIdLbl.getText(),
                        nameText.getText(),
                        durationText.getText(),
                        Double.parseDouble(feeText.getText())
                )
        );
        if (save){
            loadNextId();
            clearText();
            new Alert(Alert.AlertType.INFORMATION, "Therapy Program Saved").show();
        }else new Alert(Alert.AlertType.ERROR, "Therapy Program Saved Fail").show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadNextId();
        clearText();
        setCellValues();
        loadTable();
    }
    public void loadNextId (){
        programIdLbl.setText(programBO.getNextId());
    }
    private void clearText(){
        nameText.clear();
        durationText.clear();
        feeText.clear();

    }

    public void setCellValues() {
        thableId.setCellValueFactory(new PropertyValueFactory<>("program_id"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("program_name"));
        tableDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        tableFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
    }

    public void loadTable(){

        List<ProgramDTO> programDTOS = programBO.loadTable();

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


    public void programTableOnAction(MouseEvent mouseEvent) {

    }
}
