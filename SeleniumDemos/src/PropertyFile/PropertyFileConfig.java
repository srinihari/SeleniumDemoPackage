package PropertyFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileConfig {
	
	Properties pro;
	public PropertyFileConfig() throws Exception{
		try {
			File src=new File("./Configuration/config.property");
			FileInputStream file=new FileInputStream(src);
			pro=new Properties();
			pro.load(file);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
		
		public String getChromeDriver()
		{
			String Chromepath=pro.getProperty("Chrome_Driver");
			return Chromepath;
		}
		
		public String getUrl() {
			String url=pro.getProperty("candidate_url");
			return url;
		}
		public String getUserName() {
			String usernname=pro.getProperty("candidate_Username");
			return usernname;
		}
		public String getPassword() {
			String password=pro.getProperty("candidate_Password");
			return password;
		}
		
		public String getXmlfile() {
			String xmlfile_path=pro.getProperty("xml_path");
			return xmlfile_path;
		}
		
		
		
	}


