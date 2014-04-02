package wi.annotator;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

public class GlobalComponent {
	
	public static JButton addNEButton;
	public static JButton delNEButton;
	public static JPopupMenu entiyPopupmenu;
	public static JPopupMenu relationPopupmenu;
	
	public static JTextField entityTxt1;
	public static JTextField entityTxt2;
	
	public static ArrayList<Entity> entList1 = new ArrayList<Entity>(); 
	public static ArrayList<Entity> entList2 = new ArrayList<Entity>();  
	public static ArrayList<Relation> relationList = new ArrayList<Relation>();
	
	
	

}
