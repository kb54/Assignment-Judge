package UnitTester;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Scanner;

//import TesterCode.Tester;
import assignmentjudge.AssignmentJudge;
import assignmentjudge.UnitCaseBuilder;


public class UnitTestRunner {
	private static String workingDirectory = AssignmentJudge.workingDirectory;
        private static String unitTestCaseFileName;
	private static String unitExpectedOutputFileName;
	private static String unitProgramOutputFileName;
	public static ArrayList<String> fileNameExtensions = new ArrayList<String>();
	private int totalAssertions = 0;
	private int failedAssertions = 0;
	
	private String summary = ""; 
	
	public String methodName;
	boolean testCaseSetupFlag;
	
	
	public UnitTestRunner() {
	}
	
	public UnitTestRunner(boolean flag) {
		unitTestCaseFileName = UnitCaseBuilder.getUnitTestCaseFileName();
		unitExpectedOutputFileName = UnitCaseBuilder.getUnitExpectedOutputFileName();
		unitProgramOutputFileName = UnitCaseBuilder.getUnitProgramOutputFileName();
	}
	
	public String runUnitTests(String registrationNumber) {
		summary = "";
		int length = fileNameExtensions.size();
		String extension;
		for(int i = 0; i < length; i++) {
			extension = fileNameExtensions.get(i);
			String inputFileName = unitTestCaseFileName +  extension + ".txt";
			String expectedOutputFileName = unitExpectedOutputFileName + extension + ".txt";
			String programOutputFileName = unitProgramOutputFileName + extension + ".txt";
			runTest(inputFileName, expectedOutputFileName, programOutputFileName, extension, registrationNumber);
		}
		summary = "" + (totalAssertions - failedAssertions) + " assertions passed. " + failedAssertions + " assertions failed. " + summary;
		return summary;
	}
	
	private void runTest(String inputFileName, String expectedOutputFileName, String programOutputFileName, String extension, String registrationNumber) {
		try {
			final File folder = new File(workingDirectory);
			for (File f: folder.listFiles()) {
				String name = f.getName();
				if(f.getName().indexOf(".class") > 0 && f.getName().indexOf(registrationNumber) > 0)
				{
					name = name.substring(0, name.indexOf('.'));
					URL url = folder.toURI().toURL();
					URL[] urls = new URL[]{url}; 
					ClassLoader cl = new URLClassLoader(urls); 
				    Class<?>  cls = cl.loadClass(name);
					testFramework(cls, inputFileName, programOutputFileName, extension, expectedOutputFileName);
					
				}
			}
			
		} catch(Exception e) {
			summary += e.getMessage() + " ";
		}
	}
		
	private void testCaseSetup() {
		testCaseSetupFlag = true;
	}
	
	
	private void testFramework(Class<?> test, String inputFileName, String programOutputFileName, String extension, String expectedOutputFileName) {
		// tests the testing framework to see if its working
		File inputFile = new File(workingDirectory, inputFileName);
		File expectedOutputFile = new File(workingDirectory, expectedOutputFileName);
		FileReader inputStream;
		Scanner inputScanner;
		FileReader expectedOutputStream;
		Scanner expectedOutputScanner;
		try {
			
			inputStream = new FileReader(inputFile); 
			inputScanner = new Scanner(inputStream);
			expectedOutputStream = new FileReader(expectedOutputFile);
			expectedOutputScanner = new Scanner(expectedOutputStream);
			Class<? extends Object> className = test;
			Object obj = className.newInstance();
			Method[] methods = className.getDeclaredMethods();
			for(Method method: methods) {
				if(extension.indexOf(method.getName()) >= 0) {
					method.setAccessible(true);
					int numberOfArguments = method.getParameterCount();
					Class<?>[] parameterTypes = method.getParameterTypes();
					
					while(inputScanner.hasNext()) {
						Object[] params = new Object[numberOfArguments];
						for(int i = 0; i < numberOfArguments; i++) {
							if(parameterTypes[i].getName().equals(new String("int"))){
								params[i] = Integer.parseInt(inputScanner.next());
							}
						}
					
					
						Object returnValue = method.invoke(obj, params);
						Object expectedValue = expectedOutputScanner.next();
						totalAssertions++;
						if(!(returnValue.toString().equals(expectedValue.toString()))) {
							failedAssertions++;
							summary += "Failed Assertion: " + expectedValue.toString() + " != " + returnValue.toString() + " ";
						}
					}
					
				}			
			}
			
			if(totalAssertions == 0) {
				// implies none of the assertions were performed AND no exceptions took place
				summary += "Method not found in class file. ";
			}
		}
		
		catch(IllegalAccessException e) {
			summary += "Illegal Access: class is NOT declared public. ";
		}
		
		catch(Throwable e) {
			// Improve here later
			summary += "Throwable caught. ";
		}
	}

	
}
