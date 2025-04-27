package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.SuperBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.PatientDTO;

import java.util.List;

public interface PatientBO extends SuperBO {
    public String getNextId();
    public ObservableList<String> loadProgramNames();
    public boolean save(PatientDTO patientDTO ,String dis);
    public String paymentId();
    public List<PatientDTO> loadTable();

}
