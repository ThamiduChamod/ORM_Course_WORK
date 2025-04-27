package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.AccountBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.impl.*;

public class BOFactory {

    public static  BOFactory boFactory;
    private BOFactory() {}

    public static BOFactory getInstance() {
        return  (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType{
        ACCOUNT,LOGIN,THERAPIST,PROGRAM,SCHEDULE,PATIENT,APPOINTMENT
    }

    public SuperBO getBO(BOType type){
        switch (type) {
            case ACCOUNT:
                return new AccountBOImpl();
                case LOGIN:
                    return new LoginPageBOImpl();
            case THERAPIST:
                return new TherapistBOImpl();
                case PROGRAM:
                    return new ProgramBOImpl();
                    case SCHEDULE:
                        return new ScheduleBOImpl();
                        case PATIENT:
                            return new PatientBOImpl();
                            case APPOINTMENT:
                                return new AppointmentBOImpl();
                default:
                    return null;
        }
    }

}
