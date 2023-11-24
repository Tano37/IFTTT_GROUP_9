package it.unisa.ifttt_group_9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuleTest {

    @Test
    public void testRule(){
        String expectedRuleName = "TestRule";
        Trigger trigger=null;
        Action action=null;

        Rule rule = new Rule(expectedRuleName, trigger, action);

        assertNotNull(rule, "Rule instance should be correctly created");
    }

}