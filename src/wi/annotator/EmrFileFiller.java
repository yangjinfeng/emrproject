package wi.annotator;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class EmrFileFiller extends FileFilter {
	
	private String suffix;
	public EmrFileFiller(String suffix){
		this.suffix = suffix;
	}

	@Override
	public boolean accept(File pathname) {
		// TODO Auto-generated method stub
		return pathname.isDirectory() || pathname.getName().endsWith(suffix);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return suffix;
	}

}
