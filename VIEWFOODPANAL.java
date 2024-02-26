package resturan;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class VIEWFOODPANAL extends JPanel {

    public VIEWFOODPANAL() {
    	setSize(50,50);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblFoodNo = new JLabel("ITEM_NO");
        lblFoodNo.setBounds(40, 29, 68, 14);
        add(lblFoodNo);

        JLabel lblFoodName = new JLabel("ITEM_NAME");
        lblFoodName.setBounds(125, 29, 103, 14);
        add(lblFoodName);

        JLabel lblQuantity = new JLabel("QUANTITY");
        lblQuantity.setBounds(233, 29, 68, 14);
        add(lblQuantity);

        JLabel lblDescription = new JLabel("DESCRIPTION");
        lblDescription.setBounds(413, 29, 91, 14);
        add(lblDescription);

        JLabel lblPrice = new JLabel("PRICE");
        lblPrice.setBounds(663, 29, 49, 14);
        add(lblPrice);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(24, 56, 722, 403);
        panel.setLayout(null);

        // Retrieve data from the FOOD table and populate it in the GUI
        try {
            // Establish a database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Resturant_managment_system", "root", "1234ANDYsamson+");

            // Create a SQL statement
            Statement statement = connection.createStatement();

            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery("SELECT * FROM FOOD");

            int y = 0; // Vertical position for displaying data rows

            // Iterate through the result set and display the data in the GUI
            while (resultSet.next()) {
                int foodNo = resultSet.getInt("Food_no");
                String foodName = resultSet.getString("Food_Name");
                int quantity = resultSet.getInt("Quantity");
                String description = resultSet.getString("Description1");
                int price = resultSet.getInt("Price_no");

                JLabel lblFoodNoValue = new JLabel(String.valueOf(foodNo));
                lblFoodNoValue.setBounds(10, y, 68, 14);
                panel.add(lblFoodNoValue);

                JLabel lblFoodNameValue = new JLabel(foodName);
                lblFoodNameValue.setBounds(95, y, 103, 14);
                panel.add(lblFoodNameValue);

                JLabel lblQuantityValue = new JLabel(String.valueOf(quantity));
                lblQuantityValue.setBounds(203, y, 68, 14);
                panel.add(lblQuantityValue);

                JLabel lblDescriptionValue = new JLabel(description);
                lblDescriptionValue.setBounds(293, y, 300, 14);
                lblDescriptionValue.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        JOptionPane.showMessageDialog(null, description, "Full Description", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                panel.add(lblDescriptionValue);

                JLabel lblPriceValue = new JLabel(String.valueOf(price));
                lblPriceValue.setBounds(603, y, 49, 14);
                panel.add(lblPriceValue);

                y += 20; // Increment the vertical position for the next row
            }

            // Close the database connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(panel);

        JLabel lblNewLabel = new JLabel("CLICK ON THE DESCRIPTION ROW TO SEE THE WHOLE DESCRIPTION");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setForeground(Color.CYAN);
        lblNewLabel.setBounds(10, 369, 520, 21);
        panel.add(lblNewLabel);

        scrollPane.setBounds(24, 56, 688, 405);
        add(scrollPane);
        
    }
}