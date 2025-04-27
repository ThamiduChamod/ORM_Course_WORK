package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.SuperBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.view.tdm.ScheduleTM;

import java.util.List;

public interface AppointmentBO extends SuperBO  {
    public List<ScheduleTM> loadScheduleTable ();
    public String getNextId();
}
