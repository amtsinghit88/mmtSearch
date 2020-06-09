package fileReaderUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {

	public static Properties prop;

	public  PropertiesFileReader()
	{
		try {
			prop=new Properties();
			prop.load(new FileInputStream(Thread.currentThread().getContextClassLoader()
					.getResource("config.properties").getPath()));
		} catch (IOException e) {e.printStackTrace();}
	}

	public String getBrowserType()
	{
		return  prop.getProperty("browser.type");
	}

	public  boolean getHeadlessMode()
	{
		return Boolean.valueOf(prop.getProperty("browser.headless"));
	}

	public String getUrl()
	{
		return prop.getProperty("url");
	}

	public  String getEnvironMent()
	{
		return prop.getProperty("environment");
	}


	public void getPropertyFile()
	{
		try
		{
			prop = new Properties();
			prop.load(new FileInputStream("/Users/abhishek/Downloads/ui-automation/" +
					"src/main/resources/Config.properties"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
