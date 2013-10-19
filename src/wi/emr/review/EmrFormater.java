package wi.emr.review;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class EmrFormater {
	
	//出院  山院
	//出现  山现
	//0 o

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
//		new EmrFormater().extractEmr("yangjinfeng", "export");
//		new EmrFormater().extractEmr("F:\\dianzibingli\\EMR\\review\\renzhenyu_finished", "C:\\Users\\yangjinfeng\\Desktop\\export");
//		new EmrFormater().extractEmr("C:\\Users\\yangjinfeng\\Desktop\\liuzhiguang_finished_1", "C:\\Users\\yangjinfeng\\Desktop\\export");
//		new EmrFormater().exportDischargeToXml(new File("F:\\dianzibingli\\EMR\\review\\zhaofangfang_finished\\H913255\\出院小结11.txt"), new File("chuyuanxiaojie.xml"),"GB2312");
//		new EmrFormater().exportProgressToXml(new File("yangjinfeng/0714648/病程记录.txt"), new File("bingchengjilu.xml"));
		new EmrFormater().extractAllEmr("F:\\dianzibingli\\EMR\\review", "F:\\dianzibingli\\EMR\\xmlemr");
	}
	
	public void extractAllEmr(String emrdir,String exportdir)throws Exception{
		File emrroot = new File(emrdir);
		for(File userdir : emrroot.listFiles()){
			extractEmr(userdir.getPath(),exportdir);
		}
	}
	
	
	
	
	public void extractEmr(String userdir,String exportdir)throws Exception{
		String charset = "UTF-8";
		if(userdir.contains("wujiawei") || userdir.contains("zhaofangfang")){
			charset = "GB2312";
		}
		File userfile = new File(userdir);
		String username = userfile.getName();
		new File(exportdir+"/"+username).mkdir();
		
		for(File dir : userfile.listFiles()){
			File[] files = dir.listFiles(new FileFilter() {
				public boolean accept(File pathname) {
					if(pathname.getName().endsWith(".txt") &&
							(pathname.getName().startsWith(EmrNameConst.discharge_CH_Name) || pathname.getName().startsWith(EmrNameConst.progress_CH_Name))){
						return true;
					}
					return false;
				}
			});
			if(files != null){
				for(File file : files){
					String filename = file.getName();
					String newFilename = dir.getName();
					if(filename.contains(EmrNameConst.discharge_CH_Name)){
						filename = filename.replace(EmrNameConst.discharge_CH_Name, EmrNameConst.discharge_EN_Name);
						newFilename = exportdir+"/"+username+"_"+newFilename + "_"+filename.replace(".txt", ".xml");
						exportDischargeToXml(username,file,new File(newFilename),charset);
					}else if(filename.contains(EmrNameConst.progress_CH_Name)){
						filename = filename.replace(EmrNameConst.progress_CH_Name, EmrNameConst.progress_EN_Name);
						newFilename =  exportdir+"/"+username+"_"+newFilename + "_"+filename.replace(".txt", ".xml");
						exportProgressToXml(username,file,new File(newFilename),charset);
					}
				}
			}
		}
	}
	
	public void exportProgressToXml(String userdir,File txtfile,File xmlFile,String charset)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(txtfile),charset));
		String line = null;
		int lineNumber = 0;
		PrintWriter pw = new PrintWriter(xmlFile,"UTF-8");
		pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.println("<progress>");
		
		Block currentBlock = null;
		ArrayList<String> sectionNames = new ArrayList<String>();
		while((line = br.readLine()) != null){
			if(line.length() == 0){
				continue;
			}
			lineNumber ++;
			line = CharConverter.instance.convert(line);
			if(lineNumber == 5){//第四行是患者信息
				new Block(EmrNameConst.zhusu,line,false).output(pw);
			}else if(lineNumber > 5){
				
				line = CharConverter.instance.trim(line);
				
				if(ignore(line)){
					continue;
				}
				if(!isBlockBegin(line)){
					if(currentBlock != null){
						currentBlock.addContent(line);
					}
				}else{
					if(currentBlock != null){
						currentBlock.output(pw);
						currentBlock = null;
					}
//					line =line.trim();
					if(line.startsWith(EmrNameConst.binglitedian)){//门诊收治诊断
						currentBlock = new Block(EmrNameConst.binglitedian,line,true);
						sectionNames.add(EmrNameConst.binglitedian);
						
					}else if(line.startsWith(EmrNameConst.linchuangchubuzhenduan)){
						currentBlock = new Block(EmrNameConst.linchuangchubuzhenduan,line,true);
						sectionNames.add(EmrNameConst.linchuangchubuzhenduan);
						
					}else if(line.startsWith(EmrNameConst.zhenduanyiju)){
						currentBlock = new Block(EmrNameConst.zhenduanyiju,line,true);
						sectionNames.add(EmrNameConst.zhenduanyiju);
						
					}else if(line.startsWith(EmrNameConst.jianbiezhenduan)){
						currentBlock = new Block(EmrNameConst.jianbiezhenduan,line,true);
						sectionNames.add(EmrNameConst.jianbiezhenduan);
						
					}else if(line.startsWith(EmrNameConst.zhenliaojihua)){
						currentBlock = new Block(EmrNameConst.zhenliaojihua,line,true);
						sectionNames.add(EmrNameConst.zhenliaojihua);
						
					}
				}
			}
		}
		if(currentBlock != null){
			currentBlock.output(pw);
			currentBlock = null;
		}
		pw.println("</progress>");
		pw.close();
		
		String[] absence = EmrNameConst.validateSections(EmrNameConst.progressSectionNames, sectionNames);
		if(absence.length > 0 ){
			PrintWriter errpw = new PrintWriter(createErrFilename(xmlFile,userdir));
			for(String name : absence){
				errpw.println("缺失 ："+name);
			}
			errpw.close();
		}
		
	}
	
	private boolean ignore(String str){
		if(str.contains("病历签名") || str.contains("记录时间")|| str.contains("住院医生")){
			return true;
		}
		return false;
	}
	
	private String createErrFilename(File xmlFile,String username){
		String dirname = xmlFile.getParent();
		String filename = xmlFile.getName();
		filename = dirname +"/"  + username+ "/"+"err_"+filename.replace("xml", "txt");
		return filename;
		
	}
	
	public void exportDischargeToXml(String userdir,File txtfile,File xmlFile,String charset)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(txtfile),charset));
		String line = null;
		int lineNumber = 0;
		PrintWriter pw = new PrintWriter(xmlFile,"UTF-8");
		pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.println("<discharge>");
		ArrayList<String> sectionNames = new ArrayList<String>();
		
		Block currentBlock = null;
		while((line = br.readLine()) != null){
			if(line.length() == 0){
				continue;
			}
			lineNumber ++;
			line = CharConverter.instance.convert(line);
			if(lineNumber == 4){//第四行是患者信息
				new Block(EmrNameConst.huanzhexinxi,line,false).output(pw);
			}else if(lineNumber == 5){//第五行是患者住院起止日期
				new Block(EmrNameConst.zhuyuanqizhiri,line,false).output(pw);
			}else if(lineNumber > 5){
				
				line =line.trim();
				if(ignore(line)){
					continue;
				}
				
				if(!isBlockBegin(line)){
					if(currentBlock != null){
						currentBlock.addContent(line);
					}
				}else{
					if(currentBlock != null){
						currentBlock.output(pw);
						currentBlock = null;
					}
					if(line.startsWith(EmrNameConst.menzhenshouzhizhenduan)){//门诊收治诊断
						currentBlock = new Block(EmrNameConst.menzhenshouzhizhenduan,line,true);
						sectionNames.add(EmrNameConst.menzhenshouzhizhenduan);
						
					}else if(line.startsWith(EmrNameConst.linchuangchubuzhenduan)){
						currentBlock = new Block(EmrNameConst.linchuangchubuzhenduan,line,true);
						sectionNames.add(EmrNameConst.linchuangchubuzhenduan);
						
					}else if(line.startsWith(EmrNameConst.linchuangquedingzhenduan)){
						currentBlock = new Block(EmrNameConst.linchuangquedingzhenduan,line,true);
						sectionNames.add(EmrNameConst.linchuangquedingzhenduan);
						
					}else if(line.startsWith(EmrNameConst.ruyuanshiqingkuang)){
						line = CharConverter.instance.trim(line);
						currentBlock = new Block(EmrNameConst.ruyuanshiqingkuang,line,true);
						sectionNames.add(EmrNameConst.ruyuanshiqingkuang);
						
					}else if(line.startsWith(EmrNameConst.zhiliaojingguo)){
						line = CharConverter.instance.trim(line);
						currentBlock = new Block(EmrNameConst.zhiliaojingguo,line,true);
						sectionNames.add(EmrNameConst.zhiliaojingguo);
						
					}else if(line.startsWith(EmrNameConst.chuyuanshiqingkuang)){
						line = CharConverter.instance.trim(line);
						currentBlock = new Block(EmrNameConst.chuyuanshiqingkuang,line,true);
						sectionNames.add(EmrNameConst.chuyuanshiqingkuang);
						
					}else if(line.startsWith(EmrNameConst.zhiliaoxiaoguo)){
						line = CharConverter.instance.trim(line);
						currentBlock = new Block(EmrNameConst.zhiliaoxiaoguo,line,true);
						sectionNames.add(EmrNameConst.zhiliaoxiaoguo);
						
					}else if(line.startsWith(EmrNameConst.chuyuanyizhu)){
//						line = CharConverter.instance.trim(line);
						currentBlock = new Block(EmrNameConst.chuyuanyizhu,line,true);
						sectionNames.add(EmrNameConst.chuyuanyizhu);
					}
					
				}
				
			}
			
			
		}
		
		if(currentBlock != null){
			currentBlock.output(pw);
			currentBlock = null;
		}
		pw.println("</discharge>");
		pw.close();
		String[] absence = EmrNameConst.validateSections(EmrNameConst.dischargeSectionNames, sectionNames);
		if(absence.length > 0 ){
			PrintWriter errpw = new PrintWriter(createErrFilename(xmlFile,userdir));
			for(String name : absence){
				errpw.println("缺失 ："+name);
			}
			errpw.close();
		}

	}
	
	boolean isBlockBegin(String line){
		for(String name : EmrNameConst.sectionNames){
			if(line.startsWith(name)){
				return true;
			}
		}
		return false;
	}
	
	
	
	
	class Block{
		private String tagName;
		private ArrayList<String> content = new ArrayList<String>();
		
		Block(String tag,String cont,boolean replace){
			if(replace){
				tagName = tag;
				if(cont.startsWith(tag + ":")){
					addContent(cont.replaceFirst(tag + ":", ""));
				}else{
					addContent(cont.replaceFirst(tag, ""));
				}
			}else{
				tagName = tag;
				addContent(cont);
			}
		}
		
		void addContent(String str){
			str = str.trim();
			if(str.length() > 0){
				content.add(str);			
			}
		}
		void output(PrintWriter pw){
			pw.println("\t<"+tagName+">");
			for(String cont : content){
				String contentStr = cont;
				if(tagName.equals(EmrNameConst.huanzhexinxi)){
					contentStr = cont.replaceAll("姓名[  ]?:[  ]?[\u4E00-\u9FA5]*[  ]?", "");
				}else if(tagName.equals(EmrNameConst.zhusu)){
					contentStr = cont.replaceAll("^[\u4E00-\u9FA5]*[  ]?,[  ]?", "");
				}
				pw.println("\t\t"+contentStr);
				
			}
			pw.println("\t</"+tagName+">");
			pw.flush();

		}
	}
	
	

}
