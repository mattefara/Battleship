package com.battleship.client.window;

import com.battleship.utils.Player;

import javax.swing.*;
import java.awt.*;

public class BattleShipGame extends JFrame {

    private Player player;

    private GameBattleField userBattleField;

    public BattleShipGame(Player player, boolean test){
        super();
        this.player = player;

        if (!test){
            // Add stuff...
        }

        userBattleField = new GameBattleField(new Dimension(501,  501));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(userBattleField,BorderLayout.CENTER);

        add(panel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }
}
