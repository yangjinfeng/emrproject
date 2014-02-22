package wi.annotator;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class AsserttypeRender extends DefaultTableCellRenderer {
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		TypeColor asserttype = (TypeColor)value;
		if(asserttype != null){
			return super.getTableCellRendererComponent(table, asserttype.getTypeName(), isSelected, hasFocus, row, column);			
		}else{
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}
	}

}
