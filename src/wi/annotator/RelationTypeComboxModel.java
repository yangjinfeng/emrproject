package wi.annotator;

import javax.swing.DefaultComboBoxModel;

public class RelationTypeComboxModel  extends DefaultComboBoxModel {
	
	
	public RelationTypeComboxModel(){
		super();
		setCondition(null);
	}


	public void setCondition(String condition) {
		removeAllElements();
		if(condition == null){
			for(TypeColor tc : TypeColorMap2.getRelationTypeArray()){
				addElement(tc);
			}
		}else{
			TypeColor[] tcs = TypeColorMap2.getRelationTypes(condition);
			if(tcs == null){
				return;
			}
			for(TypeColor tc : tcs){
				addElement(tc);
			}
		}
	}
	
	
	

}
