package wi.emr.review;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		String str = "姓名: 高淑霞 性别: 女 年龄: 62岁 入院科别: 神经内科四病房";
//		String regex = "姓名:[  ]?[\u4E00-\u9FA5]*[  ]";
		String str = "张少杰, 男性,47岁, 因 \"阵发性头晕2年半余. 加重一天\", 于2012年02月27日由们诊收入中医科病房";
		String regex = "^[\u4E00-\u9FA5]*,";
		
//		String regex2 = "^"
		System.out.println(str.replaceAll(regex, ""));
		
		
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		while(m.find()){
			System.out.println(m.group());
			System.out.println(m.replaceFirst(""));
		}
	}

}
