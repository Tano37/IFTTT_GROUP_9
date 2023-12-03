package it.unisa.ifttt_group_9.Controller;

import javax.swing.*;

public class PanelPopUPManager {

    private String title;
    private String message;

    public PanelPopUPManager(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public int showMessage(){
        JFrame frame = new JFrame("Panel");
        frame.setAlwaysOnTop(true);
        JDialog dialog = new JDialog(frame, "Panel", true);
        return JOptionPane.showConfirmDialog(dialog, message, title, JOptionPane.DEFAULT_OPTION);
    }


}
