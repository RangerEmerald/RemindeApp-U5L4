package io.github.rangeremerald.RemindeApp;

import io.github.rangeremerald.RemindeApp.screens.AddRemind;
import io.github.rangeremerald.RemindeApp.screens.DeleteRemindConfirm;
import io.github.rangeremerald.RemindeApp.screens.Interface;

import javax.swing.*;
import java.awt.*;

public class RemindeApp extends JFrame {

    // Storing the classes as variables so they can be accessed easier
    public static RemindeApp remindeApp = null;
    public static Interface remindeInterface = new Interface();
    public static AddRemind addRemind = null;
    public static DeleteRemindConfirm deleteRemindConfirm = null;

    // Storing the width and height of the app so they can be easier accessed
    public final static int remindWidth = 500;
    public final static int remindHeight = 600;

    public RemindeApp() {
        add(remindeInterface); // Adds the main interface of the app so it will be visible

        setTitle("Reminde App");
        setSize(remindWidth, remindHeight);
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Makes the app not close when it has finished loading, instead closing when the user directs
        setResizable(false); // Makes the app's size unchangeable
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            remindeApp = new RemindeApp(); // Initializes the main frame of the app
            remindeApp.setVisible(true); // Makes the app visible to the user
        });
    }

}
