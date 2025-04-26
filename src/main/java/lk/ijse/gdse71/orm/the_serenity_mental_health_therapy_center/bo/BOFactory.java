package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.AccountBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.impl.AccountBOImpl;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.impl.LoginPageBOImpl;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.impl.TherapistBOImpl;

public class BOFactory {

    public static  BOFactory boFactory;
    private BOFactory() {}

    public static BOFactory getInstance() {
        return  (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType{
        ACCOUNT,LOGIN,THERAPIST
    }

    public SuperBO getBO(BOType type){
        switch (type) {
            case ACCOUNT:
                return new AccountBOImpl();
                case LOGIN:
                    return new LoginPageBOImpl();
            case THERAPIST:
                return new TherapistBOImpl();
                default:
                    return null;
        }
    }

}
