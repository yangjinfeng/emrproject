package wi.annotator;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class TypeColorMap2 {
	private  static LinkedHashMap<String, TypeColor> entityMap = new LinkedHashMap<String, TypeColor>();
	private static LinkedHashMap<String, TypeColor> assertMap = new LinkedHashMap<String, TypeColor>();
	private static LinkedHashMap<String, TypeColor> relationMap = new LinkedHashMap<String, TypeColor>();
	private static HashMap<String,LinkedHashMap<String, TypeColor> >  relationMap2 = new HashMap<String,LinkedHashMap<String, TypeColor> >();
	private static LinkedHashMap<String, TypeColor> tassertMap = new LinkedHashMap<String, TypeColor>();
	private static HashMap<String,String> relationRuleMap = new HashMap<String,String>();
	
	static{
		
		try {
			fillMapFromCfg(entityMap,"config/entitytype.properties");
			fillMapFromCfg(assertMap,"config/asserttype.properties");
//			fillMapFromCfg(relationMap,"config/relationtype.properties");
			fillRelationMapFromCfg(relationMap2,"config/relationtype.properties");
			fillMapFromCfg(tassertMap,"config/tasserttype.properties");
			fillRelationRule(relationRuleMap,"config/relationrule.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void fillRelationRule(HashMap<String,String> relationRuleMap,String file)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
		String line = null;
		while((line = br.readLine())!=null){
			if(line.startsWith("#")){
				continue;
			}
			String[] strs = line.split(",");
			relationRuleMap.put(strs[0]+strs[1], strs[2]);
		}
		br.close();
	}
	
	
	private static void fillRelationMapFromCfg(HashMap<String,LinkedHashMap<String, TypeColor> > map,String cfgfile)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(cfgfile),"UTF-8"));
		String line = null;
		while((line = br.readLine())!=null){
			if(line.startsWith("#")){
				continue;
			}
			String[] kv = line.split("=");
			
			String typeid = kv[0];
			String generaltype = typeid.substring(0,typeid.indexOf(":"));
			String specifictype = typeid.substring(typeid.indexOf(":")+1);
			
			String[] values = kv[1].split(",");
			TypeColor tc = new TypeColor();
			tc.setTypeId(specifictype);
			tc.setTypeName(values[0]);
			tc.setFlag(Integer.valueOf(values[2]));			
			String colorStr = values[1];
			Color color = new Color(Integer.parseInt(colorStr.substring(0, 2), 16),
						Integer.parseInt(colorStr.substring(2, 4), 16),
						Integer.parseInt(colorStr.substring(4, 6), 16));
			tc.setColor(color);
			if(!map.containsKey(generaltype)){
				map.put(generaltype, new LinkedHashMap<String, TypeColor>());
			}
			map.get(generaltype).put(specifictype, tc);
			
		}
		for(String key:map.keySet()){
			relationMap.putAll(map.get(key));
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
	
	public static TypeColor[] getRelationTypes(String enttype1and2){
		if(!existRelation(enttype1and2)){
			return null;
		}
		return getRelationType2Array(relationRuleMap.get(enttype1and2));
	}
	
	public static TypeColor[] getRelationType2Array(String generalType){
		
		return relationMap2.get(generalType).values().toArray(new TypeColor[0]);
	}
	
	public static boolean existRelation(String enttype1and2){
		return relationRuleMap.containsKey(enttype1and2);
	}
	
	public static void main(String[] args) {
		for(TypeColor tc:getRelationType2Array("Tr2D")){
			System.out.println(tc);
		}
	}

}
