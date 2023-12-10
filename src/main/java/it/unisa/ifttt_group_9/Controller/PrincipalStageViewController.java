package it.unisa.ifttt_group_9.Controller;

import it.unisa.ifttt_group_9.Action.Action;
import it.unisa.ifttt_group_9.Action.*;
import it.unisa.ifttt_group_9.Counters.ControllerCounter;
import it.unisa.ifttt_group_9.Counters.Counter;
import it.unisa.ifttt_group_9.Counters.CounterManager;
import it.unisa.ifttt_group_9.Rule.Rule;
import it.unisa.ifttt_group_9.Rule.RuleExecuteService;
import it.unisa.ifttt_group_9.Rule.RuleManager;
import it.unisa.ifttt_group_9.Trigger.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PrincipalStageViewController implements Initializable {
    
    
    // Buttons
    @FXML
    private Button fileDimensionChooseFileBtn;
    @FXML
    private Button file;
    @FXML
    private Button fileChooserBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button addRuleBtn;
    @FXML
    private Button deleteRuleBtn;
    @FXML
    private Button activateRuleBtn;
    @FXML
    private Button deactivateRuleBtn;
    @FXML
    private Button sleepRuleBtn;
    @FXML
    private Button backSleepBtn;
    @FXML
    private Button continueBtn;
    @FXML
    private Button backBtn1;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button backBtn2;
    @FXML
    private Button confirmSleepBtn;
    @FXML
    private Button filePathBtn;
    @FXML
    private Button destDirBtn;
    @FXML
    private Button directoryChoosingBtn;
    @FXML
    private Button fileDimensionChooser;
    @FXML
    private Button counterBtn;
    @FXML
    private Button backCounterBtn;

    @FXML
    private Button valueInsertByUserBtn;
    @FXML
    private Button selectCounterForTriggerBtn2;
    @FXML
    private Button AddMoreTriggerBtn;
    @FXML
    private Button backSelectCounterBtn;


    @FXML
    private CheckBox changeCounterField;
    @FXML
    private Button withActionBtn;
    @FXML
    private Button withTriggerBtn;
    @FXML
    private Button addActionBtn;
    @FXML
    private Button directoryChoosingControllExitStatusBtn;


    // StackPane and AnchorPanes
    @FXML
    private StackPane stackPaneId;
    @FXML
    private AnchorPane ancorPane1;
    @FXML
    private AnchorPane ancorPane2;
    @FXML
    private AnchorPane ancorPane3;
    @FXML
    private AnchorPane ancorPane4;
    @FXML
    private AnchorPane ancorPaneCounterTable;

    // TableView and TableColumns
    @FXML
    private TableView<Rule> rulesTable;
    @FXML
    private TableColumn<Rule, String> ruleClm;
    @FXML
    private TableColumn<Rule, String> ruleClmStatus;
    @FXML
    private TableColumn<Rule, String> triggerStatusClm;
    @FXML
    private TableView<Counter> counterTable;
    @FXML
    private TableColumn<Counter,String> counterClm;
    @FXML
    private TableColumn<Counter,Integer> valueCounterClm;
    @FXML
    private Button addCounterBtn;
    @FXML
    private Button deleteCounterBtn;
    @FXML
    private Button chooseCounterBtn;
    @FXML
    private Button selectCounterForTriggerBtn;

    // TabPane and Tabs
    @FXML
    private TabPane tabPane1;
    @FXML
    private Tab timeTab;
    @FXML
    private Tab dayTab;
    @FXML
    private Tab monthTab;
    @FXML
    private Tab fullDateTab;
    @FXML
    private TabPane tabPane2;
    @FXML
    private Tab textMessageTab;
    @FXML
    private Tab audioTab;
    @FXML
    private Tab fileTab;
    @FXML
    private Tab existingFileTab;
    @FXML
    private Tab fileDimensionTab;
    @FXML
    private Tab counterTab;
    @FXML
    private Tab controlExitStatusTab;

    // Date Picker, ChoiceBoxes, ComboBox, CheckBox, TextField, Label
    @FXML
    private DatePicker dataPickerId = new DatePicker();
    @FXML
    private ChoiceBox<Integer> hoursChoiceId;
    @FXML
    private ChoiceBox<Integer> minuteChoiceId;
    @FXML
    private ChoiceBox<String> dayChoiceId;
    @FXML
    private ChoiceBox<Integer> minuteChoiceIdSleep;
    @FXML
    private ChoiceBox<Integer> hourChoiceIdSleep;
    @FXML
    private ChoiceBox<Integer> dayChoiceIdSleep;
    @FXML
    private ChoiceBox<Integer> monthChoiceId;
    @FXML
    private ChoiceBox<String> audioChoice;
    @FXML
    private ChoiceBox<String> fileActionChooser;
    @FXML
    private CheckBox logicalOperationCb;

    @FXML
    private ChoiceBox<String> chooserActionCounterId;
    @FXML
    private TextField textMessageId;
    @FXML
    private TextField nameRuleText;
    @FXML
    private TextField fileActionLaunchTxt;
    @FXML
    private TextField fileNameLbl;
    @FXML
    private TextField maxFileDimensionTxt;
    @FXML
    private TextField valueInsertByUser;
    @FXML
    private TextField commandLineTextId;
    @FXML
    private TextField valueTextId;
    @FXML
    private Label fileActionLabel;

    // Checkboxes
    @FXML
    private CheckBox fireOnceCheckbox;
    @FXML
    private CheckBox varsubActionTextCb;
    @FXML
    private CheckBox varsubActionFileCb;
    @FXML
    private CheckBox negateTriggerCheckBox;
    @FXML
    private Label counterConfrontationLbl2, counterConfrontationLbl1, counterConfrontationLbl3;



    // JFileChooser
    private JFileChooser fileChooserWav = new JFileChooser();
    private JFileChooser fileChooserTxt = new JFileChooser();
    private JFileChooser directoryChooserActionFile = new JFileChooser();
    private JFileChooser directoryChooserTriggerFileExists = new JFileChooser();
    private JFileChooser fileChooserTriggerFileDimension = new JFileChooser();
    private JFileChooser fileChooserExitStatus= new JFileChooser();
    
    
    
    
    
    // Other variables
    private Trigger selectedTrigger;
    private Action selectedAction = null;
    private ObservableList<Rule> rulesList;
    private int result = -1;
    private Rule selectedRuleForDeactivation;
    private ObservableList<Counter> counterList = FXCollections.observableArrayList();

    private ControllerCounter controllerCounter= new ControllerCounter(counterList);
    private Counter selectedCounter=null, selectedCounter2=null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initializeChoiceBox();
        initializecounterTable();
        initializeCounterGUI();


        rulesList= FXCollections.observableArrayList();
        rulesTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        confirmBtn.disableProperty().bind(nameRuleText.textProperty().isEmpty());

        activateRuleBtn.disableProperty().setValue(true);
        deactivateRuleBtn.disableProperty().setValue(true);
        sleepRuleBtn.disableProperty().setValue(true);
        withActionBtn.disableProperty().setValue(true);
        withTriggerBtn.disableProperty().setValue(true);
        selectCounterForTriggerBtn.setDisable(true);
        selectCounterForTriggerBtn2.setDisable(true);

        dataPickerId.setDayCellFactory(picker -> new DatePickerDateCell());

        // Set Table Rule in The Principal Interface
        ruleClm.setCellValueFactory(new PropertyValueFactory<>("ruleName"));
        triggerStatusClm.setCellValueFactory(new PropertyValueFactory<>("ruleTriggerEvaluation"));
        rulesTable.setItems(rulesList);
        Bindings.bindContent(RuleManager.getInstance().getRuleList(), rulesList);
        TableColumn<Rule, Boolean> statusColumn = getRuleBooleanTableColumn();
        rulesTable.getColumns().add(statusColumn);
        TableColumn<Rule, String> triggerColumn = new TableColumn<>("Trigger");
        triggerColumn.setCellValueFactory(new PropertyValueFactory<>("RuleTriggerString"));
        rulesTable.getColumns().add(triggerColumn);
        TableColumn<Rule, String> actionColumn = new TableColumn<>("Action");
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("RuleActionString"));
        rulesTable.getColumns().add(actionColumn);

        rulesTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> handleRuleSelection(newValue));

        counterTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> handleCounterSelection(newValue));


        Timeline timeline = getTimeline();
        timeline.play();

        //uploading rules to file
        try {
            loadRuleList(rulesList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        maxFileDimensionTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                //if the new value doesn't math a numeric type set the text to the precedent
                maxFileDimensionTxt.setText(oldValue);
            }
        });
    }

    private Timeline getTimeline() {
        ArrayList<Rule> toCheckList= new ArrayList<>();
        //Controllore di regola
        Timeline timeline=new Timeline(new KeyFrame(
                Duration.millis(1000), e->{//settaggio del tempo di ripetizione

            for(Rule r1 : rulesList){
                if(r1.getStatus()){
                    toCheckList.add(r1);
                }else{
                    if (r1.getDateUntilSleep() != null) {
                        //implementation of sleeping period for a rule
                        LocalDateTime truncatedNow = LocalDateTime.now();
                        LocalDateTime truncatedOtherDateTime= r1.getDateUntilSleep();
                        int comparisonResult = truncatedNow.compareTo(truncatedOtherDateTime);


                        if (!r1.getStatus() && comparisonResult >= 0) {
                            r1.setStatus(true);

                            r1.setDateUntilSleep(null);
                            r1.setLaunched(false);
                        }
                    }

                }


            }
            //check on the activated rules
            for(Rule r : toCheckList){

                if(r.getRuleTrigger().evaluate() && !r.getLaunched() && r.getStatus()){

                    r.setLaunched(true);
                    RuleExecuteService myService = new RuleExecuteService(r);
                    myService.start();
                    r.setStatus(!r.isFireOnce());
                    try {
                        saveRuleList(rulesList);
                    } catch (IOException exc) {
                        throw new RuntimeException(exc);
                    }

                } else {
                    r.setLaunched(r.getRuleTrigger().evaluate() );
                }


            }
            toCheckList.clear();
        })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        return timeline;
    }
    public void initializeCounterGUI(){
        selectCounterForTriggerBtn.setVisible(false);
        selectCounterForTriggerBtn2.setVisible(false);
        valueInsertByUserBtn.visibleProperty().set(false);
        deleteCounterBtn.setDisable(true);
        valueInsertByUser.setTextFormatter(new javafx.scene.control.TextFormatter<>(new IntegerStringConverter(), null, c ->
        {
            if (c.isContentChange()) {
                String newText = c.getControlNewText();
                if (newText.matches("-?\\d*")) {
                    // Accetta solo stringhe che rappresentano numeri interi (positivi o negativi)
                    return c;
                }
            }
            // Non accettare il cambiamento
            return null;
        }));
        if(valueInsertByUserBtn.getText().isEmpty()){
            continueBtn.setDisable(true);
        }
        counterConfrontationLbl3.setVisible(false);
        counterConfrontationLbl1.textProperty().bind(Bindings.concat(
                "Valore attuale: ", valueInsertByUser.textProperty()));
        valueInsertByUser.textProperty().setValue("0");

    }

    private void initializeChoiceBox() {
       /*Initializes user interface elements.
         Populate lists used to select minutes, hours, day, month*/

        //Day list
        ObservableList<Integer> dayList = FXCollections.observableArrayList();
        for (int i = 0; i <= 363; i++) {
            dayList.add(i);
        }

        //Set options for the ChoiceBoxes of minutes and hours
        minuteChoiceId.setItems(dayList.filtered(value -> value >= 0 && value <= 59));
        minuteChoiceId.autosize();
        hoursChoiceId.setItems(dayList.filtered(value -> value >= 0 && value <= 23));
        hoursChoiceId.autosize();

        //Set options for ChoiceBoxes of minutes, hours, day, month of sleep
        minuteChoiceIdSleep.setItems(dayList.filtered(value -> value >= 1 && value <= 59));
        minuteChoiceIdSleep.autosize();
        hourChoiceIdSleep.setItems(dayList.filtered(value -> value >= 0 && value <= 23));
        hourChoiceIdSleep.autosize();
        dayChoiceIdSleep.setItems(dayList);
        dayChoiceIdSleep.autosize();


        //binding choince box Timestamp trigger whit button
        BooleanBinding bb = Bindings.or(
                hoursChoiceId.valueProperty().isNull(),
                minuteChoiceId.valueProperty().isNull()

        );
        continueBtn.disableProperty().bind(bb);

        //Set the options for the ChoiceBoxes of day, month, and day of the week
        monthChoiceId.setItems(dayList.filtered(value -> value >= 1 && value <= 31));
        monthChoiceId.autosize();
        ObservableList<String> dayStringList = FXCollections.observableArrayList( "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        dayChoiceId.setItems(dayStringList);
        dayChoiceId.autosize();

        ObservableList<String> compareValue=FXCollections.observableArrayList("greater","less","equal");
        chooserActionCounterId.setItems(compareValue);
        chooserActionCounterId.setValue("greater");

        //Values to be set of fault at startup
        dayChoiceId.setValue("Monday");
        minuteChoiceIdSleep.setValue(1);
        hourChoiceIdSleep.setValue(0);
        dayChoiceIdSleep.setValue(0);
        monthChoiceId.setValue(1);


        //A list is initialized for the choiceBox of the action on the files
        // in such a way as to change functionality based on the selection made by the user
        ObservableList<String> triggerFileType = FXCollections.observableArrayList();
        triggerFileType.add("Add String in the end");
        triggerFileType.add("Copy and Paste");
        triggerFileType.add("Delete a File");
        triggerFileType.add("Launch a Program");
        fileActionChooser.setItems(triggerFileType);
        fileActionChooser.valueProperty().setValue(triggerFileType.getFirst());
        fileActionChooser.autosize();
        // I set the buttons in such a way as to make the "Add String" functionality immediately available.
        FileNameExtensionFilter filterTxt = new FileNameExtensionFilter("File TXT (*.txt)", "txt");
        fileChooserTxt.resetChoosableFileFilters();
        fileChooserTxt.setFileFilter(filterTxt);
        destDirBtn.disableProperty().set(true);
        fileActionLabel.textProperty().set("Insert String to Add");
        // Adding a listener to catch selection changes when choosing the Action Tigger
        fileActionChooser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case "Add String in the end" -> {
                    fileActionLaunchTxt.setVisible(true);
                    destDirBtn.disableProperty().set(true);
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("File TXT (*.txt)", "txt");
                    // Applicazione del filtro al selettore di file
                    fileChooserTxt.resetChoosableFileFilters();
                    fileChooserTxt.setFileFilter(filter);
                    fileActionLabel.textProperty().set("Insert String to Add");
                    fileActionLabel.setVisible(true);
                    varsubActionFileCb.setVisible(true);
                }
                case "Launch a Program" -> {
                    fileActionLaunchTxt.setVisible(true);
                    destDirBtn.disableProperty().set(true);
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("File EXE (*.exe)", "exe");
                    // Applicazione del filtro al selettore di file
                    fileChooserTxt.resetChoosableFileFilters();
                    fileChooserTxt.setFileFilter(filter);
                    fileActionLabel.textProperty().set("Insert the Arguments");
                    fileActionLabel.setVisible(true);
                    varsubActionFileCb.setVisible(true);
                }
                case "Copy and Paste" -> {
                    fileActionLaunchTxt.setVisible(false);
                    destDirBtn.disableProperty().set(false);
                    fileChooserTxt.resetChoosableFileFilters();
                    fileActionLabel.setVisible(false);
                    varsubActionFileCb.setVisible(false);
                }
                case "Delete a File" -> {
                    fileActionLaunchTxt.setVisible(false);
                    destDirBtn.disableProperty().set(true);
                    fileChooserTxt.resetChoosableFileFilters();
                    fileActionLabel.setVisible(false);
                    varsubActionFileCb.setVisible(false);
                }
            }
        });

    }

    private void initializecounterTable() {
        try {
            // Chiamata alla funzione di caricamento
            controllerCounter.loadCounterList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Creazione delle colonne
        TableColumn<Counter, String> counterClm = new TableColumn<>("name");
        counterClm.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Counter, Integer> valueCounterClm = new TableColumn<>("value");
        valueCounterClm.setCellValueFactory(new PropertyValueFactory<>("value"));

        // Associare le colonne alla TableView
        counterTable.getColumns().addAll(counterClm, valueCounterClm);

        // Impostazione della ObservableList come modello della TableView
        counterTable.setItems(counterList);
        Bindings.bindContent(CounterManager.getInstance().getCounterList(), counterList);
    }

    private TableColumn<Rule, Boolean> getRuleBooleanTableColumn() {
        TableColumn<Rule, Boolean> statusColumn = new TableColumn<>("Rule Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Boolean status, boolean empty) {
                super.updateItem(status, empty);
                if (empty || status == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(status ? "Activate" : "Deactivate");
                    if (status)
                        setStyle("-fx-text-fill: green;");
                    else
                        setStyle("-fx-text-fill: red;");
                }
            }
        });
        return statusColumn;
    }


    void handleRuleSelection(Rule newValue) {
        if (newValue != null) {
            activateRuleBtn.setDisable(newValue.getStatus());
            deactivateRuleBtn.setDisable(!newValue.getStatus());
            sleepRuleBtn.setDisable(!newValue.getStatus());
            withActionBtn.setDisable(false);
            withTriggerBtn.setDisable(false);
        } else {
            activateRuleBtn.setDisable(true);
            deactivateRuleBtn.setDisable(true);
            sleepRuleBtn.setDisable(true);
            withActionBtn.setDisable(true);
            withTriggerBtn.setDisable(true);
        }
    }
    void handleCounterSelection( Counter newValue) {
        if (newValue!=null) {
            selectCounterForTriggerBtn2.setDisable(false);
            selectCounterForTriggerBtn.setDisable(false);
            deleteCounterBtn.setDisable(false);
        } else {
            selectCounterForTriggerBtn.setDisable(true);
            selectCounterForTriggerBtn2.setDisable(true);
            deleteCounterBtn.setDisable(true);
        }
    }


    @FXML
    void addRuleAction(ActionEvent event) {
        ancorPane1.visibleProperty().setValue(false);
        ancorPane2.visibleProperty().setValue(true);
        fieldReset();
    }

    @FXML
    void deleteRuleAction(ActionEvent event) throws IOException {
        ObservableList<Rule> selectedItems = rulesTable.getSelectionModel().getSelectedItems();
        rulesList.removeAll(selectedItems);
        saveRuleList(rulesList);
    }



    @FXML
    void activateRuleAction(ActionEvent event) {
        Rule selectedItem = rulesTable.getSelectionModel().getSelectedItem();

        if (!selectedItem.getStatus()) {
            selectedItem.setStatus(true);
            rulesTable.refresh();
            activateRuleBtn.setDisable(true);
            deactivateRuleBtn.setDisable(false);
            selectedItem.setLaunched(false);
            sleepRuleBtn.setDisable(false);
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
            selectedItem.setStatus(false);
            rulesTable.refresh();
            activateRuleBtn.setDisable(false);
            deactivateRuleBtn.setDisable(true);
            sleepRuleBtn.setDisable(true);
            selectedItem.setDateUntilSleep(null);
            try {
                saveRuleList(rulesList);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void sleepRuleAction(ActionEvent event) {
        Rule selectedItem = rulesTable.getSelectionModel().getSelectedItem();
        ancorPane1.visibleProperty().setValue(false);
        ancorPane4.visibleProperty().setValue(true);
    }
    @FXML
    void createWithAction(ActionEvent event){
        Rule selectedItem = rulesTable.getSelectionModel().getSelectedItem();
        selectedAction = selectedItem.getRuleAction();
        ancorPane1.visibleProperty().setValue(false);
        ancorPane2.visibleProperty().setValue(true);
        fieldReset();
    }
    @FXML
    void createWithTrigger(ActionEvent event){
        Rule selectedItem = rulesTable.getSelectionModel().getSelectedItem();
        selectedTrigger = selectedItem.getRuleTrigger();
        ancorPane1.visibleProperty().setValue(false);
        ancorPane2.visibleProperty().setValue(true);
        fieldReset();
    }
    @FXML
    void backSleepAction(ActionEvent event){
        ancorPane1.visibleProperty().setValue(true);
        ancorPane4.visibleProperty().setValue(false);

    }

    @FXML
    void confirmSleepAction(ActionEvent event) {
        ancorPane4.visibleProperty().setValue(false);
        ancorPane1.visibleProperty().setValue(true);
        Integer minutesOfSleep = minuteChoiceIdSleep.getValue();
        Integer hoursOfSleep = hourChoiceIdSleep.getValue();
        Integer daysOfSleep = dayChoiceIdSleep.getValue();
        minutesOfSleep.toString();
        hoursOfSleep.toString();
        daysOfSleep.toString();

        Rule selectedItem = rulesTable.getSelectionModel().getSelectedItem();
        LocalDateTime dateUntilSleep = LocalDateTime.now();

        dateUntilSleep = dateUntilSleep.plusMinutes(minutesOfSleep);
        dateUntilSleep = dateUntilSleep.plusHours(hoursOfSleep);
        dateUntilSleep = dateUntilSleep.plusDays(daysOfSleep);

        rulesTable.getSelectionModel().getSelectedItem().setDateUntilSleep(dateUntilSleep);
        rulesTable.getSelectionModel().getSelectedItem().setStatus(false);
        System.out.println(rulesTable.getSelectionModel().getSelectedItem().toString());
        rulesTable.refresh();
        sleepRuleBtn.setDisable(true);
        deactivateRuleBtn.setDisable(true);
        activateRuleBtn.setDisable(false);
        try {
            saveRuleList(rulesList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    @FXML
    void back1Action(ActionEvent event) {
        ancorPane2.visibleProperty().setValue(false);
        ancorPane1.visibleProperty().setValue(true);
        rulesTable.getSelectionModel().clearSelection();
        fieldReset();
        selectedAction=null;
        selectedTrigger=null;
        selectedCounter = null;
        selectedCounter2 = null;

    }

    void viewOfAction(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add Action List");
        alert.setHeaderText(null); // Senza intestazione
        alert.setContentText(selectedAction.toString());
        alert.showAndWait();
    }
    void viewOfTrigger(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add Trigger List");
        alert.setHeaderText(null); // Senza intestazione
        alert.setContentText(selectedTrigger.toString());
        alert.showAndWait();

    }

    @FXML
    void addSequenceTriggerAction(ActionEvent event){
        String tabId = tabPane1.getSelectionModel().getSelectedItem().getId();
        switch (tabId) {
            case "timeTab" -> {
                selectedTrigger = new TriggerTimestamp(hoursChoiceId.getValue(),
                        minuteChoiceId.getValue(), negateTriggerCheckBox.isSelected(),
                        selectedTrigger, logicalOperationCb.isSelected());
                if (negateTriggerCheckBox.isSelected()) {
                    selectedTrigger.negate();
                    negateTriggerCheckBox.selectedProperty().set(false);
                }
                viewOfTrigger();
            }
            /*questa variabile selectedTrigger andrà riazzerata una volta creata definitivamente la regola
            e alla regola andrà messo il check che i campi trigger e action siano diversi da null*/
            case "dayTab" -> {
                int numberDay = 0;
                for (DayOfWeek day : DayOfWeek.values()) {
                    if (day.toString().equalsIgnoreCase(dayChoiceId.getValue())) {
                        numberDay = day.getValue();
                        break; // Esci dal ciclo una volta trovato il giorno corrispondente
                    }
                }
                selectedTrigger = new TriggerDay(
                        numberDay, negateTriggerCheckBox.isSelected(),
                        selectedTrigger, logicalOperationCb.isSelected());
                if (negateTriggerCheckBox.isSelected()) {
                    selectedTrigger.negate();
                    negateTriggerCheckBox.selectedProperty().set(false);
                }
                viewOfTrigger();
            }
            case "monthTab" -> {
                selectedTrigger = new TriggerMonth(
                        monthChoiceId.getValue(), negateTriggerCheckBox.isSelected(),
                        selectedTrigger, logicalOperationCb.isSelected());
                if (negateTriggerCheckBox.isSelected()) {
                    selectedTrigger.negate();
                    negateTriggerCheckBox.selectedProperty().set(false);
                }
                viewOfTrigger();
            }
            case "existingFileTab" -> {
                if (directoryChooserTriggerFileExists.getSelectedFile() == null || textIsNotValid(fileNameLbl.getText())) {
                    ancorPane2.visibleProperty().setValue(true);
                    ancorPane3.visibleProperty().setValue(false);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errore");
                    alert.setContentText("Compile the fields correctly!");
                    alert.showAndWait();
                } else {
                    selectedTrigger = new TriggerFile(directoryChooserTriggerFileExists.getSelectedFile().getAbsolutePath(),
                            fileNameLbl.getText(), negateTriggerCheckBox.isSelected(),
                            selectedTrigger, logicalOperationCb.isSelected());
                    if (negateTriggerCheckBox.isSelected()) {
                        selectedTrigger.negate();
                        negateTriggerCheckBox.selectedProperty().set(false);
                    }
                    viewOfTrigger();
                    fileNameLbl.textProperty().setValue(null);
                }
            }
            case "fileDimensionTab" -> {
                if (fileChooserTriggerFileDimension.getSelectedFile() == null || textIsNotValid(maxFileDimensionTxt.getText())) {
                    ancorPane2.visibleProperty().setValue(true);
                    ancorPane3.visibleProperty().setValue(false);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errore");
                    alert.setContentText("Compile the fields correctly!");
                    alert.showAndWait();
                } else {
                    selectedTrigger = new TriggerFileDimension(fileChooserTriggerFileDimension
                            .getSelectedFile().getAbsolutePath(),
                            Long.parseLong(maxFileDimensionTxt.getText()), negateTriggerCheckBox.isSelected(),
                            selectedTrigger, logicalOperationCb.isSelected());
                    if (negateTriggerCheckBox.isSelected()) {
                        selectedTrigger.negate();
                        negateTriggerCheckBox.selectedProperty().set(false);
                    }
                    viewOfTrigger();
                }
            }
            case "fullDateTab" -> {
                LocalDate fullDateInsert = dataPickerId.getValue();
                int dayInsert = fullDateInsert.getDayOfMonth();
                int monthInsert = fullDateInsert.getMonth().getValue();
                int yearInsert = fullDateInsert.getYear();
                selectedTrigger = new TriggerFullDate(dayInsert,
                        monthInsert, yearInsert, negateTriggerCheckBox.isSelected(),
                        selectedTrigger, logicalOperationCb.isSelected());
                if (negateTriggerCheckBox.isSelected()) {
                    selectedTrigger.negate();
                    negateTriggerCheckBox.selectedProperty().set(false);
                }
                viewOfTrigger();
            }
            case "controlExitStatusTab" -> {
                if (fileChooserExitStatus.getSelectedFile() == null || textIsNotValid(valueTextId.getText())) {
                    ancorPane2.visibleProperty().setValue(true);
                    ancorPane3.visibleProperty().setValue(false);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errore");
                    alert.setContentText("Compile the fields correctly!");
                    alert.showAndWait();
                } else {
                    selectedTrigger = new TriggerExitStatus(fileChooserExitStatus.getSelectedFile().getAbsolutePath(),
                            commandLineTextId.getText(), Integer.parseInt(valueTextId.getText()), negateTriggerCheckBox.isSelected(),
                            selectedTrigger, logicalOperationCb.isSelected());
                    if (negateTriggerCheckBox.isSelected()) {
                        selectedTrigger.negate();
                        negateTriggerCheckBox.selectedProperty().set(false);
                    }
                    viewOfTrigger();
                }
            }
            case "counterTab" -> {
                if (changeCounterField.selectedProperty().get()) {
                    if (selectedCounter == null || selectedCounter2 == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Errore");
                        alert.setContentText("Compile the fields correctly!");
                        alert.showAndWait();
                    } else {
                        Counter counterInsert = selectedCounter;
                        Counter counterInsert2 = selectedCounter2;
                        String chooserActionCounter = chooserActionCounterId.getValue();
                        selectedTrigger = new TriggerCounter(selectedCounter2, selectedCounter, chooserActionCounter, negateTriggerCheckBox.isSelected(),
                                selectedTrigger, logicalOperationCb.isSelected());
                        addCounterBtn.setVisible(true);
                        deleteCounterBtn.setVisible(true);
                        backCounterBtn.setVisible(true);
                        selectCounterForTriggerBtn.setVisible(false);

                        counterConfrontationLbl2.setText("");
                        counterConfrontationLbl3.setText("");

                        if (negateTriggerCheckBox.isSelected()) {
                            selectedTrigger.negate();
                            negateTriggerCheckBox.selectedProperty().set(false);
                        }
                        selectedCounter = null;
                        selectedCounter2 = null;
                        viewOfTrigger();
                    }
                }
                if (!changeCounterField.selectedProperty().get()) {
                    if (valueInsertByUser.getText().trim().isEmpty() || selectedCounter == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Errore");
                        alert.setContentText("Compile the fields correctly!");
                        alert.showAndWait();
                    } else {
                        int valueInsert = Integer.parseInt(valueInsertByUser.textProperty().getValue());
                        String chooserActionCounter = chooserActionCounterId.getValue();
                        selectedTrigger = new TriggerCounter(valueInsert, selectedCounter, chooserActionCounter, negateTriggerCheckBox.isSelected(),
                                selectedTrigger, logicalOperationCb.isSelected());
                        addCounterBtn.setVisible(true);
                        deleteCounterBtn.setVisible(true);
                        backCounterBtn.setVisible(true);
                        selectCounterForTriggerBtn.setVisible(false);
                        //  counterConfrontationLbl1.setText("Nothing");
                        counterConfrontationLbl2.setText("");
                        counterConfrontationLbl3.setText("");

                        if (negateTriggerCheckBox.isSelected()) {
                            selectedTrigger.negate();
                            negateTriggerCheckBox.selectedProperty().set(false);
                        }
                        selectedCounter = null;
                        selectedCounter2 = null;
                        viewOfTrigger();
                    }
                }
            }
        }
    }

    @FXML
    void continueAction(ActionEvent event) {
        if(selectedTrigger==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setContentText("Add at least one Trigger to your Rule");
            alert.showAndWait();
        }else {
            ancorPane2.visibleProperty().setValue(false);
            ancorPane3.visibleProperty().setValue(true);
        }
    }

    @FXML
    void directoryChoosingBtnAction(ActionEvent event) {// Impostazione del selettore di cartelle (invece di file)
        JFrame frame = new JFrame("Panel");
        frame.setAlwaysOnTop(true);
        JDialog dialog = new JDialog(frame, "Panel", true);
        directoryChooserTriggerFileExists
                .setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File WAV (*.wav)", "wav");
        // Applicazione del filtro al selettore di file
        directoryChooserTriggerFileExists
                .setFileFilter(filter);
        // Mostra il selettore di cartelle
        result = directoryChooserTriggerFileExists
                .showOpenDialog(dialog);

        // Verifica se l'utente ha selezionato una cartella
        if (result == JFileChooser.APPROVE_OPTION) {
            // Obtain selected Folder
            File selectedFolder = directoryChooserTriggerFileExists
                    .getSelectedFile();
        }
    }


    @FXML
    void back2Action(ActionEvent event) {
        ancorPane3.visibleProperty().setValue(false);
        ancorPane2.visibleProperty().setValue(true);
    }

    
    @FXML
    void concateneteAction(ActionEvent event){

        String tabId = tabPane2.getSelectionModel().getSelectedItem().getId();
        switch (tabId) {
            case "textMessageTab" -> {
                if (textMessageId.getText().trim().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errore");
                    alert.setContentText("Insert Text");
                    alert.showAndWait();
                } else {
                    selectedAction = new ActionText(textMessageId.getText().trim(),
                            selectedAction, varsubActionTextCb.isSelected());
                    viewOfAction();
                    textMessageId.clear();
                }
            }
            case "audioTab" -> {
                if (fileChooserWav.getSelectedFile() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Insert Audio File!");
                    alert.showAndWait();
                } else {
                    File selectedFolder = fileChooserWav.getSelectedFile();
                    selectedAction = new ActionAudio(selectedFolder.getPath(), selectedAction);
                    fileChooserWav.setSelectedFile(null);
                    viewOfAction();
                    fileChooserWav.setSelectedFile(null);
                }
            }
            case "fileTab" -> {
                if (fileActionChooser.getValue().equals("Add String in the end")) {
                    if (fileChooserTxt.getSelectedFile() == null || textIsNotValid(fileActionLaunchTxt.getText())) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Compile the fields correctly!");
                        alert.showAndWait();
                    } else {
                        File selectedFolder = fileChooserTxt.getSelectedFile();
                        String testInFile = fileActionLaunchTxt.getText();
                        selectedAction = new ActionFileAddString(selectedFolder.getPath(), testInFile, selectedAction,
                                varsubActionFileCb.isSelected());
                        viewOfAction();
                        fileChooserTxt.setSelectedFile(null);
                        fileActionLaunchTxt.clear();
                    }
                } else if (fileActionChooser.getValue().equals("Copy and Paste")) {
                    if (fileChooserTxt.getSelectedFile() == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Select source file!");
                        alert.showAndWait();
                    } else if (directoryChooserActionFile.getSelectedFile() == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Select destination file!");
                        alert.showAndWait();
                    } else {
                        File selectedFolder = fileChooserTxt.getSelectedFile();
                        File dirSelectedFolder = directoryChooserActionFile.getSelectedFile();
                        selectedAction = new ActionFileCopy(selectedFolder.getPath(), dirSelectedFolder.getPath(), selectedAction);
                        fileChooserTxt.setSelectedFile(null);
                        directoryChooserActionFile.setSelectedFile(null);
                        viewOfAction();
                        fileChooserTxt.setSelectedFile(null);
                        directoryChooserActionFile.setSelectedFile(null);
                    }
                } else if (fileActionChooser.getValue().equals("Delete a File")) {
                    if (fileChooserTxt.getSelectedFile() == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Select the file to delete!");
                        alert.showAndWait();
                    } else {
                        File selectedFolder = fileChooserTxt.getSelectedFile();
                        selectedAction = new ActionFileDelete(selectedFolder.getPath(), selectedAction);
                        viewOfAction();
                        fileChooserTxt.setSelectedFile(null);
                    }
                } else if (fileActionChooser.getValue().equals("Launch a Program")) {
                    if (fileChooserTxt.getSelectedFile() == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Select the file!");
                        alert.showAndWait();
                    } else {
                        File selectedFolder = fileChooserTxt.getSelectedFile();
                        String commands = fileActionLaunchTxt.getText();
                        selectedAction = new ActionFileLaunch(selectedFolder.getPath(), commands, selectedAction, varsubActionFileCb.isSelected());
                        viewOfAction();
                        fileChooserTxt.setSelectedFile(null);
                        fileActionLaunchTxt.clear();
                    }
                }
            }
        }
    }

    @FXML
    void confirmAction(ActionEvent event) {


        if (!nameRuleText.getText().trim().isEmpty()) {
            if(selectedAction != null){
                createRule();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Add at least one Action to your Rule");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Insert a Correct Name");
            alert.showAndWait();
        }
    }

    @FXML
    void showFileChooser(ActionEvent event) {
        JFrame frame = new JFrame("Panel");
        frame.setAlwaysOnTop(true);
        JDialog dialog = new JDialog(frame, "Panel", true);
        // Impostazione del selettore di cartelle (invece di file)
        fileChooserWav.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File WAV (*.wav)", "wav");
        // Applicazione del filtro al selettore di file
        fileChooserWav.setFileFilter(filter);
        // Mostra il selettore di cartelle
        result = fileChooserWav.showOpenDialog(dialog);
        if (result == JFileChooser.APPROVE_OPTION){
            File selectedFolder = fileChooserWav.getSelectedFile();
        }

    }

    @FXML
    void destDirBtnAction(ActionEvent event){
        JFrame frame = new JFrame("Panel");
        frame.setAlwaysOnTop(true);
        JDialog dialog = new JDialog(frame, "Panel", true);
        directoryChooserActionFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        directoryChooserActionFile.showOpenDialog(dialog);
    }

    @FXML
    void filePathBtnAction(ActionEvent event){
        JFrame frame = new JFrame("Panel");
        frame.setAlwaysOnTop(true);
        JDialog dialog = new JDialog(frame, "Panel", true);
        fileChooserTxt.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooserTxt.showOpenDialog(dialog);
    }

    public void createRule(){
        Rule createdRule = new Rule(nameRuleText.getText(), selectedTrigger,
                selectedAction,fireOnceCheckbox.isSelected());
        rulesList.add(createdRule);
        try{
            saveRuleList(rulesList);
        }catch (IOException e){
            System.out.println("Error in the list adding");
        }
        selectedTrigger = null;
        selectedAction = null;

        ancorPane3.visibleProperty().setValue(false);
        ancorPane1.visibleProperty().setValue(true);
        rulesTable.getSelectionModel().clearSelection();

        if(selectedCounter!=null)
            createdRule.setCounter(selectedCounter);
        selectedCounter=null;
        fieldReset();
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
                    break;
                }
            }
        }
    }

    @FXML
    void fileDimensionChooser(ActionEvent event){
        JFrame frame = new JFrame("Panel");
        frame.setAlwaysOnTop(true);
        JDialog dialog = new JDialog(frame, "Panel", true);
        fileChooserTriggerFileDimension.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooserTriggerFileDimension.showOpenDialog(dialog);
    }

    public boolean textIsNotValid(String text){
        return text == null || text.isEmpty();
    }

    public void fieldReset(){
        //Triggers Fields Set
        hoursChoiceId.setValue(LocalTime.now().getHour());
        minuteChoiceId.setValue(LocalTime.now().getMinute());

        dayChoiceId.setValue("Monday");
        monthChoiceId.setValue(1);
        dataPickerId.setValue(LocalDate.now());

        directoryChooserTriggerFileExists.setSelectedFile(null);
        fileNameLbl.clear();

        fileChooserTriggerFileDimension.setSelectedFile(null);
        maxFileDimensionTxt.clear();

        fileChooserExitStatus.setSelectedFile(null);
        commandLineTextId.clear();
        valueTextId.clear();

        //Actions Fields Set
        textMessageId.clear();
        varsubActionTextCb.setSelected(false);

        fileChooserWav.setSelectedFile(null);

        fileActionChooser.setValue("Add String in the end");
        fileActionLaunchTxt.clear();
        fileChooserTxt.setSelectedFile(null);
        directoryChooserActionFile.setSelectedFile(null);
        varsubActionFileCb.setSelected(false);

        //Rule Fields Set
        fireOnceCheckbox.selectedProperty().setValue(false);
        nameRuleText.clear();

        negateTriggerCheckBox.selectedProperty().set(false);
        logicalOperationCb.selectedProperty().set(false);
        counterTable.getSelectionModel().clearSelection();
        selectCounterForTriggerBtn.setDisable(true);
        selectCounterForTriggerBtn2.setDisable(true);
        valueInsertByUserBtn.setText("Select a counter 1");
        chooseCounterBtn.setText("Select a counter 2");

    }

    public static class DatePickerDateCell extends javafx.scene.control.DateCell {
        @Override
        public void updateItem(LocalDate item, boolean empty) {
            super.updateItem(item, empty);
            // Disabilita la selezione delle date precedenti alla data odierna
            setDisable(item.isBefore(LocalDate.now()));
        }
    }
    @FXML
    void directoryChoosingExitStatusBtnAction(ActionEvent event) {

        JFrame frame = new JFrame("Panel");
        frame.setAlwaysOnTop(true);
        JDialog dialog = new JDialog(frame, "Panel", true);
        fileChooserExitStatus.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File EXE (*.exe)", "exe");
        fileChooserExitStatus.setFileFilter(filter);
        result = fileChooserExitStatus.showOpenDialog(dialog);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Obtain selected Folder
            File selectedFolder = fileChooserExitStatus.getSelectedFile();
        }
    }
    @FXML
    public void counterAction(ActionEvent event){
        backSelectCounterBtn.visibleProperty().setValue(false);
        ancorPane1.visibleProperty().setValue(false);
        ancorPaneCounterTable.visibleProperty().setValue(true);
        counterTable.getSelectionModel().clearSelection();
    }
    @FXML
    public void backCounterAction(ActionEvent event){
        ancorPaneCounterTable.visibleProperty().setValue(false);
        ancorPane1.visibleProperty().setValue(true);
        deleteCounterBtn.setDisable(true);

    }
    @FXML
    public void backSelectCounterAction(ActionEvent event){
        ancorPaneCounterTable.visibleProperty().setValue(false);
        ancorPane2.visibleProperty().setValue(true);
        counterTable.getSelectionModel().clearSelection();

    }



    @FXML
    void addCounterAction(ActionEvent event) {
        // ControllerCounter controller= new ControllerCounter(counterList);
        controllerCounter.creation();
    }

    @FXML
    void deleteCounterAction(ActionEvent event){
        //ControllerCounter controller= new ControllerCounter(counterList);
        ObservableList<Counter> selectedItems = counterTable.getSelectionModel().getSelectedItems();
        controllerCounter.delete(selectedItems);
    }
    @FXML
    void modifeCounterAction(ActionEvent event){
        ObservableList<Counter> selectedItems = counterTable.getSelectionModel().getSelectedItems();
       // controllerCounter.update(selectedItems);
        counterTable.refresh();
    }
    @FXML
    void chooseCounterAction(ActionEvent event){
        backSelectCounterBtn.visibleProperty().setValue(true);
        ancorPane1.visibleProperty().setValue(false);
        ancorPane2.visibleProperty().setValue(false);
        ancorPaneCounterTable.visibleProperty().setValue(true);
        addCounterBtn.setVisible(false);
        deleteCounterBtn.setVisible(false);
        backCounterBtn.setVisible(false);
        selectCounterForTriggerBtn2.setVisible(false);
        selectCounterForTriggerBtn.setVisible(true);
    }
    @FXML
    void selectCounterForTriggerAction(ActionEvent event){
        ancorPaneCounterTable.visibleProperty().setValue(false);
        ancorPane2.visibleProperty().setValue(true);
        ObservableList<Counter> selectedItems = counterTable.getSelectionModel().getSelectedItems();
        chooseCounterBtn.setText(selectedItems.toString());
        selectedCounter = selectedItems.get(0);
        counterConfrontationLbl2.setText("Name: "+ selectedCounter.getName()+" - Value: "+selectedCounter.getValue());
        addCounterBtn.setVisible(true);
        deleteCounterBtn.setVisible(true);
        backCounterBtn.setVisible(true);
        selectCounterForTriggerBtn2.setVisible(false);
        selectCounterForTriggerBtn.setVisible(false);
        counterTable.getSelectionModel().clearSelection();


    }

    private void setButtonForCounterTable(){
        addCounterBtn.setVisible(true);
        deleteCounterBtn.setVisible(true);
        backCounterBtn.setVisible(true);
        selectCounterForTriggerBtn2.setVisible(false);
        selectCounterForTriggerBtn.setVisible(false);
    }
    @FXML
    void valueInsertByUserBtnAction(ActionEvent event){
        backSelectCounterBtn.visibleProperty().setValue(true);
        ancorPane1.visibleProperty().setValue(false);
        ancorPane2.visibleProperty().setValue(false);
        ancorPaneCounterTable.visibleProperty().setValue(true);
        addCounterBtn.setVisible(false);
        deleteCounterBtn.setVisible(false);
        backCounterBtn.setVisible(false);
        selectCounterForTriggerBtn2.setVisible(true);

        selectCounterForTriggerBtn.setVisible(false);

    }
    @FXML
    void selectCounterForTrigger2Action(ActionEvent event){
        ancorPaneCounterTable.visibleProperty().setValue(false);
        ancorPane2.visibleProperty().setValue(true);
        ObservableList<Counter> selectedItems = counterTable.getSelectionModel().getSelectedItems();
        valueInsertByUserBtn.setText(selectedItems.toString());
        selectedCounter2 = selectedItems.get(0);

        counterConfrontationLbl3.setText("Name: "+ selectedCounter2.getName()+" - Value: "+selectedCounter2.getValue());

        addCounterBtn.setVisible(true);
        deleteCounterBtn.setVisible(true);
        backCounterBtn.setVisible(true);
        selectCounterForTriggerBtn2.setVisible(false);
        selectCounterForTriggerBtn.setVisible(false);
        counterTable.getSelectionModel().clearSelection();

    }
    @FXML
    void changeCounterFieldAction(ActionEvent event){
        if(changeCounterField.selectedProperty().get()){
            valueInsertByUser.visibleProperty().set(false);
            valueInsertByUserBtn.visibleProperty().set(true);
            counterConfrontationLbl1.setVisible(false);
            counterConfrontationLbl3.setVisible(true);

        }
        if(!changeCounterField.selectedProperty().get()){
            valueInsertByUserBtn.visibleProperty().set(false);
            valueInsertByUser.visibleProperty().set(true);
            counterConfrontationLbl3.setVisible(false);
            counterConfrontationLbl1.setVisible(true);

        }
    }
}
