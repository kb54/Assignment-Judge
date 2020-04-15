package TesterCode;

//import UnitTestRunner;
import assignmentjudge.AssignmentJudge;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JLabel;

//import GUI.MainGUI;
import UnitTester.UnitTestRunner;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import GUI.BuildTestCaseFile;
public class Tester {
	
	private File inputFile;
	private File expectedOutputFile;
        private File scoresFile;
	private File outputFile;
	private File CSVResultFile;
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
			inputFile = new File(AssignmentJudge.workingDirectory, BuildTestCaseFile.getTestCaseFileName());
			outputFile = new File(AssignmentJudge.workingDirectory, BuildTestCaseFile.getProgramOutputFileName());
			expectedOutputFile = new File(AssignmentJudge.workingDirectory, BuildTestCaseFile.getExpectedOutputFileName());
			CSVResultFile = new File(AssignmentJudge.workingDirectory, BuildTestCaseFile.getCSVFileName());
                        scoresFile = new File(AssignmentJudge.workingDirectory, BuildTestCaseFile.getscoresFileName());
			writeCSVHeader(messageText);
			String errorMessage = "";
			
			if(!inputFile.exists()) errorMessage += "Test case input file. ";
			if(!outputFile.exists()) errorMessage += "Program output file. ";
			if(!expectedOutputFile.exists()) errorMessage +=  "Expected output file. ";
			if(!CSVResultFile.exists()) errorMessage += "Results CSV file. ";
                        if(!scoresFile.exists()) errorMessage += "Scores file. ";
			
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
		final File folder = new File(AssignmentJudge.workingDirectory);
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
                        csvWriter.append("Score obtained(out of 100)");
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
        
	
	private void writeResults(JLabel messageText, String assignment, String registrationNumber, int failedTestCases, int scoreobtained, String summary) {
		try {
			FileWriter csvWriter = new FileWriter(CSVResultFile, true);
			csvWriter.append("" + assignment);
			csvWriter.append(",");
			csvWriter.append("" + registrationNumber);
			csvWriter.append(",");
			csvWriter.append("" + failedTestCases);
			csvWriter.append(",");
                        csvWriter.append("" + scoreobtained);
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
        
        
        private void updateTable(JLabel messageText, String assignment, String registrationNumber, int scoreobtained) {
                String  url = "jdbc:mysql://localhost:3306/";
                String dbName = "Assignment_Judge";
                String driver = "java.sql.DriverManager";
                String sqlUsername = "root"; 
                String sqlPassword = "bharat";
                try {
                    Class.forName(driver);
                    Connection con = (Connection)DriverManager.getConnection(url + dbName, sqlUsername, sqlPassword);
                    Statement st = (Statement) con.createStatement();
                    // fetching number from the Assignment(num)
                    int idx = assignment.indexOf('t');
                    String idx_str = assignment.substring(idx + 1);
                    boolean find_record = false;
                    String query_search = "Select reg_no from report;";
                    ResultSet rs = st.executeQuery(query_search);
                    while(rs.next()) {
                        if(rs.getString("reg_no").equals(registrationNumber)) {
                            find_record = true;
                            break;
                        }
                    }
                    rs.close();
                    if(!find_record) {
                        String query_insert = "Insert into report values('"+registrationNumber+"', '"+idx_str+"', '"+scoreobtained+"');";
                        st.executeUpdate(query_insert);
                        st.close();
                    }
                    else {
                        String query_update = "Update report set score_obtained = score_obtained + ?, assignment_no = CONCAT(assignment_no, ', ', ?) where reg_no = ?";
                        try (Connection conn = (Connection) DriverManager.getConnection(url + dbName, sqlUsername, sqlPassword);
                                PreparedStatement stmt = conn.prepareStatement(query_update);) {
                            stmt.setInt(1, scoreobtained);
                            stmt.setString(2, idx_str);
                            stmt.setString(3, registrationNumber);
                            stmt.executeUpdate();
                            conn.close();
                            stmt.close();
                            st.close();
                        }
                    }
                    messageText.setText("Results stored successfully");
                    con.close();
                }
                catch(SQLException e) {
                    messageText.setText("Failure in ");
                } catch (ClassNotFoundException ex) 
                {
                Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
	
	private void generateScore(JLabel messageText, String fileName) {
		int failedTestCases = 0;
                int scoreobtained = 0;
		FileReader expectedOutputReader = null;
		FileReader programOutputReader = null;
                FileReader scoresReader = null;
		Scanner expectedOutputScanner = null;
		Scanner programOutputScanner = null;
                Scanner scoresScanner = null;
		
		
		try {
			expectedOutputReader = new FileReader(expectedOutputFile);
			programOutputReader = new FileReader(outputFile);
                        scoresReader = new FileReader(scoresFile);
			expectedOutputScanner = new Scanner(expectedOutputReader);
			programOutputScanner = new Scanner(programOutputReader);
                        scoresScanner = new Scanner(scoresReader);
			String expectedOutputLine;
			String programOutputLine;
                        String scoresOutputLine;
			
			while(((programOutputLine = programOutputScanner.nextLine()) != null) &&
                                ((expectedOutputLine = expectedOutputScanner.nextLine()) != null ) &&
                                ((scoresOutputLine = scoresScanner.nextLine()) != null )) {
				if(!(programOutputLine.equals(expectedOutputLine))) {
					failedTestCases++;
				}
                                else {
                                        scoreobtained += Integer.parseInt(scoresOutputLine);
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
			writeResults(messageText, assignment, registrationNumber, failedTestCases, scoreobtained, summary);
                        updateTable(messageText, assignment, registrationNumber, scoreobtained);
			
			if(expectedOutputScanner != null) expectedOutputScanner.close();
			if(programOutputScanner != null)  programOutputScanner.close();
			
			try {
				if(expectedOutputReader != null) expectedOutputReader.close();
				if(programOutputReader != null) programOutputReader.close();
			}
			catch(Exception e) { // Do nothing
				}
			} // finally ends
		} // function end
	}
	