package wi.annotator;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ComboxRender extends DefaultListCellRenderer implements ListCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isForeground;
	

	public ComboxRender(boolean isForeground){
		this.isForeground = isForeground;
	}
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		Component result = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		TypeColor tc = (TypeColor)value;
		if(result != null && tc != null){
			if(isForeground){
				result.setForeground(tc.getColor());
			}else{
				result.setBackground(tc.getColor());
			}
		}
		return result;
	}

}
