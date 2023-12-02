module it.unisa.ifttt_group_9 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens it.unisa.ifttt_group_9 to javafx.fxml;
    exports it.unisa.ifttt_group_9;
    exports it.unisa.ifttt_group_9.Action;
    opens it.unisa.ifttt_group_9.Action to javafx.fxml;
    exports it.unisa.ifttt_group_9.Controller;
    opens it.unisa.ifttt_group_9.Controller to javafx.fxml;
    exports it.unisa.ifttt_group_9.Rule;
    opens it.unisa.ifttt_group_9.Rule to javafx.fxml;
    exports it.unisa.ifttt_group_9.Trigger;
    opens it.unisa.ifttt_group_9.Trigger to javafx.fxml;
}