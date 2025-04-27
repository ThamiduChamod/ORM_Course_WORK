package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.view.tdm;

import lombok.*;

import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ScheduleTM {

    private String id;
    private String program_id;
    private String therapist_id;
    private Date date;
    private String sTime;
    private String eTime;
}
