package resturan;


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DELETEFORMANAGERPANAL extends JPanel {
    private JTextField ITEM_NAME1;
    private JTextField SUBSTRACT_NO1;

    public DELETEFORMANAGERPANAL() {
        setLayout(null);
        setBackground(Color.WHITE);

        JButton DELETE = new JButton("DELETE");
        DELETE.setBounds(250, 300, 89, 23);
        add(DELETE);
        DELETE.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String itemName = ITEM_NAME1.getText().trim();
                if (!itemName.isEmpty()) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        deleteFoodItem(itemName);
                        ITEM_NAME1.setText("");
                        SUBSTRACT_NO1.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the ITEM NAME field.", "Missing Information", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton SUBSTRACT = new JButton("SUBSTRACT");
        SUBSTRACT.setBounds(463, 300, 120, 23);
        add(SUBSTRACT);
        SUBSTRACT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String itemName = ITEM_NAME1.getText().trim();
                String substractValue = SUBSTRACT_NO1.getText().trim();
                if (!itemName.isEmpty() && !substractValue.isEmpty()) {
                    int quantity = getFoodQuantity(itemName);
                    if (quantity != -1) {
                        int substractAmount = Integer.parseInt(substractValue);
                        if (substractAmount > quantity) {
                            JOptionPane.showMessageDialog(null, "Not enough quantity to subtract.", "Insufficient Quantity", JOptionPane.WARNING_MESSAGE);
                        } else {
                            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to subtract " + substractAmount + " from the quantity?", "Confirm Subtraction", JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION) {
                                subtractFromFoodQuantity(itemName, substractAmount);
                                ITEM_NAME1.setText("");
                                SUBSTRACT_NO1.setText("");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Item does not exist.", "Item Not Found", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in both ITEM NAME and SUBSTRACT NUMBER fields.", "Missing Information", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JLabel ITEM_NAME = new JLabel("ITEM NAME");
        ITEM_NAME.setBounds(70, 200, 89, 14);
        add(ITEM_NAME);

        ITEM_NAME1 = new JTextField();
        ITEM_NAME1.setBounds(201, 200, 428, 20);
        add(ITEM_NAME1);
        ITEM_NAME1.setColumns(10);

        JLabel SUBSTRACT_NO = new JLabel("SUBSTRACT_NO");
        SUBSTRACT_NO.setBounds(70, 230, 121, 14);
        add(SUBSTRACT_NO);

        SUBSTRACT_NO1 = new JTextField();
        SUBSTRACT_NO1.setColumns(10);
        SUBSTRACT_NO1.setBounds(201, 230, 428, 20);
        add(SUBSTRACT_NO1);
    }

    // Method to delete a food item from the database
    private void deleteFoodItem(String itemName) {
        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Resturant_managment_system", "root", "1234ANDYsamson+");

            // Prepare the SQL statement
            String sql = "DELETE FROM FOOD WHERE Food_Name = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, itemName);

            // Execute the statement
            statement.executeUpdate();

            // Close the connection
            statement.close();
            conn.close();

            // Show success message
            JOptionPane.showMessageDialog(null, "Item deleted successfully.", "Deletion Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting item.", "Deletion Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to get the quantity of a food item from the database
    private int getFoodQuantity(String itemName) {
        try {
            // Establish a connection to the database
        	 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Resturant_managment_system", "root", "1234ANDYsamson+");



            // Prepare the SQL statement
            String sql = "SELECT Quantity FROM FOOD WHERE Food_Name = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, itemName);

            // Execute the statement
            ResultSet resultSet = statement.executeQuery();

            // Check if a result is returned
            if (resultSet.next()) {
                int quantity = resultSet.getInt("Quantity");

                // Close the connection
                resultSet.close();
                statement.close();
                conn.close();

                return quantity;
            } else {
                // Close the connection
                resultSet.close();
                statement.close();
                conn.close();

                return -1; // Item not found
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving item quantity.", "Quantity Retrieval Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    // Method to subtract a quantity from a food item in the database
    private void subtractFromFoodQuantity(String itemName, int subtractAmount) {
        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Resturant_managment_system", "root", "1234ANDYsamson+");

            // Prepare the SQL statement
            String sql = "UPDATE FOOD SET Quantity = Quantity - ? WHERE Food_Name = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, subtractAmount);
            statement.setString(2, itemName);

            // Execute the statement
            statement.executeUpdate();

            // Close the connection
            statement.close();
            conn.close();

            // Show success message
            JOptionPane.showMessageDialog(null, "Quantity subtracted successfully.", "Subtraction Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error subtracting quantity.", "Subtraction Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}