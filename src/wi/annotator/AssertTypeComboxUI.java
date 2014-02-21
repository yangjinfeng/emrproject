package wi.annotator;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class AssertTypeComboxUI extends BasicComboBoxUI {

	private JTable table;
	
	
	
	public AssertTypeComboxUI(JTable table) {
		super();
		this.table = table;
	}



	@Override
	protected JButton createArrowButton() {
		// TODO Auto-generated method stub
		JButton arrowButton = super.createArrowButton();
		
		arrowButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("showPopup");
				int row = table.getSelectedRow();
				int entitytypecolumn = table.getColumnModel().getColumnIndex("¿‡–Õ");
				TypeColor tc = (TypeColor)table.getValueAt(row, entitytypecolumn);
				if(tc.getFlag() == 1){
					AssertTypeComboxModel model = (AssertTypeComboxModel)comboBox.getModel();
		    		if(tc.getTypeId().equals("treatment")){
		    			model.setCondition("treatment");
		    		}else{
		    			model.setCondition("problem");
		    		}
		    	}
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		
		return   arrowButton;
		
	}
	
	 
	
	

}
