package antipacman;

import static antipacman.Main.charger;
import static antipacman.Main.food;
import static antipacman.Ghost.isScared;
import javafx.scene.Group;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Food {

    private Circle circle;
    private char kind;
    private int x;
    private int y;
    private int row;
    private int col;
    public static Group total = new Group();
    public static int SFsize;
    public static int Csize;

    public Food(char kind, int x, int y) {
        this.kind = kind;
        this.row = y;
        this.col = x;
        this.x = 13 * x + 11;
        this.y = 13 * y + 66;
        switch (kind) {
            case 's':
                circle = new Circle(this.x, this.y, 2.2, Color.WHITE);
                circle.setEffect(new Lighting());
                SFsize++;
                break;
            case 'b':
                circle = new Circle(this.x, this.y, 5, Color.WHITE);
                circle.setEffect(new Lighting());
                Csize++;
                break;

        }
        Food.total.getChildren().add(this.getCircle());
    }

    public static void findFood(int row, int col) {
        for (int i = 0; i < food.size(); i++) {
            if (food.get(i).row == row && food.get(i).col == col) {
                if (food.get(i).circle.visibleProperty().get()) {
                    food.get(i).circle.setVisible(false);
                    SFsize--;

                }
            }
        }
    }

    public static long findCharger(int row, int col) {
        for (int i = 0; i < charger.size(); i++) {
            if (charger.get(i).row == row && charger.get(i).col == col) {
                if (charger.get(i).circle.visibleProperty().get()) {
                    charger.get(i).circle.setVisible(false);
                    isScared = true;
                    PacMan.ischarged = true;
                    Csize--;
                    return System.currentTimeMillis() / 1000;
                }
            }
        }
        return 0;
    }


    public Circle getCircle() {
        return circle;
    }

}
