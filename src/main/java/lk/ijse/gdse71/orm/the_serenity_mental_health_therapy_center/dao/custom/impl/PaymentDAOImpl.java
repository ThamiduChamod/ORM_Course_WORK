package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.impl;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.PaymentDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Payment;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean save(Payment payment, Session session) {
        try {
            session.save(payment);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Optional<String> getNext(Session session) {
        String lastPk = session
                .createQuery("SELECT t.id FROM Payment t ORDER BY t.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();


        Optional<String> nextPk = Optional.ofNullable(lastPk);
        session.close();

        return nextPk;
    }

    @Override
    public List<Payment> getAll() {
        return List.of();
    }
}
