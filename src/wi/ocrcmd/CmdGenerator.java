package wi.ocrcmd;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CmdGenerator {
	
	
	public static void main(String[] args) throws Exception{
		int argvalue = Integer.valueOf(args[0]);
		if(argvalue == 1){
			int num = Integer.valueOf(args[1]);
			splitEmr(num);
		}else if(argvalue == 2){
			convertImage();
		}else if(argvalue == 3){
			ocr();
		}else if(argvalue == 4){
			findEmpty();
		}
	}
	
	public static void ocr()throws Exception{
		File root = new File("/home/yangjinfeng/emr/ocring/preproccess");
		PrintWriter pw = new PrintWriter("ocr.sh");
		ocrImg(root,pw);
		pw.close();
	}
	
	
	public static void findEmpty() throws Exception{
		
		File root = new File("/home/yangjinfeng/emr/ocring/preproccess");
		PrintWriter pw = new PrintWriter("reocr.sh");
		empty(root,pw);
		pw.close();
	}
	
	
	private static void empty(File root,PrintWriter pw){
		File[] names = root.listFiles();
//		Set<String> nameset = null;
		if(names != null){
			for(File f : names){
				if(f.isFile()){
					
//				if(nameset == null){
//					nameset = new HashSet<String>();
//					for(File fn : names){
//						nameset.add(fn.getAbsolutePath());
//					}
//				}
					
					String path = f.getAbsolutePath();
					int dotindex = path.lastIndexOf(".");
					if(path.substring(dotindex).equals(".txt") && f.length() == 0 ){
						String tif = path.substring(0,dotindex) +".tif";
						writeCmd(pw,"tesseract "+tif+"  "+ path.substring(0,dotindex) + "   -l chi_sim");
					}
					if(path.substring(dotindex).equals(".tif") && !new File(path.substring(0,dotindex) +".txt").exists()){
						
						String tif = path.substring(0,dotindex) +".tif";
						writeCmd(pw,"tesseract "+tif+"  "+ path.substring(0,dotindex) + "   -l chi_sim");
					}
					
//				if(path.substring(dotindex).equals(".tif") && !nameset.contains(path.substring(0,dotindex)+".txt")){
//					writeCmd(qqq,"tesseract "+path+"  "+ path.substring(0,dotindex) + "   -l chi_sim");
//					qqq.flush();
//				}
				}else{
					empty(f,pw);
				}
			}
		}
	}
	
	
	private static void ocrImg(File root,PrintWriter qqq){
		File[] names = root.listFiles();
		Set<String> nameset = null;
		if(names!= null){
			for(File f : names){
				if(f.isFile()){
					
					if(nameset == null){
						nameset = new HashSet<String>();
						for(File fn : names){
							nameset.add(fn.getAbsolutePath());
						}
					}
					
					String path = f.getAbsolutePath();
					int dotindex = path.lastIndexOf(".");
					if(path.substring(dotindex).equals(".tif") && !nameset.contains(path.substring(0,dotindex)+".txt")){
						writeCmd(qqq,"tesseract "+path+"  "+ path.substring(0,dotindex) + "   -l chi_sim");
						qqq.flush();
					}
				}else{
					ocrImg(f,qqq);
				}
			}
		}
	}
	
	
	public static void convertImage()throws Exception{
		File root = new File("/home/yangjinfeng/emr/ocring/preproccess");
		
		PrintWriter pw = new PrintWriter("convert.sh");
		convertToTif(root,pw);
		pw.close();
	}
	
	/**
	 * root = new File("C:\\rootdir\\emr");
	 * @param root
	 * @throws Exception
	 */
	private static void convertToTif(File root,PrintWriter pw)throws Exception{
		File[] names = root.listFiles();
		if(names != null){
			for(File f : names){
				if(f.isFile()){
					String path = f.getAbsolutePath();
					int dotindex = path.lastIndexOf(".");
					if(path.substring(dotindex).equals(".jpg")){
						writeCmd(pw,"convert -compress none -depth 8 -alpha off "+path+" " +path.substring(0,dotindex)+".tif");
						writeCmd(pw,"rm "+path);
						pw.flush();
					}
				}else{
					convertToTif(f,pw);
				}
			}
		}
	}
	
	
	
	
	
	
	

	/**
	 * @param args
	 */
	public static void splitEmr(int num) throws Exception{
		// TODO Auto-generated method stub
		String rootdir = "/home/yangjinfeng/emr/review/preproccess";
		String destdir = "/home/yangjinfeng/emr/review";
		String cmdfile = "emr.sh";
		File root = new File(rootdir);
		File[] emrs = root.listFiles();
		int allemrcount = num < emrs.length ? num : emrs.length;
		
		List<String> names = readNames();
		int everycount = allemrcount/names.size();
		
		PrintWriter pw = new PrintWriter(new FileWriter(cmdfile));
		int nameindex = -1;
		String todir = "";
		for(int i = 0;i < allemrcount;i ++){
			if(i % everycount == 0){
				nameindex ++;
				if(nameindex < names.size()){
					todir = destdir + "/" + names.get(nameindex);
					writeCmd(pw,"mkdir " + todir);
				}
			}
//			String filedir = emrs[i].getName();
//			pw.println("mkdir " + todir+"\\"+filedir);
			 String copy = "cp -r "+ emrs[i].getAbsolutePath() +" " + todir;
			 writeCmd(pw,copy);
			
		}
		pw.close();
		
//		String
	}
	
	
	
	private static  List<String> readNames() throws Exception{
		List<String> names = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader("name.txt"));
		String name;
		while((name = br.readLine())!=null){
			if(name.length() > 0){
				names.add(name);
			}
		}
		return names;
	}
	
	private static void writeCmd(PrintWriter pw,String  cmd){
		 pw.println("echo '"+cmd+"'");
		 pw.println(cmd);
	}

}
