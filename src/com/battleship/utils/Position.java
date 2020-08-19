package com.battleship.utils;

import java.util.Objects;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Position(){
        this(0, 0);
    }

    public boolean between(Position p1, Position p2){
        return (this.x >= p1.x && this.x <= p2.x) && (this.y >= p1.y && this.y <= p2.y);
    }

    public static boolean between(Position p, Position p1, Position p2){
        return p.between(p1, p2);
    }

    public Position addToX(int value){
        return new Position(this.getX() + value, this.getY());
    }
    public Position addToY(int value){
        return new Position(this.getX(), this.getY() + value);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
