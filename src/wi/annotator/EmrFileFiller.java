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
		String[] suffixes =  suffix.split(",");
		if(pathname.isDirectory()){
			return true;
		}
		for(String s : suffixes){
			if(pathname.getName().endsWith(s)){
				return true;
			}
		}
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return suffix;
	}

}
