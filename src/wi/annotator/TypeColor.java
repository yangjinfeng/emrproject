package wi.annotator;

import java.awt.Color;

public class TypeColor {
	
	public static Color SelectedColor = new Color(173,216,230);
	
	private int flag;
	private String typeId;
	private String typeName;
	private Color color;
	
	
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public String toString(){
		return getTypeName();
	}
	

}
