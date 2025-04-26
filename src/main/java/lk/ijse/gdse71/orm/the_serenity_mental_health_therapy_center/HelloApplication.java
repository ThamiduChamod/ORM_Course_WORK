package lk.ijse.gdse71.orm.the_serenity_mental_health_therapy_center;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

//        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/CreateAccountView.fxml"))));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/view/loginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}