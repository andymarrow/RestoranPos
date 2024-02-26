package resturan;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

public class UPDATEITEMFORMANAGER extends JFrame {

    private JPanel contentPane;
    private JTextField textField_4;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UPDATEITEMFORMANAGER frame = new UPDATEITEMFORMANAGER();
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
    public UPDATEITEMFORMANAGER() {
    	
    	setTitle("UPDATE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 604, 522);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel FOOD_NO = new JLabel("ITEM_NO :");
        FOOD_NO.setBounds(54, 49, 101, 14);
        contentPane.add(FOOD_NO);

        JLabel FOOD_NAME = new JLabel("ITEM_NAME :");
        FOOD_NAME.setBounds(54, 89, 123, 14);
        contentPane.add(FOOD_NAME);

        JLabel QUANTITY = new JLabel("QUANTITY :");
        QUANTITY.setBounds(54, 129, 101, 14);
        contentPane.add(QUANTITY);

        JLabel DESCRIPTION = new JLabel("DESCRIPTION :");
        DESCRIPTION.setBounds(54, 188, 123, 14);
        contentPane.add(DESCRIPTION);

        JLabel PRICE_NO = new JLabel("PRICE_NO :");
        PRICE_NO.setBounds(54, 353, 101, 14);
        contentPane.add(PRICE_NO);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(180, 346, 337, 28);
        contentPane.add(textField_4);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(180, 183, 337, 152);
        contentPane.add(textArea);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(180, 122, 337, 28);
        contentPane.add(textField);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(180, 82, 337, 28);
        contentPane.add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(180, 42, 337, 28);
        contentPane.add(textField_2);

        JButton ADD = new JButton("UPDATE");
        ADD.setBounds(273, 424, 89, 23);
        contentPane.add(ADD);
        JLabel selectLabel = new JLabel("PLEASE SELECT THE ITEM YOU WANT TO UPDATE");
        selectLabel.setBounds(160, 450, 500, 30);
        selectLabel.setForeground(Color.CYAN);
        selectLabel.setOpaque(true);
        contentPane.add(selectLabel);
        

        ADD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the input values from the text fields and text area
                String foodNo = textField_2.getText();
                String foodName = textField_1.getText();
                String quantity = textField.getText();
                String description = textArea.getText();
                String price = textField_4.getText();

                // Check if all fields are filled
                if (foodNo.isEmpty() || foodName.isEmpty() || quantity.isEmpty() || description.isEmpty() || price.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Insert the data into the MySQL table
                try {
                    // Establish a database connection
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Resturant_managment_system", "root", "1234ANDYsamson+");

                    // Create a SQL statement
                    String query = "SELECT * FROM FOOD WHERE Food_no = ? AND Food_Name = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, foodNo);
                    statement.setString(2, foodName);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        // Item found,continue with updating the existing item
                        String updateQuery = "UPDATE FOOD SET Quantity = ?, Description1 = ?, Price_no = ? WHERE Food_no = ? AND Food_Name = ?";
                        PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                        updateStatement.setString(1, quantity);
                        updateStatement.setString(2, description);
                        updateStatement.setString(3, price);
                        updateStatement.setString(4, foodNo);
                        updateStatement.setString(5, foodName);
                        int rowsUpdated = updateStatement.executeUpdate();
                        updateStatement.close();

                        if (rowsUpdated > 0) {
                            JOptionPane.showMessageDialog(null, "Item updated successfully");
                        } else  {
                            JOptionPane.showMessageDialog(null, "Failed to update item", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        // Item not found
                        JOptionPane.showMessageDialog(null, "Item not found in the list", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    resultSet.close();
                    statement.close();
                    connection.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "An error occurred", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}