package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.impl;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.config.FactoryConfiguration;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.ScheduleDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.ProgramSession;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Therapist;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class ScheduleDAOImpl implements ScheduleDAO {
    @Override
    public boolean save(ProgramSession programSession, Session session) {
        try {
            session.save(programSession);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public Optional<String> getNext(Session session) {
        String lastPk = session
                .createQuery("SELECT t.id FROM ProgramSession t ORDER BY t.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();


        Optional<String> nextPk = Optional.ofNullable(lastPk);
        session.close();

        return nextPk;

    }

    @Override
    public List<ProgramSession> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
            Query<ProgramSession> query = session.createQuery("from ProgramSession ", ProgramSession.class);
            return query.list();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }finally{
            session.close();
        }
    }
}
