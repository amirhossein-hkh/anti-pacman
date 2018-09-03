package antipacman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Lighting;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public static Map m;
    public static ArrayList<Food> food = new ArrayList<Food>();
    public static ArrayList<Food> charger = new ArrayList<Food>();
    public static PacMan pman;
    public static Door d;
    public static BackGround bg;
    public static Group root;
    public static Scene scene;
    public static AnimationTimer timer;
    public static Ghost[] ghosts = new Ghost[4];
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        m = new Map(1);
        primaryStage.setTitle("Anti-PacMan");
        firstInitialize();
        actionHandlers();
        timer = new AnimationTimer() {
            private long lastUpdate = 0 ;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 10_000_000) {
                    for(int i=0 ; i<4 ; i++){
                        if(ghosts[i] != null) {
                            ghosts[i].move(m.getMap());
                        }
                    }
                    pman.move(m.getMap());
                    lastUpdate = now ;
                    for(int i=0 ; i<4 ; i++){
                        if(ghosts[i] != null && ghosts[i].getCrow() == pman.getCrow() && ghosts[i].getCcol() == pman.getCcol()){
                            if(Ghost.isScared){
                                //remove ghost
                                Ghost.findGhost(pman.getCrow(), pman.getCcol());
                                ghosts[i] = null;
                            }else {
                                timer.stop();
                                System.out.println("you won");
                            }
                        }
                    }
                    if(Ghost.size == 0 || (Food.Csize==0 && Food.SFsize==0)){
                        timer.stop();
                        System.out.println("you lost!!!");
                    }
                }

            }

        };
        timer.start();

        
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void actionHandlers() {
        scene.setOnKeyPressed(event -> {
            String t = event.getCode().toString();
            switch (t.charAt(0)) {
                case 'R':
                case 'U':
                case 'D':
                case 'L':
                    Ghost.userMove = Character.toLowerCase(t.charAt(0));
                    break;
            }
            if (t.length() > 5) {
                switch (t.charAt(5)) {
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                        if(ghosts[Integer.valueOf(t.charAt(5)+"")-1] != null) {
                            if(ghosts[Ghost.whichOneIsControed - 1] != null) {
                                ghosts[Ghost.whichOneIsControed - 1].getImv().setEffect(null);
                            }
                            ghosts[t.charAt(5) - 48 - 1].setThisIsControled(true);
                        }else {
                            System.out.println("this ghost is dead! chose another one.");
                        }
                        break;
                }
            }
        });
    }

    public static void firstInitialize() {
        root = new Group();
        scene = new Scene(root, 400, 500, Color.BLACK);
        d = new Door();
        bg = new BackGround();
        root.getChildren().add(bg.getRec());
        root.getChildren().add(d.getDoor());
        m.makeGroup();
        root.getChildren().add(Corner.total);
        root.getChildren().add(Food.total);
        root.getChildren().add(Quarter.total);
        root.getChildren().add(Wall.total);
        for (int i = 0; i < 4; i++) {
            ghosts[i] = new Ghost();
            root.getChildren().add(ghosts[i].getImv());
        }
        pman = new PacMan();
        root.getChildren().add(pman.getPacMan());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
