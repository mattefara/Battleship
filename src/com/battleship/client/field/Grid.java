package com.battleship.client.field;

import com.battleship.utils.Position;

public interface Grid<T extends Grid.Cell> {

    int getCols();
    int getRows();

    boolean existCell(Position position);
    T getCell(Position position);

    interface Cell {
        boolean isFree();
    }
}
