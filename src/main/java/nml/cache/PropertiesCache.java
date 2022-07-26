package nml.cache;

import java.io.*;
import java.util.Properties;
import java.util.Set;

/**
 * Reading the properties from a file application.properties which is in the classpath.
 * The class PropertiesCache acts as a cache for loaded properties.
 * The file loads the properties lazily way, but only once.
 * @author nicolaslopez
 */
public class PropertiesCache {
    private final Properties configProp = new Properties();

    private PropertiesCache()
    {
        //Private constructor to restrict new instances
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        System.out.println("Reading all properties from the property file");
        try {
            configProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Bill Pugh Solution for Singleton Pattern
     * As we can see, until we need an instance,
     * the LazyHolder class will not be initialized
     * until required, and you can still use other
     * static members of BillPughSingleton class.
     */
    private static class LazyHolder
    {
        private static final PropertiesCache INSTANCE = new PropertiesCache();
    }

    public static PropertiesCache getInstance()
    {
        return LazyHolder.INSTANCE;
    }

    public String getProperty(String key){
        return configProp.getProperty(key);
    }

    public Set<String> getAllPropertyNames(){
        return configProp.stringPropertyNames();
    }

    public boolean containsKey(String key){
        return configProp.containsKey(key);
    }

    /**
     * Write a new key-value pair in properties file.
     * From my point of view, writing a file into a pipeline is not very useful.
     * Use the setProperty(k, v) method to write new property to the properties file.
     */
    public void setProperty(String key, String value){
        configProp.setProperty(key, value);
    }

    /**
     * Use the flush() method to write the updated properties back into the application.properties file.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void flush() throws FileNotFoundException, IOException {
        try (final OutputStream outputstream
                     = new FileOutputStream("application.properties");) {
            configProp.store(outputstream,"File Updated");
            outputstream.close();
        }
    }
}
