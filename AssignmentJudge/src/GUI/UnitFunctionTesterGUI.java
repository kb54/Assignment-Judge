package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import UnitTester.BuildUnitTestCasesFile;

public class UnitFunctionTesterGUI implements ActionListener {
	
	
	private static String unitTestCaseFileName = "unitTestCases";
	private static String unitExpectedOutputFileName = "expectedUnitTestOutput";
	private static String unitProgramOutputFileName = "programUnitTestOutput";
	private JTextArea unitTestCaseField;
	private JTextArea unitExpectedOutputField;
	private JTextField targetFunctionName;
	private JButton buildButton;
	private JLabel messageLabel;
	UnitFunctionTesterGUI(JLabel messageLabel){
		this.messageLabel = messageLabel;
		this.buildFrame();
	}
	
	public static String getUnitTestCaseFileName() {return unitTestCaseFileName;}
	public static String getUnitExpectedOutputFileName() {return unitExpectedOutputFileName;}
	public static String getUnitProgramOutputFileName() {return unitProgramOutputFileName;}
	
	private void buildFrame() {
		JFrame frame = new JFrame();
		frame.add(this.buildPanel());
		frame.setTitle("Unit Test Builder");
		frame.setSize(800, 800);
		frame.pack();
		frame.setVisible(true);
	}
	
	private JPanel buildPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setLayout(new GridLayout(0, 1));

		
		// build text area to enter test cases
		unitTestCaseField = this.buildInputTextArea();
		JScrollPane testCaseScroll = new JScrollPane(unitTestCaseField);
		panel.add(this.buildLabel("Enter unit test case inputs as comma separated values. For Object foo(int a, int b) enter as: 5, 6"));
		panel.add(testCaseScroll, BorderLayout.CENTER);
		
		unitExpectedOutputField = this.buildInputTextArea();
		JScrollPane expectedOutputScroll = new JScrollPane(unitExpectedOutputField);
		panel.add(this.buildLabel("Enter expected output corresponding to the unit test cases"));
		panel.add(expectedOutputScroll, BorderLayout.CENTER);
		
		panel.add(this.buildLabel("Function signature to unit test. For Object foo(int a, int b) enter as: foo, int, int"));
		targetFunctionName = new JTextField();
		panel.add(targetFunctionName);
		
		buildButton = new JButton("Build Unit Tests");
		buildButton.addActionListener(this);
		panel.add(buildButton);
		
		return panel;
	}
	
	private JLabel buildLabel(String text) {
		return new JLabel(text);
	}
	private JTextArea buildInputTextArea() {
		JTextArea textField = new JTextArea();
		return textField;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		new BuildUnitTestCasesFile(
				unitTestCaseFileName,
				unitExpectedOutputFileName,
				unitProgramOutputFileName,
				targetFunctionName.getText(),
				unitTestCaseField.getText(),
				unitExpectedOutputField.getText(),
				messageLabel
				);
	}
	
}
