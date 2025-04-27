package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao;

import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<T> extends SuperDAO{
    boolean save(T t, Session session);
    Optional<String> getNext (Session session);
    List<T> getAll();
}
