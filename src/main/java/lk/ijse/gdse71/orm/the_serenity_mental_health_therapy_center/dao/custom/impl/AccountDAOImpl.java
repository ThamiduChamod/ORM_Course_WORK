package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.impl;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.config.FactoryConfiguration;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.AccountDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.AccountDTO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AccountDAOImpl implements AccountDAO {

//    private static FactoryConfiguration factory;

    @Override
    public boolean save(Account account) {
        System.out.println(account);
        Session session = FactoryConfiguration.getInstance().getSession();

//        factory = FactoryConfiguration.getInstance();
//        Session session = factory.getSession();
//
////        Session session = factoryConfiguration.getSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        return true;
    }

    @Override
    public String getNext() {
        return "";
    }
}
