package wi.annotator;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class RelationFileFilter extends FileFilter {

	@Override
	public boolean accept(File pathname) {
		// TODO Auto-generated method stub
		return pathname.isDirectory() || pathname.getName().endsWith(".rel");
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return ".rel";
	}

}
