package resturan;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import java.awt.Font;

import resturan.MANAGERSMAIN;

public class CUSTOMERVIEWFORMANAGER extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CUSTOMERVIEWFORMANAGER frame = new CUSTOMERVIEWFORMANAGER();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CUSTOMERVIEWFORMANAGER() {
        setTitle("VIEW FOOD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 770, 521);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton BACK = new JButton("BACK");
        BACK.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MANAGERSMAIN enteraswho=new MANAGERSMAIN();
				
				enteraswho.setVisible(true);
        		setVisible(false); // Hide the current frame
                dispose(); // Release system resources of the current frame
        		
			
        		
        		
        		
        	}
        });
        BACK.setBounds(590, 420, 89, 23);
        contentPane.add(BACK);

        JLabel lblFoodNo = new JLabel("Customer_id");
        lblFoodNo.setBounds(40, 29, 80, 14);
        contentPane.add(lblFoodNo);

        JLabel lblFoodName = new JLabel("Customer_name");
        lblFoodName.setBounds(230, 29, 103, 14);
        contentPane.add(lblFoodName);

        JLabel lblQuantity = new JLabel("Contact_no1");
        lblQuantity.setBounds(450, 29, 80, 14);
        contentPane.add(lblQuantity);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(24, 56, 722, 403);
        contentPane.add(panel);
        panel.setLayout(null);

        // Retrieve data from the FOOD table and populate it in the GUI
        try {
            // Establish a database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Resturant_managment_system", "root", "1234ANDYsamson+");

            // Create a SQL statement
            Statement statement = connection.createStatement();

            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CUSTOMER");

            int y = 0; // Vertical position for displaying data rows
            
            // Iterate through the result set and display the data in the GUI
            while (resultSet.next()) {
            	String foodNo = resultSet.getString("Customer_id");
                String foodName = resultSet.getString("Customer_name");
                int quantity = resultSet.getInt("Contact_no1");
                

                JLabel lblFoodNoValue = new JLabel(String.valueOf(foodNo));
                lblFoodNoValue.setBounds(10, y, 68, 14);
                panel.add(lblFoodNoValue);

                JLabel lblFoodNameValue = new JLabel(foodName);
                lblFoodNameValue.setBounds(210, y, 130, 14);
                panel.add(lblFoodNameValue);

                JLabel lblQuantityValue = new JLabel(String.valueOf(quantity));
                lblQuantityValue.setBounds(420, y, 68, 14);
                panel.add(lblQuantityValue);

                

                y += 35; // Increment the vertical position for the next row
            }

            // Close the database connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(panel);

        JLabel lblNewLabel = new JLabel("HERE IS ALL THE CUSOMERS ENROLLED");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setForeground(Color.CYAN);
        lblNewLabel.setBounds(141, 369, 441, 21);
        panel.add(lblNewLabel);

        scrollPane.setBounds(24, 56, 722, 403);
        contentPane.add(scrollPane);
    }
}