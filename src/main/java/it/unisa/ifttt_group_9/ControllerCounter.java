package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.Rule.Rule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.Optional;

public class ControllerCounter implements Serializable {

    ObservableMap<String, Counter> counterMap;

    public ControllerCounter(ObservableMap<String, Counter> counterMap) {
        this.counterMap= counterMap;
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
            if (counterMap != null) {
                counterMap.put(count.getName(),count);
                try {
                    saveCounterMap();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public void delete(ObservableMap<String, Counter> selectedItems){
        counterMap.remove(selectedItems);
        try {
            saveCounterMap();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Funzione per salvare la lista di contatori su un file binario
    public void saveCounterMap() throws IOException {
        try (ObjectOutputStream binaryFileOut = new ObjectOutputStream(new FileOutputStream("COUNTERS.dat"))) {
            // Itera sulla ObservableMap e scrivi gli oggetti Counter nel file binario
            for (ObservableMap.Entry<String, Counter> entry : counterMap.entrySet()) {
                Counter counter = entry.getValue();
                binaryFileOut.writeObject(counter);
            }
            System.out.println("Scrittura nel file binario completata con successo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Funzione per caricare la lista di contatori da un file binario
    public void loadCounterMap() throws IOException {
        File file = new File("COUNTERS.dat");

        if (file.exists()) {
            ObjectInputStream binaryFileIn = new ObjectInputStream(new FileInputStream("COUNTERS.dat"));
            while (true) {
                try {
                    Counter counter = (Counter) binaryFileIn.readObject();
                    counterMap.put(counter.getName(), counter);
                } catch (IOException | ClassNotFoundException e) {
                    // Fine del file
                    break;
                }
            }
        }
    }

    public void update(Counter counterToModify) {
        // Creazione della finestra di dialogo
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Modifica del Contatore");
        dialog.setHeaderText("Inserisci il nuovo valore per il contatore:");

        // Mostra la finestra di dialogo e attendi la risposta dell'utente
        Optional<String> result = dialog.showAndWait();

        // Processa la risposta dell'utente
        result.ifPresent(newValue -> {
            try {
                // Converte la stringa contenente il nuovo valore del contatore
                int newCounterValue = Integer.parseInt(newValue);

                // Aggiorna il valore del contatore
                counterToModify.setValue(newCounterValue);

                // Aggiorna la visualizzazione o esegui altre operazioni necessarie
                // Esempio: Aggiorna l'interfaccia utente se necessario

                try {
                    saveCounterMap(); // Salva i dati aggiornati
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } catch (NumberFormatException e) {
                // Gestisci il caso in cui l'utente inserisce un valore non valido
                showAlert("Errore", "Inserisci un valore valido per il contatore.");
            }
        });
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
