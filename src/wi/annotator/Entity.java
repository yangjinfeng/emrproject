package wi.annotator;

public class Entity implements Comparable<Entity>{
	
	private String entity;
	private int startPos;
	private int endPos;
	private String entityType;
	private String assertType;
	
	public static String ENT_START_POS_CHAR = "¡¾";
	public static String ENT_END_POS_CHAR = "¡¿";
	
	
	public static Entity createBySaveStr(String saveStr){
		Entity ent = new Entity();
		String[] fields = saveStr.split(" ");
		for(String field : fields){
			if(field.startsWith("C")){
				ent.setEntity(field.substring(field.indexOf("=")+1));
			}else if(field.startsWith("P")){
				String ps = field.substring(field.indexOf("=")+1);
				ent.setStartPos(Integer.valueOf(ps.substring(0,ps.indexOf(":"))));
				ent.setEndPos(Integer.valueOf(ps.substring(ps.indexOf(":")+1)));
			}else if(field.startsWith("T")){
				ent.setEntityType(field.substring(field.indexOf("=")+1));
			}else if(field.startsWith("A")){
				ent.setAssertType(field.substring(field.indexOf("=")+1));
			}
		}
		return ent;
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
