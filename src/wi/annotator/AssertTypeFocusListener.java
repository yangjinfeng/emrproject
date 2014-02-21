package wi.annotator;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class AssertTypeFocusListener implements FocusListener {

	private JTable table;
	private JComboBox combox;
	
	
	public AssertTypeFocusListener(JTable table,JComboBox combox) {
		this.table = table;
		this.combox = combox;
	}

	

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		int entitytypecolumn = table.getColumnModel().getColumnIndex("¿‡–Õ");
		TypeColor tc = (TypeColor)table.getValueAt(row, entitytypecolumn);
		if(tc.getFlag() == 1){
			AssertTypeComboxModel model = (AssertTypeComboxModel)combox.getModel();
    		if(tc.getTypeId().equals("treatment")){
    			model.setCondition("treatment");
    		}else{
    			model.setCondition("problem");
    		}
    		combox.setModel(model);
    		combox.showPopup();
    	}
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}



}
