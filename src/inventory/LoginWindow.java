package inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginWindow extends JFrame  {
	String adminusername = "admin";
	String adminpassword = "admin";
	private JPanel mainPane;
	private JTextField nameTextField;
	private JPasswordField passTextField;    
	
	public LoginWindow() {
		setTitle("Inventory System - Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainPane = new JPanel();
		mainPane.setLayout(null);
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setBounds(100, 100, 509, 361);
		setContentPane(mainPane);
		setLocationRelativeTo(null); //set location to center
		setResizable(false);

		JPanel greenPanel = new JPanel();
		nameTextField = new JTextField();
		passTextField = new JPasswordField();
		JLabel userLabel = new JLabel("Username");
		JLabel passwordLabel = new JLabel("Password");
		JLabel loginLabel = new JLabel("Admin Login");
		JButton exitButton = new JButton("Exit");
		JButton logButton = new JButton("Login");
		
		loginLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		userLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		logButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		exitButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		exitButton.setForeground(Color.WHITE);
		mainPane.setBackground(Color.WHITE);
		logButton.setForeground(Color.WHITE);
		exitButton.setBackground(Color.GRAY);
		
		greenPanel.setLayout(null);

		setForeground(new Color(255, 255, 255));
		loginLabel.setForeground(new Color(255, 255, 255));
		logButton.setBackground(new Color(0, 128, 0));
		passTextField.setBackground(new Color(255, 255, 240));
		nameTextField.setBackground(new Color(255, 255, 240));
		greenPanel.setBackground(new Color(0, 100, 0));

		nameTextField.setColumns(10);
		passTextField.setColumns(10);

		greenPanel.setBounds(0, 0, 503, 58);
		userLabel.setBounds(29, 110, 93, 16);
		loginLabel.setBounds(187, 19, 128, 26);
		passwordLabel.setBounds(29, 175, 116, 16);
		passTextField.setBounds(134, 159, 334, 52);
		nameTextField.setBounds(134, 94, 334, 52);
		logButton.setBounds(164, 239, 144, 58);
		exitButton.setBounds(320, 238, 144, 61);
		


		mainPane.add(userLabel);
		mainPane.add(logButton);
		mainPane.add(greenPanel);
		mainPane.add(passwordLabel);
		mainPane.add(nameTextField);
		mainPane.add(passTextField);
		mainPane.add(exitButton);
		greenPanel.add(loginLabel);

        exitButton.addActionListener(new ActionListener(){
        	 public void actionPerformed(ActionEvent e){
        	            int response = JOptionPane.showConfirmDialog(
        	                rootPane,
        	                "Sure to exit?",
        	                "Confirm exit",
        	                JOptionPane.OK_CANCEL_OPTION, 
        	                JOptionPane.QUESTION_MESSAGE);
        	            
        	            if (response==0){
        	                System.exit(0); 
        	            }  

        }});
        logButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
			   if(e.getSource()==logButton) {
					String userName = nameTextField.getText(); 
					char[] password = passTextField.getPassword();
			        if (userName.trim().equalsIgnoreCase(adminusername) && String.valueOf(password).contentEquals(adminpassword)) {
			        		JOptionPane.showMessageDialog(null, "Login Success", "Success Login", JOptionPane.INFORMATION_MESSAGE);
			        		InventoryMain im = new InventoryMain();  		
			        		im.setVisible(true);
			        		dispose();
			        }else if (userName.trim().equals("") || String.valueOf(password).contentEquals("")) {
			        	
			        	JOptionPane.showMessageDialog(null, "Input Required Info.", "NullInput", JOptionPane.ERROR_MESSAGE);
			        }else {
			        	JOptionPane.showMessageDialog(null, "Invalid Username orPassword.", "ErrorInput", JOptionPane.ERROR_MESSAGE);
			        }
				    	
			   }
        	}
        });
	}
}
