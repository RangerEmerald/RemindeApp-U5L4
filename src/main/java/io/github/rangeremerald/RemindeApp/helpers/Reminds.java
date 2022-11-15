package io.github.rangeremerald.RemindeApp.helpers;

import io.github.rangeremerald.RemindeApp.remindHelpers.DeleteRemind;
import io.github.rangeremerald.RemindeApp.screens.Interface;

import java.awt.*;

public class Reminds {

    public static Font remindFont = new Font("san-serif", Font.PLAIN, 10);
    public static Font popupHeaderFont = new Font("san-serif", Font.PLAIN, 20);
    public static Font popupTextFont = new Font("san-serif", Font.PLAIN, 15);
    public String remindText; // What the user wants to be reminded of
    public long remindTime; // When the user wants the reminder to go
    public Rectangle remindRect, popupRect, intersectRect; // Gives the bounding box for the three rectangles
    public boolean isVisible; // Tells the program whether to draw the reminder or popup
    public DeleteRemind deleteRemind;

    public Reminds(String remindText, long remindTime) {
        int remindRectHeight = 20;

        int remindRectY = Interface.reminderBackground.y;
        if (Interface.reminders.size() > 0) remindRectY = Interface.reminders.get(Interface.reminders.size() - 1).remindRect.y + remindRectHeight;

        this.remindText = remindText;
        this.remindTime = remindTime;

        this.remindRect = new Rectangle(Interface.reminderBackground.x, remindRectY, Interface.reminderBackground.width, remindRectHeight);
        this.popupRect = new Rectangle(remindRect.x + 5, remindRect.y + remindRect.height + 5, remindRect.width - 10, 200);
        this.intersectRect = new Rectangle(this.popupRect.x, this.remindRect.y + this.remindRect.height, this.popupRect.width, (this.popupRect.y + this.popupRect.height) - (this.remindRect.y + this.remindRect.height));

        // Checks if the reminder is within the overflow
        this.isVisible = this.remindRect.intersects(Interface.reminderBackground);

        // Adds the button that allows you to delete the remind
        this.deleteRemind = new DeleteRemind(this.popupRect, this);
    }

    public void drawRemind(Graphics g) {
        // Draws the rect that depicts the reminder
        g.setColor(Color.gray);
        g.fillRoundRect(remindRect.x, remindRect.y, remindRect.width, remindRect.height, 20, 20);

        // Draws what the reminder will remind you of
        int stringWidth = g.getFontMetrics(remindFont).stringWidth(remindText);
        String drawString = remindText;

        if (stringWidth > remindRect.width - 50) drawString = remindText.substring(0, 40) + "..."; // Checks if the string is too long; if it is, then make it shorter

        g.setColor(Color.white);
        g.setFont(remindFont);
        g.drawString(drawString, remindRect.x + 10, remindRect.y + remindFont.getSize() / 2 + remindRect.height / 2);
    }

    public void popupRemind(Graphics g) {
        int endY = WordWrap.drawWordWrap(g, remindText, popupRect.x + 5, popupRect.y + popupHeaderFont.getSize() + popupTextFont.getSize(), popupRect.width); // Gets the last line of the wrapped text so the popup can be constrained to the length of the text
        popupRect.height = endY - popupRect.y;

        // Draw the popup window over the other reminds, showing all the information
        g.setColor(Color.gray);
        g.fillRoundRect(popupRect.x, popupRect.y, popupRect.width, popupRect.height, 20, 20);

        g.setColor(Color.white);
        g.setFont(popupHeaderFont);
        g.drawString("Remind Message: ", popupRect.x + 5, popupRect.y + popupHeaderFont.getSize());

        g.setFont(popupTextFont);
        WordWrap.drawWordWrap(g, remindText, popupRect.x + 5, popupRect.y + popupHeaderFont.getSize() + popupTextFont.getSize(), popupRect.width); // Draws the reminder message, but soft-wraps it so it does not go off the popup

        // Draws the delete remind button
        deleteRemind.drawButton(g);
    }

    public void moveRemind(int amount) {
        intersectRect.y += amount;
        remindRect.y += amount;
        popupRect.y += amount;
        deleteRemind.button.y += amount;

        isVisible = remindRect.intersects(Interface.reminderBackground);
    }

    public void reposition(int remindPos) {
        int remindRectHeight = 20;
        this.remindRect = new Rectangle(Interface.reminderBackground.x, Interface.reminderBackground.y + remindPos * remindRectHeight, Interface.reminderBackground.width, remindRectHeight);
        this.popupRect = new Rectangle(remindRect.x + 5, remindRect.y + remindRect.height + 5, remindRect.width - 10, 200);
        this.intersectRect = new Rectangle(0, 0, 0, 0);


        // Creates the rectangle that allows the popup to remain while the mouse hovors over it
        this.intersectRect.x = this.popupRect.x;
        this.intersectRect.y = this.remindRect.y + this.remindRect.height;
        this.intersectRect.width = this.popupRect.width;
        this.intersectRect.height = (this.popupRect.y + this.popupRect.height) - this.intersectRect.y;

        // Checks if the reminder is within the overflow
        this.isVisible = this.remindRect.intersects(Interface.reminderBackground);

        // Adds the button that allows you to delete the remind
        this.deleteRemind = new DeleteRemind(this.popupRect, this);
    }

}
