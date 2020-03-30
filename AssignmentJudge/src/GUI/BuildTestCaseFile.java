package GUI;

import java.io.*;

import javax.swing.JLabel;

import TesterCode.Tester;

public class BuildTestCaseFile {
	
	private static String testCaseFileName;
	private static String expectedOutputFileName;
	private static String programOutputFileName;
	private static String resultCSVFileName;
	
	public static String getTestCaseFileName() {return testCaseFileName;}
	
	public static String getExpectedOutputFileName() {return expectedOutputFileName;}
	
	public static String getProgramOutputFileName() {return programOutputFileName;}
	
	public static String getCSVFileName() {return resultCSVFileName;}
	
	@SuppressWarnings("resource")
	boolean createFiles(JLabel messageLabel, String testCaseFileName, String expectedOutputFileName, String programOutputFileName, String resultCSVFileName ,String testCases, String expectedOutputs) {
		BuildTestCaseFile.testCaseFileName = testCaseFileName;
		BuildTestCaseFile.expectedOutputFileName = expectedOutputFileName;
		BuildTestCaseFile.programOutputFileName = programOutputFileName;
		BuildTestCaseFile.resultCSVFileName = resultCSVFileName;
		FileOutputStream testCasesStream;
		FileOutputStream expectedOutputStream;
		try {
			testCasesStream = new FileOutputStream(Tester.workingDirectory + testCaseFileName, true);
			expectedOutputStream = new FileOutputStream(Tester.workingDirectory + expectedOutputFileName, true);
			new FileOutputStream(Tester.workingDirectory + programOutputFileName, true);
			new FileOutputStream(Tester.workingDirectory + resultCSVFileName, true);
			
			boolean writeResult = writeToFiles(messageLabel, testCases, expectedOutputs, testCasesStream, expectedOutputStream);
			messageLabel.setText("File Operations successful");
			testCasesStream.close();
			expectedOutputStream.close();
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
