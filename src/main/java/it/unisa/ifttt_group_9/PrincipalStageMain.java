package it.unisa.ifttt_group_9;




import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

//import resources.it.unisa.ifttt_group_9.ActionTextView.fxml;

public class PrincipalStageMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalStageMain.class.getResource("PrincipalStage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Rules Manager");
        stage.setMinWidth(550);
        stage.setMaxWidth(550);
        stage.setMinHeight(450);
        stage.setMaxHeight(450);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
