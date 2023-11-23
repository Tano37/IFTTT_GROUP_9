package it.unisa.ifttt_group_9;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalStageViewController implements Initializable {

    @FXML
    private StackPane stackPaneId;

    @FXML
    private AnchorPane ancorPane1;

    @FXML
    private TableView<Rule> tableView;

    @FXML
    private TableColumn<Rule, String> ruleClm;

    @FXML
    private Button addRuleBtn;

    @FXML
    private AnchorPane ancorPane2;

    @FXML
    private TabPane tabPane1;

    @FXML
    private Tab timeTab;

    @FXML
    private ChoiceBox<Integer> hoursChoiceId;

    @FXML
    private ChoiceBox<Integer> minuteChoiceId;

    @FXML
    private Button continueBtn;

    @FXML
    private Button backBtn1;

    @FXML
    private AnchorPane ancorPane3;

    @FXML
    private TabPane tabPane11;

    @FXML
    private Tab textMessageTab;

    @FXML
    private TextField textMessageId;

    @FXML
    private Tab audioTab;

    @FXML
    private Button confirmBtn;

    @FXML
    private Button backBtn2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Integer> hoursList = FXCollections.observableArrayList();
        for (int i = 0; i <= 23; i++) {
            hoursList.add(i);
        }
        hoursChoiceId.setItems(hoursList);

        ObservableList<Integer> minuteList = FXCollections.observableArrayList();
        for (int i = 0; i <= 59; i++) {
            minuteList.add(i);
        }
        minuteChoiceId.setItems(minuteList);

        BooleanBinding bb = Bindings.or(
                hoursChoiceId.valueProperty().isNull(),
                minuteChoiceId.valueProperty().isNull()
        );
        continueBtn.disableProperty().bind(bb);

    }
}
