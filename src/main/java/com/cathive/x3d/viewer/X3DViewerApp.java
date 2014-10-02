/*
 * Copyright (C) 2013-2014 The Cat Hive Developers.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cathive.x3d.viewer;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author Benjamin P. Jung
 */
public class X3DViewerApp extends Application {

    /** Logger for this class. */
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
