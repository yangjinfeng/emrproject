package wi.annotator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EntityCleaner {
	private static Character[] fuhao = new Character[]{' ','	','.',',','"','\'',':',';','¡¢','£»','¡¢','¡°','¡±'};
	private static Set<Character> fuhaoset = new HashSet<Character>(Arrays.asList(fuhao));
	
	
	public static Entity cleanEntity(Entity entity){
		String oldStr = entity.getEntity();
		String newStr = oldStr.trim();
		int startIndex = 0,endIndex = newStr.length();
		for(int i = 0;i < newStr.length(); i ++){
			if(fuhaoset.contains(newStr.charAt(i))){
				startIndex = i + 1;
			}else{
				break;
			}
		}
		for(int i = newStr.length() - 1;i >= 0; i --){
			if(fuhaoset.contains(newStr.charAt(i))){
				endIndex = i;
			}else{
				break;
			}
		}
		
		newStr = newStr.substring(startIndex,endIndex);
		
		int startpos = oldStr.indexOf(newStr);
		int endpos = startpos + newStr.length();
		
		int pos1 = entity.getStartPos() + startpos;
		int pos2 = entity.getStartPos() + endpos;
		
		entity.setEntity(newStr);
		entity.setStartPos(pos1);
		entity.setEndPos(pos2);
		return entity;
	}
	
	public static void main(String[] args) {
		Entity ent = new Entity();
		ent.setEntity(" yangjifnng\", \"");
		ent.setStartPos(12);
		ent.setEndPos(24);
		
		Entity e = EntityCleaner.cleanEntity(ent);
		System.out.println(e.getEntity());
		System.out.println(e.getStartPos());
		System.out.println(e.getEndPos());
		
//		System.out.println(fuhao.length);
//		System.out.println(fuhaoset.contains(','));
	}

}
