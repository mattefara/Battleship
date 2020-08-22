package com.battleship.client;

import com.battleship.client.window.BattleShipGame;
import com.battleship.utils.Player;
import com.battleship.utils.Position;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();

        Player player = new Player(random.nextInt(50000), null);

        BattleShipGame game = new BattleShipGame(player, args.length > 0 && Boolean.getBoolean(args[0]));
//        System.out.println(Arrays.deepToString(Position.betweenPositions(new Position(0, 1), new Position(7, 0))));
    }
}
