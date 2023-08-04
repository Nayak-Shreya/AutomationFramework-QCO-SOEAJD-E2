package vtiger.GenericUtilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtility {
	/**
	 * This method reads Data from property file based on given key
	 * @param key
	 * @return value
	 * @throws Throwable
	 */
	public String getDataFromProprtyFile(String key) throws Throwable
	{
		FileInputStream fis = new FileInputStream(IConstants.propertyFilePath);
		Properties p = new Properties();
		p.load(fis);
		String value = p.getProperty(key);
		return value;
	}


}
