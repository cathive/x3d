package com.cathive.x3d.viewer;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Mesh;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Benjamin P. Jung
 */
public class X3DViewerApp extends Application {

    private static final Logger LOGGER = Logger.getLogger(X3DViewerApp.class.getName());

    public X3DViewerApp() {
        super();

    }

    @Override
    public void init() throws Exception {
        performJavaFxWorkarounds();
        super.init();
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {

        primaryStage.setTitle(Messages.getString(Messages.APP_TITLE));
        primaryStage.setWidth(1280.0);
        primaryStage.setHeight(720.0);

        final Scene scene = new Scene(new X3DViewer());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    protected void performJavaFxWorkarounds() {
        LOGGER.log(Level.INFO, "Workaround for JavaFX 8 - Loading fonts...");
        // TODO Stupid workaround - JavaFX 8 has some problems with fonts.mf on my machine (?)
        try {
            final Enumeration<URL> fontDefinitions = getClass().getClassLoader().getResources("META-INF/fonts.mf");
            while (fontDefinitions.hasMoreElements()) {
                final Properties props = new Properties();
                props.load(fontDefinitions.nextElement().openStream());
                for (final Object v: props.values()) {
                    final String fontName = (String) v;
                    Font.loadFont(getClass().getResourceAsStream(fontName), 10.0);
                }
            }
        } catch (final IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void main(String... args) {
        Application.launch(args);
    }

}
