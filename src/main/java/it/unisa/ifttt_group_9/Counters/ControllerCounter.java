package it.unisa.ifttt_group_9.Counters;


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


        HBox nameBox = new HBox(nameLabel, name);
        HBox valueBox = new HBox(valueLabel, value);


        VBox container = new VBox(nameBox, valueBox);


        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create a counter");
        dialog.setHeaderText("Insert name and value:");


        dialog.getDialogPane().setContent(container);


        Button okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setDisable(true); // Disabilita il pulsante OK all'inizio

        value.textProperty().addListener((observable, oldValue, newValue) -> {
            try {

                Integer.parseInt(newValue);
                okButton.setDisable(false);
            } catch (NumberFormatException e) {

                okButton.setDisable(true);
            }
        });


        dialog.showAndWait().ifPresent(response -> {

            String enteredName = name.getText();
            int enteredValue = Integer.parseInt(value.getText());


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

    //Function that saves all the counters on a binary file
    public void saveCounterList() throws IOException {
        ObjectOutputStream binaryFileOut = new ObjectOutputStream(new FileOutputStream("COUNTERS.dat"));
        for (Counter counter : counterList) {
            binaryFileOut.writeObject(counter);
        }
        binaryFileOut.close();
    }

    //Function that loads all the counters from a binary file
    public void loadCounterList() throws IOException {
        File file = new File("COUNTERS.dat");

        if (file.exists()) {
            ObjectInputStream binaryFileIn = new ObjectInputStream(new FileInputStream("COUNTERS.dat"));
            while (true) {
                try {
                    Counter counter = (Counter) binaryFileIn.readObject();
                    counterList.add(counter);
                } catch (IOException | ClassNotFoundException e) {
                    //end of the file
                    break;
                }
            }
        }
    }


}
