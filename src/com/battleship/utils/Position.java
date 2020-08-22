package com.battleship.utils;

import java.util.Objects;

public class Position implements Comparable<Position> {

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

    public Position[][] betweenPositions(Position p1){
        int xLength = Math.max(this.x, p1.x) - Math.min(this.x, p1.x) + 1;
        int yLength = Math.max(this.y, p1.y) - Math.min(this.y, p1.y) + 1;
        Position[][] positions = new Position[xLength][yLength];
        for (int i = 0; i < xLength; i++){
            for (int j = 0; j < yLength; j++){
                positions[i][j] = new Position( Math.min(this.x, p1.x) + i, Math.min(this.y, p1.y) + j);
            }
        }
        return positions;
    }

    public static Position[][] betweenPositions(Position p1, Position p2){
        return p1.betweenPositions(p2);
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
    public int compareTo(Position position) {
        return this.x < position.x && this.y < position.y ? -1 : 1;
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
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
