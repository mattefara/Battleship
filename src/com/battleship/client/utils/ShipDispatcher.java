package com.battleship.client.utils;

import com.battleship.utils.Position;

public class ShipDispatcher {

    private class CountedShip extends Ship {
        private final int SHIP_COUNT;
        private int ship_placed = 0;

        public CountedShip(Position position, int length, int shipCount){
            super(position, length);
            this.SHIP_COUNT = shipCount;
        }

        public void addShip(){

            this.ship_placed ++;
        }

        public boolean allPlaced() {
            return this.ship_placed >= this.SHIP_COUNT;
        }
    }

    private final CountedShip[] ships = {
            new CountedShip(new Position(), 4, 1),
            new CountedShip(new Position(), 3, 2),
            new CountedShip(new Position(), 2, 3),
            new CountedShip(new Position(), 1, 4),
    };

    private final int MAX_SHIPS = ships.length;
    private int currentShipIndex = 0;

    public Ship getNextShip(){
        if (!this.hasMoreShips()){
            return null;
        }

        Ship ship = this.ships[currentShipIndex];
        this.ships[currentShipIndex].addShip();
        if (this.ships[currentShipIndex].allPlaced()){
            this.currentShipIndex ++;
        }
        return ship;
    }

    public Ship getShip(){
        return this.ships[currentShipIndex];
    }

    public boolean hasMoreShips(){
        return this.MAX_SHIPS > this.currentShipIndex;
    }

}
