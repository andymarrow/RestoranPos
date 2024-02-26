package resturan;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import resturan.ENTERASWHO;

import resturan.USERORDER;

public class USERSMAIN extends JFrame {

    private JPanel contentPane;
    private VIEWFOODFORUSER viewFoodFrame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    USERSMAIN frame = new USERSMAIN();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public USERSMAIN() {
        setTitle("HELLO USER");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 492, 525);
        
        // Create a layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        setContentPane(layeredPane);
        
        // Create a JLabel to display the image
        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(-6, 0, 696, 500); // Set the position and size of the image label

        // Load the image using ImageIcon
        ImageIcon icon = new ImageIcon("C:\\Users\\Administrator\\Desktop\\for java project\\img\\c8a7c3a22a8f1e23c11bfe498146f2d6.jpg"); // Replace with the actual image file path
        imageLabel.setIcon(icon); // Set the icon for the image label

        layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER); // Add the image label to the layered pane
        
        
        
     // Create the main content pane
        contentPane = new JPanel();
        contentPane.setOpaque(false); // Make the content pane transparent
        contentPane.setBounds(-75, 27, 583, 432);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        layeredPane.add(contentPane, JLayeredPane.PALETTE_LAYER);
        contentPane.setLayout(null);
        
        
        JButton BACK = new JButton("BACK");
		BACK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ENTERASWHO enteraswho=new ENTERASWHO();
				
				enteraswho.setVisible(true);
        		setVisible(false); // Hide the current frame
                dispose(); // Release system resources of the current frame
				
				
			}
		});
		BACK.setBounds(450, 400, 89, 23);
		BACK.setBackground(new Color(0, 0, 0));
		BACK.setForeground(Color.MAGENTA);
		contentPane.add(BACK);
		

        JButton VIEW = new JButton("VIEW");
        VIEW.setBackground(new Color(0, 0, 0));
        VIEW.setForeground(Color.MAGENTA);
        VIEW.setBounds(97, 201, 198, 45);
        contentPane.add(VIEW);
        VIEW.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create an instance of VIEWFOODFORUSER
                viewFoodFrame = new VIEWFOODFORUSER();

                // Hide the USERSMAIN frame
                setVisible(false);

                // Show the VIEWFOODFORUSER frame
                viewFoodFrame.setVisible(true);
            }
        });

        JButton ORDER = new JButton("ORDER");
        ORDER.setBackground(new Color(0, 0, 0));
        ORDER.setForeground(Color.MAGENTA);
        ORDER.setBounds(346, 201, 190, 45);
        contentPane.add(ORDER);
        
        ORDER.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create an instance of VIEWFOODFORUSER
            	USERORDER stg= new USERORDER();

               dispose();
                stg.setVisible(true);

                
            }
        });
    }
}