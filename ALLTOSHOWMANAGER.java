package resturan;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import resturan.UPDATEPANAL;
import resturan.ADDPANAL;
import resturan.VIEWFOODPANAL;

import resturan.DELETEFORMANAGERPANAL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import resturan.MANAGERSMAIN;
import javax.swing.JTextArea;

public class ALLTOSHOWMANAGER extends JFrame {

    private JPanel contentPane;
    private JPanel mainbar;
    private CardLayout cardLayout;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ALLTOSHOWMANAGER frame = new ALLTOSHOWMANAGER();
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
    public ALLTOSHOWMANAGER() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 919, 752);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel sidebar = new JPanel();
        sidebar.setBackground(Color.WHITE);
        sidebar.setBounds(0, 84, 129, 605);
        contentPane.add(sidebar);
        sidebar.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.CYAN);
        panel_1.setBounds(0, 0, 905, 85);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JLabel ELITE_RESETURANT = new JLabel("ELITE RESETURANT");
        ELITE_RESETURANT.setForeground(Color.MAGENTA);
        ELITE_RESETURANT.setFont(new Font("Segoe UI Emoji", Font.BOLD, 30));
        ELITE_RESETURANT.setBounds(256, 27, 304, 36);
        panel_1.add(ELITE_RESETURANT);

        mainbar = new JPanel();
        mainbar.setBackground(Color.GREEN);
        mainbar.setBounds(128, 84, 777, 605);
        contentPane.add(mainbar);
        cardLayout = new CardLayout();
        mainbar.setLayout(cardLayout);

        JButton btnView = new JButton("VIEW");
        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //cardLayout.show(mainbar, "viewPanel");
            	cardLayout.show(mainbar, "viewPanel");
            }
        });
        btnView.setBounds(10, 38, 110, 50);
        sidebar.add(btnView);

       

        JButton btnUpdate = new JButton("UPDATE");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //cardLayout.show(mainbar, "updatePanel");
                cardLayout.show(mainbar, "updatepanal1");
            }
        });
        btnUpdate.setBounds(10, 244, 110, 50);
        sidebar.add(btnUpdate);
        
        JButton btnAdd = new JButton("ADD");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainbar, "addPanel");
            }
        });
        btnAdd.setBounds(10, 152, 110, 50);
        sidebar.add(btnAdd);

        JButton btnDelete = new JButton("DELETE");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainbar, "deletePanel");
            }
        });
        btnDelete.setBounds(10, 364, 103, 50);
        sidebar.add(btnDelete);
        
        JButton BACK = new JButton("BACK");
        BACK.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MANAGERSMAIN enteraswho=new MANAGERSMAIN();
				
				enteraswho.setVisible(true);
        		setVisible(false); // Hide the current frame
                dispose(); // Release system resources of the current frame
        		
			
        		
        		
        		
        	}
        });
        BACK.setBounds(30, 485, 89, 23);
        sidebar.add(BACK);

        //JPanel viewPanel = new JPanel();
        //viewPanel.setBackground(Color.RED);
       // mainbar.add(viewPanel, "viewPanel");

        //JPanel addPanel = new JPanel();
        //addPanel.setBackground(Color.BLUE);
        //mainbar.add(addPanel, "addPanel");

        //JPanel updatePanel = new JPanel();
        JPanel updatepanal1=new UPDATEPANAL();
        //updatePanel.setBackground(Color.YELLOW);
        //mainbar.add(updatePanel, "updatePanel");
        mainbar.add(updatepanal1,"updatepanal1");
        
        JPanel addPanel=new ADDPANAL();
        mainbar.add(addPanel,"addPanel");
        
        JPanel viewPanel=new VIEWFOODPANAL();
        mainbar.add(viewPanel,"viewPanel");
        
        DELETEFORMANAGERPANAL deletePanel = new DELETEFORMANAGERPANAL();
        mainbar.add(deletePanel, "deletePanel");
        
        JTextArea txtrWantToDelete = new JTextArea();
        txtrWantToDelete.setFont(new Font("Monospaced", Font.PLAIN, 15));
        txtrWantToDelete.setForeground(Color.BLACK);
        txtrWantToDelete.setText("WANT TO DELETE                   THEN WRITE THE ITEM NAME AND THEN HIT DELETE\r\n\r\n\r\nWANT TO DECREASE              THEN FILL BOTH THE REQUIRED FIELDS AND HIT SUBSTRACT\r\nTHE NUMBER OF\r\nITEM");
        txtrWantToDelete.setBounds(10, 11, 757, 143);
        deletePanel.add(txtrWantToDelete);
        
     
        
       

        //JPanel deletePanel = new JPanel();
        //deletePanel.setBackground(Color.ORANGE);
        //mainbar.add(deletePanel, "deletePanel");
    }
}