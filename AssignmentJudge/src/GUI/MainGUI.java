package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import MailHandler.MailDownloader;
import TesterCode.Tester;
public class MainGUI implements ActionListener {
	
	
	private HashMap <String, JButton> buttons;
	private JTextField testCaseFileNameField;
	private JTextField expectedOutputFileNameField;
	private JTextField programOutputFileNameField;
	private JTextField resultCSVNameField;
	private JTextArea testCaseField;
	private JTextArea expectedOutputField;
	private JLabel messageLabel;
	
	public MainGUI(){
		buttons = new HashMap <String, JButton> ();
		this.buildFrame();
		
	}
	
	
	private void buildFrame() {
		JFrame frame = new JFrame();
		frame.add(this.buildPanel());
	    frame.addWindowListener(new WindowEventHandler());
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setTitle("Automatic Assignment Judge");
		frame.setSize(800, 800);
		frame.pack();
		frame.setVisible(true);
	}
	
	private JPanel buildPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setLayout(new GridLayout(0, 1));

		// build text field to enter test case file name
		testCaseFileNameField = new JTextField("testCases.txt");
		panel.add(this.buildLabel("Enter file name for test case file (recommended extension: .txt)"));
		panel.add(testCaseFileNameField);
		
		// build text field to enter expected output file name
		expectedOutputFileNameField = new JTextField("expectedOutput.txt");
		panel.add(this.buildLabel("Enter file name for expected output file (recommended extension: .txt)"));
		panel.add(expectedOutputFileNameField);
		
		// build text field to enter program output file name
		programOutputFileNameField = new JTextField("programOutput.txt");
		panel.add(this.buildLabel("Enter file name for program output file (recommended extension: .txt)"));
		panel.add(programOutputFileNameField);
		
		// build text field to enter result file name
		resultCSVNameField = new JTextField("results.csv");
		panel.add(this.buildLabel("Enter file name for result output file (mandatory extension: .csv)"));
		panel.add(resultCSVNameField);
		
		// build text area to enter test cases
		testCaseField = this.buildInputTextArea();
		JScrollPane testCaseScroll = new JScrollPane(testCaseField);
		panel.add(this.buildLabel("Enter test cases (first enter number of test cases, followed by individial case: )"));
		panel.add(testCaseScroll, BorderLayout.CENTER);
		
		expectedOutputField = this.buildInputTextArea();
		JScrollPane expectedOutputScroll = new JScrollPane(expectedOutputField);
		panel.add(this.buildLabel("Enter expected output"));
		panel.add(expectedOutputScroll, BorderLayout.CENTER);
		
		panel.add(this.buildButton("Build Test Cases file"));
		panel.add(this.buildButton("Download mail attachments"));
		panel.add(this.buildButton("Unit Test Builder"));
		panel.add(this.buildButton("Evaluate"));
		
		panel.add(this.buildLabel("Message to the user: "));
		messageLabel = this.buildLabel("Ready.");
		panel.add(messageLabel);
		return panel;
	}
	
	private JLabel buildLabel(String text) {
		return new JLabel(text);
	}
	private JTextArea buildInputTextArea() {
		JTextArea textField = new JTextArea();
		return textField;
		
	}
	
	private JButton buildButton(String text) {
		JButton button = new JButton(text);
		button.addActionListener(this);
			
		if(!buttons.containsKey(text)) {
			buttons.put(text, button);
		}
		return button;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getSource() == buttons.get("Build Test Cases file")) {
			messageLabel.setText("Building test cases and expected output files");
			BuildTestCaseFile buildFiles = new BuildTestCaseFile();
			
			// Problem here:
			// Only on pressing this button does the file names update there in another class.
			// If someone gets the Evaluate button before this, file names are not updated
			
			boolean result = buildFiles.createFiles(messageLabel, testCaseFileNameField.getText(), expectedOutputFileNameField.getText(), programOutputFileNameField.getText(), resultCSVNameField.getText(),testCaseField.getText(), expectedOutputField.getText());
			if(!result) {
				return;
			}
		}
		
		else if(e.getSource() == buttons.get("Unit Test Builder")) {
			messageLabel.setText("Building unit test cases");
			new UnitFunctionTesterGUI(messageLabel);
		}
		
		else if(e.getSource() == buttons.get("Download mail attachments")) {
			MailDownloader mailDownloader = new MailDownloader();
			
	        String host = "pop.gmail.com";
	        String port = "995";
	        String userName = "";
	        String password = "";
	        //mailDownloader.downloadEmailAttachments(host, port, userName, password);
		}
		
		else if(e.getSource() == buttons.get("Evaluate")) {
			Tester test = new Tester();
			test.evaluate(messageLabel);
		}
	}
}
