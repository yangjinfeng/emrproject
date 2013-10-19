package wi.annotator;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class EntityFileFilter extends FileFilter {

	@Override
	public boolean accept(File pathname) {
		// TODO Auto-generated method stub
		return pathname.isDirectory() || pathname.getName().endsWith(".ent");
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return ".ent";
	}

}
