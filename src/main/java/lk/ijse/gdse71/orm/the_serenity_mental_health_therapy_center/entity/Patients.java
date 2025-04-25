package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Patients {
   @Id
   private String id;
   private String name;
   private String nic;
   private String email;
   private String phone;

   @OneToOne
   private Payment payment;

   @OneToMany(mappedBy = "patient") //map karagena innawa appoinment ekapaththe inna patient
   private List<Appointment> appointment;


}
