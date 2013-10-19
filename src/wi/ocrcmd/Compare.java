package wi.ocrcmd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class Compare {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		String head = "tesseract";
		BufferedReader br = new BufferedReader(new FileReader("ocr.sh"));
		String line = null;
		Set<String> nameset = new HashSet<String>();
		while((line = br.readLine())!= null){
			if(line.startsWith("tesseract")){
//				System.out.println(line);
				String[] path = line.split(" ")[3].split("/");
				String name = path[path.length - 2]+"_"+path[path.length - 1];
				nameset.add(name);
			}
		}
		System.out.println(nameset.size());
		
		
		String path = "F:\\dianzibingli\\EMR\\ศหนคักิ๑";
		String[] dirs = new String[]{"licongyu","luzhenpeng","zhanglibang","liuzhiguang"};
		int count = 0;
		for(String d : dirs){
			String p = path +"\\" +d;
			File[] ds = new File(p).listFiles();
			for(File df : ds){
				String dn = df.getName();
				File[] fs = df.listFiles();
				count = count + fs.length;
				for(File tif : fs){
					String filename = dn + "_" + tif.getName();
					String fname = filename.substring(0,filename.indexOf("."));
//					System.out.println(fname);
					nameset.remove(fname);
				}
			}
		}
		
		for(String name : nameset){
			System.out.println(name);
		}

	}

}
