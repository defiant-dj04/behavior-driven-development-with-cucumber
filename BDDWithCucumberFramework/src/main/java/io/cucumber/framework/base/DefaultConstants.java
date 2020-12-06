package io.cucumber.framework.base;

public interface DefaultConstants {
    long IMPLICIT_TIMEOUT = 10;
    long EXPLICIT_TIMEOUT = 20;

    String DEFAULT_PROPERTIES_PATH   = "./src/main/resources/commonData/appConfig.properties";
    String DEFAULT_DRIVER_PATH       = "./src/main/resources/driver/";
    String DEFAULT_DOWNLOAD_DIR_PATH = "./src/test/output/downloads/";
}
