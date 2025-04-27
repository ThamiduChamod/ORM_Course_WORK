package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.impl;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.ProgramBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.config.FactoryConfiguration;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.DAOFactory;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.ProgramDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.ProgramDTO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Program;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgramBOImpl implements ProgramBO {

    ProgramDAO programDAO =  (ProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PROGRAM);

    @Override
    public String getNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();

        Optional<String> lastPkOptional = programDAO.getNext(session);
        if (lastPkOptional.isPresent()) {
            String lastPk = lastPkOptional.get();
            int nextId = Integer.parseInt(lastPk.replace("MT", "")) + 1;
            return String.format("MT%03d", nextId);
        } else {
            return "MT001";
        }
    }

    @Override
    public boolean save(ProgramDTO programDTO) {
        Session session = FactoryConfiguration.getInstance().getSession();

        Program program = new Program();

        program.setProgram_id(programDTO.getProgram_id());
        program.setProgram_name(programDTO.getProgram_name());
        program.setDuration(programDTO.getDuration());
        program.setFee(programDTO.getFee());

        Transaction transaction = session.beginTransaction();
        boolean save = programDAO.save(program, session);
        transaction.commit();
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
}
