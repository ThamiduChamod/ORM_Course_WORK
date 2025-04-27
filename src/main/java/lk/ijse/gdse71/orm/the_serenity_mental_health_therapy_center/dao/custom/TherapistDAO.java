package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.CrudDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Therapist;

import java.util.List;

public interface TherapistDAO extends CrudDAO<Therapist>{
        public List<String> getAllId();
        public String therapistNameById(String id);
}