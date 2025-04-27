package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.impl;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.config.FactoryConfiguration;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.TherapistDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Patients;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<Therapist> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
            Query<Therapist> query = session.createQuery("from Therapist ", Therapist.class);
            return query.list();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }finally{
            session.close();
        }
    }

    @Override
    public List<String> getAllId() {
        List<String> list = new ArrayList<>();
        Session session = FactoryConfiguration.getInstance().getSession();

         list = session.createQuery("SELECT t.id FROM Therapist t", String.class).list();
         session.close();
         return list;
    }
    public String therapistNameById(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();

        Query<String> name   = session.createQuery("SELECT t.name FROM Therapist t WHERE t.id = :id", String.class)
                .setParameter("id", id);

        return name.getSingleResult();
    }
}
