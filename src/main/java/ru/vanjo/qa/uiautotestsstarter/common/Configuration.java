package ru.vanjo.qa.uiautotestsstarter.common;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Configuration {

    private final Config config;

    private Configuration(){
        config = ConfigFactory.load("application.conf");
    }

    private static class ConfigurationHolder {
        private final static Configuration INSTANCE = new Configuration();
    }

    public static Configuration instance() {
        return Configuration.ConfigurationHolder.INSTANCE;
    }

    public Config getConfig() {
        return config;
    }
}
