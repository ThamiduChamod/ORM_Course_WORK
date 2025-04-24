package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao;

public interface CrudDAO<T> extends SuperDAO{
    boolean save(T t);
}
