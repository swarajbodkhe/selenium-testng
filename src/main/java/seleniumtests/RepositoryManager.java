package seleniumtests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class RepositoryManager {
	FileInputStream fs=null;
	Properties prop=null;
	
	
	public static Properties getConfig() throws IOException {
		FileInputStream fis = null;
	      Properties prop = null;
	      String stream=System.getProperty("user.dir")+"/src/test/resources/Configuration/config.properties";
	      try {
	         fis = new FileInputStream(stream);
	         prop = new Properties();
	         prop.load(fis);
	      } catch(FileNotFoundException fnfe) {
	         fnfe.printStackTrace();
	      } catch(IOException ioe) {
	         ioe.printStackTrace();
	      } finally {
	         fis.close();
	      }
	      return prop;
	}
	
	
	
}
