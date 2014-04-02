package wi.annotator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JTable;

public class RelationTypeListener extends MouseAdapter {

	private JTable table;
	private JComboBox combox;
	
	
	public RelationTypeListener(JTable table,JComboBox combox) {
		this.table = table;
		this.combox = combox;
	}
	
	

	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		NewRelation nr = GlobalComponent.relationList.get(row);
		String condition = nr.getEnts1Type() + nr.getEnts2Type();

		RelationTypeComboxModel model = (RelationTypeComboxModel)combox.getModel();
		model.setCondition(condition);
		combox.setModel(model);
	}

}
