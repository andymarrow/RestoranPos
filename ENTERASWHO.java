package resturan;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import resturan.RESTURANT;

public class ENTERASWHO extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ENTERASWHO frame = new ENTERASWHO();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ENTERASWHO() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 410, 381);
        
        // Create a layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        setContentPane(layeredPane);
        
        // Create a JLabel to display the image
        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, 396, 344); // Set the position and size of the image label

        // Load the image using ImageIcon
        ImageIcon icon = new ImageIcon("C:\\Users\\Administrator\\Desktop\\for java project\\img\\3d-render-model-of-user-icon-png-modern-transparent-free-photo.jpg"); // Replace with the actual image file path
        imageLabel.setIcon(icon); // Set the icon for the image label

        layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER); // Add the image label to the layered pane
        
        // Create the main content pane
        contentPane = new JPanel();
        contentPane.setOpaque(false); // Make the content pane transparent
        contentPane.setBounds(0, 0, 396, 344);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        layeredPane.add(contentPane, JLayeredPane.PALETTE_LAYER);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("ENTER AS WHO ?");
        lblNewLabel.setForeground(Color.MAGENTA);
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        lblNewLabel.setBounds(101, 78, 295, 38);
        contentPane.add(lblNewLabel);

        JButton USER = new JButton("USER");
        USER.setFont(new Font("Dialog", Font.BOLD, 20));
        USER.setForeground(Color.MAGENTA);
        USER.setBackground(Color.GREEN);
        USER.setBounds(10, 186, 151, 38);
        contentPane.add(USER);

        JButton MANAGER = new JButton("MANAGER");
        MANAGER.setFont(new Font("Dialog", Font.BOLD, 20));
        MANAGER.setForeground(Color.MAGENTA);
        MANAGER.setBackground(Color.GREEN);
        MANAGER.setBounds(219, 186, 151, 38);
        contentPane.add(MANAGER);
        
        JButton BACK = new JButton("BACK");
        BACK.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		RESTURANT resturant=new RESTURANT();
        		resturant.setBounds(500, 100, 700, 400);
        		resturant.setVisible(true);
        		setVisible(false); // Hide the current frame
                dispose(); // Release system resources of the current frame
        		
        	}
        });
        BACK.setBounds(288, 310, 82, 23);
        contentPane.add(BACK);

        // ActionListener for the USER button
        USER.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LOGINFRAME loginframe = new LOGINFRAME();
                loginframe.setVisible(true);
                setVisible(false); // Hide the current frame
                dispose(); // Release system resources of the current frame
            }
        });

        // ActionListener for the MANAGER button
        MANAGER.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MANAGERLOGIN managerlogin = new MANAGERLOGIN();
                managerlogin.setVisible(true);
                setVisible(false); // Hide the current frame
                dispose(); // Release system resources of the current frame
            }
        });
    }
}