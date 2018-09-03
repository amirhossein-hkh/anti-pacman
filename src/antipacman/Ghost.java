package antipacman;

import static antipacman.Main.ghosts;
import static antipacman.Main.root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Ghost {

    private int Frow;
    private int Fcol;
    private int Crow;
    private int Ccol;
    private final ArrayList<Image> images;
    private final DropShadow ds;
    private ImageView imv;
    private boolean thisIsControled;
    private final int number;
    public static int size;
    private char CmoveDirection;
    private char NmoveDirection;
    private int translateX;
    private int translateY;
    private int remain = 13;
    private boolean isDead;
    public static char userMove;
    public static boolean isScared = false;
    public static int whichOneIsControed = 1;
    public static ArrayList<Image> scaredGhost = new ArrayList<Image>();

    public Ghost() {
        size++;
        number = size;
        Frow = 15;
        isDead = false;
        images = new ArrayList<Image>();
        images.add(new Image(Main.class.getResourceAsStream("images/"+(number + "r.png"))));
        images.add(new Image(Main.class.getResourceAsStream("images/"+(number + "l.png"))));
        images.add(new Image(Main.class.getResourceAsStream("images/"+(number + "u.png"))));
        images.add(new Image(Main.class.getResourceAsStream("images/"+(number + "d.png"))));
        imv = new ImageView(images.get(0));
        ds = new DropShadow(20, 0, 0, Color.WHITE);
        ds.setSpread(.7);
        switch (number) {
            case 1:
                Fcol = 16;
                imv.setLayoutX(212);
                imv.setEffect(ds);
                scaredGhost.add(new Image(Main.class.getResourceAsStream("images/"+("Sr.png"))));
                scaredGhost.add(new Image(Main.class.getResourceAsStream("images/"+("Sl.png"))));
                scaredGhost.add(new Image(Main.class.getResourceAsStream("images/"+("Su.png"))));
                scaredGhost.add(new Image(Main.class.getResourceAsStream("images/"+("Sd.png"))));
                break;
            case 2:
                Fcol = 15;
                imv.setLayoutX(201);
                break;
            case 3:
                Fcol = 14;
                imv.setLayoutX(187);
                break;
            case 4:
                Fcol = 13;
                imv.setLayoutX(176);
                break;
        }
        imv.setLayoutY(252);
        Ccol = Fcol;
        Crow = Frow;
    }

    public ImageView getImv() {
        return imv;
    }

    public void setThisIsControled(boolean thisIsControled) {
        this.thisIsControled = thisIsControled;
        whichOneIsControed = number;
        imv.setEffect(ds);
    }

    public static void findGhost(int row, int col) {
        for (Ghost g : ghosts) {
            if (g != null && g.Crow == row && g.Ccol == col) {
                if (g.isDead == false) {
                    root.getChildren().remove(g.imv);
                    g.isDead = true;
                    size--;
                }
            }
        }
    }

    public void move(char[][] arr) {
        if (number == whichOneIsControed) {
            if (isValid(arr, userMove)) {
                NmoveDirection = userMove;
            }
        }
        if (remain != 0 && CmoveDirection != ' ') {
            switch (CmoveDirection) {
                case 'r':
                    translateX++;
                    imv.setTranslateX(translateX);
                    if (isScared) {
                        imv.setImage(scaredGhost.get(0));
                    } else {
                        imv.setImage(images.get(0));
                    }
                    break;
                case 'l':
                    translateX--;
                    imv.setTranslateX(translateX);
                    if (isScared) {
                        imv.setImage(scaredGhost.get(1));
                    } else {
                        imv.setImage(images.get(1));
                    }
                    break;
                case 'u':
                    translateY--;
                    imv.setTranslateY(translateY);
                    if (isScared) {
                        imv.setImage(scaredGhost.get(2));
                    } else {
                        imv.setImage(images.get(2));
                    }
                    break;
                case 'd':
                    translateY++;
                    imv.setTranslateY(translateY);
                    if (isScared) {
                        imv.setImage(scaredGhost.get(3));
                    } else {
                        imv.setImage(images.get(3));
                    }
                    break;
            }
            remain--;
            Ccol = Fcol + (translateX / 13);
            Crow = Frow + (translateY / 13);

        } else if (isValid(arr, NmoveDirection)) {
            if (number != whichOneIsControed && Fcol + (translateX / 13) > 0 && Fcol + (translateX / 13) < 29) {
                random(arr);
            }

            if (!isScared) {
                PacMan.findPacMan(Crow, Ccol);
            }
            remain = 13;
            CmoveDirection = NmoveDirection;

        } else {
            NmoveDirection = CmoveDirection;
            if (number != whichOneIsControed) {
                random(arr);
            }
        }
    }

    private boolean isValid(char arr[][], char move) {

        int colStep = 1;
        int rowStep = 1;
        switch (move) {
            case 'r':
                rowStep = 0;
                break;
            case 'l':
                colStep = -1;
                rowStep = 0;
                break;
            case 'u':
                colStep = 0;
                rowStep = -1;
                break;
            case 'd':
                colStep = 0;
                break;
        }
        char t;
        if (Fcol + (translateX / 13) == 29 && NmoveDirection == 'r') {
            translateX = (number - 17) * 13;
            imv.setTranslateX(translateX);
        }
        if (Fcol + (translateX / 13) == 0 && NmoveDirection == 'l') {
            translateX = (12 + number) * 13;
            imv.setTranslateX(translateX);
        }
        t = arr[Frow + rowStep + (translateY / 13)][Fcol + colStep + (translateX / 13)];
        switch (t) {
            case '.':
            case '0':
            case 'e':
            case 'd':
            case 'p':
            case 'r':
            case 'y':
            case 'g':
                return true;
        }
        return false;
    }

    private void random(char[][] arr) {
        ArrayList<Character> possibleMove = new ArrayList<Character>();
        possibleMove.addAll(Arrays.asList('r', 'l', 'u', 'd'));
        ArrayList<Character> illigalMove = new ArrayList<Character>();
        Character illigal;
        switch (CmoveDirection) {
            case 'r':
                illigal = 'l';
                break;
            case 'l':
                illigal = 'r';
                break;
            case 'u':
                illigal = 'd';
                break;
            case 'd':
                illigal = 'u';
                break;
            default:
                illigal = 'l';
        }
        illigalMove.add(illigal);
        for (int i = 0; i < possibleMove.size(); i++) {
            if (!Objects.equals(possibleMove.get(i), illigal)) {
                if (!isValid(arr, possibleMove.get(i))) {
                    illigalMove.add(possibleMove.get(i));
                }
            }
        }
        possibleMove.removeAll(illigalMove);
        if (!possibleMove.isEmpty()) {
            NmoveDirection = possibleMove.get((int) (Math.random() * possibleMove.size()));
        } else {
            NmoveDirection = illigal;
        }
    }

    public int getCrow() {
        return Crow;
    }

    public int getCcol() {
        return Ccol;
    }
}
