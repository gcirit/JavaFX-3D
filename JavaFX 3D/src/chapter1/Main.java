package chapter1;

import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

/**
 * @author Gokturk Cirit
 * @version 1.0
 *
 */

public class Main extends Application{


    private static final int WIDTH = 1400;
    private static final int HEIGHT = 800;

    @Override
    public void start(Stage primaryStage) {
        Sphere sphere = new Sphere(50);


        Group group = new Group();
        group.getChildren().add(sphere);

        Camera camera = new PerspectiveCamera();
        Scene scene = new Scene(group, WIDTH, HEIGHT);
        scene.setFill(Color.DARKBLUE);
        scene.setCamera(camera);

        sphere.translateXProperty().set(WIDTH / 2);
        sphere.translateYProperty().set(HEIGHT / 2);

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W:
                    sphere.translateYProperty().set(sphere.getTranslateY() - 100);
                    break;
                case S:
                    sphere.translateYProperty().set(sphere.getTranslateY() + 100);
                    break;
                case A:
                    sphere.translateXProperty().set(sphere.getTranslateX() - 100);
                    break;
                case D:
                    sphere.translateXProperty().set(sphere.getTranslateX() + 100);
                    break;
                case Q:
                    sphere.translateZProperty().set(sphere.getTranslateZ() - 100);
                    break;
                case E:
                    sphere.translateZProperty().set(sphere.getTranslateZ() + 100);
                    break;
            }
        });

        primaryStage.setTitle("3D Sphere Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
