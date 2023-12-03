package it.unisa.ifttt_group_9.Controller;

import it.unisa.ifttt_group_9.Action.Action;
import it.unisa.ifttt_group_9.Action.*;
import it.unisa.ifttt_group_9.Rule.Rule;
import it.unisa.ifttt_group_9.Rule.RuleExecuteService;
import it.unisa.ifttt_group_9.Rule.RuleManager;
import it.unisa.ifttt_group_9.Trigger.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private Button sleepRuleBtn;

    @FXML
    private AnchorPane ancorPane2;

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
    private DatePicker dataPickerId= new DatePicker();
    @FXML
    private Button backSleepBtn;

    @FXML
    private ChoiceBox<Integer> hoursChoiceId;

    @FXML
    private ChoiceBox<Integer> minuteChoiceId;

    @FXML
    private ChoiceBox<String> dayChoiceId;


    @FXML
    private Button continueBtn;

    @FXML
    private Button backBtn1;

    @FXML
    private AnchorPane ancorPane3;
    @FXML
    private AnchorPane ancorPane4;

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
    private CheckBox fireOnceCheckbox;

    @FXML
    private ChoiceBox<Integer> minuteChoiceIdSleep;
    @FXML
    private ChoiceBox<Integer> hourChoiceIdSleep;
    @FXML
    private ChoiceBox<Integer> dayChoiceIdSleep;
    @FXML
    private ChoiceBox<Integer> monthChoiceId;
 
    @FXML
    private Button confirmSleepBtn;

    @FXML
    private Tab fileTab;
    @FXML
    private ChoiceBox<String> fileActionChooser;
    @FXML
    private Button filePathBtn;
    @FXML
    private Button destDirBtn;
    @FXML
    private TextField fileActionLaunchTxt;
    @FXML
    private Label fileActionLabel;

    private Trigger selectedTrigger;
    private Action selectedAction;
    private ObservableList<Rule> rulesList;
    private int result = -1;
    private JFileChooser fileChooserWav = new JFileChooser();
    private JFileChooser fileChooserTxt = new JFileChooser();
    private JFileChooser directoryChooserActionFile = new JFileChooser();
    private JFileChooser directoryChooserTriggerFileExists = new JFileChooser();
    private JFileChooser fileChooserTriggerFileDimension = new JFileChooser();

    @FXML
    private TableColumn<Rule, String> triggerStatusClm;

    @FXML
    private Tab existingFileTab;
    @FXML
    private Button directoryChoosingBtn;
    @FXML
    private TextField fileNameLbl;
    @FXML
    private Tab fileDimensionTab;
    @FXML
    private Button fileDimensionChooser;
    @FXML
    private TextField maxFileDimensionTxt;


    private Rule selectedRuleForDeactivation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeChoiceBox();
        initializeTable();

        dataPickerId.setValue(LocalDate.now());

        dataPickerId.setDayCellFactory(picker -> new DatePickerDateCell());




        rulesList= FXCollections.observableArrayList();
        rulesTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setActualTime();

        confirmBtn.disableProperty().bind(nameRuleText.textProperty().isEmpty());
        activateRuleBtn.disableProperty().setValue(true);
        deactivateRuleBtn.disableProperty().setValue(true);
        sleepRuleBtn.disableProperty().setValue(true);

        ruleClm.setCellValueFactory(new PropertyValueFactory<>("ruleName"));
        triggerStatusClm.setCellValueFactory(new PropertyValueFactory<>("ruleTriggerEvaluation"));
        rulesTable.setItems(rulesList);
        Bindings.bindContent(RuleManager.getInstance().getRuleList(), rulesList);

        TableColumn<Rule, Boolean> statusColumn = getRuleBooleanTableColumn();

        rulesTable.getColumns().add(statusColumn);

        ObservableList<String> triggerFileType = FXCollections.observableArrayList();
        triggerFileType.add("Add String in the end");
        triggerFileType.add("Copy and Paste");
        triggerFileType.add("Delete a File");
        triggerFileType.add("Launch a Program");
        fileActionChooser.setItems(triggerFileType);
        fileActionChooser.valueProperty().setValue(triggerFileType.getFirst());
        fileActionChooser.autosize();

        FileNameExtensionFilter filterTxt = new FileNameExtensionFilter("File TXT (*.txt)", "txt");
        // Applicazione del filtro al selettore di file
        fileChooserTxt.setFileFilter(filterTxt);
        destDirBtn.disableProperty().set(true);
        fileActionLabel.textProperty().set("Insert String to Add");
        // Aggiunta di un listener per catturare i cambiamenti nella selezione nella scelta della Action Tigger
        fileActionChooser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if(newValue.equals("Add String in the end")){
                fileActionLaunchTxt.setVisible(true);
                destDirBtn.disableProperty().set(true);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("File TXT (*.txt)", "txt");
                // Applicazione del filtro al selettore di file
                fileChooserTxt.setFileFilter(filter);
                fileActionLabel.textProperty().set("Insert String to Add");
                fileActionLabel.setVisible(true);
            }
            else if (newValue.equals("Launch a Program")){
                fileActionLaunchTxt.setVisible(true);
                destDirBtn.disableProperty().set(true);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("File EXE (*.exe)", "exe");
                // Applicazione del filtro al selettore di file
                fileChooserTxt.setFileFilter(filter);
                fileActionLabel.textProperty().set("Insert the Arguments");
                fileActionLabel.setVisible(true);
            }
            else if(newValue.equals("Copy and Paste") ){
                fileActionLaunchTxt.setVisible(false);
                destDirBtn.disableProperty().set(false);
                fileChooserTxt.resetChoosableFileFilters();
                fileActionLabel.setVisible(false);
            } else if ( newValue.equals("Delete a File")) {
                fileActionLaunchTxt.setVisible(false);
                destDirBtn.disableProperty().set(true);
                fileChooserTxt.resetChoosableFileFilters();
                fileActionLabel.setVisible(false);
            }
        });

        //binding choince box Timestamp trigger whit button
        BooleanBinding bb = Bindings.or(
                hoursChoiceId.valueProperty().isNull(),
                minuteChoiceId.valueProperty().isNull()
        );
        continueBtn.disableProperty().bind(bb);



            /*LocalDate today=LocalDate.now();
        datePickerId.setValue(today);*/



        rulesTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Rule>() {
            @Override
            public void changed(ObservableValue<? extends Rule> observable, Rule oldValue, Rule newValue) {
                handleRuleSelection(newValue);
            }
        });


        //Controllore di regola
        Timeline timeline=new Timeline(new KeyFrame(
                Duration.millis(400), e->{  //settaggio del tempo di ripetizione
            for(Rule r : rulesList){
                if (r.getDateUntilSleep() != null) {
                    /*LocalDateTime truncatedNow = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
                    LocalDateTime truncatedOtherDateTime = r.getDateUntilSleep().truncatedTo(ChronoUnit.MINUTES);*/

                    LocalDateTime truncatedNow = LocalDateTime.now();
                    LocalDateTime truncatedOtherDateTime= r.getDateUntilSleep();
                    System.out.println("now: "+ truncatedNow+"/other: "+ truncatedOtherDateTime+ "/status: "+ r.getStatus()+ "/name: "+ r.getRuleName());
                    int comparisonResult = truncatedNow.compareTo(truncatedOtherDateTime);

                    if (!r.getStatus() && comparisonResult >= 0) {

                        r.setStatus(true);
                        rulesTable.refresh();
                        r.setDateUntilSleep(null);
                        r.setLaunched(false);
                        //System.out.println(r.getRuleTrigger().evaluate()+"/"+!r.getLaunched()+"/"+r.getStatus());
                    }
                }
                //System.out.printf(r.getRuleTrigger().evaluate()+"//"+!r.getLaunched()+"//"+r.getStatus());
                if(r.getRuleTrigger().evaluate() && !r.getLaunched() && r.getStatus()){

                    r.setLaunched(true);
                    RuleExecuteService myService = new RuleExecuteService(r);
                    myService.start();
                    r.setStatus(!r.isFireOnce());
                    rulesTable.refresh();
                    try {
                        saveRuleList(rulesList);
                    } catch (IOException exc) {
                        throw new RuntimeException(exc);
                    }

                } else {
                    r.setLaunched(r.getRuleTrigger().evaluate() );
                    rulesTable.refresh();
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

        maxFileDimensionTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                //if the new value doesn't math a numeric type set the text to the precedent
                maxFileDimensionTxt.setText(oldValue);
            }
        });
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
        minuteChoiceIdSleep.setItems(dayList.filtered(value -> value >= 0 && value <= 59));
        minuteChoiceIdSleep.autosize();
        hourChoiceIdSleep.setItems(dayList.filtered(value -> value >= 0 && value <= 23));
        hourChoiceIdSleep.autosize();
        dayChoiceIdSleep.setItems(dayList);
        dayChoiceIdSleep.autosize();

        //Set the options for the ChoiceBoxes of day, month, and day of the week
        monthChoiceId.setItems(dayList.filtered(value -> value >= 1 && value <= 31));
        monthChoiceId.autosize();
        ObservableList<String> dayStringList = FXCollections.observableArrayList("Ever", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        dayChoiceId.setItems(dayStringList);
        dayChoiceId.autosize();

        //Values to be set of fault at startup
        dayChoiceId.setValue("Ever");
        minuteChoiceIdSleep.setValue(0);
        hourChoiceIdSleep.setValue(0);
        dayChoiceIdSleep.setValue(0);
        monthChoiceId.setValue(1);
    }

    private void initializeTable() {
        // Inizializza la tabella e le colonne
        // ...

    }

    private TableColumn<Rule, Boolean> getRuleBooleanTableColumn() {
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
        return statusColumn;
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
            sleepRuleBtn.setDisable(!newValue.getStatus());
        } else {
            activateRuleBtn.setDisable(true);
            deactivateRuleBtn.setDisable(true);
            sleepRuleBtn.setDisable(true);
        }
    }



    @FXML
    void addRuleAction(ActionEvent event) {
        ancorPane1.visibleProperty().setValue(false);
        ancorPane2.visibleProperty().setValue(true);
        setActualTime();

    }

    @FXML
    void deleteRuleAction(ActionEvent event) throws IOException {
        ObservableList<Rule> selectedItems = rulesTable.getSelectionModel().getSelectedItems();
        rulesList.removeAll(selectedItems);
        saveRuleList(rulesList);
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
            System.out.println("Regola: "+ selectedItem.getRuleName()+ " disattivata");
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

        Rule selectedItem = rulesTable.getSelectionModel().getSelectedItem();
        LocalDateTime dateUntilSleep = LocalDateTime.now();

        dateUntilSleep = dateUntilSleep.plusMinutes(minutesOfSleep);
        dateUntilSleep = dateUntilSleep.plusHours(hoursOfSleep);
        dateUntilSleep = dateUntilSleep.plusDays(daysOfSleep);

        selectedItem.setDateUntilSleep(dateUntilSleep);
        rulesTable.refresh();
        selectedItem.setStatus(false);
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
        hoursChoiceId.setValue(null);
        minuteChoiceId.setValue(null);
        selectedTrigger = null;
    }

    @FXML
    void continueAction(ActionEvent event) {
        ancorPane2.visibleProperty().setValue(false);
        ancorPane3.visibleProperty().setValue(true);

        String tabId = tabPane1.getSelectionModel().getSelectedItem().getId();
        if (tabId.equals("timeTab"))
        {
            selectedTrigger = new TriggerTimestamp(hoursChoiceId.getValue(), minuteChoiceId.getValue());
            /*questa variabile selectedTrigger andrà riazzerata una volta creata definitivamente la regola
            e alla regola andrà messo il check che i campi trigger e action siano diversi da null*/
        }
        else if (tabId.equals("dayTab"))
        {
            int numberDay = 0;
            for (DayOfWeek day : DayOfWeek.values()) {
                if (day.toString().equalsIgnoreCase(dayChoiceId.getValue())) {
                    numberDay = day.getValue();

                    break; // Esci dal ciclo una volta trovato il giorno corrispondente
                }
            }
            selectedTrigger=new TriggerDay(hoursChoiceId.getValue(), minuteChoiceId.getValue(), numberDay);
        }
        else if (tabId.equals("monthTab"))
        {
           /* minuteChoiceId.setValue(0);
            hourChoiceIdSleep.setValue(0);*/
            //System.out.println("Controller: "+hoursChoiceId.getValue()+"//"+ minuteChoiceId.getValue()+"//"+monthChoiceId.getValue());
            selectedTrigger = new TriggerMonth(hoursChoiceId.getValue(), minuteChoiceId.getValue(), 0,
                    monthChoiceId.getValue());
        }
        else if(tabId.equals("existingFileTab"))
        {
            if (directoryChooserTriggerFileExists.getSelectedFile() == null || fileNameLbl.getText() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setContentText("Compile the fields correctly!");
                alert.showAndWait();
            } else {
                selectedTrigger = new TriggerFile(directoryChooserTriggerFileExists.getSelectedFile().getAbsolutePath(),
                        fileNameLbl.getText());
            }
        }
        else if(tabId.equals("fileDimensionTab"))
        {
            if (fileChooserTriggerFileDimension.getSelectedFile() == null || maxFileDimensionTxt.getText() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setContentText("Compile the fields correctly!");
                alert.showAndWait();
            }else{
                selectedTrigger= new TriggerFileDimension(fileChooserTriggerFileDimension
                        .getSelectedFile().getAbsolutePath(), Long.parseLong(maxFileDimensionTxt.getText()));
            }

        }
        else if (tabId.equals("fullDateTab"))
        {
            LocalDate fullDateInsert= dataPickerId.getValue();

            int dayInsert=fullDateInsert.getDayOfMonth();
            int monthInsert=fullDateInsert.getMonth().getValue();
            int yearInsert=fullDateInsert.getYear();
            int hourchoise= hoursChoiceId.getValue();
            int minutechoise= minuteChoiceId.getValue();

            selectedTrigger =new TriggerFullDate(hoursChoiceId.getValue(), minuteChoiceId.getValue(), dayInsert,
                    monthInsert,yearInsert);
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
            // Ottieni la cartella selezionata
            File selectedFolder = directoryChooserTriggerFileExists
                    .getSelectedFile();
            // Stampa il percorso della cartella
            System.out.println("Cartella selezionata: " + selectedFolder.getAbsolutePath());
            //selectedAction = new ActionAudio(selectedFolder.getPath());
        } else {
            System.out.println("Nessuna cartella selezionata.");
        }
    }


    @FXML
    void back2Action(ActionEvent event) {
        ancorPane3.visibleProperty().setValue(false);
        ancorPane2.visibleProperty().setValue(true);
        selectedAction = null;
        fileChooserWav.setSelectedFile(null);

    }

    @FXML
    void confirmAction(ActionEvent event) throws IOException {

        String tabId = tabPane2.getSelectionModel().getSelectedItem().getId();

        if(tabId.equals("textMessageTab") && !nameRuleText.getText().trim().isEmpty())
        {
            if(textMessageId.getText().trim().isEmpty() ){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setContentText("Inserisci un testo!");
                alert.showAndWait();
            }else {
                selectedAction = new ActionText(textMessageId.getText());
                //System.out.println(selectedAction.toString());
                createRule();

            }
        }
        else if(tabId.equals("audioTab") && !nameRuleText.getText().trim().isEmpty())
        {
            if (fileChooserWav.getSelectedFile() == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setContentText("Inserisci il file audio!");
                alert.showAndWait();

            }else {
                File selectedFolder = fileChooserWav.getSelectedFile();
                selectedAction = new ActionAudio(selectedFolder.getPath());
                fileChooserWav.setSelectedFile(null);
                createRule();
            }
        }
        else if(nameRuleText.getText().trim().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setContentText("Inserisci un nome al file");
            alert.showAndWait();
        }
        else if(tabId.equals("fileTab") && !nameRuleText.getText().trim().isEmpty())
        {
            if(fileActionChooser.getValue().equals("Add String in the end")){

                if (fileChooserTxt.getSelectedFile() == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errore");
                    alert.setContentText("Inserisci il file!");
                    alert.showAndWait();
                }
                else{

                    File selectedFolder = fileChooserTxt.getSelectedFile();
                    String testInFile = fileActionLaunchTxt.getText();
                    selectedAction = new ActionFileAddString(selectedFolder.getPath(), testInFile);
                    fileChooserTxt.setSelectedFile(null);
                    createRule();
                }
            }
            else if(fileActionChooser.getValue().equals("Copy and Paste")){

                if (fileChooserTxt.getSelectedFile() == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errore");
                    alert.setContentText("Inserisci il file");
                    alert.showAndWait();
                }
                else if (directoryChooserActionFile.getSelectedFile() == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errore");
                    alert.setContentText("Inserisci la cartella di destinazione!");
                    alert.showAndWait();
                }
                else{
                    File selectedFolder = fileChooserTxt.getSelectedFile();
                    File dirSelectedFolder = directoryChooserActionFile.getSelectedFile();

                    selectedAction = new ActionFileCopy(selectedFolder.getPath(),dirSelectedFolder.getPath());
                    fileChooserTxt.setSelectedFile(null);
                    directoryChooserActionFile.setSelectedFile(null);
                    createRule();
                }
            }
            else if(fileActionChooser.getValue().equals("Delete a File")){

                if (fileChooserTxt.getSelectedFile() == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errore");
                    alert.setContentText("Inserisci il file!");
                    alert.showAndWait();
                }
                else{
                    File selectedFolder = fileChooserTxt.getSelectedFile();
                    selectedAction = new ActionFileDelete(selectedFolder.getPath());
                    fileChooserTxt.setSelectedFile(null);
                    createRule();
                }
            }else if(fileActionChooser.getValue().equals("Launch a Program")){

                if (fileChooserTxt.getSelectedFile() == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errore");
                    alert.setContentText("Inserisci il file!");
                    alert.showAndWait();
                }
                else{
                    File selectedFolder = fileChooserTxt.getSelectedFile();
                    String comandi= fileActionLaunchTxt.getText();
                    System.out.println(comandi);
                    selectedAction = new ActionFileLaunch(selectedFolder.getPath(),comandi);
                    fileChooserTxt.setSelectedFile(null);
                    fileActionLaunchTxt.clear();
                    createRule();
                }
            }


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
        // Verifica se l'utente ha selezionato una cartella
        if (result == JFileChooser.APPROVE_OPTION) {
            // Ottieni la cartella selezionata
            File selectedFolder = fileChooserWav.getSelectedFile();
            // Stampa il percorso della cartella
            System.out.println("Cartella selezionata: " + selectedFolder.getAbsolutePath());
            //selectedAction = new ActionAudio(selectedFolder.getPath());
        } else {
            System.out.println("Nessuna cartella selezionata.");
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

        Rule createdRule = new Rule(nameRuleText.getText(), selectedTrigger, selectedAction,fireOnceCheckbox.isSelected());
        rulesList.add(createdRule);
        try{
            saveRuleList(rulesList);
        }catch (IOException e){
            System.out.println("Error in the list adding");
        }

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

    void saveRuleList(ObservableList<Rule> list) throws IOException {
        ObjectOutputStream binaryFileOut = new ObjectOutputStream(new FileOutputStream("RULES.dat"));
        for (Rule rule : list) {
            binaryFileOut.writeObject(rule);
        }
        binaryFileOut.close();
    }


// ...


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
    void fileDimensionChooser(ActionEvent event){

        JFrame frame = new JFrame("Panel");
        frame.setAlwaysOnTop(true);
        JDialog dialog = new JDialog(frame, "Panel", true);

        fileChooserTriggerFileDimension.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooserTriggerFileDimension.showOpenDialog(dialog);
    }

    public static class DatePickerDateCell extends javafx.scene.control.DateCell {
        @Override
        public void updateItem(LocalDate item, boolean empty) {
            super.updateItem(item, empty);

            // Disabilita la selezione delle date precedenti alla data odierna
            setDisable(item.isBefore(LocalDate.now()));
        }
    }


}
