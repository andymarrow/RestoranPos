package resturan;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import resturan.ALLTOSHOWMANAGER;
import resturan.CHOICEFORCUTOMERTOMMANGER;

import resturan.MANAGERLOGIN;


public class MANAGERSMAIN extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MANAGERSMAIN frame = new MANAGERSMAIN();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MANAGERSMAIN() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 464, 347);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.GREEN);
        panel.setBounds(40, 42, 367, 223);
        contentPane.add(panel);
        panel.setLayout(null);

        JButton ITEM = new JButton("ITEM");
        ITEM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ALLTOSHOWMANAGER manegment=new ALLTOSHOWMANAGER();
                dispose();
                manegment.setVisible(true);
                
            }
        });
        ITEM.setBounds(122, 61, 119, 23);
        panel.add(ITEM);
        
        
        
        

        JButton CUSTOMER = new JButton("CUSTOMER");
        CUSTOMER.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CHOICEFORCUTOMERTOMMANGER manegment1=new CHOICEFORCUTOMERTOMMANGER();
                dispose();
                manegment1.setVisible(true);
                
            }
        });
        CUSTOMER.setBounds(122, 131, 119, 23);
        panel.add(CUSTOMER);
        
        JButton BACK = new JButton("BACK");
        BACK.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	
        		MANAGERLOGIN enteraswho=new MANAGERLOGIN();
				
				enteraswho.setVisible(true);
        		setVisible(false); // Hide the current frame
                dispose(); // Release system resources of the current frame
        		
        	
        	
        	
        	
        	}
        });
        BACK.setBounds(268, 200, 89, 23);
        panel.add(BACK);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(128, 128, 128));
        panel_2.setBounds(0, 42, 42, 223);
        contentPane.add(panel_2);
        panel_2.setLayout(null);
        
        JPanel panel_2_1 = new JPanel();
        panel_2_1.setLayout(null);
        panel_2_1.setBackground(Color.GRAY);
        panel_2_1.setBounds(408, 42, 42, 223);
        contentPane.add(panel_2_1);
        
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setLayout(null);
        panel_1_1.setBackground(new Color(86, 86, 86));
        panel_1_1.setBounds(0, 265, 450, 45);
        contentPane.add(panel_1_1);
        
        JPanel panel_1_1_1 = new JPanel();
        panel_1_1_1.setLayout(null);
        panel_1_1_1.setBackground(new Color(86, 86, 86));
        panel_1_1_1.setBounds(0, 0, 450, 45);
        contentPane.add(panel_1_1_1);

       
       
    }
}