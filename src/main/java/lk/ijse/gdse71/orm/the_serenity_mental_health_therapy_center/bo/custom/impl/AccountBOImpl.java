package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.impl;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.AccountBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.DAOFactory;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.AccountDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.AccountDTO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Account;

public class AccountBOImpl  implements AccountBO {

    AccountDAO accountDAO = (AccountDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Account);

    @Override
    public boolean saveUser(AccountDTO createAccountDTO) {

        return accountDAO.save(new Account(
                createAccountDTO.getId(),
                createAccountDTO.getName(),
                createAccountDTO.getNic(),
                createAccountDTO.getEmail(),
                createAccountDTO.getPhone(),
                createAccountDTO.getJobTitle(),
                createAccountDTO.getPassword()
        ));


    }

    @Override
    public String nextId() {
        return accountDAO.getNext();
    }
}
