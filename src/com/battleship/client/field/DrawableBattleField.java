package com.battleship.client.field;

import java.awt.*;

public class DrawableBattleField extends BattleField {

    public DrawableBattleField(int cols, int rows) {
        super(cols, rows);
    }

    public static Color toColor(BattleCell.Status status){
        switch (status){
            default:
            case BLANK:
                return Color.WHITE;
            case BLUEPRINT:
                return changeAlpha(Color.BLUE, 0.5);
            case HIT:
                return Color.RED;
            case MISS:
                return Color.LIGHT_GRAY;
            case PLACED:
                return changeAlpha(Color.GRAY, 0.7);
        }
    }

    protected static Color changeAlpha(Color color, double percentage){
        if (percentage > 0 && percentage <= 1)
            return new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (255 * percentage));
        return color;
    }
}
