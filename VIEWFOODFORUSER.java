package resturan;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import resturan.USERSMAIN;

public class VIEWFOODFORUSER extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VIEWFOODFORUSER frame = new VIEWFOODFORUSER();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VIEWFOODFORUSER() {
        setTitle("VIEW FOOD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 770, 521);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblFoodNo = new JLabel("ITEM_NO");
        lblFoodNo.setBounds(40, 29, 68, 14);
        contentPane.add(lblFoodNo);

        JLabel lblFoodName = new JLabel("ITEM_NAME");
        lblFoodName.setBounds(125, 29, 103, 14);
        contentPane.add(lblFoodName);

        JLabel lblQuantity = new JLabel("QUANTITY");
        lblQuantity.setBounds(233, 29, 68, 14);
        contentPane.add(lblQuantity);

        JLabel lblDescription = new JLabel("DESCRIPTION");
        lblDescription.setBounds(413, 29, 91, 14);
        contentPane.add(lblDescription);

        JLabel lblPrice = new JLabel("PRICE");
        lblPrice.setBounds(663, 29, 49, 14);
        contentPane.add(lblPrice);

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

        JLabel lblNewLabel = new JLabel("CLICK ON THE DESCRIPTION ROW TO SEE WHOLE DESCRIPTION");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setForeground(Color.CYAN);
        lblNewLabel.setBounds(141, 369, 441, 21);
        panel.add(lblNewLabel);
        
        JButton BACK = new JButton("BACK");
        BACK.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		USERSMAIN enteraswho=new USERSMAIN();
				
				enteraswho.setVisible(true);
        		setVisible(false); // Hide the current frame
                dispose(); // Release system resources of the current frame
        		
        		
        		
        	}
        });
        BACK.setBounds(631, 378, 89, 23);
        panel.add(BACK);

        scrollPane.setBounds(24, 56, 722, 403);
        contentPane.add(scrollPane);
    }
}