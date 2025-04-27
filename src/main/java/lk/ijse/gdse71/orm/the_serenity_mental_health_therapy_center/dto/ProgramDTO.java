package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProgramDTO {

    private String program_id;
    private String program_name;
    private String duration;
    private double fee;
}
