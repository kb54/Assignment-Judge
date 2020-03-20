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

import MailHandler.MailDownloader;
import TesterCode.Tester;

public class MainGUI implements ActionListener {
	
	
	private HashMap <String, JButton> buttons;
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Automatic Assignment Judge");
		frame.setSize(800, 800);
		frame.pack();
		frame.setVisible(true);
	}
	
	private JPanel buildPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setLayout(new GridLayout(0, 1));
		
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
			boolean result = buildFiles.createTestCaseFile(messageLabel, testCaseField.getText(), expectedOutputField.getText());
			if(!result) {
				return;
			}
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
