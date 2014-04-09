package wi.annotator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JTable;

public class RelationTypeItemListener implements FocusListener {

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		TypeColor tc = (TypeColor)combox.getSelectedItem();
		if(tc == null){
			return;
		}
		String generalType = TypeColorMap2.getGeneralType(tc.getTypeId());
		RelationTypeCellEditor editor = (RelationTypeCellEditor)table.getColumn("关系类型").getCellEditor();
		int row = editor.getRow();
		int col = editor.getColumn();
		NewRelation nr = GlobalComponent.relationList.get(row);
		
		String condition = nr.getEnts1Type() + nr.getEnts2Type();
		String gt = TypeColorMap2.getGeneralTypeByRule(condition);
		if(!gt.equals(generalType)){
			RelationTypeComboxModel model = (RelationTypeComboxModel)combox.getModel();
			model.setCondition(condition);
			combox.setModel(model);
			table.setValueAt(null, row,  col);
//			combox.setSelectedIndex(-1);
		}


	}
	private JTable table;
	private JComboBox combox;
	
	
	public RelationTypeItemListener(JTable table,JComboBox combox) {
		this.table = table;
		this.combox = combox;
	}


	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

	

}
