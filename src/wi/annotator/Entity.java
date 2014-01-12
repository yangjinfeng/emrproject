package wi.annotator;

public class Entity implements Comparable<Entity>{
	
	private String entity;
	private int startPos;
	private int endPos;
	private String entityType;
	private String assertType;
	private int diff = 0;
	
	public static String ENT_START_POS_CHAR = "¡¾";
	public static String ENT_END_POS_CHAR = "¡¿";
	
	
	public static Entity createBySaveStr(String saveStr){
		 Entity ent = new Entity();
		    String[] fields = saveStr.split("(C=)|(P=)|(T=)|(A=)|(X=)");
		    int index = 0;
		    for (String field : fields) {
		      String f = field.trim();
		      if (f.length() != 0)
		      {
		        if (index == 0) {
		          ent.setEntity(f);
		        } else if (index == 1) {
		          String ps = f;
		          ent.setStartPos(Integer.valueOf(ps.substring(0, ps.indexOf(":"))).intValue());
		          ent.setEndPos(Integer.valueOf(ps.substring(ps.indexOf(":") + 1)).intValue());
		        } else if (index == 2) {
		          ent.setEntityType(f);
		        } else if (index == 3) {
		          ent.setAssertType(f);
		        }else if (index == 4) {
		          ent.setDiff(Integer.valueOf(f));
		        }
		        index++;
		      }
		    }
		    return ent;
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
		if(getDiff() == 1){
			saveStr = saveStr   +" X="+getDiff();
		}
		return saveStr;
	}
	
	public String toRelationSave(){
		return "C=" + entity +" P="+startPos+":"+endPos;
	}
	
	public String toAnnotation(){
		return entity+ENT_START_POS_CHAR+startPos+"-"+endPos+ENT_END_POS_CHAR;
	}

	@Override
	public int compareTo(Entity o) {
		return this.getStartPos() - o.getStartPos();
	}
	
	
	

}
