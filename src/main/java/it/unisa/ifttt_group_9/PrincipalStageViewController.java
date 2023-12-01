package it.unisa.ifttt_group_9;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.stage.DirectoryChooser;
import javafx.util.Duration;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.net.URL;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;
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
    private JFileChooser fileChooser = new JFileChooser();
    private JFileChooser filePathAction = new JFileChooser();
    private JFileChooser dirPathAction = new JFileChooser();

    private JFileChooser directoryChooser= new JFileChooser();

    @FXML
    private Tab existingFileTab;
    @FXML
    private Button directoryChoosingBtn;
    @FXML
    private TextField fileNameLbl;

    private Rule selectedRuleForDeactivation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        rulesList= FXCollections.observableArrayList();
        rulesTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setActualTime();

        confirmBtn.disableProperty().bind(nameRuleText.textProperty().isEmpty());
        activateRuleBtn.disableProperty().setValue(true);
        deactivateRuleBtn.disableProperty().setValue(true);
        sleepRuleBtn.disableProperty().setValue(true);

        ruleClm.setCellValueFactory(new PropertyValueFactory<>("ruleName"));
        rulesTable.setItems(rulesList);
        Bindings.bindContent(RuleManager.getInstance().getRuleList(), rulesList);

        TableColumn<Rule, Boolean> statusColumn = getRuleBooleanTableColumn();

        rulesTable.getColumns().add(statusColumn);

        ObservableList<Integer> hoursList = FXCollections.observableArrayList();
        for (int i = 0; i <= 23; i++) {
            hoursList.add(i);
        }
        hoursChoiceId.setItems(hoursList);
        hourChoiceIdSleep.setItems(hoursList);
        hoursChoiceId.autosize();
        hourChoiceIdSleep.autosize();

        ObservableList<Integer> dayList = FXCollections.observableArrayList();
        for(int i=0; i<=363; i++){
            dayList.add(i);
        }

        dayChoiceIdSleep.setItems(dayList);
        dayChoiceIdSleep.autosize();

        monthChoiceId.setItems(dayList.filtered(value -> value >= 1 && value <= 31));
        monthChoiceId.autosize();


        ObservableList<Integer> minuteList = FXCollections.observableArrayList();
        for (int i = 0; i <= 59; i++) {
            minuteList.add(i);
        }
        minuteChoiceId.setItems(minuteList);
        minuteChoiceId.autosize();
        minuteChoiceIdSleep.setItems(minuteList);
        minuteChoiceIdSleep.autosize();

        ObservableList<String> dayStringList = FXCollections.observableArrayList();

        String[] daysOfWeek = {"Ever", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        dayStringList.addAll(daysOfWeek);

        dayChoiceId.setItems(dayStringList);
        dayChoiceId.setValue("Ever");

        ObservableList<String> triggerFileType = FXCollections.observableArrayList();
        triggerFileType.add("Add String in the end");
        triggerFileType.add("Copy and Paste");
        triggerFileType.add("Delete a File");
        triggerFileType.add("Launch a Program");
        fileActionChooser.setItems(triggerFileType);
        fileActionChooser.valueProperty().setValue(triggerFileType.getFirst());
        fileActionChooser.autosize();




        // Aggiunta di un listener per catturare i cambiamenti nella selezione nella scelta della Action Tigger
        fileActionChooser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if(newValue.equals("Add String in the end")){
                fileActionLaunchTxt.setVisible(true);
                destDirBtn.disableProperty().set(true);
            }
            else if (newValue.equals("Launch a Program")){
                fileActionLaunchTxt.setVisible(true);
                destDirBtn.disableProperty().set(true);
            }
            else if(newValue.equals("Copy and Paste") ){
                fileActionLaunchTxt.setVisible(false);
                destDirBtn.disableProperty().set(false);
            } else if ( newValue.equals("Delete a File")) {
                fileActionLaunchTxt.setVisible(false);
                destDirBtn.disableProperty().set(true);

            }
        });

        //binding choince box Timestamp trigger whit button
        BooleanBinding bb = Bindings.or(
                hoursChoiceId.valueProperty().isNull(),
                minuteChoiceId.valueProperty().isNull()
        );
        continueBtn.disableProperty().bind(bb);
        minuteChoiceIdSleep.setValue(0);
        hourChoiceIdSleep.setValue(0);
        dayChoiceIdSleep.setValue(0);
        monthChoiceId.setValue(1);

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
        TriggerFactory factory = new TriggerFactory();
        if (tabId.equals("timeTab")) {




            selectedTrigger = factory.createTrigger(hoursChoiceId.getValue(), minuteChoiceId.getValue());
            /*questa variabile selectedTrigger andrà riazzerata una volta creata definitivamente la regola
            e alla regola andrà messo il check che i campi trigger e action siano diversi da null*/
        }
            else if(tabId.equals("dayTab")){
                int numberDay=0;
                for (DayOfWeek day : DayOfWeek.values()) {
                    if (day.toString().equalsIgnoreCase(dayChoiceId.getValue())) {
                        numberDay = day.getValue();

                        break; // Esci dal ciclo una volta trovato il giorno corrispondente
                    }
                }
                selectedTrigger = factory.createTrigger(hoursChoiceId.getValue(), minuteChoiceId.getValue(),numberDay);
            }
            else if(tabId.equals("monthTab")){
           /* minuteChoiceId.setValue(0);
            hourChoiceIdSleep.setValue(0);*/
            System.out.println("Controller: "+hoursChoiceId.getValue()+"//"+ minuteChoiceId.getValue()+"//"+monthChoiceId.getValue());
            selectedTrigger = factory.createTrigger(hoursChoiceId.getValue(), minuteChoiceId.getValue(),0,monthChoiceId.getValue());
            }
            else if(tabId.equals("existingFileTab")){
                if (directoryChooser.getSelectedFile() == null || fileNameLbl.getText() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errore");
                    alert.setContentText("Compile the fields correctly!");
                    alert.showAndWait();
                }else{
                    selectedTrigger= new TriggerFile(directoryChooser.getSelectedFile().getAbsolutePath(), fileNameLbl.getText());
                }

            }
        //qui andranno il resto degli if per gli altri trigger
    }

    @FXML
    void directoryChoosingBtnAction(ActionEvent event) {// Impostazione del selettore di cartelle (invece di file)
        directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File WAV (*.wav)", "wav");
        // Applicazione del filtro al selettore di file
        directoryChooser.setFileFilter(filter);
        // Mostra il selettore di cartelle
        result = directoryChooser.showOpenDialog(null);
        // Verifica se l'utente ha selezionato una cartella
        if (result == JFileChooser.APPROVE_OPTION) {
            // Ottieni la cartella selezionata
            File selectedFolder = directoryChooser.getSelectedFile();
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
        fileChooser.setSelectedFile(null);

    }

    @FXML
    void confirmAction(ActionEvent event) throws IOException {

        String tabId = tabPane2.getSelectionModel().getSelectedItem().getId();

        if(tabId.equals("textMessageTab") && !nameRuleText.getText().trim().isEmpty()) {
            if(textMessageId.getText().trim().isEmpty() ){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setContentText("Inserisci un testo!");
                alert.showAndWait();
            }else {
                ActionFactory factory = new ActionTextFactory();
                selectedAction = factory.createAction(textMessageId.getText());
                //System.out.println(selectedAction.toString());


                createRule();

            }
        }
        else if(tabId.equals("audioTab") && !nameRuleText.getText().trim().isEmpty()){
            ActionAudioFactory factory = new ActionAudioFactory();

            if (fileChooser.getSelectedFile() == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setContentText("Inserisci il file audio!");
                alert.showAndWait();

            }else {

                File selectedFolder = fileChooser.getSelectedFile();
                selectedAction = factory.createAction(selectedFolder.getPath());
                fileChooser.setSelectedFile(null);
                createRule();
            }
        }else if(nameRuleText.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setContentText("Inserisci un nome al file");
            alert.showAndWait();
        }
        else if(tabId.equals("fileTab") && !nameRuleText.getText().trim().isEmpty()){

            if(fileActionChooser.getValue().equals("Add String in the end")){

                if (filePathAction.getSelectedFile() == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errore");
                    alert.setContentText("Inserisci il file!");
                    alert.showAndWait();
                }
                else{

                    File selectedFolder = filePathAction.getSelectedFile();
                    String testInFile = fileActionLaunchTxt.getText();
                    selectedAction = new ActionFileAddString (selectedFolder.getPath(),testInFile);
                    filePathAction.setSelectedFile(null);
                    createRule();
                }
            }
            else if(fileActionChooser.getValue().equals("Copy and Paste")){

                if (filePathAction.getSelectedFile() == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errore");
                    alert.setContentText("Inserisci il file");
                    alert.showAndWait();
                }
                else if (dirPathAction.getSelectedFile() == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errore");
                    alert.setContentText("Inserisci la cartella di destinazione!");
                    alert.showAndWait();
                }
                else{
                    File selectedFolder = filePathAction.getSelectedFile();
                    File dirSelectedFolder = dirPathAction.getSelectedFile();

                    selectedAction = new ActionFileCopy(selectedFolder.getPath(),dirSelectedFolder.getPath());
                    filePathAction.setSelectedFile(null);
                    dirPathAction.setSelectedFile(null);
                    createRule();
                }
            }
            else if(fileActionChooser.getValue().equals("Delete a File")){

                if (filePathAction.getSelectedFile() == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errore");
                    alert.setContentText("Inserisci il file!");
                    alert.showAndWait();
                }
                else{
                    File selectedFolder = filePathAction.getSelectedFile();
                    selectedAction = new ActionFileDelete(selectedFolder.getPath());
                    filePathAction.setSelectedFile(null);
                    createRule();
                }
            }else if(fileActionChooser.getValue().equals("Launch a Program")){

                if (filePathAction.getSelectedFile() == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errore");
                    alert.setContentText("Inserisci il file!");
                    alert.showAndWait();
                }
                else{
                    File selectedFolder = filePathAction.getSelectedFile();
                    String comandi= fileActionLaunchTxt.getText();
                    System.out.println(comandi);
                    selectedAction = new ActionFileLaunch(selectedFolder.getPath(),comandi);
                    filePathAction.setSelectedFile(null);
                    fileActionLaunchTxt.clear();
                    createRule();
                }
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

    @FXML
    void destDirBtnAction(ActionEvent event){
        dirPathAction.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        dirPathAction.showOpenDialog(null);
    }

    @FXML
    void filePathBtnAction(ActionEvent event){

        filePathAction.setFileSelectionMode(JFileChooser.FILES_ONLY);

        filePathAction.showOpenDialog(null);

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


}
