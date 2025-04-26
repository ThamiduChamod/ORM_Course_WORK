package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.impl;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.TherapistBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.config.FactoryConfiguration;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.DAOFactory;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.TherapistDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.TherapistDTO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class TherapistBOImpl implements TherapistBO {

    TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPIST);
    @Override
    public String getNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();

        Optional<String> lastPkOptional = therapistDAO.getNext(session);
        if (lastPkOptional.isPresent()) {
            String lastPk = lastPkOptional.get();
            int nextId = Integer.parseInt(lastPk.replace("T", "")) + 1;
            return String.format("T%03d", nextId);
        } else {
            return "T001";
        }
    }

    @Override
    public boolean save(TherapistDTO therapistDTO) {

        Session session = FactoryConfiguration.getInstance().getSession();


        Therapist therapist = new Therapist();

        therapist.setId(therapistDTO.getId());
        therapist.setName(therapistDTO.getName());
        therapist.setNic(therapistDTO.getNic());
        therapist.setEmail(therapistDTO.getEmail());
        therapist.setPhone(therapistDTO.getPhone());

        Transaction transaction = session.beginTransaction();

        boolean save = therapistDAO.save(therapist, session);

        transaction.commit();
        session.close();
        return save;

    }
}
