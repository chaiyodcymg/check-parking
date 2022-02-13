
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.sql.*;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.BevelBorder;

import javax.swing.border.SoftBevelBorder;

import javax.swing.border.EtchedBorder;
import java.awt.Window.Type;

public class login {

	public JFrame frmComfortParking;
	public JTextField textusername;
	public JPasswordField textpassword;


	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frmComfortParking.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static Connection ConnectDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver"); //driver
            String url="jdbc:mysql://localhost/esp_8266";
            Connection connect = DriverManager.getConnection(url,"admin","packkcap1669");
            System.out.println("Connected Successfully! ");
            return connect;
        } catch(Exception e){
            JOptionPane.showMessageDialog(null,"Database not Connected!");
        }
        return null;
        
    }	
	
	
	/**
	 * Create the application.
	 */
	public login() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmComfortParking = new JFrame();
		frmComfortParking.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Codes\\Project oopandcomarg\\car2.png"));
		frmComfortParking.setTitle("Comfort Parking");
		frmComfortParking.getContentPane().setBackground(new Color(221, 160, 221));
		frmComfortParking.setBounds(100, 100, 940, 689);
		frmComfortParking.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmComfortParking.getContentPane().setLayout(null);
		frmComfortParking.setLocationRelativeTo(null);
		

		JLabel lblNewLabel = new JLabel("Username :");
		lblNewLabel.setFont(new Font("TH Sarabun New", Font.PLAIN, 45));
		lblNewLabel.setBounds(222, 214, 168, 68);
		frmComfortParking.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setFont(new Font("TH Sarabun New", Font.PLAIN, 45));
		lblNewLabel_1.setBounds(230, 321, 160, 47);
		frmComfortParking.getContentPane().add(lblNewLabel_1);
		
		JButton btnlogin = new JButton("Login");
		btnlogin.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnlogin.setBackground(new Color(255, 192, 203));
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection c = ConnectDB(); 
					PreparedStatement st = c.prepareStatement("SELECT * FROM login WHERE username='"+textusername.getText()+"'AND password='"+textpassword.getText()+"'");
					ResultSet rs = st.executeQuery();
					if(rs.next()) {
						System.out.println("Finnish");
						JOptionPane.showMessageDialog(null, "Welcome "+textusername.getText());
						frmComfortParking.dispose();
						GUIproject  guipro = new GUIproject() ;
						guipro.frmComfortParking.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Username or Password is Wrong! ");
					}
					}catch(Exception a) {
						System.out.println(a.getMessage());

			
					}
			}


		});
		btnlogin.setFont(new Font("TH Sarabun New", Font.PLAIN, 30));
		btnlogin.setBounds(602, 410, 122, 47);
		frmComfortParking.getContentPane().add(btnlogin);
		
		textusername = new JTextField();
		textusername.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textusername.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					btnlogin.doClick();
				}
			}
		});
		textusername.setFont(new Font("TH Sarabun New", Font.PLAIN, 30));
		textusername.setBounds(411, 227, 314, 47);
		frmComfortParking.getContentPane().add(textusername);
		
		textusername.setColumns(10);
		textpassword = new JPasswordField();
		textpassword.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textpassword.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					btnlogin.doClick();
				}
			}
		});
		textpassword.setFont(new Font("TH Sarabun New", Font.PLAIN, 30));
		textpassword.setBounds(410, 323, 314, 47);
		frmComfortParking.getContentPane().add(textpassword);
		
		JLabel lblNewLabel_2 = new JLabel("Comfort Parking");
		lblNewLabel_2.setFont(new Font("Castellar", Font.PLAIN, 54));
		lblNewLabel_2.setBounds(166, 55, 676, 82);
		frmComfortParking.getContentPane().add(lblNewLabel_2);
	}
}