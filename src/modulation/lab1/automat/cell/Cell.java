package modulation.lab1.automat.cell;

import modulation.lab1.automat.util.Position2D;
import javafx.scene.paint.Color;

/**
 *
 * @author Admin
 */
public abstract class Cell {
    
    protected String type;
    protected Double price;
    protected Boolean isFlammable;
    protected Position2D position;
    protected javafx.scene.paint.Color color;
    
    public Cell () {
    }

    public Position2D getPosition() {
        return position;
    }

    public void setPosition(Position2D position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean getIsFlammable() {
        return isFlammable;
    }

    public Color getColor() {
        return color;
    }
    
    
}
