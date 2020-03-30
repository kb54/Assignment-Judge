/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.judge;

import java.io.File;


import javax.swing.JLabel;

public class Tester {
	
	public static String workingDirectory = "/Users/nimishmishra/Documents/assignment_checker/";
	private File inputFile;
	private File outputFile;
	private void runProcess(String command, String arg, File folder) throws Exception {
		    ProcessBuilder pro = new ProcessBuilder(command, arg);
		    pro.directory(folder);
		    pro.redirectInput(inputFile);
		    pro.redirectOutput(outputFile);
		    Process p = pro.start();
		    p.waitFor();
	}
	
	public void evaluate(JLabel messageText) {
		inputFile = new File(workingDirectory, "testCases.txt");
		outputFile = new File(workingDirectory, "programOutput.txt");
		final File folder = new File(workingDirectory);
		for(final File file: folder.listFiles()) {
			String fileName = file.getName();
			int index;
			if((index = fileName.indexOf(".java")) < 0)
				continue;
			
			String fileNameWithoutExtension = fileName.substring(0, index);
			try {
		    	messageText.setText("Executing " + file.getName()); 
		        runProcess("javac", fileName, folder);		        
		        runProcess("java", fileNameWithoutExtension, folder);
		      } 
		    
		    catch (Exception e) {
		        messageText.setText(e.getMessage());
		   }
		}
	}
	
}