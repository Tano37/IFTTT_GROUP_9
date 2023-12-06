package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.Action.Action;
import it.unisa.ifttt_group_9.Action.ActionDecorator;
import it.unisa.ifttt_group_9.Action.ActionText;
import it.unisa.ifttt_group_9.Controller.PrincipalStageViewController;
import it.unisa.ifttt_group_9.Rule.Rule;
import it.unisa.ifttt_group_9.Trigger.Trigger;
import it.unisa.ifttt_group_9.Trigger.TriggerTimestamp;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SleepTest {

    @Test
    void testSleepRuleLogic() {
        // Simula la logica della regola di sonno
        PrincipalStageViewController controller = new PrincipalStageViewController();

        // Crea un trigger alle 11:05
        LocalDateTime futureDate = LocalDateTime.now().plusSeconds(3);
        Integer hour = futureDate.getHour();
        Integer minute = futureDate.getMinute();
        Trigger trigger = new TriggerTimestamp(hour, minute);

        // Crea un'azione di test
        Action action = new ActionText("ProvaAction",null);

        // Crea una regola con il trigger e l'azione
        Rule testRule = new Rule("TestRule", trigger, action, false);
        testRule.setDateUntilSleep(futureDate);
        testRule.setStatus(false);

        // Chiamata al metodo di logica della regola di sonno simulato
        sleepRuleLogicSimulation(controller, testRule);

        // Verifica che la regola sia stata attivata correttamente
        assertTrue(testRule.getStatus());
        assertTrue(testRule.getDateUntilSleep() == null);
        // Altri controlli specifici per isLaunched, se necessario
        assertTrue(isLaunchedSimulation(testRule));
    }

    private void sleepRuleLogicSimulation(PrincipalStageViewController controller, Rule rule) {
        LocalDateTime truncatedNow = LocalDateTime.now();
        LocalDateTime truncatedOtherDateTime = rule.getDateUntilSleep();
        //System.out.println(truncatedNow + "/" + truncatedOtherDateTime);

        while (truncatedNow.isBefore(truncatedOtherDateTime)) {
            if (rule.getDateUntilSleep() != null) {
                truncatedNow = LocalDateTime.now();
                truncatedOtherDateTime = rule.getDateUntilSleep();

                int comparisonResult = truncatedNow.compareTo(truncatedOtherDateTime);

                if (!rule.getStatus() && comparisonResult >= 0) {
                    System.out.println("ciaooo2");
                    rule.setStatus(true);
                    rule.setDateUntilSleep(null);
                    rule.setLaunched(false);
                }

            }
            try {
                Thread.sleep(100); // Ritardo di 100 millisecondi
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private boolean isLaunchedSimulation(Rule rule) {
        // Simula la logica di isLaunched
        return rule.getRuleTrigger().evaluate() && rule.getStatus();
    }
}
