package wi.emr.review;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomChoose {
	private Set<String> nameSet = new HashSet<String>();
	private String rootdir = "F:\\dianzibingli\\EMR\\xmlemr";
	private static String dischargedir = "F:\\dianzibingli\\EMR\\xmlemr\\discharge";
	private static String progressdir = "F:\\dianzibingli\\EMR\\xmlemr\\progress";
	private static String root = "F:\\dianzibingli\\EMR\\xmlemr\\progress";
	
	private Set<String> removeSet = new HashSet<String>();
	
	
	public void listName(String dir)throws Exception{
		File[] fs = new File(dir).listFiles();
		for(File f : fs){
			removeSet.add(f.getName());
		}
	}
	
	public void deleteFiles(String dir){
		File[] fs = new File(dir).listFiles();
		for(File f : fs){
			if(f.isFile()){
				if(removeSet.contains(f.getName())){
					f.delete();
				}
			}
		}
	}
	
	public static void main(String[] args)throws Exception{
		String discharge = args[0];
		String progress = args[1];
		String root = args[2];
		RandomChoose rc = new RandomChoose();
		rc.listName(discharge);
		rc.listName(progress);
		rc.deleteFiles(root);
	}
	
	public void chooseDischarge(int count){
		File[] files = new File(rootdir).listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.getName().contains("discharge");
			}
		});
		int maxindex = files.length;
		int counter = 0;
		Random rd = new Random();
		while(counter < count){
			int index = rd.nextInt(maxindex);
			String filename = files[index].getName();
			String name = filename.substring(0,filename.lastIndexOf("_"));
			if(nameSet.contains(name)){
				continue;
			}
			String path = files[index].getAbsolutePath();
			String cpcommand = "copy \""+ path +"\" "+dischargedir;
			System.out.println(cpcommand);
			counter ++;
			
			
			nameSet.add(name);
		}
	}
	
	
	public void chooseProgress(int count){
		File[] files = new File(rootdir).listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.getName().contains("progress");
			}
		});
		int maxindex = files.length;
		int counter = 0;
		Random rd = new Random();
		while(counter < count){
			int index = rd.nextInt(maxindex);
			String filename = files[index].getName();
			String name = filename.substring(0,filename.lastIndexOf("_"));
			if(nameSet.contains(name)){
				continue;
			}
			
			String path = files[index].getAbsolutePath();
			String cpcommand = "copy \""+ path +"\" "+progressdir;
			System.out.println(cpcommand);
			counter ++;
			
			nameSet.add(name);
		}

	}
	
	public void compair(){
		File[] files = new File(dischargedir).listFiles();
		List<String> list = new ArrayList<String>();
		for(File f: files){
			list.add(f.getName());
		}
		System.out.println(list.size());
		try{
			BufferedReader br = new BufferedReader(new FileReader("cp2.bat"));
			String line = null;
			int i = 0;
			while((line = br.readLine()) != null){
				String path = line.split(" ")[1];
				String name = path.substring(path.lastIndexOf("\\")+1);
				if(!list.contains(name)){
					System.out.println(name);
				}
				i ++;
			}
			System.out.println(i);
		}catch(Exception e){

		}
	}
	
	
	
	
	
	
	
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		RandomChoose rc = new RandomChoose();
//		rc.chooseDischarge(50);
//		rc.chooseProgress(50);
////		rc.compair();
//	}

}
