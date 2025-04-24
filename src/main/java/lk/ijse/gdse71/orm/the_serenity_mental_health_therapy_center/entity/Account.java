package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class Account {
    @Id
    private String id;
    private String name;
    private String nic;
    private String email;
    private String phone;
    private String jobTitle;
    private String password;
}
