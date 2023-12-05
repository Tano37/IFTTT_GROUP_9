package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.Rule.Rule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.*;

public class ControllerCounter implements Serializable {
    ObservableList<Counter> counterList;

    public ControllerCounter(ObservableList<Counter> counterList) {
        this.counterList = counterList;
    }

    public void creation(){
        // Counter count=null;
        // Creazione dei campi di testo
        TextField name = new TextField();
        TextField value = new TextField();

        // Creazione delle etichette
        Label nameLabel = new Label("Name:");
        Label valueLabel = new Label("Value:");

        // Creazione del contenitore HBox per ciascuna coppia etichetta-campo di testo
        HBox nameBox = new HBox(nameLabel, name);
        HBox valueBox = new HBox(valueLabel, value);

        // Creazione del contenitore VBox per contenere tutte le coppie
        VBox container = new VBox(nameBox, valueBox);

        // Creazione della finestra di dialogo
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create a counter");
        dialog.setHeaderText("Insert name and value:");

        // Impostazione del contenitore come contenuto della finestra di dialogo
        dialog.getDialogPane().setContent(container);

        // Ottieni il pulsante OK
        Button okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setDisable(true); // Disabilita il pulsante OK all'inizio

        // Aggiungi un listener per il cambiamento nel campo di testo del valore
        value.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                // Prova a convertire il testo in un numero
                Integer.parseInt(newValue);
                okButton.setDisable(false); // Abilita il pulsante OK se il testo può essere convertito in un numero
            } catch (NumberFormatException e) {
                // Il testo non può essere convertito in un numero
                okButton.setDisable(true); // Disabilita il pulsante OK
            }
        });

        // Mostra la finestra di dialogo e attendi la sua chiusura
        dialog.showAndWait().ifPresent(response -> {
            // response contiene il valore inserito dall'utente (OK è stato premuto)
            String enteredName = name.getText();
            Integer enteredValue = Integer.parseInt(value.getText());

            // Ora puoi utilizzare enteredName e enteredValue come desideri
            Counter count = new Counter(enteredName, enteredValue);
            if (counterList != null) {

                counterList.add(count);
                try {
                    saveCounterList();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public void delete(ObservableList<Counter> selectedItems){
        counterList.removeAll(selectedItems);
    }
    public void saveCounterList() throws IOException {
        ObjectOutputStream binaryFileOut = new ObjectOutputStream(new FileOutputStream("COUNTERS.dat"));
        for (Counter counter : counterList) {
            binaryFileOut.writeObject(counter);
        }
        binaryFileOut.close();
    }

    public void loadCounterList(ObservableList<Counter> listCounter) throws IOException {
        File file = new File("COUNTER.dat");

        if(file.exists()){
            ObjectInputStream binaryFileIn = new ObjectInputStream(new FileInputStream("COUNTER.dat"));
            while (true) {
                try {
                    Counter counter = (Counter) binaryFileIn.readObject();
                    listCounter.add(counter);
                } catch (IOException | ClassNotFoundException  e) {
                    // Fine del file
                    break;
                }
            }
        }

    }



}
