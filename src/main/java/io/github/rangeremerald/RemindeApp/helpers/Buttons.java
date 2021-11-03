package io.github.rangeremerald.RemindeApp.helpers;

import java.awt.*;

public abstract class Buttons {

    public Font textFont = new Font("san-serif", Font.BOLD, 20);
    public String text;
    public Rectangle button;

    public void drawButton(Graphics g) {
        // Draws visible button hitbox
        g.setColor(Color.gray);

        g.fillRoundRect(button.x, button.y, button.width, button.height, 20, 20);

        // Draws button content
        g.setColor(Color.white);
        g.setFont(textFont);

        g.drawString(text, button.x - g.getFontMetrics(textFont).stringWidth(text) / 2 + button.width / 2, button.y + textFont.getSize() / 2 + button.height / 2); // Centers button content to button
    }

    abstract public void buttonAction();
}
