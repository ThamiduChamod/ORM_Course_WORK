package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.CrudDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Program;

import java.util.List;

public interface ProgramDAO extends CrudDAO<Program> {

    public List<String> getAllName();

}
