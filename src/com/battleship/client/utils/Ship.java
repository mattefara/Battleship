package com.battleship.client.utils;

import com.battleship.utils.Position;

public class Ship {

    //Enum for Orientation
    public enum Orientation{
        HORIZONTAL, VERTICAL;
    };

    //Enum for the status of the ship
    public enum Status {
        NORMAL, HIT, SINK;
    }

    public final int SIZE;
    private Orientation orientation;
    private Position shipPosition;
    private int hitCount = 0;

    public Ship(Position position, int length, Orientation orientation){
        this.shipPosition = position;
        this.orientation = orientation;
        this.SIZE = length;
    }

    public Ship(Position position, int size){
        this(position, size, Orientation.HORIZONTAL);
    }

    public Ship(){
        this.orientation = Orientation.HORIZONTAL;
        this.SIZE = 1;
        this.hitCount = 0;

    }

    public void hit(){
        if (this.hitCount < this.SIZE){
            this.hitCount ++;
        }
    }

    public boolean isHit(Position position){
        Position endPosition;
        if (this.orientation == Orientation.HORIZONTAL){
            int newX = this.shipPosition.getX() - position.getX();
            if (newX < 0 ) return false;
            endPosition = this.shipPosition.addToX(newX);
        } else {
            int newY = this.shipPosition.getY() - position.getY();
            if (newY < 0 ) return false;
            endPosition = this.shipPosition.addToX(newY);
        }
        return position.between(this.shipPosition, endPosition);
    }

    public Status getStatus(){
        if (this.hitCount == 0) {
            return Status.NORMAL;
        } else if ( this.hitCount > 0 && this.hitCount < this.SIZE){
            return Status.HIT;
        } else {
            return Status.SINK;
        }
    }

    public Position getShipPosition() {
        return shipPosition;
    }

    public void setShipPosition(Position position) {
        this.shipPosition = position;
    }

    public Position[] getShipPositions(){
        if (this.getOrientation() == Orientation.HORIZONTAL){
            Position[] positions = new Position[this.SIZE];
            Position[][] matrixPositions = Position.betweenPositions(this.shipPosition, this.shipPosition.addToX(this.SIZE));
            for (int i = 0; i < this.SIZE; i++){
                positions[i] = matrixPositions[i][0];
            }
            return positions;
        } else {
            return Position.betweenPositions(this.shipPosition, this.shipPosition.addToY(this.SIZE - 1))[0];
        }
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void changeOrientation(){
        switch (orientation){
            case HORIZONTAL: {
                setOrientation(Orientation.VERTICAL);
                break;
            }
            case VERTICAL: {
                setOrientation(Orientation.HORIZONTAL);
                break;
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Ship && this.SIZE == ((Ship) obj).SIZE;
    }
}
