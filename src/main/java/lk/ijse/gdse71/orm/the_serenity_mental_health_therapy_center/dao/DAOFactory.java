package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.AccountDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.impl.AccountDAOImpl;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.impl.TherapistDAOImpl;

public class DAOFactory {

    public static DAOFactory daoFactory;
    public DAOFactory() {}

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOType {
        Account,THERAPIST
    }

    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case Account:
                return new AccountDAOImpl();
                case THERAPIST:
                    return new TherapistDAOImpl();
            default:return null;
        }

    }
}
