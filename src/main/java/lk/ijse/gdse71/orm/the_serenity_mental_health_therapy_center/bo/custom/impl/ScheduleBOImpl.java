package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.ScheduleBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.config.FactoryConfiguration;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.DAOFactory;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.ProgramDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.ScheduleDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.TherapistDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.ProgramDTO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.ScheduleDTO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Program;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.ProgramSession;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Therapist;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.view.tdm.ScheduleTM;
import org.hibernate.Session;

import java.lang.module.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScheduleBOImpl implements ScheduleBO {

    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PROGRAM);
    TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPIST);
    ScheduleDAO scheduleDAO =(ScheduleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SCHEDULE);

    @Override
    public String getNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Optional<String> lastPkOptional = scheduleDAO.getNext(session);
        if (lastPkOptional.isPresent()) {
            String lastPk = lastPkOptional.get();
            int nextId = Integer.parseInt(lastPk.replace("S", "")) + 1;
            return String.format("S%03d", nextId);
        } else {
            return "S001";
        }
    }

    @Override
    public boolean save(ScheduleDTO scheduleDTO, String programId, String therapistId) {
        Session session = FactoryConfiguration.getInstance().getSession();

        Program program = new Program();
        program.setProgram_id(programId);
        Therapist therapist = new Therapist();
        therapist.setId(therapistId);

        ProgramSession programSession = new ProgramSession();

        programSession.setId(scheduleDTO.getId());
        programSession.setDate(scheduleDTO.getDate());
        programSession.setStime(scheduleDTO.getSTime());
        programSession.setETime(scheduleDTO.getETime());

        programSession.setProgram(program);
        programSession.setTherapist(therapist);

        session.beginTransaction();
        boolean save = scheduleDAO.save(programSession, session);
        session.getTransaction().commit();
        session.close();
        return save;
    }


    @Override
    public List<ProgramDTO> loadTable() {
        List<Program> Program = programDAO.getAll();

        List<ProgramDTO> dtos = new ArrayList<>();

        for (Program programs : Program) {
            ProgramDTO dto = new ProgramDTO(
                    programs.getProgram_id(),
                    programs.getProgram_name(),
                    programs.getDuration(),
                    programs.getFee()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    public List<ScheduleTM> loadScheduleTable (){
        List<ProgramSession> all = scheduleDAO.getAll();

        List<ScheduleTM> dtos = new ArrayList<>();

        for (ProgramSession programSession : all) {
            ScheduleTM dto = new ScheduleTM(
                    programSession.getId(),
                    programSession.getProgram().getProgram_id(),
                    programSession.getTherapist().getId(),
                    programSession.getDate(),
                    programSession.getStime(),
                    programSession.getETime()
            );
            dtos.add(dto);

        }
        return dtos;
    }

    public ObservableList<String> loadTherapistID(){
        List<String> allId = therapistDAO.getAllId();

        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(allId);
        return observableList;
    }

    public String findTherapistName(String therapistId){
                return therapistDAO.therapistNameById(therapistId);
    }

}
