package wi.emr.review;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;


public class CharConverter {
	
	private Properties prop = new Properties();
	
	public static CharConverter instance = new CharConverter();
	
	private CharConverter(){
		try {
			prop.load(new InputStreamReader(new FileInputStream("char.properties"), "UTF-8"));
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public String convert(String line){
		Enumeration propnames = prop.propertyNames();
		while(propnames.hasMoreElements()){
			String name = (String)propnames.nextElement();
			String value = prop.getProperty(name);
			line = line.replaceAll(name, value);
		}
		return line;
	}
	
	
	public String trim(String line){
		return line.replaceAll("\t| ", "");
	}
	
	
	public static void main(String[] args)throws Exception {
		CharConverter cc = new CharConverter();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("’‘∑º∑º1400-2453.txt"),"UTF-8"));
		PrintWriter pw = new PrintWriter("’‘∑º∑º1400-2453-1.txt","UTF-8");
		String line = null;
		while((line = br.readLine()) != null){
			String result = cc.convert(line);
			pw.println(result);
			
		}
		pw.flush();
		pw.close();
	}

}
