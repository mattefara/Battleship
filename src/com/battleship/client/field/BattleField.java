package com.battleship.client.field;

import com.battleship.utils.Position;

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

    public boolean isInsideGrid(Position position){
        return position.between(new Position(1, 1), new Position(this.getCols() - 1, this.getRows() - 1));
    }

    public boolean areInsideGrid(Position[] positions){
        for (Position position: positions){
            if (!this.isInsideGrid(position)){
                return false;
            }
        }
        return true;
    }

    public boolean isFreeCell(Position position){
        return this.existCell(position) && this.getCell(position).isFree();
    }

    public boolean areFreeCells(Position[] positions){
        for (Position position: positions){
            if (!this.isFreeCell(position)){
                return false;
            }
        }
        return true;
    }

    @Override
    public BattleCell getCell(Position position) {
        if (this.existCell(position))
            return this.cells[position.getX()][position.getY()];
        return null;
    }

    public static class BattleCell implements Grid.Cell{

        public enum Status {
            BLANK, BLUEPRINT, HIT, MISS, PLACED,
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
