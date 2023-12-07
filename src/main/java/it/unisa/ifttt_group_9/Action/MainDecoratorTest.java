package it.unisa.ifttt_group_9.Action;


import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MainDecoratorTest{

    private static JPanel createPopupPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton closeButton = new JButton("Chiudi");
        panel.add(new JButton("Questo è un popup!"));
        panel.add(closeButton);

        return panel;
    }
    public static void main(String[] args){
        /*Action selectedAction = new ActionText("3", null);
        selectedAction = new ActionText("2", selectedAction);
        selectedAction = new ActionText("1", selectedAction);
        selectedAction = new ActionAudio("C:\\Users\\alfon\\OneDrive\\Desktop\\Università Magistrale\\Software Engineering\\IFTTT_GROUP_9\\src\\main\\resources\\it\\unisa\\ifttt_group_9\\testAudio.wav", selectedAction);
        selectedAction = new ActionFileLaunch("C:\\Users\\alfon\\OneDrive\\Desktop\\Università Magistrale\\Software Engineering\\IFTTT_GROUP_9\\src\\main\\resources\\it\\unisa\\ifttt_group_9\\draw.exe", "", selectedAction);

        selectedAction.executeAction();
        */

        JFrame tempFrame = new JFrame();
        tempFrame.setUndecorated(true);
        tempFrame.setSize(1, 1); // Imposta la dimensione a 1x1 per renderlo invisibile
        tempFrame.setLocationRelativeTo(null); // Centra il frame temporaneo
        tempFrame.setVisible(true);


        PopupFactory factory = PopupFactory.getSharedInstance();
        JPanel popupPanel = createPopupPanel();
        Popup popup = factory.getPopup(tempFrame, popupPanel, tempFrame.getLocationOnScreen().x + 50, tempFrame.getLocationOnScreen().y + 50);

        JButton closeButton = (JButton) popupPanel.getComponent(1);
        closeButton.addActionListener(e -> popup.hide());

        popup.show();
    }




}
