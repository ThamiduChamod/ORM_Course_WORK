package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.config;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration; //private static instance for singleton
    private SessionFactory sessionFactory; //build karapu session gactory eka me insttance ekata dagena session ekak open karagannawa (line 21)

    private FactoryConfiguration() { // private constructor for did not create object any class
        Configuration configuration = new Configuration().configure();//session ekak open karagnna nam session factory ekak configyre karaganna oni. e nisa ekata object ekak hadagaththa

        configuration.addAnnotatedClass(Account.class);

        sessionFactory = configuration.buildSessionFactory(); //object eka true session factory ekak build karagaththa
    }

    public static FactoryConfiguration getInstance() { // static method for instance eka allaganna
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() { // public session for session ekak ganna illana illana thenata denna
        return sessionFactory.openSession(); //open session
    }
}
