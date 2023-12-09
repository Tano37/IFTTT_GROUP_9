package it.unisa.ifttt_group_9.RuleTest;

import it.unisa.ifttt_group_9.Action.Action;
import it.unisa.ifttt_group_9.Rule.Rule;
import it.unisa.ifttt_group_9.Trigger.Trigger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuleTest {

    @Test
    public void testRule(){
        String expectedRuleName = "TestRule";
        Trigger trigger=null;
        Action action=null;
        Boolean fireOnce=false;

        Rule rule = new Rule(expectedRuleName, trigger, action, fireOnce);

        assertNotNull(rule, "Rule instance should be correctly created");
    }

}