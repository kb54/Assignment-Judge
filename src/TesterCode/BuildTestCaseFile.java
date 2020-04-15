package TesterCode;

import assignmentjudge.AssignmentJudge;
import java.io.*;

//import javax.swing.JLabel;
// Removed messageLabel

public class BuildTestCaseFile {
	
	private static String testCaseFileName;
	private static String expectedOutputFileName;
	private static String programOutputFileName;
	private static String resultCSVFileName;
        // Added by bharat
        private static String scoresFileName;
	
	public static String getTestCaseFileName() {return testCaseFileName;}
	
	public static String getExpectedOutputFileName() {return expectedOutputFileName;}
	
	public static String getProgramOutputFileName() {return programOutputFileName;}
	
	public static String getCSVFileName() {return resultCSVFileName;}
                
        public static String getscoresFileName() {return scoresFileName;}
	@SuppressWarnings("resource")
	public boolean createFiles(String testCaseFileName, String expectedOutputFileName, String programOutputFileName, String scoresFileName, 
                String resultCSVFileName ,String testCases, String expectedOutputs, String scores) {
		BuildTestCaseFile.testCaseFileName = testCaseFileName;
		BuildTestCaseFile.expectedOutputFileName = expectedOutputFileName;
		BuildTestCaseFile.programOutputFileName = programOutputFileName;
		BuildTestCaseFile.resultCSVFileName = resultCSVFileName;
                BuildTestCaseFile.scoresFileName = scoresFileName;
		FileOutputStream testCasesStream;
		FileOutputStream expectedOutputStream;
                FileOutputStream scoresStream;
		try {
                        // added back slash so as to make a valid absolute_path
                        // Later realised + will do same job
			testCasesStream = new FileOutputStream(AssignmentJudge.workingDirectory + "\\" + testCaseFileName, true);
			expectedOutputStream = new FileOutputStream(AssignmentJudge.workingDirectory + "\\" +  expectedOutputFileName, true);
                        scoresStream = new FileOutputStream(AssignmentJudge.workingDirectory + "\\" +  scoresFileName, true);
			new FileOutputStream(AssignmentJudge.workingDirectory + "\\" + programOutputFileName, true);
			new FileOutputStream(AssignmentJudge.workingDirectory + "\\" + resultCSVFileName, true);
			boolean writeResult = writeToFiles(testCases, expectedOutputs, scores, testCasesStream, expectedOutputStream, scoresStream);
			testCasesStream.close();
			expectedOutputStream.close();
			return writeResult;
		}
		
		catch(Exception e) {
			//messageLabel.setText("Error in file creation");
			return false;
		}
	}
	
	private boolean writeToFiles(String testCases, String expectedOutputs, String scores, 
                FileOutputStream testCasesStream, FileOutputStream expectedOutputStream, FileOutputStream scoresStream) {

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
                        
                        for(int i = 0; i < scores.length(); i++) {
				ch = scores.charAt(i);
				scoresStream.write(ch);
			}
			testCasesStream.close();
			expectedOutputStream.close();
                        scoresStream.close();
			return true;
		}
		
		catch(Exception e) {
			//messageLabel.setText("Error writing to files");
			return false;
		}
	}
}