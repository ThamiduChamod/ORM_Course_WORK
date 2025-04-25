package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    private String date;

    @ManyToOne
    private Patients patient;



}
