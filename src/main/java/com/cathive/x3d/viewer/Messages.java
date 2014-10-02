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

    public static String getString(final String key) {
        return RESOURCE_BUNDLE.getString(key);
    }

    public static String[] getStringArray(final String key) {
        return RESOURCE_BUNDLE.getStringArray(key);
    }

}
