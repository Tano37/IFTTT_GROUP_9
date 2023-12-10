package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.Rule.Rule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Active_DeactiveTest {

   // private YourController controller;
    private ObservableList<Rule> rulesList;

    @BeforeEach
    public void setUp() {
        // Inizializza il controller e la lista di regole per ogni test
        //controller = new YourController();  // Sostituisci con il nome effettivo del tuo controller
        rulesList = FXCollections.observableArrayList();
        rulesList.add((new Rule("ciao",null,null,false)));

    }

    @Test
    public void testDeactiveRule(){

        //Set the element to deactive becouse for the logic Status=true a rule is deactive
        rulesList.get(0).setStatus(true);

        assertTrue(rulesList.get(0).getStatus());

    }
    @Test
    public void testActiveeRule(){

        //Set the element to active becouse for the logic Status=false a rule is active
        rulesList.get(0).setStatus(false);

        assertFalse(rulesList.get(0).getStatus());

    }


}
