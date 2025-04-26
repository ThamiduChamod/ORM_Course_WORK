package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Time;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ProgramSession {

    @Id
    private String id;
    private Date date;
    private Time time;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    @OneToOne(mappedBy = "session")
    private Appointment appointment;

}
