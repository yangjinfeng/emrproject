package wi.annotator;

import java.util.HashMap;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

public class AssertTypeComboxModel extends DefaultComboBoxModel {
	private HashMap<String, Vector<String> > content = new HashMap<String, Vector<String> >();
	
	private String condition = "problem";//treatment
	
	public AssertTypeComboxModel(){
		super();
		setCondition(condition);
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
		removeAllElements();
		if(condition.equals("problem")){
			for(TypeColor tc : TypeColorMap.getAssertTypeArray()){
				addElement(tc);
			}
		}else if(condition.equals("treatment")){
			for(TypeColor tc : TypeColorMap.getTAssertTypeArray()){
				addElement(tc);
			}
		}
	}
	
	
	

}
