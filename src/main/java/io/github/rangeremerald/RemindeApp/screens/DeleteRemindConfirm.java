package io.github.rangeremerald.RemindeApp.screens;

import io.github.rangeremerald.RemindeApp.RemindeApp;
import io.github.rangeremerald.RemindeApp.helpers.Reminds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteRemindConfirm extends JFrame {

    public void deleteRemindConfirm(Reminds reminds, Runnable repaint) {
        add(new ConfirmationPanel(reminds, repaint));

        setSize(350, 100);
        setTitle("Delete Remind Confirmation");
        setLocationRelativeTo(RemindeApp.remindeApp);
        setVisible(true);
        setResizable(false);

        RemindeApp.remindeApp.setEnabled(false);
    }

    public static class ConfirmationPanel extends JPanel implements ActionListener {

        private JTextArea confirmationText;
        private JButton cancelButton, confirmButton;

        private final Reminds reminds;
        private final Runnable repaint;

        public ConfirmationPanel(Reminds reminds, Runnable repaint) {
            this.reminds = reminds;
            this.repaint = repaint;

            initVariables();

            add(confirmationText);
            add(cancelButton);
            add(confirmButton);

            cancelButton.addActionListener(this);
            confirmButton.addActionListener(this);
        }

        private void initVariables() {
            confirmationText = new JTextArea("Are you sure you want to delete the reminder?");
            confirmationText.setEditable(false);
            confirmationText.setBackground(RemindeApp.deleteRemindConfirm.getBackground());

            cancelButton = new JButton("Cancel");
            confirmButton = new JButton("Confirm");
        }

        private void exitAddRemind(Runnable waitCursor, Runnable defaultCursor, boolean confirm) {
            if (confirm) { // Checks to make sure that it was confirmed, instead of cancelled
                Interface.mouseOverRemind = null;
                Interface.reminders.remove(reminds);

                for (int i = 0; i < Interface.reminders.size(); ++i) Interface.reminders.get(i).reposition(i); // Repositions each reminder so there will not be a gap when a reminder is deleted

                repaint.run(); // Repaints window so the action will appear
            }

            RemindeApp.remindeApp.setEnabled(true);

            waitCursor.run();
            RemindeApp.deleteRemindConfirm.dispose();
            RemindeApp.deleteRemindConfirm = null;
            defaultCursor.run();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Checks which button was pressed
            if (e.getSource() == cancelButton) {
                exitAddRemind(() -> setCursor(new Cursor(Cursor.HAND_CURSOR)), () -> setCursor(new Cursor(Cursor.DEFAULT_CURSOR)), false);
            } else if (e.getSource() == confirmButton) {
                exitAddRemind(() -> setCursor(new Cursor(Cursor.HAND_CURSOR)), () -> setCursor(new Cursor(Cursor.DEFAULT_CURSOR)), true);
            }
        }
    }

}
