package antipacman;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class Quarter {

    private int x;
    private int y;
    private char kind;
    private Arc arc;
    private int arrayX;
    private int arrayY;
    public static ArrayList<Quarter> member = new ArrayList<Quarter>();
    public static Group total = new Group();

    public Quarter(char kind, int x, int y) {
        this.kind = kind;
        this.arrayX = y;
        this.arrayY = x;
        this.x = 5 + 13 * x;
        this.y = 60 + 13 * y;
        arc = new Arc(this.x, this.y, 6, 6, 0, 90);
        arc.setFill(Color.rgb(0, 98, 254));
        arc.setType(ArcType.ROUND);
        switch (kind) {
            case '5':
                arc.setCenterY(this.y + 13);
                break;
            case '6':
                arc.setCenterX(this.x + 13);
                arc.setCenterY(this.y + 13);
                arc.setStartAngle(90);
                break;
            case '8':
                arc.setCenterX(this.x + 13);
                arc.setStartAngle(180);
                break;
            case '7':
                arc.setStartAngle(270);
                break;
        }
        Quarter.total.getChildren().add(this.getArc());
        Quarter.member.add(this);
    }

    public int getArrayX() {
        return arrayX;
    }

    public void setArrayX(int arrayX) {
        this.arrayX = arrayX;
    }

    public int getArrayY() {
        return arrayY;
    }

    public void setArrayY(int arrayY) {
        this.arrayY = arrayY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getKind() {
        return kind;
    }

    public void setKind(char kind) {
        this.kind = kind;
    }

    public Arc getArc() {
        return arc;
    }

    public void setArc(Arc arc) {
        this.arc = arc;
    }

}
