package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.impl;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.LoginpageBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.config.FactoryConfiguration;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.DAOFactory;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.AccountDAO;
import at.favre.lib.crypto.bcrypt.BCrypt;


import java.util.List;

public class LoginPageBOImpl implements LoginpageBO {

    AccountDAO accountDAO = (AccountDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Account);

    @Override
    public boolean searchUser (String username) {
        System.out.println(username);
        return accountDAO.findUser(username);
    }


    @Override
    public boolean checkPassword(String password, String username) {
        List<String> passwords = accountDAO.findPasswordByUsername(username);


        for (String p : passwords) {
            System.out.println(p);
            boolean isPasswordValid = BCrypt.verifyer().verify(password.toCharArray(), p.toCharArray()).verified;
            if (isPasswordValid) {

               return true;
            } else {
                return false;

            }

        }
        return false;
    }

    public String getJobRollByUsername(String username) {

        List<String> jobRollByUsername = accountDAO.getJobRollByUsername(username);
        jobRollByUsername.forEach(System.out::println);

        return jobRollByUsername.getFirst();


    }
}
