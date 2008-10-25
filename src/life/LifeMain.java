package life;

import javax.swing.*;
import java.awt.*;

/**
 */
public class LifeMain
{
    public static void main(String[] args) throws InterruptedException
    {
        JFrame life = new JFrame();
        life.setLayout(new FlowLayout());
        life.setSize(Life.mainWindowWidth, Life.mainWindowHeight);
        life.setVisible(true);
        life.setTitle("Game Of Life");

        Life game = new Life();
        game.addComponents(life);
    }
}
