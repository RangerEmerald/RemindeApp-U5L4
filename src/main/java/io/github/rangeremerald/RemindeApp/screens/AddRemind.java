package io.github.rangeremerald.RemindeApp.screens;

import io.github.rangeremerald.RemindeApp.screenHelpers.SaveRemindButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Class that creates the window that allows you to add a reminder
public class AddRemind extends JFrame {

    public void addRemind() {
        add(new RemindPanel());

        setSize(500, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static class RemindPanel extends JPanel implements MouseListener {
        public JTextField addReminderText;
        public JLabel addReminderLabel;
        public SaveRemindButton saveRemindButton;

        public RemindPanel() {
            addReminderText = new JTextField("", 30);
            addReminderLabel = new JLabel("Reminder Message: ");

            saveRemindButton = new SaveRemindButton();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            try {
                Rectangle mouse = new Rectangle(e.getX(), e.getY(), 1, 1);

                if (mouse.intersects(saveRemindButton.button)) saveRemindButton.buttonAction();
            } catch (IllegalComponentStateException exception) {
                System.out.println(exception);
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
