package it.unisa.ifttt_group_9.RuleTest;

import it.unisa.ifttt_group_9.Action.Action;
import it.unisa.ifttt_group_9.Rule.Rule;
import it.unisa.ifttt_group_9.Rule.RuleManager;
import it.unisa.ifttt_group_9.Trigger.Trigger;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RuleManagerTest {

    @Test
    void testGetInstance() {
        RuleManager instance1 = RuleManager.getInstance();
        RuleManager instance2 = RuleManager.getInstance();

        assertSame(instance1, instance2, "RuleManager objects should consist in the same instance");
    }

    @Test
    void addRule() {
        RuleManager ruleManager = RuleManager.getInstance();
        Trigger trigger=null;
        Action action=null;
        Rule rule = new Rule("TestRule", trigger, action, false);

        ruleManager.addRule(rule);

        assertTrue(ruleManager.getRuleList().contains(rule), "The rule should be present in the list");
    }

    @Test
    void getRuleList() {
        RuleManager ruleManager = RuleManager.getInstance();
        Trigger trigger=null;
        Action action=null;
        Boolean fireOnce=false;
        Rule rule1 = new Rule("TestRule1", trigger, action, fireOnce);
        Rule rule2 = new Rule("TestRule2", trigger, action, fireOnce);

        ruleManager.addRule(rule1);
        ruleManager.addRule(rule2);

        List<Rule> ruleList = ruleManager.getRuleList();

        assertTrue(ruleList.contains(rule1), "Rule 1 should be present on the list");
        assertTrue(ruleList.contains(rule2), "Rule 2 should be present on the list");
    }
}