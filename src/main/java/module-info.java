module lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires static lombok;
    requires jakarta.persistence;
    requires java.naming;
    requires static bcrypt;
    requires mysql.connector.j;


    opens lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center to javafx.fxml;
    opens lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.controller to javafx.fxml;
    opens lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.config to org.hibernate.orm.core;
    opens lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.entity to org.hibernate.orm.core;
//    opens lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.view.tdm to javafx.fxml;
    opens lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.view.tdm to javafx.base;
    exports lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center;


}