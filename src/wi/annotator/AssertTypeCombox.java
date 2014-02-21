package wi.annotator;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class AssertTypeCombox extends JComboBox {

	private JTable table;
	
	
	public AssertTypeCombox(JTable table) {
		super();
		this.table = table;
		setModel(new AssertTypeComboxModel());
	}


//	@Override
//	public void showPopup() {
//		// TODO Auto-generated method stub
//		System.out.println("showPopup");
//		int row = table.getSelectedRow();
//		int entitytypecolumn = table.getColumnModel().getColumnIndex("¿‡–Õ");
//		TypeColor tc = (TypeColor)table.getValueAt(row, entitytypecolumn);
//		if(tc.getFlag() == 1){
////    		DefaultCellEditor editor = (DefaultCellEditor)table.getCellEditor(row, table.getColumnModel().getColumnIndex("–ﬁ Œ"));
////    		AssertTypeComboxModel model = (AssertTypeComboxModel)((JComboBox)editor.getComponent()).getModel();
//			AssertTypeComboxModel model = (AssertTypeComboxModel)getModel();
//    		if(tc.getTypeId().equals("treatment")){
//    			model.setCondition("treatment");
//    		}else{
//    			model.setCondition("problem");
//    		}
//    	}
//		
//		super.showPopup();
//	}
//	
	

}
