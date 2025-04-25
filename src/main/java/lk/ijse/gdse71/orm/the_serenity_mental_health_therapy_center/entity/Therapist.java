package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Therapist {

    @Id
    private String id;
    private String name;
    private String nic;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "therapist")
    private List<Session> session;

}
