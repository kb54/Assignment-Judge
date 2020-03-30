package TesterCode;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JLabel;

import GUI.MainGUI;
import UnitTester.UnitTestRunner;
import GUI.BuildTestCaseFile;
public class Tester {
	
	public static String workingDirectory = "/Users/nimishmishra/Documents/assignment_checker/";
	private File inputFile;
	private File expectedOutputFile;
	private File outputFile;
	private File CSVResultFile;
	public static void main(String [] args)
	{
		new MainGUI(); 
	}
	

	
	private void runProcess(String command, String arg, File folder) throws Exception {
		    ProcessBuilder pro = new ProcessBuilder(command, arg);
		    pro.directory(folder);
		    pro.redirectInput(inputFile);
		    pro.redirectOutput(outputFile);
		    Process p = pro.start();
		    p.waitFor();
	}
	
	private boolean openFiles(JLabel messageText) {
		
		try {
			inputFile = new File(workingDirectory, BuildTestCaseFile.getTestCaseFileName());
			outputFile = new File(workingDirectory, BuildTestCaseFile.getProgramOutputFileName());
			expectedOutputFile = new File(workingDirectory, BuildTestCaseFile.getExpectedOutputFileName());
			CSVResultFile = new File(workingDirectory, BuildTestCaseFile.getCSVFileName());
			writeCSVHeader(messageText);
			String errorMessage = "";
			
			if(!inputFile.exists()) errorMessage += "Test case input file. ";
			if(!outputFile.exists()) errorMessage += "Program output file. ";
			if(!expectedOutputFile.exists()) errorMessage +=  "Expected output file. ";
			if(!CSVResultFile.exists()) errorMessage += "Results CSV file. ";
			
			messageText.setText("Non existent files: " + errorMessage);
			
			return (errorMessage == "") ? true : false;
		}
		
		catch(Exception e) {
			messageText.setText("Non existent files");
			return false;
		}
	}
	
	public void evaluate(JLabel messageText) {
		if(!openFiles(messageText))
			return;
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
		        generateScore(messageText, fileNameWithoutExtension);
		        
		        // Imagine a scenario where each java file builds several of classes: A, B, C, D, and the main class itself
		        
		        // Assignment1_335 will build each of these classes. If you delete these files after use, HERE.
		        // Assignment1_362 will again build each of these classes. So you have unique version of classes
		        // A, B, C, D in each run. And you can utilize this thing in your unit tests.
		      } 
		    
		    catch (Exception e) {
		        messageText.setText(e.getMessage());
		   }
			
		}
		
		messageText.setText("Evaluation completed");
	}
	

	
	
	private void writeCSVHeader(JLabel messageText) {
		try {
			FileWriter csvWriter = new FileWriter(CSVResultFile);
			csvWriter.append("Assignment");
			csvWriter.append(",");
			csvWriter.append("Registration number");
			csvWriter.append(",");
			csvWriter.append("Failed test cases");
			csvWriter.append(",");
			csvWriter.append("Unit testing summary (Failed assertions reported as (expected value != output value))");
			csvWriter.append("\n");
			csvWriter.flush();
			csvWriter.close();
		}
		
		catch(Exception e) {
			messageText.setText(e.getMessage());
		}
	}
	
	
	private void writeResults(JLabel messageText, String assignment, String registrationNumber, int failedTestCases, String summary) {
		try {
			FileWriter csvWriter = new FileWriter(CSVResultFile, true);
			csvWriter.append("" + assignment);
			csvWriter.append(",");
			csvWriter.append("" + registrationNumber);
			csvWriter.append(",");
			csvWriter.append("" + failedTestCases);
			csvWriter.append(",");
			csvWriter.append(summary);
			csvWriter.append("\n");
			csvWriter.flush();
			csvWriter.close();
		}
		
		catch(Exception e) {
			messageText.setText(e.getMessage());
		}
	}
	
	private void generateScore(JLabel messageText, String fileName) {
		int failedTestCases = 0;
		FileReader expectedOutputReader = null;
		FileReader programOutputReader = null;
		Scanner expectedOutputScanner = null;
		Scanner programOutputScanner = null;
		
		
		try {
			expectedOutputReader = new FileReader(expectedOutputFile);
			programOutputReader = new FileReader(outputFile);
			expectedOutputScanner = new Scanner(expectedOutputReader);
			programOutputScanner = new Scanner(programOutputReader);
			String expectedOutputLine;
			String programOutputLine;
			
			while(((programOutputLine = programOutputScanner.nextLine()) != null) && ((expectedOutputLine = expectedOutputScanner.nextLine()) != null )) {
				if(!(programOutputLine.equals(expectedOutputLine))) {
					failedTestCases++;
				}
			}

		} // try ends
		
		catch(NoSuchElementException e) {
			// Do nothing
		} // first catch ends
		
		catch(Exception e) {
			messageText.setText(e.getMessage());
		} // second catch ends
		
		finally {
			UnitTestRunner runner = new UnitTestRunner(true);
			
			int underscoreIndex = fileName.indexOf('_');
			String registrationNumber = fileName.substring(underscoreIndex+1);
			String assignment = fileName.substring(0, underscoreIndex);
			String summary = runner.runUnitTests(registrationNumber);
			writeResults(messageText, assignment, registrationNumber, failedTestCases, summary);
			
			if(expectedOutputScanner != null) expectedOutputScanner.close();
			if(programOutputScanner != null)  programOutputScanner.close();
			
			try {
				if(expectedOutputReader != null) expectedOutputReader.close();
				if(programOutputReader != null) programOutputReader.close();
			}
			catch(Exception e) { // Do nothing
				}
			} // finally ends
		} // function ends
		 
		
	}
	
