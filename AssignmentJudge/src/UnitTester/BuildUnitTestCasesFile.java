package UnitTester;

import java.io.FileOutputStream;

import javax.swing.JLabel;
import TesterCode.Tester;

public class BuildUnitTestCasesFile {
	
	private String unitTestCaseFileName;
	private String unitExpectedOutputFileName;
	private String unitProgramOutputFileName;
	private String functionSignature;
	private String unitTestCases;
	private String unitTestCasesExpectedOutput;
	private JLabel messageText;
	
	public BuildUnitTestCasesFile(
			String unitTestCaseFileName,
			String unitExpectedOutputFileName,
			String unitProgramOutputFileName,
			String functionSignature,
			String unitTestCases,
			String unitTestCasesExpectedOutput,
			JLabel messageText
			) {
		
		this.unitTestCaseFileName = unitTestCaseFileName;
		this.unitExpectedOutputFileName = unitExpectedOutputFileName;
		this.unitProgramOutputFileName = unitProgramOutputFileName;
		this.functionSignature = functionSignature;
		this.unitTestCases = unitTestCases;
		this.unitTestCasesExpectedOutput = unitTestCasesExpectedOutput;
		this.messageText = messageText;
		updateFileNames();
	}
	

	
	private void updateFileNames() {
		// to ensure the file names are unique
		String functionInfo = functionSignature.replace(',', '_');
		functionInfo = functionInfo.replace(' ', '_');
		unitTestCaseFileName += functionInfo + ".txt";
		unitExpectedOutputFileName += functionInfo + ".txt";
		unitProgramOutputFileName += functionInfo + ".txt";
		
		UnitTestRunner.fileNameExtensions.add(functionInfo); // to let function signature propagate to UnitTestRunner
		// You can let resident class of desired unit function to propagate in similar way
		
		buildFiles();
	}
	
	private void buildFiles() {
		FileOutputStream unitTestCasesOutputStream = null;
		FileOutputStream unitTestCasesExpectedOutputStream = null;
		FileOutputStream unitTestCasesProgramOutputStream = null;

		
		try {
			unitTestCasesOutputStream = new FileOutputStream(Tester.workingDirectory + unitTestCaseFileName, true);
			unitTestCasesExpectedOutputStream = new FileOutputStream(Tester.workingDirectory + unitExpectedOutputFileName, true);
			//unitTestCasesProgramOutputStream = new FileOutputStream(Tester.workingDirectory + unitProgramOutputFileName, true);
			
			char ch;
			for(int i = 0; i < unitTestCases.length(); i++) {
				ch = unitTestCases.charAt(i);
				unitTestCasesOutputStream.write(ch);
			}
			
			for(int i = 0; i < unitTestCasesExpectedOutput.length(); i++) {
				ch = unitTestCasesExpectedOutput.charAt(i);
				unitTestCasesExpectedOutputStream.write(ch);
			}
		}
		
		catch(Throwable e) {
			messageText.setText("Unit test file building failed");
		}
		
		finally {
			try {
				unitTestCasesOutputStream.close();
				unitTestCasesExpectedOutputStream.close();
				unitTestCasesProgramOutputStream.close();
			}catch(Exception e) {}
			
		}
	}
	
	
}
