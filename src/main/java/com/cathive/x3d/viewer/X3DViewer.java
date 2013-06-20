package com.cathive.x3d.viewer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Benjamin P. Jung
 */
public final class X3DViewer extends BorderPane implements Initializable {

    @FXML
    private ToolBar toolBar;

    public X3DViewer() {

        super();

        final URL location = getClass().getResource(getClass().getSimpleName() + ".fxml");
        final ResourceBundle messages = ResourceBundle.getBundle(getClass().getName());

        final FXMLLoader fxmlLoader = new FXMLLoader(location, messages);
        fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> aClass) {
                return X3DViewer.this;
            }
        });
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (final IOException e) {
            throw new RuntimeException("An error occurred during initialization of the X3D Viewer component.", e);
        }
    }

    @Override
    public void initialize(final URL location, final ResourceBundle messages) {
    }

}
