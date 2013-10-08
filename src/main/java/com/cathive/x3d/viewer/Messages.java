package com.cathive.x3d.viewer;

import java.util.ResourceBundle;

/**
 * @author Benjamin P. Jung
 */
public final class Messages {

    private static final ResourceBundle RESOURCE_BUNDLE;
    static {
        RESOURCE_BUNDLE = ResourceBundle.getBundle(Messages.class.getName());
    }

    public static final String APP_NAME = "app.name";
    public static final String APP_TITLE = "app.title";
    public static final String APP_DESCRIPTION = "app.description";

    public static ResourceBundle getResourceBundle() {
        return RESOURCE_BUNDLE;

    }

    public static final String getString(final String key) {
        return RESOURCE_BUNDLE.getString(key);
    }

}
