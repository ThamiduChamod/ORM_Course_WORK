package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.CrudDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.SuperDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Account;

public interface AccountDAO extends CrudDAO<Account> {
    boolean save(Account account);
}
