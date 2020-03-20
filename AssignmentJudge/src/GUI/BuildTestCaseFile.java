package GUI;

import java.io.*;

import javax.swing.JLabel;

import TesterCode.Tester;

public class BuildTestCaseFile {
	
	boolean createTestCaseFile(JLabel messageLabel, String testCases, String expectedOutputs) {
		
		FileOutputStream testCasesStream;
		FileOutputStream expectedOutputStream;
		try {
			testCasesStream = new FileOutputStream(Tester.workingDirectory + "testCases.txt", true);
			expectedOutputStream = new FileOutputStream(Tester.workingDirectory + "expectedOutput.txt", true);
			
			boolean writeResult = writeToFiles(messageLabel, testCases, expectedOutputs, testCasesStream, expectedOutputStream);
			messageLabel.setText("File Operations successful");
			return writeResult;
		}
		
		catch(Exception e) {
			messageLabel.setText("Error in file creation");
			return false;
		}
	}
	
	private boolean writeToFiles(JLabel messageLabel, String testCases, String expectedOutputs, FileOutputStream testCasesStream, FileOutputStream expectedOutputStream) {

		try {
			int ch;
			for(int i = 0; i < testCases.length(); i++) {
				ch = testCases.charAt(i);
				testCasesStream.write(ch);
			}
			
			for(int i = 0; i < expectedOutputs.length(); i++) {
				ch = expectedOutputs.charAt(i);
				expectedOutputStream.write(ch);
			}
			testCasesStream.close();
			expectedOutputStream.close();
			return true;
		}
		
		catch(Exception e) {
			messageLabel.setText("Error writing to files");
			return false;
		}
	}
}
