package wi.annotator;

import java.util.ArrayList;

public class NewRelation {
	private ArrayList<Entity> ents1 = new ArrayList<Entity>();
	private ArrayList<Entity> ents2 = new ArrayList<Entity>();
	private String relationType;	
	
	
	public ArrayList<Entity> getEnts1() {
		return ents1;
	}
	public void setEnts1(ArrayList<Entity> ents1) {
		this.ents1 = ents1;
	}
	public ArrayList<Entity> getEnts2() {
		return ents2;
	}
	public void setEnts2(ArrayList<Entity> ents2) {
		this.ents2 = ents2;
	}
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	
	/**
	 * E={xxx¡¾p1-p2¡¿type;xxx¡¾¡¿type}||R=xxx||E={xxx¡¾p1-p2¡¿type;xxx¡¾¡¿type}
	 * @param saveStr
	 * @return
	 */
	public static Relation createBySaveStr(String saveStr){
		Relation relation = new Relation();
		String[] fields = saveStr.split("\\|\\|");
		String  entityStr1 = fields[0].substring(fields[0].indexOf("{")+1, fields[0].indexOf("}"));
		String  entityStr2 = fields[2].substring(fields[2].indexOf("{")+1, fields[2].indexOf("}"));
		createEntsByStr(relation.getEnts1(),entityStr1);
		createEntsByStr(relation.getEnts2(),entityStr2);
		
		String type = fields[1].substring(fields[1].indexOf("=")+1);
		relation.setRelationType(type);
		return relation;
	}
	
	public String getEnts1Type(){
		return getEnts1().get(0).getEntityType();
	}
	public String getEnts2Type(){
		return getEnts2().get(0).getEntityType();
	}
	
	public String ents1ToAnnotation(){
		StringBuffer sb = new StringBuffer();
		for(Entity ent : getEnts1()){
			sb.append(ent.getEntity()+"£»");
		}
		return sb.toString();
	}
	public String ents2ToAnnotation(){
		StringBuffer sb = new StringBuffer();
		for(Entity ent : getEnts2()){
			sb.append(ent.getEntity()+"£»");
		}
		return sb.toString();
	}
	
	private static void createEntsByStr(ArrayList<Entity> ents, String entstr){
		String[] strs = entstr.split(";");
		for(String str : strs){
			if(str.length() > 0){
				ents.add(Entity.createByRelSaveStr(str));
			}
		}
	}
	
	public String toSave(){
		StringBuffer sb1 = new StringBuffer();
		for(Entity ent : ents1){
			sb1.append(ent.toRelationSave()+";");
		}
		StringBuffer sb2 = new StringBuffer();
		for(Entity ent : ents2){
			sb2.append(ent.toRelationSave()+";");
		}
		return "E={"+ sb1.toString() +"}||"+"R="+relationType+"||"+"E={"+ sb2.toString()+"}";
	}

}
