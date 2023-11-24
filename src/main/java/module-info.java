module it.unisa.ifttt_group_9 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens it.unisa.ifttt_group_9 to javafx.fxml;
    exports it.unisa.ifttt_group_9;
}