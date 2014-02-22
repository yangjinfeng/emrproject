package wi.annotator;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class AssertTypeMouseListener extends MouseAdapter {

	private JTable table;
	private JComboBox combox;
	
	
	public AssertTypeMouseListener(JTable table,JComboBox combox) {
		this.table = table;
		this.combox = combox;
	}
	
	

	

	@Override
	public void mouseClicked(MouseEvent e) {
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
    	}
	}








}
