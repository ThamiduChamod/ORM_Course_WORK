module lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires static lombok;
    requires jakarta.persistence;

    opens lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center to javafx.fxml;
    opens lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center.controller to javafx.fxml;
    exports lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center;
}