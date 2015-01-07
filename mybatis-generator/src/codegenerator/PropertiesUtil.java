package codegenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * 读取sys.properities
 */
public class PropertiesUtil {
	
	private static Properties prop = new Properties();
	
	private static File propFile;

	public static void loadProperties(InputStream is) throws Exception {
		prop.load(is);
	}

	public static void loadProperties(File propFile) throws Exception {
		prop.load(new FileInputStream(propFile));
	}
	
	public static void loadProperties(String propFilePath) throws Exception {
		propFile = new File(propFilePath);
		prop.load(new FileInputStream(propFile));
	}
	
	public static String getProperty(String key) {
		return prop.getProperty(key);
	}

	public static Properties getProperty() {
		return prop;
	}

	public void setProperty(String key, String value) {
		prop.setProperty(key, value);
		try {
			OutputStream os = new FileOutputStream(propFile);
			prop.store(os, key);
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
