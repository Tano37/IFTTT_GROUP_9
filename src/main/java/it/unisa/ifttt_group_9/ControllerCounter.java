package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.Rule.Rule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.Optional;

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
        try {
            saveCounterList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Funzione per salvare la lista di contatori su un file binario
    public void saveCounterList() throws IOException {
        ObjectOutputStream binaryFileOut = new ObjectOutputStream(new FileOutputStream("COUNTERS.dat"));
        for (Counter counter : counterList) {
            binaryFileOut.writeObject(counter);
        }
        binaryFileOut.close();
    }

    // Funzione per caricare la lista di contatori da un file binario
    public void loadCounterList() throws IOException {
        File file = new File("COUNTERS.dat");

        if (file.exists()) {
            ObjectInputStream binaryFileIn = new ObjectInputStream(new FileInputStream("COUNTERS.dat"));
            while (true) {
                try {
                    Counter counter = (Counter) binaryFileIn.readObject();
                    counterList.add(counter);
                } catch (IOException | ClassNotFoundException e) {
                    // Fine del file
                    break;
                }
            }
        }
    }

    public void update(ObservableList<Counter> countersToModify) {
        // Creazione della finestra di dialogo
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Modifica dei Contatori");
        dialog.setHeaderText("Inserisci i nuovi valori per i contatori:");

        // Mostra la finestra di dialogo e attendi la risposta dell'utente
        Optional<String> result = dialog.showAndWait();

        // Processa la risposta dell'utente
        result.ifPresent(newValues -> {
            try {
                // Dividi la stringa contenente i nuovi valori per i contatori
                String[] newValuesArray = newValues.split(",");

                // Verifica che il numero di nuovi valori corrisponda al numero di contatori
                if (newValuesArray.length == countersToModify.size()) {
                    // Aggiorna i valori dei contatori
                    for (int i = 0; i < countersToModify.size(); i++) {
                        Counter counter = countersToModify.get(i);
                        int newCounterValue = Integer.parseInt(newValuesArray[i]);
                        counter.setValue(newCounterValue);
                    }

                    // Aggiorna la visualizzazione o esegui altre operazioni necessarie
                    // Esempio: Aggiorna la TableView se stai utilizzando una TableView
                    // tableView.refresh();

                } else {
                    showAlert("Errore", "Il numero di nuovi valori non corrisponde al numero di contatori.");
                }

            } catch (NumberFormatException e) {
                // Gestisci il caso in cui l'utente inserisce un valore non valido
                showAlert("Errore", "Inserisci valori validi per i contatori.");
            }
        });
        try {
            saveCounterList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo di utilità per mostrare una finestra di dialogo di avviso
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }




}
