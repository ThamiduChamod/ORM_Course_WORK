package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.config;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Account;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    public FactoryConfiguration() {
        Configuration configuration = new Configuration().configure()
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(Payment.class);

        sessionFactory = configuration.buildSessionFactory();
    }


    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
