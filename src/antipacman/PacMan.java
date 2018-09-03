package antipacman;

import static antipacman.Main.timer;
import static antipacman.Food.findCharger;
import static antipacman.Food.findFood;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class PacMan {

    private final Arc pacman;
    private int Frow;
    private int Fcol;
    private int Crow;
    private int Ccol;
    private char CmoveDirection;
    private char NmoveDirection;
    private int translateX;
    private int translateY;
    private double startangel;
    private double length;
    private int remain = 13;
    private boolean isopen;
    private boolean isDead;
    public static boolean ischarged;
    private long CTime;

    public void setCmoveDirection(char CmoveDirection) {
        this.CmoveDirection = CmoveDirection;
        switch (CmoveDirection) {
            case 'r':
                this.startangel = 40;
                break;
            case 'l':
                this.startangel = 180 + 40;
                break;
            case 'u':
                this.startangel = 90 + 40;
                break;
            case 'd':
                this.startangel = 270 + 40;
                break;
        }
    }


    public Arc getPacMan() {
        return pacman;
    }

    public void setStartangel(double startangel) {
        this.startangel = startangel;
        pacman.setStartAngle(startangel);
    }

    public void setLength(double length) {
        this.length = length;
        pacman.setLength(length);
    }

    public PacMan() {
        Frow = 24;
        Fcol = 14;
        Ccol = Fcol;
        Crow = Frow;
        this.setCmoveDirection('r');
        this.length = 360;
        this.startangel = 0;
        this.isopen = false;
        pacman = new Arc(195, 377, 10, 10, startangel, length);
        pacman.setType(ArcType.ROUND);
        RadialGradient gradient1 = new RadialGradient(180 + 45, .25, .5, .5, 1,
                true, CycleMethod.NO_CYCLE, new Stop(0, Color.YELLOW),
                new Stop(1, Color.BLACK));
        pacman.setFill(gradient1);

    }

    public static void findPacMan(int row, int col) {
        if (Main.pman.Crow == row && Main.pman.Ccol == col) {
            Main.pman.isDead = true;
            timer.stop();

        }
    }

    public void openPacmanMouth() {

        if (!isopen) {
            setLength(length - 10);
            setStartangel(startangel + 5);

        } else {
            setLength(length + 10);
            setStartangel(startangel - 5);
        }

        if (length > 360) {
            isopen = false;
        } else if (length < 280) {
            isopen = true;
        }
    }

    public void move(char[][] arr) {
        openPacmanMouth();
        if (remain != 0 && CmoveDirection != ' ') {
            switch (CmoveDirection) {
                case 'r':
                    translateX++;
                    pacman.setTranslateX(translateX);
                    break;
                case 'l':
                    translateX--;
                    pacman.setTranslateX(translateX);
                    break;
                case 'u':
                    translateY--;
                    pacman.setTranslateY(translateY);
                    break;
                case 'd':
                    translateY++;
                    pacman.setTranslateY(translateY);
                    break;
            }
            remain--;
            Ccol = Fcol + (translateX / 13);
            Crow = Frow + (translateY / 13);

        } else if (isValid(arr, NmoveDirection)) {
            if (Fcol + (translateX / 13) > 0 && Fcol + (translateX / 13) < 29) {
                random(arr);
            }
            remain = 13;
            findFood(Crow, Ccol);
            long temp = findCharger(Crow, Ccol); 
            if(temp!=0)
                CTime = temp;
            if ((System.currentTimeMillis() / 1000) == CTime + 10) {
                ischarged = false;
                Ghost.isScared = false;
            }
//            if (ischarged) {
//                Ghost.findGhost(Crow, Ccol);
//            }
            setCmoveDirection(NmoveDirection);
        } else {
            NmoveDirection = CmoveDirection;
            random(arr);
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
            translateX = -14 * 13;
            pacman.setTranslateX(translateX);
            translateY = -9 * 13;
            pacman.setTranslateY(translateY);
        }
        if (Fcol + (translateX / 13) == 0 && NmoveDirection == 'l') {
            translateX = 14 * 13;
            pacman.setTranslateX(translateX);
            translateY = -9 * 13;
            pacman.setTranslateY(translateY);
        }

        t = arr[Frow + rowStep + (translateY / 13)][Fcol + colStep + (translateX / 13)];
        switch (t) {
            case '.':
            case '0':
            case 'e':
                return true;
        }
        return false;
    }
            
    private void random(char[][] arr) {
        ArrayList<Character> possibleMove = new ArrayList<Character>();
        possibleMove.addAll(Arrays.asList('r', 'l', 'u', 'd'));
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
        ArrayList<Character> illigalMove = new ArrayList<Character>();
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
