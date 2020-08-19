package com.battleship.client.field;

import com.battleship.utils.Position;

import java.awt.*;

public class BattleField implements Grid<BattleField.BattleCell>{

    private final BattleCell[][] cells;

    public BattleField(int cols, int rows){
        this.cells = new BattleCell[cols][rows];
        for (int i = 0; i < this.getCols(); i++) {
            for (int j = 0; j < this.getRows(); j++) {
                cells[i][j] = new BattleCell();
            }
        }
    }

    @Override
    public int getCols() {
        return this.cells.length;
    }

    @Override
    public int getRows() {
        return this.cells[0].length;
    }

    @Override
    public boolean existCell(Position position) {
        return ((position.getX() >= 0 && position.getY() >= 0) && (position.getX() < getCols() && position.getY() < getRows()));
    }

    public BattleCell.Status getCellStatus(Position position){
        return getCell(position).status;
    }

    public void setCellStatus(Position position, BattleCell.Status status){
        this.getCell(position).setStatus(status);
    }

    @Override
    public BattleCell getCell(Position position) {
        if (this.existCell(position))
            return this.cells[position.getX()][position.getY()];
        return null;
    }

    public Color toColor(BattleCell.Status status){
        switch (status){
            default:
            case BLANK:
                return Color.WHITE;
            case BLUEPRINT:
                return Color.BLUE;
            case HIT:
                return Color.RED;
            case MISS:
                return Color.LIGHT_GRAY;
            case FOCUS:
                return Color.GRAY;
        }
    }

    public static class BattleCell implements Grid.Cell{

        public enum Status {
            BLANK, BLUEPRINT, HIT, MISS, FOCUS,
        }

        public Status status;

        public BattleCell(){
            status = Status.BLANK;
        }

        @Override
        public boolean isFree() {
            return this.status == Status.BLANK || this.status == Status.BLUEPRINT;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }
    }
}
