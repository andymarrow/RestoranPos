package resturan;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import resturan.CUSTOMERVIEWFORMANAGER;
import resturan.CUSTOMERADDFORMANAGER;
import resturan.CUSTOMERUPDATEFORMANAGER;

import resturan.MANAGERSMAIN;

public class CHOICEFORCUTOMERTOMMANGER extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CHOICEFORCUTOMERTOMMANGER frame = new CHOICEFORCUTOMERTOMMANGER();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CHOICEFORCUTOMERTOMMANGER() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 480, 500);
        
        
        // Create a layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        setContentPane(layeredPane);
        
        // Create a JLabel to display the image
        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, 696, 500); // Set the position and size of the image label

        // Load the image using ImageIcon
        ImageIcon icon = new ImageIcon("C:\\Users\\Administrator\\Desktop\\for java project\\img\\1967ddeb64c46314f41e085beafd12a8.jpg"); // Replace with the actual image file path
        imageLabel.setIcon(icon); // Set the icon for the image label

        layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER); // Add the image label to the layered pane
        
        
        
     // Create the main content pane
        contentPane = new JPanel();
        contentPane.setOpaque(false); // Make the content pane transparent
        contentPane.setBounds(32, 23, 583, 432);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        layeredPane.add(contentPane, JLayeredPane.PALETTE_LAYER);
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
        BACK.setBounds(320, 350, 89, 23);
        contentPane.add(BACK);

        JLabel lblNewLabel = new JLabel("CHOOSE WHAT TO DO WITH CUSTOMER : ");
        lblNewLabel.setForeground(Color.MAGENTA);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(30, 150, 400, 38);
        contentPane.add(lblNewLabel);

        JButton USER = new JButton("VIEW");
        USER.setForeground(Color.MAGENTA);
        USER.setBackground(Color.GREEN);
        USER.setBounds(20, 250, 121, 23);
        contentPane.add(USER);

        JButton MANAGER = new JButton("ADD");
        MANAGER.setForeground(Color.MAGENTA);
        MANAGER.setBackground(Color.GREEN);
        MANAGER.setBounds(170, 250, 107, 23);
        contentPane.add(MANAGER);

        JButton CHEF = new JButton("UPDATE");
        CHEF.setForeground(Color.MAGENTA);
        CHEF.setBackground(Color.GREEN);
        CHEF.setBounds(320, 250, 107, 23);
        contentPane.add(CHEF);

        // ActionListener for the USER button
        USER.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CUSTOMERVIEWFORMANAGER loginframe = new CUSTOMERVIEWFORMANAGER();
                loginframe.setVisible(true);
                setVisible(false); // Hide the current frame
                dispose(); // Release system resources of the current frame
            }
        });

        // ActionListener for the MANAGER button
        MANAGER.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CUSTOMERADDFORMANAGER managerlogin = new CUSTOMERADDFORMANAGER();
                managerlogin.setVisible(true);
                setVisible(false); // Hide the current frame
                dispose(); // Release system resources of the current frame
            }
        });
        
        CHEF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CUSTOMERUPDATEFORMANAGER managerlogin1 = new CUSTOMERUPDATEFORMANAGER();
                managerlogin1.setVisible(true);
                setVisible(false); // Hide the current frame
                dispose(); // Release system resources of the current frame
            }
        });
    }
}