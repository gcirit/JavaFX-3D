package chapter14;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 * @author Göktürk Cirit
 * @version 1.0
 */
public class SpinningEarth extends Application {

    private static final float WIDTH = 1280;
    private static final float HEIGHT = 720;

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    private final Sphere sphere = new Sphere(150);


    @Override
    public void start(Stage primaryStage) {
        Camera camera = new PerspectiveCamera(true);
        camera.setNearClip(1);
        camera.setFarClip(10000);
        camera.translateZProperty().set(-1000);

        Group world = new Group();
        world.getChildren().add(prepareEarth());

        Scene scene = new Scene(world, WIDTH, HEIGHT, true);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        initMouseControl(world, scene, primaryStage);

        primaryStage.setTitle("3D Spinning Earth");
        primaryStage.setScene(scene);
        primaryStage.show();

        prepareAnimation();
    }

    private void prepareAnimation() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                sphere.rotateProperty().set(sphere.getRotate() + 0.2);
            }
        };
        timer.start();
    }

    private Node prepareEarth() {
        PhongMaterial earthMaterial = new PhongMaterial();
        earthMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-d.jpg")));
        earthMaterial.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-l.jpg")));
        earthMaterial.setSpecularMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-s.jpg")));
        earthMaterial.setBumpMap(new Image(getClass().getResourceAsStream("/resources/earth/earth-n.jpg")));

        sphere.setRotationAxis(Rotate.Y_AXIS);
        sphere.setMaterial(earthMaterial);
        return sphere;
    }

    private void initMouseControl(Group group, Scene scene, Stage stage) {
        Rotate xRotate;
        Rotate yRotate;
        group.getTransforms().addAll(
                xRotate = new Rotate(0, Rotate.X_AXIS),
                yRotate = new Rotate(0, Rotate.Y_AXIS)
        );
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + anchorX - event.getSceneX());
        });

        stage.addEventHandler(ScrollEvent.SCROLL, event -> {
            double delta = event.getDeltaY();
            group.translateZProperty().set(group.getTranslateZ() + delta);
        });
    }
}
