package client;

import actors.Actor;
import actors.BoxBug;
import actors.Bug;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import world.GridWorld;
import world.Location;

public class Main extends Application {

    GridWorld world = new GridWorld(23, 23);

    @Override
    public void start(Stage primaryStage) throws Exception{

        world.addActor(new Bug(new Location(5,7),world));
        world.addActor(new Actor(new Location(8,7),world));
        world.addActor(new BoxBug(new Location(9,7),world, 5));

        //Panes control layout of window
        StackPane root = new StackPane();
        int width = 800;
        int height = 600;

        //canvas - a place to draw
        Canvas canvas = new Canvas(width, height);

        //GraphicsContext allow us to draw to a particular canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //add the canvas to the pane, so we can draw yo
        root.getChildren().add(canvas);

        //every javafx app needs at least one scene, :)
        Scene scene = new Scene(root, width, height);

        //every javafx app needs a stage
        primaryStage.setTitle("GridWorld");

        //we just added our scene to the stage
        primaryStage.setScene(scene);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                world.stepWorld();

                //------ draw the world :) --------- :/
                world.drawWorld(gc);

                try {
                    Thread.sleep(1000); //1000 ms = 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        //show the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
