package wi.emr.review;

import java.util.ArrayList;

public class EmrNameConst {
	
	public static String discharge_CH_Name = "出院小结";
	public static String progress_CH_Name = "病程记录";
	public static String discharge_EN_Name = "discharge";
	public static String progress_EN_Name = "progress";
	
	//病程记录部分
	public static String zhusu = "主诉";
	public static String binglitedian = "病例特点";
	public static String linchuangchubuzhenduan = "临床初步诊断";
	public static String zhenduanyiju = "诊断依据";
	public static String jianbiezhenduan = "鉴别诊断";
	public static String zhenliaojihua = "诊疗计划";
	
	
	//出院小结部分
	
	public static String huanzhexinxi = "患者信息";
	public static String zhuyuanqizhiri = "住院起止日";
	public static String menzhenshouzhizhenduan = "门诊收治诊断";
//	public static String linchuangchubuzhenduan = "临床初步诊断";
	public static String linchuangquedingzhenduan = "临床确定诊断";
	public static String ruyuanshiqingkuang = "入院时情况";
	public static String zhiliaojingguo = "治疗经过";
	public static String chuyuanshiqingkuang = "出院时情况";
	public static String zhiliaoxiaoguo = "治疗效果";
	public static String chuyuanyizhu = "出院医嘱";
	
	public static String[] progressSectionNames = new String[]{
//		zhusu,
		binglitedian,
		linchuangchubuzhenduan,
		zhenduanyiju,
		jianbiezhenduan,
		zhenliaojihua		
	};
	
	public static String[] dischargeSectionNames = new String[]{
//		huanzhexinxi,
//		zhuyuanqizhiri,
		menzhenshouzhizhenduan,
		linchuangchubuzhenduan,
		linchuangquedingzhenduan,
		ruyuanshiqingkuang,
		zhiliaojingguo,
		chuyuanshiqingkuang,
		zhiliaoxiaoguo,
		chuyuanyizhu
	};
	
	public  static String[] validateSections(String[] sectionNames,ArrayList<String> existNames){
		ArrayList<String> absence = new ArrayList<String>();
		for(String name : sectionNames){
			if(!existNames.contains(name)){
				absence.add(name);
			}
		}
		return absence.toArray(new String[absence.size()]);
	}
	
	
	
	public static String[] sectionNames = new String[]{binglitedian,
														linchuangchubuzhenduan,
														zhenduanyiju,
														jianbiezhenduan,
														zhenliaojihua,
														huanzhexinxi,
														zhuyuanqizhiri,
														menzhenshouzhizhenduan,
														linchuangquedingzhenduan,
														ruyuanshiqingkuang,
														zhiliaojingguo,
														chuyuanshiqingkuang,
														zhiliaoxiaoguo,
														chuyuanyizhu,
														};
}
