package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.impl;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.config.FactoryConfiguration;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.TherapistDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class TherapistDAOImpl implements TherapistDAO {
    @Override
    public boolean save(Therapist therapist ,Session session) {


//        factory = FactoryConfiguration.getInstance();
//        Session session = factory.getSession();
//
////        Session session = factoryConfiguration.getSession();
        try {
            session.save(therapist);
            return true;
        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<String> getNext(Session session) {

        String lastPk = session
                .createQuery("SELECT t.id FROM Therapist t ORDER BY t.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();


        Optional<String> nextPk = Optional.ofNullable(lastPk);
        session.close();

        return nextPk;

    }
}
