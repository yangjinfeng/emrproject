package wi.annotator;

public class Relation {
	private Entity ent1;
	private Entity ent2;
	private String relationType;
	public Entity getEnt1() {
		return ent1;
	}
	public void setEnt1(Entity ent1) {
		this.ent1 = ent1;
	}
	public Entity getEnt2() {
		return ent2;
	}
	public void setEnt2(Entity ent2) {
		this.ent2 = ent2;
	}
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	
	public static Relation createBySaveStr(String saveStr){
		String[] fields = saveStr.split("\\|\\|");
		Entity entity1 = Entity.createBySaveStr(fields[0]);
		Entity entity2 = Entity.createBySaveStr(fields[2]);
		String type = fields[1].substring(fields[1].indexOf("=")+1);
		Relation rel = new Relation();
		rel.setEnt1(entity1);
		rel.setEnt2(entity2);
		rel.setRelationType(type);
		return rel;
	}
	
	public String toSave(){
		return ent1.toRelationSave() +"||"+"R="+relationType+"||"+ent2.toRelationSave();
	}

}
