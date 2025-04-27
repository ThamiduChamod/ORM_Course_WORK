package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.bo.custom.PatientBO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.config.FactoryConfiguration;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.DAOFactory;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.PatientDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.PaymentDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dao.custom.ProgramDAO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.PatientDTO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto.ProgramDTO;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Patients;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Payment;
import lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity.Program;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

public class PatientBOImpl implements PatientBO {

    PatientDAO patientDAO = (PatientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PATIENT);
    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PROGRAM);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);

    public String getNextId(){
        Session session = FactoryConfiguration.getInstance().getSession();

        Optional<String> lastPkOptional = patientDAO.getNext(session);
        if (lastPkOptional.isPresent()) {
            String lastPk = lastPkOptional.get();
            int nextId = Integer.parseInt(lastPk.replace("P", "")) + 1;
            return String.format("P%03d", nextId);
        } else {
            return "P001";
        }
    }

    public boolean save(PatientDTO patientDTO, String dis) {

        Session session = FactoryConfiguration.getInstance().getSession();


        Payment payment = new Payment();
        payment.setId(paymentId());
        payment.setDescription(dis);

        Patients patients = new Patients();

        patients.setId(patientDTO.getId());
        patients.setName(patientDTO.getName());
        patients.setNic(patientDTO.getNic());
        patients.setEmail(patientDTO.getEmail());
        patients.setPhone(patientDTO.getPhone());

        patients.setPayment(payment);









        Transaction transaction = session.beginTransaction();
        boolean save = false;
         save= patientDAO.save(patients, session);
         save = paymentDAO.save(payment, session);

//        if () && ){
//            save = true;
//        }





        transaction.commit();
        session.close();
        return save;

    }

    public ObservableList<String> loadProgramNames() {
        List<String> allId = programDAO.getAllName();

        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(allId);
        return observableList;

    }

    public String paymentId(){
            Session session = FactoryConfiguration.getInstance().getSession();

        Optional<String> lastPkOptional = paymentDAO.getNext(session);
        if (lastPkOptional.isPresent()) {
            String lastPk = lastPkOptional.get();
            int nextId = Integer.parseInt(lastPk.replace("S", "")) + 1;
            return String.format("S%03d", nextId);
        } else {
            return "S001";
        }

    }

    public List<PatientDTO> loadTable() {
        List<Patients> patient = patientDAO.getAll();

        List<PatientDTO> dtos = new ArrayList<>();

        for (Patients patients : patient) {
            PatientDTO dto = new PatientDTO(
                    patients.getId(),
                    patients.getName(),
                    patients.getNic(),
                    patients.getEmail(),
                    patients.getPhone()
            );
            dtos.add(dto);
        }
        return dtos;
    }




}
