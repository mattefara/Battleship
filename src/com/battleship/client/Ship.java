package com.battleship.client;

import com.battleship.utils.Position;

public class Ship {

    public enum Orientation{
        HORIZONTAL, VERTICAL
    };

    public enum Status {
        NORMAL, HIT, SINK
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
        this.hitCount = -1;
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

    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Ship && this.SIZE == ((Ship) obj).SIZE;
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
}
