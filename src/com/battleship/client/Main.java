package com.battleship.client;

import com.battleship.client.window.BattleShipGame;
import com.battleship.utils.Player;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();

        Player player = new Player(random.nextInt(50000), null);

        BattleShipGame game = new BattleShipGame(player, args.length > 0 && Boolean.getBoolean(args[0]));
    }
}
