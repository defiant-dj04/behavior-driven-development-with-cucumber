package io.cucumber.framework.utility;

import io.cucumber.framework.base.DefaultConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * It is built for reading data from files of different formats given below:
 * <p>- <strong>.properties</strong></p>
 * <p>- <strong>xls / .xlsx</strong></p>
 * <p>- <strong>.json</strong></p>
 * <p>- <strong>.xml</strong></p>
 *
 * @author Deepjyoti Barman
 * @since August 02, 2020
 */
public class FileHandler implements DefaultConstants
{
    /**
     * Method to get the required key from the .properties file.
     *
     * @param key Key to search in the file
     * @return The value assigned to the respective key
     * @throws IOException
     */
    public String getDataFromProperties(String key) throws IOException {
        FileInputStream fis = new FileInputStream(DEFAULT_PROPERTIES_PATH);
        Properties prop = new Properties();
        prop.load(fis);

        String data = prop.getProperty(key);
        fis.close();

        return data;
    }


    /**
     * Method to get the required key from the .properties file.
     *
     * @param key Key to search in the file
     * @param filePath Path of the .properties file
     * @return The value assigned to the respective key
     * @throws IOException
     */
    public String getDataFromProperties(String key, String filePath) throws IOException
    {
        FileInputStream fis = new FileInputStream(filePath);
        Properties prop = new Properties();
        prop.load(fis);

        String data = prop.getProperty(key);
        fis.close();

        return data;
    }
}