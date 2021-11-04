package io.github.rangeremerald.RemindeApp.remindHelpers;

import io.github.rangeremerald.RemindeApp.helpers.Buttons;
import io.github.rangeremerald.RemindeApp.helpers.Reminds;
import io.github.rangeremerald.RemindeApp.screens.Interface;

import java.awt.*;

public class DeleteRemind extends Buttons {

    private final Reminds reminds;

    public DeleteRemind(Rectangle popup, Reminds reminds) {
        int deleteRemindWidth = 50;

        this.text = "Delete";
        this.button = new Rectangle(popup.x + popup.width / 2 + deleteRemindWidth / 2, popup.y + 50, deleteRemindWidth, 50);
        this.reminds = reminds;
    }

    public void buttonAction(Runnable repaint) {
        Interface.mouseOverRemind = null;
        Interface.reminders.remove(reminds);

        for (int i = 0; i < Interface.reminders.size(); ++i) Interface.reminders.get(i).reposition(i);

        repaint.run();
    }

    // Unused function
    @Override
    public void buttonAction() {

    }
}
