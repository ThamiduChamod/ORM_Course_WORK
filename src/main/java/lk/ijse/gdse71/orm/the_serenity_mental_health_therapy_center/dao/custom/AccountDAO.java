package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.CrudDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.SuperDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.AccountDTO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountDAO extends CrudDAO<Account> {
    public boolean findUser(String username);
    public List<String> findPasswordByUsername(String username);
    public List<Account> getALLByUserName(String UserName);
}
