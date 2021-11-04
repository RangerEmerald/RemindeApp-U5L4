package io.github.rangeremerald.RemindeApp.remindHelpers;

import io.github.rangeremerald.RemindeApp.RemindeApp;
import io.github.rangeremerald.RemindeApp.helpers.Buttons;
import io.github.rangeremerald.RemindeApp.helpers.Reminds;
import io.github.rangeremerald.RemindeApp.screens.DeleteRemindConfirm;

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
        RemindeApp.deleteRemindConfirm = new DeleteRemindConfirm();
        RemindeApp.deleteRemindConfirm.deleteRemindConfirm(reminds, repaint);
    }

    // Unused function
    @Override
    public void buttonAction() {

    }
}
