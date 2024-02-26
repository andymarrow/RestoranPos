package resturan;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import resturan.VIEWFOODPANAL;
import resturan.USERSMAIN;

public class USERORDER extends JFrame {

private JPanel contentPane;
private JTextField ITEM_NAME_TEXTFIELD;
private JTextField QUANTITY_TEXTFIELD;
private JTextField CANCEL_TEXTFIELD;
private JTextField TOTAL_PRICE_TEXT_FIELD;

private Connection connection;


private int cartItemCount = 0;
private int totalCost = 0;
private JTextField TOTAL_VAT_TEXTFIELD;
private JTextField FINAL_PRIZE_TEXTFIELD;


public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
        public void run() {
            try {
                USERORDER frame = new USERORDER();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
}

public USERORDER() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 1087, 646);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

    setContentPane(contentPane);
    contentPane.setLayout(null);

    JPanel viewfoodpanal = new VIEWFOODPANAL();
    viewfoodpanal.setBounds(0, 0, 701, 507);

    JPanel bigBOARD = new JPanel();
    bigBOARD.setBackground(new Color(255, 0, 255));
    bigBOARD.setBounds(10, 11, 701, 507);
    bigBOARD.add(viewfoodpanal);

    JLabel ITEMS_LEFT = new JLabel("ITEMS LEFT IN ELITE RESTAURANT");
    ITEMS_LEFT.setForeground(Color.CYAN);
    ITEMS_LEFT.setBounds(10, 11, 236, 14);
    viewfoodpanal.add(ITEMS_LEFT);
    contentPane.add(bigBOARD);
    bigBOARD.setLayout(null);

    JPanel cart = new JPanel();
    cart.setBackground(new Color(0, 255, 64));
    cart.setBounds(716, 11, 357, 507);
    contentPane.add(cart);
    cart.setLayout(null);

    JLabel ITEM_NAME = new JLabel("ITEM_NAME");
    ITEM_NAME.setBounds(10, 11, 85, 20);
    cart.add(ITEM_NAME);
    ITEM_NAME.setFont(new Font("Sitka Display", Font.BOLD, 15));

    JLabel PRICE = new JLabel("PRICE");
    PRICE.setBounds(208, 14, 96, 14);
    cart.add(PRICE);
    PRICE.setFont(new Font("Sitka Display", Font.BOLD, 15));

    JLabel TOTAL_COST = new JLabel("TOTAL_PRICE");
    TOTAL_COST.setBounds(10, 445, 143, 14);
    cart.add(TOTAL_COST);

    TOTAL_PRICE_TEXT_FIELD = new JTextField();
    TOTAL_PRICE_TEXT_FIELD.setColumns(10);
    TOTAL_PRICE_TEXT_FIELD.setBounds(101, 439, 203, 20);
    cart.add(TOTAL_PRICE_TEXT_FIELD);
    
    JLabel lblNewLabel = new JLabel("QUANTITY");
    lblNewLabel.setFont(new Font("Sitka Display", Font.BOLD, 15));
    lblNewLabel.setBounds(118, 14, 104, 14);
    cart.add(lblNewLabel);
    
    JLabel VAT_TEXTFIELD = new JLabel("VAT");
    VAT_TEXTFIELD.setFont(new Font("Sitka Display", Font.BOLD, 15));
    VAT_TEXTFIELD.setBounds(295, 14, 59, 14);
    cart.add(VAT_TEXTFIELD);
    
    JLabel TOTAL_VAT = new JLabel("TOTAL_VAT");
    TOTAL_VAT.setBounds(10, 408, 85, 14);
    cart.add(TOTAL_VAT);
    
    TOTAL_VAT_TEXTFIELD = new JTextField();
    TOTAL_VAT_TEXTFIELD.setColumns(10);
    TOTAL_VAT_TEXTFIELD.setBounds(101, 405, 203, 20);
    cart.add(TOTAL_VAT_TEXTFIELD);
    
    JLabel FINAL_PRICE = new JLabel("FINAL_PRICE");
    FINAL_PRICE.setBounds(10, 471, 85, 14);
    cart.add(FINAL_PRICE);
    
    FINAL_PRIZE_TEXTFIELD = new JTextField();
    FINAL_PRIZE_TEXTFIELD.setColumns(10);
    FINAL_PRIZE_TEXTFIELD.setBounds(101, 465, 203, 20);
    cart.add(FINAL_PRIZE_TEXTFIELD);

    JLabel ITEM_NAME_YOU_WANT = new JLabel("ITEM_NAME");
    ITEM_NAME_YOU_WANT.setBounds(185, 539, 75, 14);
    contentPane.add(ITEM_NAME_YOU_WANT);

    JLabel QUANTITY = new JLabel("QUANTITY");
    QUANTITY.setBounds(185, 572, 75, 14);
    contentPane.add(QUANTITY);

    ITEM_NAME_TEXTFIELD = new JTextField();
    ITEM_NAME_TEXTFIELD.setBounds(283, 536, 272, 20);
    contentPane.add(ITEM_NAME_TEXTFIELD);
    ITEM_NAME_TEXTFIELD.setColumns(10);

    QUANTITY_TEXTFIELD = new JTextField();
    QUANTITY_TEXTFIELD.setBounds(283, 569, 272, 20);
    contentPane.add(QUANTITY_TEXTFIELD);
    QUANTITY_TEXTFIELD.setColumns(10);

    CANCEL_TEXTFIELD = new JTextField();
    CANCEL_TEXTFIELD.setBounds(668, 536, 203, 20);
    contentPane.add(CANCEL_TEXTFIELD);
    CANCEL_TEXTFIELD.setColumns(10);

    JButton ADD_TO_CART = new JButton("ADD TO CART");
    ADD_TO_CART.setBounds(599, 568, 134, 23);
    contentPane.add(ADD_TO_CART);

    //int updatedQuantity = 0; // Declare as instance variable
    String itemName = ""; // Declare as instance variable
    int availableQuantity = 0; 
    double[] totalVAT = { 0.0 };
    
    ADD_TO_CART.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String itemName = ITEM_NAME_TEXTFIELD.getText();
            String quantityText = QUANTITY_TEXTFIELD.getText();

            int quantity = 0;
            try {
                quantity = Integer.parseInt(quantityText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid quantity. Please enter a valid number.");
                return;
            }

            if (quantity <= 0) {
                JOptionPane.showMessageDialog(null, "Quantity must be greater than zero.");
                return;
            }

            try {
                // Connect to the database
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Resturant_managment_system", "root", "1234ANDYsamson+");

                // Fetch the item details from the database
                String query = "SELECT * FROM FOOD WHERE Food_Name = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, itemName);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int availableQuantity = resultSet.getInt("Quantity");
                    int price = resultSet.getInt("Price_no");

                    if (quantity <= availableQuantity) {
                        // Calculate the total cost
                        int itemTotalCost = price * quantity;

                        // Update the total cost
                        totalCost += itemTotalCost;

                        // Update the total cost text field
                        JLabel itemLabel = new JLabel(itemName);
                        itemLabel.setBounds(10, 36 + cartItemCount * 20, 134, 14);
                        cart.add(itemLabel);

                        JLabel priceLabel = new JLabel(String.valueOf(price));
                        priceLabel.setBounds(230, 36 + cartItemCount * 20, 96, 14);
                        cart.add(priceLabel);

                        JLabel quantityLabel = new JLabel(String.valueOf(quantity));
                        quantityLabel.setBounds(140, 36 + cartItemCount * 20, 104, 14);
                        cart.add(quantityLabel);

                        // Update the position of the total cost label
                        //TOTAL_COST.setBounds(70, 36 + cartItemCount * 20, 143, 14);
                     // Calculate the VAT amount
                        double vatAmount = itemTotalCost * 0.1; // Assuming VAT is 10% of the item total cost
                        // Calculate the total cost including VAT
                        double totalCostWithVAT = itemTotalCost + vatAmount;
                        // Update the total cost text field with VAT
                        TOTAL_PRICE_TEXT_FIELD.setText(String.format("%.2f $BIRR", totalCostWithVAT));
                        // Create a label for the VAT information
                        JLabel vatLabel = new JLabel("VAT: " + String.format("%.2f $BIRR", vatAmount));
                        vatLabel.setBounds(260, 36 + cartItemCount * 20, 143, 14);
                        cart.add(vatLabel);
                        // Update the position of the total cost label
                        //TOTAL_COST.setBounds(70, 36 + (cartItemCount + 1) * 20, 143, 14);
                     // Add vatAmount to totalVAT
                        totalVAT[0] += vatAmount;                    
                     // Calculate the final prize (total price + total VAT)
                        double finalPrize = totalCost + totalVAT[0];
                        // Update the FINAL_PRIZE_TEXTFIELD
                        FINAL_PRIZE_TEXTFIELD.setText(String.format("%.2f $BIRR", finalPrize));
                        // Increment the cart item count
                        cartItemCount += 1;                    
                        // Update the total cost text field
                        TOTAL_PRICE_TEXT_FIELD.setText(String.valueOf(totalCost+"  $BIRR"));                    
                        TOTAL_VAT_TEXTFIELD.setText(String.format("%.2f $BIRR", totalVAT[0]));
                  // Decrement the available quantity in the database
                        int updatedQuantity = availableQuantity - quantity;
                        String updateQuery = "UPDATE FOOD SET Quantity = ? WHERE Food_Name = ?";
                        PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                        updateStatement.setInt(1, updatedQuantity);
                        updateStatement.setString(2, itemName);
                        updateStatement.executeUpdate();
                        // Increment the cart item count
                        cartItemCount++;
                        // Repaint the cart panel to reflect the changes
                        cart.revalidate();
                        cart.repaint();
                        // Clear the input fields
                        ITEM_NAME_TEXTFIELD.setText("");
                        QUANTITY_TEXTFIELD.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Not enough food in the restaurant.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Item not found in the restaurant.");
                }

                // Close the database connection
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    });

    JButton CANCEL_BUTTON = new JButton("CANCEL ORDER");
    CANCEL_BUTTON.setBounds(904, 535, 159, 23);
    contentPane.add(CANCEL_BUTTON);
    
    JButton BACK = new JButton("BACK");
    BACK.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		
    		USERSMAIN enteraswho=new USERSMAIN();
			
			enteraswho.setVisible(true);
    		setVisible(false); // Hide the current frame
            dispose(); // Release system resources of the current frame
    		
    		
    		
    	}
    });
    BACK.setBounds(960, 568, 89, 23);
    contentPane.add(BACK);

    CANCEL_BUTTON.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String cancelText = CANCEL_TEXTFIELD.getText();

            if (cancelText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter an item name to cancel.");
                return;
            }

            Component[] components = cart.getComponents();
            boolean itemRemoved = false;

            for (int i = 0; i < components.length; i ++) {
                Component component = components[i];

                if (component instanceof JLabel) {
                    JLabel label = (JLabel) component;
                    if (label.getText().equals(cancelText)) {
                        JLabel quantityLabel = (JLabel) components[i + 1]; // Get the corresponding quantity label
                        JLabel priceLabel = (JLabel) components[i + 2]; // Get the corresponding price label
                        JLabel vatLabel = (JLabel) components[i + 3]; // Get the corresponding VAT label
                        
                        int canceledQuantity = Integer.parseInt(quantityLabel.getText());
                        int canceledPrice = Integer.parseInt(priceLabel.getText());
                        int canceledTotalCost = canceledQuantity * canceledPrice;

                        // Subtract the canceled total cost from the total cost
                        totalCost -= canceledTotalCost;
                        
                        // Subtract the canceled VAT amount from the total VAT
                        double canceledVATAmount = canceledTotalCost * 0.1; // Assuming VAT is 10% of the canceled total cost
                        totalVAT[0] -= canceledVATAmount;
                        
                        // Add the canceled quantity back to the available quantity
                        int updatedQuantity = canceledQuantity + availableQuantity;

                        try {
                            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Resturant_managment_system", "root", "1234ANDYsamson+");
                            String updateQuery = "UPDATE FOOD SET Quantity = ? WHERE Food_Name = ?";

                            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                            updateStatement.setInt(1, updatedQuantity);
                            updateStatement.setString(2, itemName);
                            updateStatement.executeUpdate();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            // Handle the exception (e.g., show an error message)
                        }

                        // Remove the labels from the cart
                        cart.remove(label);
                        cart.remove(quantityLabel);
                        cart.remove(priceLabel);
                        cart.remove(vatLabel);
                        
                        itemRemoved = true;
                        break;
                    }
                }
            }

            if (itemRemoved) {
                // Update the position of the total cost label
                //TOTAL_COST.setBounds(70, 36 + (cartItemCount - 1) * 20, 143, 14);

                // Update the total cost text field
                TOTAL_PRICE_TEXT_FIELD.setText(String.valueOf(totalCost + " $BIRR"));
                
             // Update the total VAT text field
                TOTAL_VAT_TEXTFIELD.setText(String.format("%.2f $BIRR", totalVAT[0]));
             // Recalculate the final prize
                double finalPrize = totalCost + totalVAT[0];
                
                // Update the final prize text field
                FINAL_PRIZE_TEXTFIELD.setText(String.format("%.2f $BIRR", finalPrize));
                // Decrement the cart item count
                cartItemCount--;

                // Reposition the remaining items in the cart panel work it out later because repositioning the below also repositions the main above
//                for (int i = 0; i < cartItemCount; i++) {
//                    JLabel itemLabel = (JLabel) components[i * 3];
//                    JLabel quantityLabel = (JLabel) components[i * 3 + 1];
//                    JLabel priceLabel = (JLabel) components[i * 3 + 2];
    //
//                    itemLabel.setBounds(10, 36 + i * 20, 134, 14);
//                    quantityLabel.setBounds(140, 36 + i * 20, 104, 14);
//                    priceLabel.setBounds(230, 36 + i * 20, 96, 14);
//                }

                // Repaint the cart panel to reflect the changes
                cart.revalidate();
                cart.repaint();

                // Clear the cancel text field
                CANCEL_TEXTFIELD.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Item not found in the cart.");
            }
        }
    });
}
}