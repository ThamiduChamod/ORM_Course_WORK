package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Payment {
    @Id
    private String id;
    private String description;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patients patient;



}
