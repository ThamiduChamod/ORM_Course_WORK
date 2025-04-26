package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.config;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;
    public SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration().configure();

        configuration.addAnnotatedClass(Account.class);
        configuration.addAnnotatedClass(Therapist.class);
        configuration.addAnnotatedClass(Appointment.class);
        configuration.addAnnotatedClass(Patients.class);
        configuration.addAnnotatedClass(Program.class);
        configuration.addAnnotatedClass(ProgramSession.class);
        configuration.addAnnotatedClass(Payment.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        if (factoryConfiguration == null) {
            return factoryConfiguration = new FactoryConfiguration();

        }
        return factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
