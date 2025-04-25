package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.impl;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.config.FactoryConfiguration;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.AccountDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AccountDAOImpl implements AccountDAO {
    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(Account account) {
        Session session = factoryConfiguration.getSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
