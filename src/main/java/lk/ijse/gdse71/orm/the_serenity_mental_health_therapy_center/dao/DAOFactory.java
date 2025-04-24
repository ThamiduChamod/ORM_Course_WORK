package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.AccountDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.impl.AccountDAOImpl;

public class DAOFactory {

    public static DAOFactory daoFactory;
    public DAOFactory() {}

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOType {
        Account
    }

    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case Account:
                return new AccountDAOImpl();
            default:return null;
        }

    }
}
