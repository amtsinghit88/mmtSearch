package fileReaderUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {

	public static Properties prop;

	public void getPropertyFile()
	{
		try
		{
			prop = new Properties();
			FileInputStream fis = new FileInputStream("/Users/abhishek/Downloads/ui-automation/" +
					"src/main/resources/Config.properties");
			prop.load(fis);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
