package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.dto;

import lombok.*;

import java.sql.Date;
import java.sql.Time;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ScheduleDTO {

    private String id;
    private Date date;
    private String sTime;
    private String eTime;
}
