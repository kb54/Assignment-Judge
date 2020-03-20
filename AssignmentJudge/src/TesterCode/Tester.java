package TesterCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JLabel;

import GUI.MainGUI;
public class Tester {
	
	public static String workingDirectory = "/Users/nimishmishra/Documents/assignment_checker/";
	private File inputFile;
	private File outputFile;
	public static void main(String [] args)
	{
		new MainGUI(); 
	}
	

	
	private void runProcess(String command, String arg) throws Exception {
		    ProcessBuilder pro = new ProcessBuilder(command, arg);
		    pro.redirectInput(inputFile);
		    Process p =pro.start();
		    
		    BufferedReader reader = 
	                new BufferedReader(new InputStreamReader(p.getInputStream()));
		    StringBuilder builder = new StringBuilder();
		    String line = null;
		    while ( (line = reader.readLine()) != null) {
		    		builder.append(line);
		    		builder.append(System.getProperty("line.separator"));
		    }
		    
		    p.waitFor();
		    String result = builder.toString();
		    System.out.println("Result: " + result);
	}
	
	public void evaluate(JLabel messageText) {
		inputFile = new File(workingDirectory, "testCases.txt");
		outputFile = new File(workingDirectory, "programOutput.txt");
		final File folder = new File(workingDirectory + "downloadedAssignments");
		for(final File file: folder.listFiles()) {
			String filePath = file.getAbsolutePath();
			int index;
			if((index = filePath.indexOf(".java")) < 0)
				continue;
			String filePathWithoutExtension = filePath.substring(0, index);
			System.out.println(filePathWithoutExtension);
			try {
		    	messageText.setText("Executing " + file.getName()); 
		        runProcess("javac", filePath);		        
		        runProcess("java", filePathWithoutExtension);
		      } 
		    
		    catch (Exception e) {
		        messageText.setText(e.getMessage());
		   }
		}
	}
	
	
}
