package chapter2;

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

public class Cam3D extends Application{

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    @Override
    public void start(Stage primaryStage) {
        Sphere sphere = new Sphere(50);

        Group group = new Group();
        group.getChildren().add(sphere);

        Camera camera = new PerspectiveCamera(true);
        Scene scene = new Scene(group, WIDTH, HEIGHT);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        camera.translateZProperty().set(-1000);

        camera.setNearClip(1);
        camera.setFarClip(1000);

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W:
                   camera.translateYProperty().set(camera.getTranslateY() + 50);
                    break;
                case S:
                    camera.translateYProperty().set(camera.getTranslateY() - 50);
                    break;
                case A:
                    camera.translateXProperty().set(camera.getTranslateX() + 50);
                    break;
                case D:
                    camera.translateXProperty().set(camera.getTranslateX() - 50);
                    break;
                case Q:
                    camera.translateZProperty().set(camera.getTranslateZ() + 50);
                    break;
                case E:
                    camera.translateZProperty().set(camera.getTranslateZ() - 50);
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
