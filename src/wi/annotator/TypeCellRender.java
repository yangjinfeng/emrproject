package wi.annotator;

import java.awt.Component;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class TypeCellRender extends DefaultTableCellRenderer{
	private boolean isForeground;


	public TypeCellRender(boolean isForeground){
		this.isForeground = isForeground;
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		Component render = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if(value != null){
			if(isForeground){
				render.setForeground(((TypeColor)value).getColor());
			}else{
				render.setBackground(((TypeColor)value).getColor());
			}
		}else{
			render.setBackground(Color.WHITE);
		}
		return render;
	}

}
