package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.impl;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.config.FactoryConfiguration;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.AccountDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDAOImpl implements AccountDAO {

//    private static FactoryConfiguration factory;

    @Override
    public boolean save(Account account,Session session) {
        System.out.println(account);


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
    public Optional<String> getNext(Session session) {

        String lastPk = session
                .createQuery("SELECT t.id FROM Account t ORDER BY t.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();


        Optional<String> nextPk = Optional.ofNullable(lastPk);
        session.close();

        return nextPk;

    }

    public boolean findUser(String username) {
        System.out.println(username);

        Session session = FactoryConfiguration.getInstance().getSession();
        List<String> usernameList = new ArrayList<>();
        try {
            Query<String> query = session.createQuery("SELECT a.name FROM Account a WHERE a.name = :name", String.class);
            query.setParameter("name", username);

            usernameList = query.getResultList();
            session.close();

            if (usernameList.isEmpty()){
                return false;
            }else return true;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> findPasswordByUsername(String username) {

        Session session = FactoryConfiguration.getInstance().getSession();
        List<String> passwordList = new ArrayList<>();
        try {
            Query<String> query = session.createQuery("SELECT a.password FROM Account a WHERE a.name = :name", String.class);
            query.setParameter("name", username);

            passwordList = query.getResultList();
            session.close();
            return passwordList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Account> getALLByUserName(String UserName) {

        Session session = FactoryConfiguration.getInstance().getSession();
        return session.createQuery("FROM Account WHERE name = :username", Account.class)
                .setParameter("username", UserName)
                .list();
    }}
