package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.impl;

import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.AccountBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.config.FactoryConfiguration;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.DAOFactory;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.AccountDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.AccountDTO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class AccountBOImpl  implements AccountBO {

    AccountDAO accountDAO = (AccountDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Account);

    @Override
    public boolean saveUser(AccountDTO createAccountDTO) {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();
        boolean save = accountDAO.save(new Account(
                createAccountDTO.getId(),
                createAccountDTO.getName(),
                createAccountDTO.getNic(),
                createAccountDTO.getEmail(),
                createAccountDTO.getPhone(),
                createAccountDTO.getJobTitle(),
                createAccountDTO.getPassword()
        ), session);
        transaction.commit();
        session.close();
        return save;


    }

    @Override
    public String nextId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Optional<String> lastPkOptional = accountDAO.getNext(session);
        if (lastPkOptional.isPresent()) {
            String lastPk = lastPkOptional.get();
            int nextId = Integer.parseInt(lastPk.replace("U", "")) + 1;
            return String.format("U%03d", nextId);
        } else {
            return "U001";
        }
    }
}
