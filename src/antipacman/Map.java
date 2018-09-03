package antipacman;

import static antipacman.Main.charger;
import static antipacman.Main.food;
import static antipacman.Main.m;
import java.io.*;

public class Map {

    private int level;
    private char[][] map;
    private String mapfile;

    public Map(int level) throws IOException {
        this.level = level;
        this.mapfile = "level" + level + ".txt";
        map = new char[33][30];
        makeMap();

    }

    public char[][] getMap() {
        return map;
    }

    private void makeMap() throws IOException {
        try (FileReader in = new FileReader(mapfile)) {
            int row = 0;
            int col = 0;
            int c;
            while ((c = in.read()) != -1) {
                char C = (char) c;
                if (!Character.isWhitespace(C)) {
                    map[row][col] = C;
                    col++;
                    if (col == 30) {
                        col = 0;
                        row++;
                    }
                    if (row == 33) {
                        break;
                    }
                }

            }
        }
    }

    public void makeGroup() {
        char t;
        Wall w;
        Corner c;
        Quarter q;
        Food f;
        for (int i = 0; i < 33; i++) {
            for (int j = 0; j < 30; j++) {
                t = m.getMap()[i][j];
                switch (t) {
                    case 'w':
                    case 'o':
                    case 'O':
                    case 'A':
                    case 'a':
                        w = new Wall(t, j, i);
                        break;
                    case 'c':
                    case 'C':
                    case 'Q':
                    case 'q':
                        c = new Corner(t, j, i);
                        break;
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                        q = new Quarter(t, j, i);
                        break;
                    case '.':
                        f = new Food('s', j, i);
                        food.add(f);
                        break;
                    case '0':
                        f = new Food('b', j, i);
                        charger.add(f);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
