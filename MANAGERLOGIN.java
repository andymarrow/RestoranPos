package resturan;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
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
import resturan.ENTERASWHO;

import resturan.MANAGERSMAIN;



import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class MANAGERLOGIN extends JFrame {

	private JPanel MAINBACK;
	private JTextField textField;
	private JPasswordField passwordField;
	 private JTextField userTypeField; 

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
					MANAGERLOGIN frame = new MANAGERLOGIN();
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
	public MANAGERLOGIN() {
		setForeground(Color.DARK_GRAY);
		setTitle("WElCOME MANAGER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 434);
		
		
		
		MAINBACK = new JPanel();
		MAINBACK.setBackground(Color.LIGHT_GRAY);
		MAINBACK.setBorder(null);

		setContentPane(MAINBACK);
		MAINBACK.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(68, 51, 461, 295);
		MAINBACK.add(panel);
		panel.setLayout(null);
		
		JButton Login = new JButton("Login");
		Login.setBounds(186, 246, 118, 25);
		panel.add(Login);
		Login.setForeground(Color.GREEN);
		Login.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(230, 140, 196, 27);
		panel.add(passwordField);
		passwordField.setBackground(Color.WHITE);
		
		JLabel Password = new JLabel("Password :");
		Password.setBounds(61, 136, 90, 30);
		panel.add(Password);
		Password.setForeground(UIManager.getColor("CheckBox.foreground"));
		Password.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setBounds(230, 76, 196, 27);
		panel.add(textField);
		textField.setBackground(Color.WHITE);
		textField.setColumns(10);
		
		JLabel UserName = new JLabel("MANAGER Name :");
		UserName.setBounds(49, 72, 209, 30);
		panel.add(UserName);
		UserName.setForeground(UIManager.getColor("CheckBox.foreground"));
		UserName.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton BACK = new JButton("BACK");
		BACK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				ENTERASWHO enteraswho=new ENTERASWHO();
				
				enteraswho.setVisible(true);
        		setVisible(false); // Hide the current frame
                dispose(); // Release system resources of the current frame
        		
			
			
			
			
			}
		});
		BACK.setBounds(337, 248, 89, 23);
		panel.add(BACK);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(86, 86, 86));
		panel_1.setBounds(0, 0, 612, 50);
		MAINBACK.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(86, 86, 86));
		panel_1_1.setBounds(0, 347, 612, 50);
		MAINBACK.add(panel_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(128, 128, 128));
		panel_2.setBounds(0, 51, 70, 295);
		MAINBACK.add(panel_2);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.GRAY);
		panel_2_1.setBounds(529, 51, 70, 295);
		MAINBACK.add(panel_2_1);
		
		
    	
	
	
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
		                    statement.setString(1, username);//the 1 2 and 3  here and on the next code sets the place holder place the 1 means the first place holder ?
		                    statement.setString(2, password);
		                    statement.setString(3, "MANAGER");

		                    // Execute the query
		                    ResultSet resultSet = statement.executeQuery();//the resultset retrives data by excuting query and checks if they mach by .next walking

		                    if (resultSet.next()) {//the next acts like a cursor moving to the next row till it finds one that matches
		                        // Login successful
		                        JOptionPane.showMessageDialog(null, "Login successful!");
		                        dispose(); // Dispose of the MANAGERLOGIN frame
		                        MANAGERSMAIN managersmain = new MANAGERSMAIN();
		                        managersmain.setVisible(true);
		                    } else {
		                        // Invalid username, password, or user type
		                        JOptionPane.showMessageDialog(null, "Invalid username, password, or user type.");
		                    }
		                }
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});

		
	}
}
