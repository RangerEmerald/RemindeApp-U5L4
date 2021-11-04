package io.github.rangeremerald.RemindeApp.screenHelpers;

import io.github.rangeremerald.RemindeApp.RemindeApp;
import io.github.rangeremerald.RemindeApp.helpers.Buttons;
import io.github.rangeremerald.RemindeApp.screens.AddRemind;

import java.awt.*;

public class AddRemindButton extends Buttons {

    {
        int addRemindWidth = 200;
        text = "Add New Remind";
        button = new Rectangle(RemindeApp.remindWidth / 2 - addRemindWidth / 2, 450, addRemindWidth, 40);
    }

    @Override
    public void buttonAction() {
        if (RemindeApp.addRemind == null || !RemindeApp.addRemind.isVisible()) {
            RemindeApp.addRemind = new AddRemind();
            RemindeApp.addRemind.addRemind();
        }
        RemindeApp.addRemind.toFront();
    }
}
