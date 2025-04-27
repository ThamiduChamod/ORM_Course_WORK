package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.impl;

import jakarta.persistence.PersistenceUnit;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.config.FactoryConfiguration;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.ProgramDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Program;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgramDAOImpl implements ProgramDAO {
    @Override
    public boolean save(Program program, Session session) {
        try {
            session.save(program);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Optional<String> getNext(Session session) {
        String lastPk = session
                .createQuery("SELECT t.program_id FROM Program t ORDER BY t.program_id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();


        Optional<String> nextPk = Optional.ofNullable(lastPk);
        session.close();

        return nextPk;

    }

    @Override
    public List<Program> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
            Query<Program> query = session.createQuery("from Program ", Program.class);
            return query.list();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }finally{
            session.close();
        }
    }

    public List<String> getAllName() {
        List<String> list ;
        Session session = FactoryConfiguration.getInstance().getSession();

        list = session.createQuery("SELECT t.program_name FROM Program t", String.class).list();
        session.close();
        return list;
    }



}
