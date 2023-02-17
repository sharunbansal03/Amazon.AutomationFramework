package GenericUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains generic methods related to Property file handling
 * @author sharu
 *
 */
public class PropertyFileUtility {

	/**
	 * This method will read and return value corresponding to given key from Properties file
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String readDataFromPropertyFile(String key) throws IOException {
		FileInputStream fis = new FileInputStream(IConstantsUtility.PropertyFilePath);
		Properties pObj = new Properties();
		pObj.load(fis);
		return pObj.getProperty(key);
	}
}
