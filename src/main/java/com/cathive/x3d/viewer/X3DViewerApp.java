package com.cathive.x3d.viewer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Benjamin P. Jung
 */
public class X3DViewerApp extends Application {

    public X3DViewerApp() {
        super();

    }

    @Override
    public void start(final Stage primaryStage) throws Exception {

        primaryStage.setTitle("X3D Viewer");
        primaryStage.setWidth(1280.0);
        primaryStage.setHeight(720.0);

        final Scene scene = new Scene(new X3DViewer());
        primaryStage.setScene(scene);

        primaryStage.show();

    }

    public static void main(String... args) {
        Application.launch(args);
    }
}
