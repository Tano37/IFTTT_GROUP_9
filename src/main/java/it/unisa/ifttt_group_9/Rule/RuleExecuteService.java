package it.unisa.ifttt_group_9.Rule;

import it.unisa.ifttt_group_9.Rule.Rule;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class RuleExecuteService extends Service<Void> {

    private final Rule ruleToExecute;

    public RuleExecuteService(Rule ruleToExecute) {
        this.ruleToExecute = ruleToExecute;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                ruleToExecute.getRuleAction().executeAction();
                return null;
            }
        };
    }
}

