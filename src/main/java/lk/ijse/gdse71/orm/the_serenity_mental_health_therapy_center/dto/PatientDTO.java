package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PatientDTO {

    private String id;
    private String name;
    private String nic;
    private String email;
    private String phone;



}
