package wi.annotator;

import java.util.ArrayList;
import java.util.Collections;

public class NewRelation implements Comparable<NewRelation>{
	private ArrayList<Entity> ents1 = new ArrayList<Entity>();
	private ArrayList<Entity> ents2 = new ArrayList<Entity>();
	private String relationType;	
	private boolean qst=false; 
	
	
	public boolean isQst() {
		return qst;
	}
	public void setQst(boolean qst) {
		this.qst = qst;
	}
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
	 * E={xxx¡¾p1-p2¡¿type;xxx¡¾¡¿type}||R=xxx||E={xxx¡¾p1-p2¡¿type;xxx¡¾¡¿type}||Q=true
	 * @param saveStr
	 * @return
	 */
	public static NewRelation createBySaveStr(String saveStr){
		NewRelation relation = new NewRelation();
		String[] fields = saveStr.split("\\|\\|");
//		if(fields.length == 1){
//			String  entityStr1 = fields[0].substring(fields[0].indexOf("{")+1, fields[0].indexOf("}"));
//			createEntsByStr(relation.getEnts1(),entityStr1);
//		}else{
//			
//			String  entityStr1 = fields[0].substring(fields[0].indexOf("{")+1, fields[0].indexOf("}"));
//			String  entityStr2 = fields[2].substring(fields[2].indexOf("{")+1, fields[2].indexOf("}"));
//			createEntsByStr(relation.getEnts1(),entityStr1);
//			createEntsByStr(relation.getEnts2(),entityStr2);
//			
//			String type = fields[1].substring(fields[1].indexOf("=")+1);
//			relation.setRelationType(type);
//		}
		
		for(String field : fields){
			if(field.startsWith("E")){
				if(relation.getEnts1().size() == 0){
					String  entityStr1 = field.substring(field.indexOf("{")+1, field.indexOf("}"));
					createEntsByStr(relation.getEnts1(),entityStr1);
					continue;
				}
				if(relation.getEnts2().size() == 0){
					String  entityStr2 = field.substring(field.indexOf("{")+1, field.indexOf("}"));
					createEntsByStr(relation.getEnts2(),entityStr2);
				}
			}else if(field.startsWith("R")){
				String type = field.substring(field.indexOf("=")+1);
				relation.setRelationType(type);
			}else if(field.startsWith("Q")){
				String qst = field.substring(field.indexOf("=")+1);
				relation.setQst(Boolean.valueOf(qst));
			}
		}
		
		return relation;
	}
	
	public static void main(String[] args) {
//		String ss = "E={xxx¡¾1-2¡¿type;xxx¡¾3-4¡¿type}||R=www||E={yyy¡¾3-4¡¿type;yyy¡¾3-4¡¿type}";
		String ss = "E={xxx¡¾1-2¡¿type;xxx¡¾3-4¡¿type}";
		NewRelation r = NewRelation.createBySaveStr(ss);
		System.out.println(r.toSave());
	}
	
	public void addEnts1(ArrayList<Entity> es1){
		ents1.addAll(es1);
		Collections.sort(ents1);
	}
	public void addEnts2(ArrayList<Entity> es2){
		ents2.addAll(es2);
		Collections.sort(ents2);
	}
	
	public String getEnts1Type(){
		return getEnts1().size() > 0 ? getEnts1().get(0).getEntityType() : "";
	}
	public String getEnts2Type(){
		return getEnts2().size() > 0 ? getEnts2().get(0).getEntityType() : "";
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
		String qst = "";
		if(isQst()){
			qst = "||Q="+isQst();
		}
		if(ents2.size() == 0){
			return "E={"+ sb1.toString() +"}"+qst;
		}
		return "E={"+ sb1.toString() +"}||"+"R="+relationType+"||"+"E={"+ sb2.toString()+"}"+qst;
	}
	
	public Entity[] toAllEntity(){
		ArrayList<Entity> all = new ArrayList<Entity>();
		all.addAll(ents1);
		all.addAll(ents2);
		return all.toArray(new Entity[all.size()]);
	}
	
	public boolean  existRelation(){
		boolean exist = TypeColorMap2.existRelation(getEnts1Type()+getEnts2Type());
		if(!exist){
			exist = TypeColorMap2.existRelation(getEnts2Type()+getEnts1Type());
			if(exist){
				ArrayList<Entity> tt = ents1;
				ents1 = ents2;
				ents2 = tt;
			}
		}
		return exist;
	}
	@Override
	public int compareTo(NewRelation o) {
		// TODO Auto-generated method stub
		return this.getEnts1().get(0).getStartPos() - o.getEnts1().get(0).getStartPos();
	}
	

}
