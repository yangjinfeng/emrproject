package wi.annotator;

import java.util.ArrayList;
import java.util.Arrays;

public class Entity implements Comparable<Entity>{
	
	private String entity;
	private int startPos;
	private int endPos;
	private String entityType;
	private String assertType;
	private int diff = 0;
	private boolean qst;
	
	public static String ENT_START_POS_CHAR = "¡¾";
	public static String ENT_END_POS_CHAR = "¡¿";
	
	public static void main(String[] args) {
//		String ss = "C=Ã÷ÏÔ±ä»¯ P=369:373 T=complaintsymptom A=absent";
//		Entity e = createBySaveStr(ss);
		String[] ss = ".xml,.qst,.ent".split(",");
		for(String s : ss){
			
			System.out.println(s);
		}
	}
	
	
	public static Entity createBySaveStr(String saveStr){
		 Entity ent = new Entity();
		 int Pindex = saveStr.indexOf(" P=");
		 String entname = saveStr.substring(0, Pindex);
		 String other = saveStr.substring(Pindex+1);
		 ArrayList<String> fieldStrs = new ArrayList<String>();
		 fieldStrs.add(entname);
		 fieldStrs.addAll(Arrays.asList(other.split(" ")));
		 for(String field : fieldStrs){
			 String[] fn = field.split("=");
			 if(fn[0].equals("C")){
				 ent.setEntity(fn[1]);
			 }else if(fn[0].equals("P")){
				 String ps = fn[1];
				 ent.setStartPos(Integer.valueOf(ps.substring(0, ps.indexOf(":"))).intValue());
				 ent.setEndPos(Integer.valueOf(ps.substring(ps.indexOf(":") + 1)).intValue());
			 }else if(fn[0].equals("T")){
				 ent.setEntityType(fn[1]);
			 }else if(fn[0].equals("A")){
				 ent.setAssertType(fn[1]);
			 }else if(fn[0].equals("X")){
				 ent.setDiff(Integer.valueOf(fn[1]));
			 }else if(fn[0].equals("Q")){
				 ent.setQst(Boolean.valueOf(fn[1]));
			 }
		 }
		 return ent;
		 
//		    String[] fields = saveStr.split("(C=)|(P=)|(T=)|(A=)|(X=)|(Q=)");
//		    int index = 0;
//		    for (String field : fields) {
//		      String f = field.trim();
//		      if (f.length() != 0)
//		      {
//		        if (index == 0) {
//		          ent.setEntity(f);
//		        } else if (index == 1) {
//		          String ps = f;
//		          ent.setStartPos(Integer.valueOf(ps.substring(0, ps.indexOf(":"))).intValue());
//		          ent.setEndPos(Integer.valueOf(ps.substring(ps.indexOf(":") + 1)).intValue());
//		        } else if (index == 2) {
//		          ent.setEntityType(f);
//		        } else if (index == 3) {
//		        	if(ent.getEntityType().equals("test") || ent.getEntityType().equals("diseasetype") ){
//		        		if(f.equals("true")){
//		        			ent.setQst(true);
//		        		}else{
//		        			ent.setDiff(Integer.valueOf(f));
//		        		}
//		        	}else{
//		        		ent.setAssertType(f);
//		        	}
//		        }else if (index == 4) {
//		        	if( !(ent.getEntityType().equals("test") || ent.getEntityType().equals("diseasetype")) ){
//		        		if(f.equals("true")){
//		        			ent.setQst(true);
//		        		}else{
//		        			ent.setDiff(Integer.valueOf(f));
//		        		}
//		        	}
//		        }
//		        index++;
//		      }
//		    }
//		    return ent;
	}
	
	public int getDiff() {
		return diff;
	}

	public void setDiff(int diff) {
		this.diff = diff;
	}

	public static Entity createByAnnotationStr(String annotation){
		Entity ent = new Entity();
		String str = annotation.substring(annotation.indexOf(ENT_START_POS_CHAR)+1,annotation.indexOf(ENT_END_POS_CHAR));
		String entity = annotation.substring(0,annotation.indexOf(ENT_START_POS_CHAR));
		int p0 = Integer.valueOf(str.substring(0,str.indexOf("-")));
		int p1 = Integer.valueOf(str.substring(str.indexOf("-")+1));
		ent.setEntity(entity);
		ent.setStartPos(p0);
		ent.setEndPos(p1);
		return ent;
	}
	
	
	public boolean isQst() {
		return qst;
	}

	public void setQst(boolean qst) {
		this.qst = qst;
	}

	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public String getAssertType() {
		return assertType;
	}
	public void setAssertType(String assertType) {
		this.assertType = assertType;
	}
	
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public int getStartPos() {
		return startPos;
	}
	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}
	public int getEndPos() {
		return endPos;
	}
	public void setEndPos(int endPos) {
		this.endPos = endPos;
	}
	
	public String toSave(){
		String saveStr = "C=" + entity +" P="+startPos+":"+endPos+" T="+getEntityType();
		if(getAssertType() != null){
			saveStr = saveStr   +" A="+getAssertType();
		}
		if(getDiff() == 1 || getDiff() == 2){
			saveStr = saveStr   +" X="+getDiff();
		}
		if(isQst()){
			saveStr = saveStr   +" Q="+isQst();
		}
		return saveStr;
	}
	//xxx¡¾p1-p2¡¿type
	public String toRelationSave(){
		return toAnnotation() + getEntityType();
	}
	
	public static Entity createByRelSaveStr(String relSaveStr){
		Entity ent = createByAnnotationStr(relSaveStr);
//		String str = relSaveStr.substring(relSaveStr.indexOf(ENT_START_POS_CHAR)+1,relSaveStr.indexOf(ENT_END_POS_CHAR));
//		String entity = relSaveStr.substring(0,relSaveStr.indexOf(ENT_START_POS_CHAR));
//		int p0 = Integer.valueOf(str.substring(0,str.indexOf("-")));
//		int p1 = Integer.valueOf(str.substring(str.indexOf("-")+1));
//		ent.setEntity(entity);
//		ent.setStartPos(p0);
//		ent.setEndPos(p1);
		ent.setEntityType(relSaveStr.substring(relSaveStr.indexOf(ENT_END_POS_CHAR)+1));
		
		return ent;
	}
	
	public String toAnnotation(){
		return entity+ENT_START_POS_CHAR+startPos+"-"+endPos+ENT_END_POS_CHAR;
	}

	@Override
	public int compareTo(Entity o) {
		return this.getStartPos() - o.getStartPos();
	}


	@Override
	public boolean equals(Object obj) {
		Entity ent = (Entity)obj;
		
		return getStartPos() == ent.getStartPos() && getEndPos() == ent.getEndPos();
	}
	
	
	

}
