package it.unisa.ifttt_group_9;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ActionTextViewController {

    @FXML
    private Button closeBtn;

    @FXML
    private Label actionTextLabel;


    @FXML
    void closeTextActionPopUp(ActionEvent event) {

        //ActionTextView.close();
    }

}
