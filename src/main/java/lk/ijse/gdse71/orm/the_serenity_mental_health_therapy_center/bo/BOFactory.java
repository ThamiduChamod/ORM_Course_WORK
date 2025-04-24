package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.AccountBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.impl.AccountBOImpl;

public class BOFactory {

    public static  BOFactory boFactory;
    private BOFactory() {}

    public static BOFactory getInstance() {
        return  (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType{
        ACCOUNT
    }

    public SuperBO getBO(BOType type){
        switch (type) {
            case ACCOUNT:
                return new AccountBOImpl();
                default:
                    return null;
        }
    }

}
