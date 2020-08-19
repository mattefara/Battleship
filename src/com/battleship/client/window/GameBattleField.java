package com.battleship.client.window;

import com.battleship.client.utils.Ship;
import com.battleship.client.utils.ShipDispatcher;
import com.battleship.client.field.BattleField;
import com.battleship.utils.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GameBattleField extends JPanel implements MouseMotionListener, MouseListener {

    private BattleField battleField;
    private ShipDispatcher shipDispatcher;

    private Ship currentShip;
    private Position currentPosition;

    public GameBattleField(Dimension size){
        this.battleField = new BattleField(10, 10);
        this.setPreferredSize(size);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);

        this.shipDispatcher = new ShipDispatcher();
        this.currentShip = this.shipDispatcher.getShip();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension cellDimension = getCellDimension();

        for (int i = 0; i < battleField.getCols(); i++) {
            for (int j = 0; j< battleField.getRows(); j++) {
                Position cellPosition = new Position(i * cellDimension.width, j * cellDimension.height);

                g.setColor(battleField.toColor(battleField.getCellStatus(new Position(i, j))));
//                g.setColor(field.getCellCondition(Coordinates.convertToCoordsRelativeToGrid(i,j)).getColor());
                g.fillRect(cellPosition.getX(), cellPosition.getY(), cellDimension.width, cellDimension.height);
                g.setColor(Color.GRAY);
                g.drawRect(cellPosition.getX(), cellPosition.getY(), cellDimension.width, cellDimension.height);

                g.setColor(Color.GRAY);
                if (i == 0 && j > 0){
                    g.drawString(String.valueOf(j) , cellPosition.getX() + cellDimension.width / 2,cellPosition.getY() + cellDimension.height / 2 + 5);
                } else if (j == 0 && i > 0){
                    g.drawString( String.valueOf( (char) (i-1 + (int) 'A') ), cellPosition.getX() + cellDimension.width / 2,cellPosition.getY() + cellDimension.height / 2 + 5);
                }
            }
        }
    }

    protected Dimension getCellDimension(){
        return new Dimension(getWidth() / battleField.getCols(), getHeight() / battleField.getRows());
    }

    protected Position getPosition(int x, int y, Dimension cellDimension){
        return new Position(x / cellDimension.width,y / cellDimension.height);
    }

    protected boolean isInsideGameGrid(Position position){
        return position.getX() > 0 && position.getY() > 0;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Dimension cellDimension = getCellDimension();
        Position coordinates = getPosition(e.getX(), e.getY(), cellDimension);
        if (this.shipDispatcher.hasMoreShips() && !coordinates.equals(this.currentPosition) && this.isInsideGameGrid(coordinates)){
            System.out.println(coordinates);
            this.currentPosition = coordinates;

        }
//        if (!isReady()){
//            if (!coordinates.equals(getCurrentCoordinates())) {
//                Ship ship = new Ship(coordinates, 3);
//                toggleBlueprint(getCurrentCoordinates(), getSelectedShip(), Condition.BLANK);
//                getCurrentCoordinates().setCoords(coordinates.getCol(), coordinates.getRow());
//                toggleBlueprint(getCurrentCoordinates(), getSelectedShip(), Condition.BLUEPRINT);
//                repaint();
//            }
//        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Dimension cellDimension = getCellDimension();
        Position clickPosition = getPosition(e.getX(), e.getY(), cellDimension);
        if (this.isInsideGameGrid(clickPosition)){
            if (this.shipDispatcher.hasMoreShips()){
                Ship ship = this.shipDispatcher.getNextShip();
                System.out.println(ship.SIZE + " " + ship.getOrientation());
            }

            this.battleField.setCellStatus(clickPosition, BattleField.BattleCell.Status.HIT);
            repaint(new Rectangle(clickPosition.getX() * cellDimension.width, clickPosition.getY() * cellDimension.height, cellDimension.width, cellDimension.height));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Dimension cellDimension = getCellDimension();
        Position coordinates = getPosition(e.getX(), e.getY(), cellDimension);
        if (coordinates.getX() > 0 && coordinates.getY() > 0){
            this.currentPosition = coordinates;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
