package wi.annotator;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class RelationTypeCellEditor extends DefaultCellEditor {
	private int row;
	private int column;
	
	public RelationTypeCellEditor(JComboBox comboBox) {
		super(comboBox);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
		this.row = row;
		this.column = column;
		return super.getTableCellEditorComponent(table, value, isSelected, row, column);
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
	
	

	
}
