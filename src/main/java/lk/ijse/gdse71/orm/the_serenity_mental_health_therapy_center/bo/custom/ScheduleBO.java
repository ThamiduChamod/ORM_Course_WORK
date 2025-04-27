package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.SuperBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.ProgramDTO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.ScheduleDTO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.view.tdm.ScheduleTM;

import java.util.List;

public interface ScheduleBO extends SuperBO {

    String getNextId ();
    boolean save(ScheduleDTO scheduleDTO, String programId, String therapistId);
    List<ProgramDTO> loadTable();
    public ObservableList<String> loadTherapistID();
    public String findTherapistName(String therapistId);
    public List<ScheduleTM> loadScheduleTable ();
}
