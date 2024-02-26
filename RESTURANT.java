package resturan;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Image;
import resturan.ENTERASWHO;
import java.sql.ResultSet;

public class RESTURANT extends JFrame {
    JLabel message;
    JLabel nameLabel, passwordLabel, userTypeLabel;
    JTextField nameField;
    JPasswordField passwordField;
    JComboBox<String> userTypeComboBox;
    JButton registerButton;
    Container container;
    JButton loginButton; 
    
    
    
    public RESTURANT() {
        message = new JLabel("Register a new User :");
        message.setFont(new Font("Courier", Font.BOLD, 20));
        nameLabel = new JLabel("Name");
        nameField = new JTextField();
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        userTypeLabel = new JLabel("User Type");
        userTypeComboBox = new JComboBox<String>();
        userTypeComboBox.addItem("Manager");
        
        userTypeComboBox.addItem("User");
        registerButton = new JButton("Register");
        container = getContentPane();
        container.setLayout(null);
        loginButton = new JButton("Login"); // Create the login button
        setLoginButtonProperties();
        container.add(loginButton); // Add the login button to the container
        addLoginButtonListener(); // Register ActionListener for the login button
        setBounds();
        addComponents();
        addListeners();
        
        JLabel imageLabel = createImageLabel();
        container.add(imageLabel);
    }

    private JLabel createImageLabel() {
    	// Load the image using ImageIcon
        ImageIcon icon = new ImageIcon("C:\\Users\\Administrator\\Desktop\\for java project\\img\\5645449.png"); // Replace with the actual image file path

        // Get the original image from the ImageIcon
        Image image = icon.getImage();

        // Calculate the desired width and height for the resized image
        int desiredWidth = 300; // Replace with the desired width
        int desiredHeight = 250; // Replace with the desired height

        // Resize the image using getScaledInstance()
        Image resizedImage = image.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);

        // Create a new ImageIcon from the resized image
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Calculate the desired position for the image
        int x = 350; // Replace with the desired x-coordinate
        int y = 60; // Replace with the desired y-coordinate

        // Create a JLabel to display the image and set its position and size
        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setBounds(x, y, desiredWidth, desiredHeight);

        return imageLabel;
    }

    public void setBounds() {
        loginButton.setBounds(130, 260, 200, 30);
        message.setBounds(50, 10, 600, 30);
        nameLabel.setBounds(50, 60, 100, 30);
        nameField.setBounds(130, 60, 200, 30);
        passwordLabel.setBounds(50, 110, 100, 30);
        passwordField.setBounds(130, 110, 200, 30);
        userTypeLabel.setBounds(50, 160, 100, 30);
        userTypeComboBox.setBounds(130, 160, 200, 30);
        registerButton.setBounds(130, 210, 200, 30);
      
        message.setForeground(Color.WHITE);
        nameLabel.setForeground(Color.CYAN);
        passwordLabel.setForeground(Color.CYAN);
        userTypeLabel.setForeground(Color.CYAN);

        registerButton.setBackground(Color.GREEN);
    }

    public void addComponents() {
        container.add(loginButton);
        container.add(message);
        container.add(nameLabel);
        container.add(nameField);
        container.add(passwordLabel);
        container.add(passwordField);
        container.add(userTypeLabel);
        container.add(userTypeComboBox);
        container.add(registerButton);
        container.setBackground(Color.BLACK);
    }

    public void setLoginButtonProperties() {
        // Set properties for the login button
        loginButton.setBackground(Color.GREEN);
    }

    public void addLoginButtonListener() {
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform login functionality here
                // You can open a new JFrame for login or handle it in any other way
                // For example, you can create a new instance of the MANAGERLOGIN class and make it visible
            	ENTERASWHO enteraswho = new ENTERASWHO();
                enteraswho.setVisible(true);
                
                enteraswho.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                enteraswho.setResizable(false);
                dispose();
            }
        });
    }

    public void addListeners() {
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String password = new String(passwordField.getPassword());
                String userType = (String) userTypeComboBox.getSelectedItem();

                // Establish a connection to the MySQL database
                String url = "jdbc:mysql://localhost:3306/Resturant_managment_system";
                String username = "root";
                String password1 = "1234ANDYsamson+";

                try (Connection connection = DriverManager.getConnection(url, username, password1)) {
                    // Prepare the SQL SELECT statement to check if the user already exists
                    String checkSql = "SELECT * FROM USERINFO WHERE UserName = ? AND Password1 = ?";
                    try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
                        checkStatement.setString(1, name);
                        checkStatement.setString(2, password);
                        ResultSet resultSet = checkStatement.executeQuery();

                        if (resultSet.next()) {
                            // User already exists, show a dialog box
                            JOptionPane.showMessageDialog(null, "User already exists. Please choose a different username and password.");
                        } else {
                            // Prepare the SQL INSERT statement
                            String insertSql = "INSERT INTO USERINFO (UserName, Password1, UserType) VALUES (?, ?, ?)";
                            try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                                insertStatement.setString(1, name);
                                insertStatement.setString(2, password);
                                insertStatement.setString(3, userType);

                                // Execute the SQL statement
                                int rowsAffected = insertStatement.executeUpdate();

                                if (rowsAffected > 0) {
                                    JOptionPane.showMessageDialog(null, "Registration successful. Data inserted into the database.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Registration failed. No data inserted into the database.");
                                }//////////////////////////////
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    public static void main(String[] args) {
        RESTURANT frame = new RESTURANT();
        frame.setTitle("User Register Form");
        frame.setVisible(true);
        frame.setBounds(500, 100, 700, 400);
        frame.setBackground(Color.GREEN);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//////
        frame.setResizable(false);
    }
}