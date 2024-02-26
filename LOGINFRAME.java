package resturan;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.UIManager;

import resturan.USERSMAIN;

import resturan.ENTERASWHO;

import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class LOGINFRAME extends JFrame {

	private JPanel MAINBACK;
	private JTextField textField;
	private JPasswordField passwordField;

	// Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Resturant_managment_system";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "1234ANDYsamson+";

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LOGINFRAME frame = new LOGINFRAME();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LOGINFRAME() {
		setForeground(Color.DARK_GRAY);
		setTitle("WElCOME User");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 383);
		MAINBACK = new JPanel();
		MAINBACK.setBackground(Color.LIGHT_GRAY);
		MAINBACK.setBorder(null);

		setContentPane(MAINBACK);
		MAINBACK.setLayout(null);
		
		JLabel UserName = new JLabel("User Name :");
		UserName.setForeground(UIManager.getColor("CheckBox.foreground"));
		UserName.setFont(new Font("Tahoma", Font.BOLD, 15));
		UserName.setBounds(103, 81, 104, 30);
		MAINBACK.add(UserName);
		
		JLabel Password = new JLabel("Password :");
		Password.setForeground(UIManager.getColor("CheckBox.foreground"));
		Password.setFont(new Font("Tahoma", Font.BOLD, 15));
		Password.setBounds(103, 151, 90, 30);
		MAINBACK.add(Password);
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setBounds(217, 81, 196, 27);
		MAINBACK.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(Color.WHITE);
		passwordField.setBounds(217, 151, 196, 27);
		MAINBACK.add(passwordField);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(68, 52, 393, 244);
		MAINBACK.add(panel);
		panel.setLayout(null);
		
		JButton BACK = new JButton("BACK");
		BACK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ENTERASWHO enteraswho=new ENTERASWHO();
				
				enteraswho.setVisible(true);
        		setVisible(false); // Hide the current frame
                dispose(); // Release system resources of the current frame
				
				
			}
		});
		BACK.setBounds(288, 210, 89, 23);
		panel.add(BACK);
		
		JButton Login = new JButton("Login");
		Login.setBounds(161, 209, 117, 23);
		panel.add(Login);
		Login.setForeground(Color.GREEN);
		Login.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		 // Add an ActionListener to the Login button
		Login.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String username = textField.getText();
		        String password = new String(passwordField.getPassword());

		        if (username.isEmpty() || password.isEmpty()) {
		            // Show an error message if the username or password is empty
		            JOptionPane.showMessageDialog(null, "Please enter both username and password.");
		        } else {
		            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
		                // Prepare the SQL SELECT statement
		                String sql = "SELECT * FROM USERINFO WHERE UserName = ? AND Password1 = ? AND UserType = ?";
		                try (PreparedStatement statement = connection.prepareStatement(sql)) {
		                    statement.setString(1, username);
		                    statement.setString(2, password);
		                    statement.setString(3, "USER");
		                    // Execute the query
		                    ResultSet resultSet = statement.executeQuery();

		                    if (resultSet.next()) {
		                        // Login successful
		                        JOptionPane.showMessageDialog(null, "Login successful!");
		                        dispose(); // Dispose of the VIEWFOODFORUSER frame
		                        USERSMAIN usersmain = new USERSMAIN();
		                        usersmain.setVisible(true);;
		                    } else {
		                        // Invalid username or password
		                        JOptionPane.showMessageDialog(null, "Invalid username or password, or user type.");
		                    }
		                }
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(86, 86, 86));
		panel_1.setBounds(0, 0, 533, 53);
		MAINBACK.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(86, 86, 86));
		panel_1_1.setBounds(0, 297, 533, 53);
		MAINBACK.add(panel_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(100, 100, 100));
		panel_2.setBounds(462, 52, 71, 244);
		MAINBACK.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(100, 100, 100));
		panel_3.setBounds(0, 52, 71, 244);
		MAINBACK.add(panel_3);
	}
}
