package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.SuperBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.TherapistDTO;

public interface TherapistBO extends SuperBO {
    String getNextId ();
    boolean save(TherapistDTO therapistDTO);
}
