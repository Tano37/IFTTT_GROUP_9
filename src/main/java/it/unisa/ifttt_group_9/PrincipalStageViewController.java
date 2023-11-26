package it.unisa.ifttt_group_9;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class PrincipalStageViewController implements Initializable {
    @FXML
    private Button fileChooserBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private StackPane stackPaneId;

    @FXML
    private AnchorPane ancorPane1;

    @FXML
    private TableView<Rule> rulesTable;

    @FXML
    private TableColumn<Rule, String> ruleClm;

    @FXML
    private TableColumn<Rule, String> ruleClmStatus;

    @FXML
    private Button addRuleBtn;
    @FXML
    private Button deleteRuleBtn;

    @FXML
    private Button activateRuleBtn;

    @FXML
    private Button deactivateRuleBtn;

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

    private Trigger selectedTrigger;
    private Action selectedAction;
    private ObservableList<Rule> rulesList;
    private int result = -1;
    private JFileChooser fileChooser = new JFileChooser();

    private Rule selectedRuleForDeactivation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        rulesList= FXCollections.observableArrayList();
        //rulesList.add(new Rule("CIap",new TriggerTimestamp(11,11), new ActionText("cIap")));
        rulesTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setActualTime();

        /*BooleanBinding bb1 = Bindings.or(
                nameRuleText.textProperty().isEmpty(),
                // Bindings.and(
                Bindings.or(
                        textMessageId.textProperty().isEmpty(),
                        textMessageId.textProperty().isEqualTo(" ")
                ).and(tabPane2.selectionModelProperty().isEqualTo(tabPane2.selectionModelProperty()))
                //,
                //   new SimpleBooleanProperty(result == -1)
                // )
        );*/
        confirmBtn.disableProperty().bind(nameRuleText.textProperty().isEmpty());
        activateRuleBtn.disableProperty().setValue(true);
        deactivateRuleBtn.disableProperty().setValue(true);

        ruleClm.setCellValueFactory(new PropertyValueFactory("ruleName"));
        rulesTable.setItems(rulesList);
        Bindings.bindContent(RuleManager.getInstance().getRuleList(), rulesList);

        TableColumn<Rule, Boolean> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setCellFactory(column -> new TableCell<Rule, Boolean>() {
            @Override
            protected void updateItem(Boolean status, boolean empty) {
                super.updateItem(status, empty);
                if (empty || status == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(status ? "Activate" : "Deactivate");
                    setStyle(status ? "-fx-text-fill: green;" : "-fx-text-fill: red;");
                }
            }
        });

        rulesTable.getColumns().add(statusColumn);

        ObservableList<Integer> hoursList = FXCollections.observableArrayList();
        for (int i = 0; i <= 23; i++) {
            hoursList.add(i);
        }
        hoursChoiceId.setItems(hoursList);
        hoursChoiceId.autosize();


        ObservableList<Integer> minuteList = FXCollections.observableArrayList();
        for (int i = 0; i <= 59; i++) {
            minuteList.add(i);
        }
        minuteChoiceId.setItems(minuteList);
        minuteChoiceId.autosize();


        BooleanBinding bb = Bindings.or(
                hoursChoiceId.valueProperty().isNull(),
                minuteChoiceId.valueProperty().isNull()
        );
        //continueBtn.disableProperty().bind(bb);

        /*rulesTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Rule>() {
            @Override
            public void changed(ObservableValue<? extends Rule> observable, Rule oldValue, Rule newValue) {
                // Esegue l'azione quando un elemento viene selezionato
                if (newValue != null) {
                    System.out.println("Elemento selezionato");
                    activateRuleBtn.disableProperty().setValue(false);
                    deactivateRuleBtn.disableProperty().setValue(false);
                }
                else{
                    activateRuleBtn.disableProperty().setValue(true);
                    deactivateRuleBtn.disableProperty().setValue(true);
                }
            }
        });
         */
        rulesTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Rule>() {
            @Override
            public void changed(ObservableValue<? extends Rule> observable, Rule oldValue, Rule newValue) {
                handleRuleSelection(newValue);
            }
        });



        //CONTROLLO TRIGGER (MIGLIORIE DA APPLICARE, INCLUDERE MECCANISMO PER FAR PARTIRE UNA VOLTA I TIMESTAMP TRIGGERS)
        Timeline timeline=new Timeline(new KeyFrame(
                Duration.millis(4000), e->{

            for(Rule r : rulesList){

                if(r.getRuleTrigger().evaluate() && !r.getLaunched() && r.getStatus()){
                    System.out.print(r.getRuleTrigger().evaluate()+ ":::"+ r.getLaunched());
                    r.setLaunched(true);
                    RuleExecuteService myService = new RuleExecuteService(r);
                    myService.start();
                }else {
                    r.setLaunched(r.getRuleTrigger().evaluate() );


                }
            }
        })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        try {
            loadRuleList(rulesList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void setActualTime(){
        LocalTime now= LocalTime.now();
        hoursChoiceId.setValue(now.getHour());
        minuteChoiceId.setValue(now.getMinute());
    }
    void handleRuleSelection(Rule newValue) {
        if (newValue != null) {
            System.out.println("Elemento selezionato: " + newValue.getRuleName());
            activateRuleBtn.setDisable(newValue.getStatus());
            deactivateRuleBtn.setDisable(!newValue.getStatus());
        } else {
            activateRuleBtn.setDisable(true);
            deactivateRuleBtn.setDisable(true);
        }
    }



    @FXML
    void addRuleAction(ActionEvent event) {
        ancorPane1.visibleProperty().setValue(false);
        ancorPane2.visibleProperty().setValue(true);

    }

    @FXML
    void deleteRuleAction(ActionEvent event) {
        ObservableList<Rule> selectedItems = rulesTable.getSelectionModel().getSelectedItems();
        rulesList.removeAll(selectedItems);
    }

    @FXML
    void updateActivationState(MouseEvent event) {
        //
    }

    @FXML
    void activateRuleAction(ActionEvent event) {
        Rule selectedItem = rulesTable.getSelectionModel().getSelectedItem();

        if (!selectedItem.getStatus()) {
            System.out.println("Regola: "+ selectedItem.getRuleName()+ " attivata");
            selectedItem.setStatus(true);
            rulesTable.refresh();
            activateRuleBtn.setDisable(true);
            deactivateRuleBtn.setDisable(false);
            try {
                saveRuleList(rulesList);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @FXML
    void deactivateRuleAction(ActionEvent event) {
        Rule selectedItem = rulesTable.getSelectionModel().getSelectedItem();

        if (selectedItem.getStatus()) {
            System.out.println("Regola: "+ selectedItem.getRuleName()+ " disattivata");
            selectedItem.setStatus(false);
            rulesTable.refresh();
            activateRuleBtn.setDisable(false);
            deactivateRuleBtn.setDisable(true);
            try {
                saveRuleList(rulesList);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void back1Action(ActionEvent event) {
        ancorPane2.visibleProperty().setValue(false);
        ancorPane1.visibleProperty().setValue(true);
        rulesTable.getSelectionModel().clearSelection();
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
    void confirmAction(ActionEvent event) throws IOException {
        /*ancorPane3.visibleProperty().setValue(false);
        ancorPane1.visibleProperty().setValue(true);
        rulesTable.getSelectionModel().clearSelection();*/

        String tabId = tabPane2.getSelectionModel().getSelectedItem().getId();
       // System.out.println(tabId);

        if(tabId.equals("textMessageTab")) {
            if(textMessageId.getText().isEmpty() || textMessageId.getText().equals(" ")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setContentText("Inserisci un testo!");
                alert.showAndWait();
            }else {
                ActionFactory factory = new ActionTextFactory();
                selectedAction = factory.createAction(textMessageId.getText());
                //System.out.println(selectedAction.toString());

                Rule createdRule = new Rule(nameRuleText.getText(), selectedTrigger, selectedAction);
                rulesList.add(createdRule);
                saveRuleList(rulesList);
                selectedTrigger = null;
                selectedAction = null;

                setActualTime();
                textMessageId.clear();
                nameRuleText.clear();

                System.out.println(RuleManager.getInstance().toString());
                ancorPane3.visibleProperty().setValue(false);
                ancorPane1.visibleProperty().setValue(true);
                rulesTable.getSelectionModel().clearSelection();
            }
        }
        else if(tabId.equals("audioTab")){
            ActionAudioFactory factory = new ActionAudioFactory();

            if (fileChooser.getSelectedFile() == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setContentText("Inserisci il file audio!");
                alert.showAndWait();

            }else {

                File selectedFolder = fileChooser.getSelectedFile();
                selectedAction = factory.createAction(selectedFolder.getPath());

                Rule createdRule = new Rule(nameRuleText.getText(), selectedTrigger, selectedAction);
                rulesList.add(createdRule);
                saveRuleList(rulesList);
                selectedTrigger = null;
                selectedAction = null;

                setActualTime();
                textMessageId.clear();
                nameRuleText.clear();

                System.out.println(RuleManager.getInstance().toString());
                ancorPane3.visibleProperty().setValue(false);
                ancorPane1.visibleProperty().setValue(true);
                rulesTable.getSelectionModel().clearSelection();
            }
        }
    }

    void saveRuleList(ObservableList<Rule> list) throws IOException {
        ObjectOutputStream binaryFileOut = new ObjectOutputStream(new FileOutputStream("RULES.dat"));
        for (Rule rule : list) {
            binaryFileOut.writeObject(rule);
        }
        binaryFileOut.close();
    }

    void loadRuleList(ObservableList<Rule> list) throws IOException {

        File file = new File("RULES.dat");

        if(file.exists()){
            ObjectInputStream binaryFileIn = new ObjectInputStream(new FileInputStream("RULES.dat"));
            while (true) {
                try {
                    Rule rule = (Rule) binaryFileIn.readObject();
                    list.add(rule);
                } catch (IOException | ClassNotFoundException  e) {
                    // Fine del file
                    break;
                }
            }
        }
    }

    @FXML
    void showFileChooser(ActionEvent event) {


        // Impostazione del selettore di cartelle (invece di file)
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("File WAV (*.wav)", "wav");

        // Applicazione del filtro al selettore di file
        fileChooser.setFileFilter(filter);


        // Mostra il selettore di cartelle
        result = fileChooser.showOpenDialog(null);

        // Verifica se l'utente ha selezionato una cartella
        if (result == JFileChooser.APPROVE_OPTION) {
            // Ottieni la cartella selezionata
            File selectedFolder = fileChooser.getSelectedFile();

            // Stampa il percorso della cartella
            System.out.println("Cartella selezionata: " + selectedFolder.getAbsolutePath());
            //selectedAction = new ActionAudio(selectedFolder.getPath());
        } else {
            System.out.println("Nessuna cartella selezionata.");
        }
    }

}
