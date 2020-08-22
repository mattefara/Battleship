package com.battleship.client.window;

import com.battleship.client.field.DrawableBattleField;
import com.battleship.client.utils.Ship;
import com.battleship.client.utils.ShipDispatcher;
import com.battleship.client.field.BattleField;
import com.battleship.utils.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameBattleField extends JPanel implements MouseMotionListener, MouseListener, KeyListener {

    private DrawableBattleField battleField;
    private ShipDispatcher shipDispatcher;

    private Ship currentShip;
    private Position currentPosition;

    public GameBattleField(Dimension size){
        this.battleField = new DrawableBattleField(10, 10);
        this.setPreferredSize(size);

        this.addMouseMotionListener(this);
        this.addMouseListener(this);

        this.setFocusable(true);
        this.addKeyListener(this);

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

                g.setColor(DrawableBattleField.toColor(battleField.getCellStatus(new Position(i, j))));
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

    private void placeShip(Ship ship, BattleField.BattleCell.Status status){
//        System.out.println("-----------------------");
        Position[] shipPosition = ship.getShipPositions();
        if (this.battleField.areInsideGrid(shipPosition) && this.battleField.areFreeCells(shipPosition)){
            for (Position position: shipPosition){
//                System.out.println("Position: " + position + " " + ship.toString());
                this.battleField.setCellStatus(position, status);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Dimension cellDimension = getCellDimension();
        Position coordinates = getPosition(e.getX(), e.getY(), cellDimension);
        if (this.shipDispatcher.hasMoreShips() && this.battleField.isInsideGrid(coordinates) && this.battleField.isInsideGrid(this.currentPosition)){
            if (!coordinates.equals(this.currentPosition) && this.battleField.getCell(coordinates).isFree()){
                Ship ship = shipDispatcher.getShip();
                ship.setShipPosition(this.currentPosition);
                this.placeShip(ship, BattleField.BattleCell.Status.BLANK);
                this.currentPosition = coordinates;
                ship.setShipPosition(this.currentPosition);
                this.placeShip(ship, BattleField.BattleCell.Status.BLUEPRINT);

                repaint();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Dimension cellDimension = getCellDimension();
        Position clickPosition = getPosition(e.getX(), e.getY(), cellDimension);
        if (this.battleField.isInsideGrid(clickPosition)){
            if (this.shipDispatcher.hasMoreShips()){
                Ship ship = shipDispatcher.getShip();
                if (this.battleField.areFreeCells(ship.getShipPositions())){
                    ship = shipDispatcher.getNextShip();
                }
                ship.setShipPosition(clickPosition);
                placeShip(ship, BattleField.BattleCell.Status.PLACED);
                System.out.println(ship.SIZE + " " + ship.getOrientation());
                repaint();
            }
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
        if (this.shipDispatcher.hasMoreShips() && this.battleField.isInsideGrid(coordinates)){
            this.currentPosition = coordinates;
            Ship ship = this.shipDispatcher.getShip();
            ship.setShipPosition(coordinates);
            this.placeShip(ship, BattleField.BattleCell.Status.BLUEPRINT);
        }
        if (coordinates.getX() > 0 && coordinates.getY() > 0){
        }
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (this.shipDispatcher.hasMoreShips()){
            Ship ship = this.shipDispatcher.getShip();
            this.placeShip(ship, BattleField.BattleCell.Status.BLANK);
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        System.out.println("Typed");
        if (" ".compareToIgnoreCase(String.valueOf(keyEvent.getKeyChar())) == 0){
            Ship ship = this.shipDispatcher.getShip();
            this.placeShip(ship, BattleField.BattleCell.Status.BLANK);
            ship.changeOrientation();
            this.placeShip(ship, BattleField.BattleCell.Status.BLUEPRINT);
            repaint();
        }
    }
}
