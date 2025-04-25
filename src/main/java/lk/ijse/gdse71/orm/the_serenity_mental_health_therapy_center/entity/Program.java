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

public class Program {

    @Id
    private String program_id;
    private String program_name;
    private String duration;
    private double fee;

    @OneToMany(mappedBy = "program")
    private List<Patients> patients;

    @OneToMany(mappedBy = "program")
    private List<Session> session;


}
