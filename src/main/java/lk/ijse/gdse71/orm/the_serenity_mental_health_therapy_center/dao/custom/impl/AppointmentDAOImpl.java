package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.impl;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.AppointmentDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Appointment;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class AppointmentDAOImpl implements AppointmentDAO {
    @Override
    public boolean save(Appointment appointment, Session session) {
        return false;
    }

    @Override
    public Optional<String> getNext(Session session) {
        String lastPk = session
                .createQuery("SELECT t.id FROM Appointment t ORDER BY t.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();


        Optional<String> nextPk = Optional.ofNullable(lastPk);
        session.close();

        return nextPk;
    }

    @Override
    public List<Appointment> getAll() {
        return List.of();
    }
}
