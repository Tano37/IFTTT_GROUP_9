package it.unisa.ifttt_group_9.Action;

import java.io.Serializable;

// This interface represents an action that can be executed.
// It extends Serializable to indicate that implementing classes can be serialized.
public interface Action extends Serializable {

    // Method to execute the action. Implementing classes should provide the specific logic for the action.
    void executeAction();
}

