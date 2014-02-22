package wi.annotator;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;

public class TypeColorMap {
	private  static LinkedHashMap<String, TypeColor> entityMap = new LinkedHashMap<String, TypeColor>();
	private static LinkedHashMap<String, TypeColor> assertMap = new LinkedHashMap<String, TypeColor>();
	private static LinkedHashMap<String, TypeColor> relationMap = new LinkedHashMap<String, TypeColor>();
	private static LinkedHashMap<String, TypeColor> tassertMap = new LinkedHashMap<String, TypeColor>();
	
	static{
		
		try {
			fillMapFromCfg(entityMap,"config/entitytype.properties");
			fillMapFromCfg(assertMap,"config/asserttype.properties");
			fillMapFromCfg(relationMap,"config/relationtype.properties");
			fillMapFromCfg(tassertMap,"config/tasserttype.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private static void fillMapFromCfg(HashMap<String, TypeColor> map,String cfgfile)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(cfgfile),"UTF-8"));
		String line = null;
		while((line = br.readLine())!=null){
			if(line.startsWith("#")){
				continue;
			}
			String[] kv = line.split("=");
			
			String typeid = kv[0];
			String[] values = kv[1].split(",");
			TypeColor tc = new TypeColor();
			tc.setTypeId(typeid);
			tc.setTypeName(values[0]);
			tc.setFlag(Integer.valueOf(values[2]));			
			String colorStr = values[1];
			Color color = new Color(Integer.parseInt(colorStr.substring(0, 2), 16),
						Integer.parseInt(colorStr.substring(2, 4), 16),
						Integer.parseInt(colorStr.substring(4, 6), 16));
			tc.setColor(color);
			map.put(typeid, tc);
		}
	}
	
	public static TypeColor getType(String typeId){
		if(entityMap.containsKey(typeId)){
			return entityMap.get(typeId);
		}else if(assertMap.containsKey(typeId)){
			return assertMap.get(typeId);
		}else if(relationMap.containsKey(typeId)){
			return relationMap.get(typeId);
		}else if(tassertMap.containsKey(typeId)){
			return tassertMap.get(typeId);
		}
		return null;
	} 
	
	public static TypeColor[] getEntityTypeArray(){
		return entityMap.values().toArray(new TypeColor[0]);
	}
	
	public static TypeColor[] getAssertTypeArray(){
		return assertMap.values().toArray(new TypeColor[0]);
	}
	public static TypeColor[] getTAssertTypeArray(){
		return tassertMap.values().toArray(new TypeColor[0]);
	}
	
	public static TypeColor[] getRelationTypeArray(){
		return relationMap.values().toArray(new TypeColor[0]);
	}
	
	public static void main(String[] args) {
		System.out.println(TypeColorMap.getType("DIS"));
	}

}
