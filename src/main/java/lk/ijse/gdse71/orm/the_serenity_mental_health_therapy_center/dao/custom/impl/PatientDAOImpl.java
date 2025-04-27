package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.impl;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.config.FactoryConfiguration;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.PatientDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Patients;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Program;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PatientDAOImpl implements PatientDAO {
    @Override
    public boolean save(Patients patients, Session session) {
        try {
            session.save(patients);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Optional<String> getNext(Session session) {

        String lastPk = session
                .createQuery("SELECT t.id FROM Patients t ORDER BY t.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();


        Optional<String> nextPk = Optional.ofNullable(lastPk);
        session.close();

        return nextPk;

    }

    @Override
    public List<Patients> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
            Query<Patients> query = session.createQuery("from Patients ", Patients.class);
            return query.list();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }finally{
            session.close();
        }
    }
}
