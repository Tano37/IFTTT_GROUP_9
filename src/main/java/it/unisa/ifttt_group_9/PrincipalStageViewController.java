package it.unisa.ifttt_group_9;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import javafx.stage.FileChooser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;



import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalStageViewController implements Initializable {

    @FXML
    private StackPane stackPaneId;

    @FXML
    private AnchorPane ancorPane1;

    @FXML
    private TableView<Rule> rulesTable;

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
    private TabPane tabPane2;

    @FXML
    private Tab textMessageTab;

    @FXML
    private TextField textMessageId;

    @FXML
    private Tab audioTab;

    @FXML
    private ComboBox<String> audioChoice;

    @FXML
    private Button confirmBtn;

    @FXML
    private Button backBtn2;

    @FXML
    private TextField nameRuleText;

    @FXML
    private Button fileChooserBtn;

    private Trigger selectedTrigger;
    private Action selectedAction;
    private ObservableList<Rule> rulesList;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        rulesList= FXCollections.observableArrayList();
        rulesTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        BooleanBinding bb1 = Bindings.or(
                nameRuleText.textProperty().isEmpty(),
                //Bindings.and(
                        Bindings.or(
                                textMessageId.textProperty().isEmpty(),
                                textMessageId.textProperty().isEqualTo(" ")
                       // )

                )
        );
        confirmBtn.disableProperty().bind(bb1);

        ruleClm.setCellValueFactory(new PropertyValueFactory("ruleName"));
        rulesTable.setItems(rulesList);
        Bindings.bindContent(RuleManager.getInstance().getRuleList(), rulesList);

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

        //CONTROLLO TRIGGER (MIGLIORIE DA APPLICARE, INCLUDERE MECCANISMO PER FAR PARTIRE UNA VOLTA I TIMESTAMP TRIGGERS)
        Timeline timeline=new Timeline(new KeyFrame(
                Duration.millis(400), e->{

            for(Rule r : rulesList){
                if(r.getRuleTrigger().evaluate() && !r.getLaunched()){
                    r.setLaunched(true);
                    RuleExecuteService myService = new RuleExecuteService(r);
                    myService.start();
                }else {
                    r.setLaunched(r.getRuleTrigger().evaluate());
                }
            }
        })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    void addRuleAction(ActionEvent event) {
        ancorPane1.visibleProperty().setValue(false);
        ancorPane2.visibleProperty().setValue(true);

    }

    @FXML
    void back1Action(ActionEvent event) {
        ancorPane2.visibleProperty().setValue(false);
        ancorPane1.visibleProperty().setValue(true);
        hoursChoiceId.setValue(null);
        minuteChoiceId.setValue(null);
        selectedTrigger = null;
    }

    @FXML
    void continueAction(ActionEvent event) {
        ancorPane2.visibleProperty().setValue(false);
        ancorPane3.visibleProperty().setValue(true);

        String tabId = tabPane1.getSelectionModel().getSelectedItem().getId();

        if (tabId.equals("timeTab")) {
            TriggerFactory factory = new TriggerTimestampFactory();
            selectedTrigger = factory.createTrigger(hoursChoiceId.getValue(), minuteChoiceId.getValue());
            /*questa variabile selectedTrigger andrà riazzerata una volta creata definitivamente la regola
            e alla regola andrà messo il check che i campi trigger e action siano diversi da null*/
        }
        //qui andranno il resto degli if per gli altri trigger
    }

    @FXML
    void back2Action(ActionEvent event) {
        ancorPane3.visibleProperty().setValue(false);
        ancorPane2.visibleProperty().setValue(true);
        selectedAction = null;
    }

    @FXML
    void confirmAction(ActionEvent event) {
        ancorPane3.visibleProperty().setValue(false);
        ancorPane1.visibleProperty().setValue(true);

        String tabId = tabPane2.getSelectionModel().getSelectedItem().getId();
       // System.out.println(tabId);

        if(tabId.equals("textMessageTab")) {
            ActionFactory factory = new ActionTextFactory();

            selectedAction = factory.createAction(textMessageId.getText());
           //System.out.println(selectedAction.toString());
        }
        /*else if(tabId.equals("audioTab")){

        }*/

        Rule createdRule = new Rule(nameRuleText.getText(), selectedTrigger, selectedAction);
        rulesList.add(createdRule);
        selectedTrigger = null;
        selectedAction = null;

        hoursChoiceId.setValue(null);
        minuteChoiceId.setValue(null);
        textMessageId.clear();
        nameRuleText.clear();

        System.out.println(RuleManager.getInstance().toString());
    }

    @FXML
    void showFileChooser(ActionEvent event) {

            // Creazione di un nuovo oggetto JFileChooser
            JFileChooser fileChooser = new JFileChooser();

            // Impostazione del selettore di cartelle (invece di file)
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            FileNameExtensionFilter filter = new FileNameExtensionFilter("File WAV (*.wav)", "wav");

            // Applicazione del filtro al selettore di file
            fileChooser.setFileFilter(filter);


        // Mostra il selettore di cartelle
            int result = fileChooser.showOpenDialog(null);

            // Verifica se l'utente ha selezionato una cartella
            if (result == JFileChooser.APPROVE_OPTION) {
                // Ottieni la cartella selezionata
                File selectedFolder = fileChooser.getSelectedFile();

                // Stampa il percorso della cartella
                System.out.println("Cartella selezionata: " + selectedFolder.getAbsolutePath());
                selectedAction = new ActionAudio(selectedFolder.getPath());
            } else {
                System.out.println("Nessuna cartella selezionata.");
            }
    }

}
