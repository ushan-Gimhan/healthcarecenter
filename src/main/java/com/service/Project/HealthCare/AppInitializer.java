package com.service.Project.HealthCare;

import com.service.Project.HealthCare.config.FactoryConfiguration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.close();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent load = FXMLLoader.load(getClass().getResource("/View/registration.fxml"));

        Image image = new Image(getClass().getResource("/images/healthicon2.png").toExternalForm());

        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("SERENITY");
        stage.getIcons().add(image);
        stage.show();
    }
}