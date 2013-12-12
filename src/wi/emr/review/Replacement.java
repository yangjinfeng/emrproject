package wi.emr.review;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;


public class Replacement {
	
	private HashMap<String, String> replacemap = new HashMap<String, String>();
	{
		try {
			String line = null;
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("rule.txt"), "UTF-8"));
			while((line = br.readLine())!=null){
				String[] fileds = line.split(" ");
				replacemap.put(fileds[0], fileds[1]);
			}
			
		} catch (Exception e) {
		}
		
	}
	
	private String replace(String input){
		String result = input;
		for(String key : replacemap.keySet()){
			String value = replacemap.get(key);
			if(value.equals("null")){
				value = "";
			}
			result = result.replaceAll(key, value);
		}
		return result;
	}
	
	public static void main(String[] args)throws Exception {
		FileFilter filter = new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.isDirectory() || pathname.getName().endsWith(".txt");
			}
		};
		new Replacement().replaceAll(args[0],filter);
	}
	
	
	public void replaceFile(File txt)throws Exception{
		ArrayList<String> list = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(txt), "UTF-8"));
		String line = null;
		while((line = br.readLine())!=null){
			list.add(replace(line));
		}
		br.close();
		PrintWriter pw = new PrintWriter(txt,"UTF-8");
		for(String str : list){
			pw.println(str);
		}
		pw.close();
	}

	/**
	 * @param args
	 */
	public void replaceAll(String dir,FileFilter filter) throws Exception{
		File[] files = new File(dir).listFiles(filter);
		for(File file : files){
			if(file.isDirectory()){
				replaceAll(file.getAbsolutePath(),filter);
			}else{
				replaceFile(file);
			}
			
		}
	}

}
