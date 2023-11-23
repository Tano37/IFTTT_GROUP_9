package it.unisa.ifttt_group_9;




import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

//import resources.it.unisa.ifttt_group_9.ActionTextView.fxml;

public class ActionTextView extends Application {
    public static void show() {
        launch();
    }

    public static void close(Stage stage) {
        stage.close();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ActionTextView.class.getResource("ActionTextView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("TriggerText");
        stage.setScene(scene);
        stage.show();


    }

}

