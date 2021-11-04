package io.github.rangeremerald.RemindeApp.screens;

import io.github.rangeremerald.RemindeApp.RemindeApp;
import io.github.rangeremerald.RemindeApp.helpers.Reminds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Class that creates the window that allows you to add a reminder
public class AddRemind extends JFrame {

    public static int addWidth = 500;
    public static int addHeight = 150;

    public void addRemind() {
        add(new RemindPanel()); // Adds the panels to the main frame

        setBackground(Color.lightGray);
        setSize(addWidth, addHeight);
        setLocationRelativeTo(RemindeApp.remindeApp);
        setResizable(false);
        setVisible(true);
    }

    public static class RemindPanel extends JPanel implements ActionListener, KeyListener {
        public JTextField addReminderText;
        public JTextArea noText;
        public JLabel addReminderLabel;
        public JButton saveReminderButton;

        public RemindPanel() {
            initVariables();

            add(addReminderLabel);
            add(addReminderText);
            add(saveReminderButton);
            add(noText);

            addReminderText.addKeyListener(this); // Makes the text field able to listen to key strokes
            saveReminderButton.addActionListener(this); // Makes the button able to be pressed
        }

        private void initVariables() {
            addReminderText = new JTextField("", 30);
            addReminderLabel = new JLabel("Reminder Message: ");
            saveReminderButton = new JButton("Save Reminder");

            // Initializes the text that tells users that they don't have anything in the text field
            noText = new JTextArea("No text in reminder");
            noText.setForeground(Color.red);
            noText.setBackground(new Color(238, 238, 238)); // Sets the background of the text area to be the same as the panel so it looks seemless
            noText.setVisible(false);
            noText.setEditable(false);
        }

        public void saveReminder() {
            if (addReminderText.getText().length() == 0) {
                noText.setVisible(true);
            } else {
                Interface.reminders.add(new Reminds(addReminderText.getText(), 100)); // Adds the new reminder to the list of reminders on the homepage
                exitAddRemind(() -> setCursor(new Cursor(Cursor.WAIT_CURSOR)), () -> setCursor(new Cursor(Cursor.DEFAULT_CURSOR)));
            }
        }

        // Closes and disposes of the addRemind frame
        public static void exitAddRemind(Runnable waitCursor, Runnable defaultCursor) {
            waitCursor.run();
            RemindeApp.addRemind.dispose();
            RemindeApp.addRemind = null;
            defaultCursor.run();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            saveReminder(); // If the button has been pressed, then the reminder will save
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) saveReminder(); // If the enter key is pressed, then the reminder will save
        }

        // Unused functions
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }
    }

}
