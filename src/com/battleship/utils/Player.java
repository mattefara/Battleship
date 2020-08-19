package com.battleship.utils;

public class Player {

    private final int ID;
    private final String NAME;

    /*
     TODO:
        - save position of ships the player has placed
     */

    public Player(int id, String name){
        this.ID = id;
        this.NAME = (name == null || name.isEmpty()) ? "User" : name ;
    }

}
