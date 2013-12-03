package wi.emr.review;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class NewEmrFormater{
	
	//出院  山院
	//出现  山现
	//0 o

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
//		new NewEmrFormater().extractEmr("C:\\Users\\yangjinfeng\\Desktop\\evaluation\\WangCongjian\\WangCongjian", "C:\\Users\\yangjinfeng\\Desktop\\output");
//		new EmrFormater().extractEmr("F:\\dianzibingli\\EMR\\review\\renzhenyu_finished", "C:\\Users\\yangjinfeng\\Desktop\\export");
//		new EmrFormater().extractEmr("C:\\Users\\yangjinfeng\\Desktop\\liuzhiguang_finished_1", "C:\\Users\\yangjinfeng\\Desktop\\export");
//		new EmrFormater().exportDischargeToXml(new File("F:\\dianzibingli\\EMR\\review\\zhaofangfang_finished\\H913255\\出院小结11.txt"), new File("chuyuanxiaojie.xml"),"GB2312");
//		new EmrFormater().exportProgressToXml(new File("yangjinfeng/0714648/病程记录.txt"), new File("bingchengjilu.xml"));
//		new NewEmrFormater().extractAllEmr("F:\\dianzibingli\\EMR\\review", "F:\\dianzibingli\\EMR\\xmlemr");
		if(args== null || args.length !=2){
			throw new Exception("参数必须两个，一个是要处理的病历所在文件夹，一个是输出文件夹");
		}
//		new NewEmrFormater().extractEmr(args[0],args[1]);
		new NewEmrFormater().extractAllEmr(args[0],args[1]);
		
	}
	
	public void extractAllEmr(String emrdir,String exportdir)throws Exception{
		File emrroot = new File(emrdir);
		for(File userdir : emrroot.listFiles()){
			extractEmr(userdir.getPath(),exportdir);
		}
	}
	
	
	
	
	public void extractEmr(String userdir,String exportdir)throws Exception{
		String charset = "UTF-8";
		File userfile = new File(userdir);
		String username = userfile.getName();
		new File(exportdir+"/"+username).mkdir();
		
		for(File file : userfile.listFiles()){
			String filename = file.getName();
			String newFilename = exportdir+"/"+filename.replace(".txt", ".xml");
			exportToXml(username,file,newFilename,charset);			
		}
	}
	
	public static void main1(String[] args)throws Exception {
		
		
		NewEmrFormater n = new NewEmrFormater();
		
		n.exportToXml("",new File("C:\\Users\\yangjinfeng\\Desktop\\evaluation\\WangCongjian\\WangCongjian\\7160838483progress.txt"),"","UTF-8");	
	}
	
	
	public void exportToXml(String userdir,File txtfile,String xmlFile,String charset)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(txtfile),charset));
		String line = null;
//		int lineNumber = 0;
		
		Block currentBlock = null;
		ArrayList<String> sectionNames = new ArrayList<String>();
		ArrayList<Block> blocks = new ArrayList<Block>(); 
		
		while((line = br.readLine()) != null){
			if(line.length() == 0){
				continue;
			}
			line = CharConverter.instance.convert(line);
			line = line.trim();
			if(ignore(line)){
				continue;
			}
			if(!isBlockBegin(line)){
				if(currentBlock != null){
					currentBlock.addContent(line);
				}
			}else{
				if(currentBlock != null){
//					currentBlock.output(pw);
					blocks.add(currentBlock);
					currentBlock = null;
				}
				//					line =line.trim();
				if(matchSectionName(line,EmrNameConst.binglitedian)){//
					currentBlock = new Block(EmrNameConst.binglitedian,line,true);
					sectionNames.add(EmrNameConst.binglitedian);

				}else if(matchSectionName(line,EmrNameConst.linchuangchubuzhenduan)){
					currentBlock = new Block(EmrNameConst.linchuangchubuzhenduan,line,true);
					sectionNames.add(EmrNameConst.linchuangchubuzhenduan);

				}else if(matchSectionName(line,EmrNameConst.zhenduanyiju)){
					currentBlock = new Block(EmrNameConst.zhenduanyiju,line,true);
					sectionNames.add(EmrNameConst.zhenduanyiju);

				}else if(matchSectionName(line,EmrNameConst.jianbiezhenduan)){
					currentBlock = new Block(EmrNameConst.jianbiezhenduan,line,true);
					sectionNames.add(EmrNameConst.jianbiezhenduan);

				}else if(matchSectionName(line,EmrNameConst.zhenliaojihua)){
					currentBlock = new Block(EmrNameConst.zhenliaojihua,line,true);
					sectionNames.add(EmrNameConst.zhenliaojihua);

				}else if(matchSectionName(line,EmrNameConst.ruyuanriqi)){//门诊收治诊断   从这儿开始是出院小结的
					currentBlock = new Block(EmrNameConst.zhuyuanqizhiri,line,false);
					sectionNames.add(EmrNameConst.zhuyuanqizhiri);
				}else if(matchSectionName(line,EmrNameConst.menzhenshouzhizhenduan)){
					currentBlock = new Block(EmrNameConst.menzhenshouzhizhenduan,line,true);
					sectionNames.add(EmrNameConst.menzhenshouzhizhenduan);
					
				}else if(matchSectionName(line,EmrNameConst.linchuangquedingzhenduan)){
					currentBlock = new Block(EmrNameConst.linchuangquedingzhenduan,line,true);
					sectionNames.add(EmrNameConst.linchuangquedingzhenduan);
					
				}else if(matchSectionName(line,EmrNameConst.ruyuanshiqingkuang)){
					line = CharConverter.instance.trim(line);
					currentBlock = new Block(EmrNameConst.ruyuanshiqingkuang,line,true);
					sectionNames.add(EmrNameConst.ruyuanshiqingkuang);
					
				}else if(matchSectionName(line,EmrNameConst.zhiliaojingguo)){
					line = CharConverter.instance.trim(line);
					currentBlock = new Block(EmrNameConst.zhiliaojingguo,line,true);
					sectionNames.add(EmrNameConst.zhiliaojingguo);
					
				}else if(matchSectionName(line,EmrNameConst.chuyuanshiqingkuang)){
					line = CharConverter.instance.trim(line);
					currentBlock = new Block(EmrNameConst.chuyuanshiqingkuang,line,true);
					sectionNames.add(EmrNameConst.chuyuanshiqingkuang);
					
				}else if(matchSectionName(line,EmrNameConst.zhiliaoxiaoguo)){
					line = CharConverter.instance.trim(line);
					currentBlock = new Block(EmrNameConst.zhiliaoxiaoguo,line,true);
					sectionNames.add(EmrNameConst.zhiliaoxiaoguo);
					
				}else if(matchSectionName(line,EmrNameConst.chuyuanyizhu)){
//					line = CharConverter.instance.trim(line);
					currentBlock = new Block(EmrNameConst.chuyuanyizhu,line,true);
					sectionNames.add(EmrNameConst.chuyuanyizhu);
				}
			}
			//			}
		}
		if(currentBlock != null){
//			currentBlock.output(pw);
			blocks.add(currentBlock);
			currentBlock = null;
		}
		

		boolean isDischarge = isDischarge(sectionNames);
		String oldxmlFilename = xmlFile;
		if(isDischarge){
			if(xmlFile.contains(EmrNameConst.progress_EN_Name)){
				xmlFile = xmlFile.replace(EmrNameConst.progress_EN_Name, EmrNameConst.discharge_EN_Name);
			}
		}else{
			if(xmlFile.contains(EmrNameConst.discharge_EN_Name)){
				xmlFile = xmlFile.replace(EmrNameConst.discharge_EN_Name, EmrNameConst.progress_EN_Name);
			}
		}
		PrintWriter pw = new PrintWriter(xmlFile,"UTF-8");
		pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.println(isDischarge? "<discharge>" : "<progress>");
		for(Block blck : blocks){
			blck.output(pw);
		}		
		pw.println(isDischarge? "</discharge>" : "</progress>");
		pw.close();
		
		String[] absence = null;
		if(isDischarge){
			absence = EmrNameConst.validateSections(EmrNameConst.dischargeSectionNames, sectionNames);
		}else{
			absence = EmrNameConst.validateSections(EmrNameConst.progressSectionNames, sectionNames);
		}
		if(absence.length > 0 ){
			PrintWriter errpw = new PrintWriter(createErrFilename(new File(oldxmlFilename),userdir),"GBK");
			for(String name : absence){
				errpw.println("缺失 ："+name);
			}
			errpw.close();
		}
	}
	
	
	public boolean isDischarge(ArrayList<String> sectionNames){
		boolean result = false;
		for(String sectionname : EmrNameConst.dischargeSectionNames){
			if(sectionname.equals(EmrNameConst.linchuangchubuzhenduan)){
				continue;
			}
			if(sectionNames.contains(sectionname)){
				result = true;
			}
		}
		return result;
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
	
	public boolean matchSectionName(String line,String sectionName){
//		System.out.println(EmrNameConst.toSectionNameRegex(sectionName));
		
		return line.matches(EmrNameConst.toSectionNameRegex(sectionName)+".*");
	}
	
	
	boolean isBlockBegin(String line){
		for(String name : EmrNameConst.sectionNames){
			if(matchSectionName(line,name)){
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
				addContent(cont.replaceFirst(EmrNameConst.toSectionNameRegex(tag) + "[ \t:]*", ""));
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
