package it.unisa.ifttt_group_9;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PrincipalStage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello world Giuseppe!ciaoo");
        stage.setScene(scene);
        stage.show();
    }
// dsnfjdsifdnsofjadsojfhdishfpidhsfahsfihadsiuhfu
    //commento di gaetano reajhj
    //commento di davide
    // commento giuseppe
    public static void main(String[] args) {
        ActionText actionText = new ActionText("Ciao");
        actionText.executeAction();
    }
}