package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.SuperBO;

public interface LoginpageBO extends SuperBO {
    public boolean searchUser (String username);
    public boolean checkPassword (String password, String username);
}
