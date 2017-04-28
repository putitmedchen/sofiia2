package com.good.www1.win.utils;

import com.good.www1.win.data.Urls;

import java.util.ResourceBundle;

public class PropertyManager {

    public static ResourceBundle initProperties() {
        ResourceBundle properties = ResourceBundle.getBundle("test");
        return properties;
    }

    public static int getSearchTimeOut() {
        ResourceBundle properties = initProperties();
        String searchTime = properties.getString("search.timeout");
        int searchTimeOut = Integer.parseInt(searchTime);

        return searchTimeOut;
    }

    public static Urls getEnvironment() {
        ResourceBundle properties = initProperties();
        String environment = properties.getString("environment");

        Urls environmentUrl = getSettingsUrl(environment);
        return environmentUrl;

    }

    private static Urls getSettingsUrl(String appSettingUrl) {
        Urls environment = null;
        if (appSettingUrl.startsWith("stage")) {
            environment = Urls.RIM_NET;
        } else if (appSettingUrl.startsWith("g3")) {
            environment = Urls.G3_URL;
        } else if (appSettingUrl.startsWith("prod")) {
            environment = Urls.PROD_URL;
        }
        return environment;
    }
}
