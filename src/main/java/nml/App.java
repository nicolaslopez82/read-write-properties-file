package nml;

import nml.cache.PropertiesCache;
import nml.constants.CATConstant;

import java.io.IOException;

/**
 * Reading properties file using Properties.load() method.
 * Also, we will use Properties.setProperty() method
 * to write a new property into the .properties file.
 * @author nicolaslopez
 */
public class App 
{
    public static void main( String[] args )
    {
        //======= Reading the property file ========//

        //Get individual properties
        System.out.println(PropertiesCache.getInstance().getProperty(CATConstant.COMPANY_NAME));
        System.out.println(PropertiesCache.getInstance().getProperty(CATConstant.LOCATION_COUNTRY));
        System.out.println(PropertiesCache.getInstance().getProperty(CATConstant.LOCATION_IP));
        System.out.println(PropertiesCache.getInstance().getProperty(CATConstant.ENVIRONMENT));
        System.out.println('\n');

        //All property names
        System.out.println(PropertiesCache.getInstance().getAllPropertyNames());
        System.out.println('\n');

        //Testing containsKey(string key) method
        System.out.println("Testing containsKey(string CATConstant.LOCATION_COUNTRY) method: " + PropertiesCache.getInstance().containsKey("location"));


        //======= Writing the property file ========//

        // Checking if the country key exists. Otherwise, create it.
        PropertiesCache cache = PropertiesCache.getInstance();
        if(cache.containsKey("city") == false){
            cache.setProperty("city", "Chicago");
        }

        //Verify property
        System.out.println("Showing the new property: " + cache.getProperty("city"));

        //Write to the file
        try {
            PropertiesCache.getInstance().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
