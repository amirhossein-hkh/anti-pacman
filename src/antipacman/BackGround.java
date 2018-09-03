package antipacman;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BackGround {

    private Rectangle rec;

    public BackGround() {
        rec = new Rectangle(5, 60, 390, 430);
        rec.setFill(Color.rgb(0, 49, 119));
    }

    public Rectangle getRec() {
        return rec;
    }

    public void setRec(Rectangle rec) {
        this.rec = rec;
    }

}
