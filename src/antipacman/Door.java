package antipacman;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Door {

    private Rectangle door;

    public Door() {
        door = new Rectangle(180, 240, 40, 3);
        door.setFill(Color.WHITE);
        DropShadow ds = new DropShadow();
        ds.setRadius(20);
        ds.setHeight(20);
        ds.setWidth(40);
        ds.setColor(Color.WHITE);
        door.setEffect(ds);
    }

    public Rectangle getDoor() {
        return door;
    }

    public void setDoor(Rectangle door) {
        this.door = door;
    }

}
