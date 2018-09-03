package antipacman;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall {

    private Rectangle wall;
    private int x;
    private int y;
    private int arrayX;
    private int arrayY;
    private char kind;
    public static ArrayList<Wall> member = new ArrayList<Wall>();
    public static Group total = new Group();

    public Wall(char kind, int x, int y) {
        this.kind = kind;
        this.arrayX = y;
        this.arrayY = x;
        wall = new Rectangle();
        wall.setFill(Color.rgb(0, 98, 254));
        switch (kind) {
            case 'w':
                wall.setWidth(13);
                wall.setHeight(13);
                break;
            case 'o':
            case 'O':
                wall.setWidth(13);
                wall.setHeight(6);
                break;
            case 'a':
            case 'A':
                wall.setWidth(6);
                wall.setHeight(13);

                break;
        }
        this.setX(x);
        this.setY(y);
        Wall.total.getChildren().add(this.getWall());
        Wall.member.add(this);
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
        this.x = 5 + x * 13;
        switch (this.kind) {
            case 'w':
            case 'o':
            case 'O':
            case 'a':
                wall.setX(this.x);
                break;
            case 'A':
                wall.setX(this.x + 7);
                break;
        }

    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = 60 + y * 13;
        switch (this.kind) {
            case 'w':
            case 'O':
            case 'A':
            case 'a':
                wall.setY(this.y);
                break;
            case 'o':
                wall.setY(this.y + 7);
                break;
        }
    }

    public Rectangle getWall() {
        return wall;
    }

    public void setWall(Rectangle wall) {
        this.wall = wall;
    }

    public char getKind() {
        return kind;
    }

    public void setKind(char kind) {
        this.kind = kind;
    }

}
