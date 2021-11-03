package io.github.rangeremerald.RemindeApp.screens;

import io.github.rangeremerald.RemindeApp.RemindeApp;
import io.github.rangeremerald.RemindeApp.helpers.Buttons;
import io.github.rangeremerald.RemindeApp.helpers.Reminds;
import io.github.rangeremerald.RemindeApp.screenHelpers.AddRemindButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Interface extends JPanel implements ActionListener, MouseListener {

    public final static ArrayList<Reminds> reminders = new ArrayList<>(); // Stores all the reminders
    public final static HashMap<String, Buttons> buttons = new HashMap<>(); // Stores all the buttons on the main interface

    private Reminds mouseOverRemind = null; // Stores which reminder the mouse is over

    // Stores the reminders background so they can be accessed elsewhere for ease of use
    public final static int remindersWidth = 300;
    public final static int remindersX = RemindeApp.remindWidth / 2 - remindersWidth / 2;
    public final static int remindersY = 80;

    private final Timer timer;

    public Interface() {
        timer = new Timer(5, this);
        timer.start();

        initButtons();
        reminders.add(new Reminds("Do lunch Do lunch Do lunch Do lunch Do lunch Do lunch Do lunch Do lunch Do lunch Do lunch Do lunch ", 1000, reminders.size()));
    }

    // Initializes all the buttons the main interface will be using
    private void initButtons() {
        buttons.put("addRemind", new AddRemindButton());
    }

    @Override
    public void paintComponent(Graphics g) {
        // Makes the graphics sharper and cleaner
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        // Sets the background of the app to light grey
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, RemindeApp.remindWidth, RemindeApp.remindHeight);

        // Sets the background of the scrollable reminders to white centered horizontally to the app
        g.setColor(Color.white);
        g.fillRect(remindersX, remindersY, remindersWidth, 300);

        // Drawing the title of the app centered horizontally to the app
        Font titleFont = new Font("san-serif", Font.PLAIN, 40);
        String title = "Reminders";

        g.setFont(titleFont);

        g.drawString(title, RemindeApp.remindWidth / 2 - g.getFontMetrics(titleFont).stringWidth(title) / 2, 40);

        // Draws all the reminders
        for (Reminds reminder : reminders) reminder.drawRemind(g);

        // Draws all the buttons
        for (Buttons button : buttons.values()) button.drawButton(g);

        // Draws the reminder popup if a reminder is hovered over
        if (mouseOverRemind != null) mouseOverRemind.popupRemind(g);

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            boolean isMouseHovered = false; // Stores whether a button or reminder has been hovered over
            boolean overRemind = false; // Stores whether a reminder has been hovered over

            timer.restart();
            Rectangle mouse = new Rectangle(MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x, MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y, 1, 1); // Makes a custom mouse hitbox so the program can detect if the mouse is over a button or reminder

            for (Buttons button : buttons.values()) if (mouse.intersects(button.button)) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                isMouseHovered = true;
            } // Checks if the cursor is over a button

            for (Reminds remind : reminders) if (mouse.intersects(remind.remindRect)) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                isMouseHovered = true;
                overRemind = true;

                mouseOverRemind = remind;
            } // Checks if the cursor is over a reminder

            if (!overRemind && (mouseOverRemind != null && !mouseOverRemind.intersectRect.intersects(mouse))) mouseOverRemind = null; // Resets if no reminder is hovered over (or if the reminder popup is not hovered over)

            if (!isMouseHovered) setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Sets cursor to default cursor if the cursor is not over an object

            repaint();
        } catch (IllegalComponentStateException exception) { // Catches and prints exception so the app does not crash
            System.out.println(exception);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        try {
            Rectangle mouse = new Rectangle(e.getX(), e.getY(), 1, 1); // Makes a custom mouse hitbox so the program can detect if the mouse is over a button or reminder

            for (Buttons button : buttons.values()) if (mouse.intersects(button.button)) button.buttonAction(); // Does the custom action when button is pressed

            for (Reminds reminds : reminders) if (mouse.intersects(reminds.remindRect)) reminds.remindAction();
        } catch (IllegalComponentStateException exception) { // Catches and prints exception so the app does not crash
            System.out.println(exception);
        }
    }

    // Unused necessary functions
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
