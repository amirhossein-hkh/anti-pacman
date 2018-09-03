package antipacman;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class Corner {

    private Rectangle rectangleo;
    private Rectangle rectanglea;
    private int x;
    private int y;
    private Shape corner;
    private char kind;
    private int arrayX;
    private int arrayY;
    public static ArrayList<Corner> member = new ArrayList<Corner>();
    public static Group total = new Group();

    public Corner(char kind, int x, int y) {
        this.kind = kind;
        this.arrayX = y;
        this.arrayY = x;
        this.x = 5 + 13 * x;
        this.y = 60 + 13 * y;
        rectangleo = new Rectangle(this.x, this.y, 13, 6);
        rectanglea = new Rectangle(this.x, this.y, 6, 13);
        DropShadow ds = new DropShadow();
        ds.setOffsetX(2);
        ds.setOffsetY(2);
        ds.setRadius(5);
        ds.setColor(Color.rgb(0, 98, 254));
        switch (kind) {
            case 'C':
                rectanglea.setX(this.x + 7);
                ds.setOffsetX(-2);
                break;
            case 'q':
                rectangleo.setY(this.y + 7);
                ds.setOffsetY(-2);
                break;
            case 'Q':
                rectanglea.setX(this.x + 7);
                rectangleo.setY(this.y + 7);
                ds.setOffsetX(-2);
                ds.setOffsetY(-2);
                break;
        }
        corner = Shape.union(rectanglea, rectangleo);
        corner.setFill(Color.rgb(0, 98, 254));
        corner.setEffect(ds);
        Corner.total.getChildren().add(this.getCorner());
        Corner.member.add(this);
    }


    public Shape getCorner() {
        return corner;
    }
}
