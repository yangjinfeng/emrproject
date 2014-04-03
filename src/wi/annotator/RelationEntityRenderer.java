package wi.annotator;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RelationEntityRenderer  extends DefaultTableCellRenderer{

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		 setToolTipText((String)table.getValueAt(row, column));
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus,row, column);
	}
	
	

}
