package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.impl;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.AppointmentBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.config.FactoryConfiguration;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.DAOFactory;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.ScheduleDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.ProgramSession;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.view.tdm.ScheduleTM;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppointmentBOImpl implements AppointmentBO {

    ScheduleDAO scheduleDAO = (ScheduleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SCHEDULE);

    public List<ScheduleTM> loadScheduleTable (){
        List<ProgramSession> all = scheduleDAO.getAll();

        List<ScheduleTM> dtos = new ArrayList<>();

        for (ProgramSession programSession : all) {
            ScheduleTM dto = new ScheduleTM();

            dto.setId(programSession.getId());
            dto.setDate(programSession.getDate());
            dto.setSTime(programSession.getStime());
            dto.setETime(programSession.getETime());
            dtos.add(dto);

        }
        return dtos;
    }

    public String getNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Optional<String> lastPkOptional = scheduleDAO.getNext(session);
        if (lastPkOptional.isPresent()) {
            String lastPk = lastPkOptional.get();
            int nextId = Integer.parseInt(lastPk.replace("A", "")) + 1;
            return String.format("A%03d", nextId);
        } else {
            return "A001";
        }
    }
}
