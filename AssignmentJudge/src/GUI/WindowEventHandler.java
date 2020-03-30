package GUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import TesterCode.Tester;

public class WindowEventHandler extends WindowAdapter {
	  
	
	public void windowClosing(WindowEvent evt) {
	
		final File folder = new File(Tester.workingDirectory);
		for(File f: folder.listFiles()) {
			if(f.getName().indexOf(".txt") > 0) {
				f.delete();
			}
		}
	  
	}
}