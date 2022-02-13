import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.AncestorEvent;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.border.SoftBevelBorder;




public class GUIproject extends JFrame{
	

	
	static String rsshow = "",rsshow2="",username;
	static int check1 ;
	static String image;
	JFrame frmComfortParking;
	private JTable table;
	static String combo="-",combo2="-" ,combocheckyear ="",combotime="";
	int checkcase = 0,checkcase1 = 0,cost_car,cost_car1;
	String checkyear ="2020" , checkmonth ="01",checkday="01";
	private JTable table2;
	private JTextField textstart;
	
	String time_start, time_out;
	static boolean checkcobobox ;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIproject window = new GUIproject();
					window.frmComfortParking.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	


	public GUIproject() {
		
		initialize();
		

	}
	//// connect database   /////////
	    
	    public static Connection ConnectDB(){
	        try{
	            Class.forName("com.mysql.jdbc.Driver");
	            String url="jdbc:mysql://localhost/nodemcu_8266?useUnicode=true&characterEncoding=UTF-8";
	            Connection connect = DriverManager.getConnection(url,"root","packkcap1669");
	            System.out.println("Connected successfully ");
	            return connect;
	        } catch(Exception e){
	            e.printStackTrace(); 
	        }
	        return null;
	        
	    }

	

	private void initialize() {
		

		frmComfortParking = new JFrame();
		frmComfortParking.setTitle("Comfort Parking");
		frmComfortParking.getContentPane().setBackground(new Color(221, 160, 221));
		frmComfortParking.getContentPane().setForeground(Color.RED);
		frmComfortParking.setForeground(Color.GREEN);
		frmComfortParking.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Codes\\Project oopandcomarg\\car2.png"));
		frmComfortParking.setBackground(Color.MAGENTA);
		frmComfortParking.setBounds(100, 100, 1520, 1080);
		frmComfortParking.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmComfortParking.setExtendedState(MAXIMIZED_BOTH);
		frmComfortParking.getContentPane().setLayout(null);
		
		textstart = new JTextField();
		textstart.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textstart.setBackground(new Color(221, 160, 221));
		textstart.setFont(new Font("TH Sarabun New", Font.PLAIN, 15));
		textstart.setBounds(466, 427, 166, 23);
		frmComfortParking.getContentPane().add(textstart);
		textstart.setColumns(10);
		textstart.setEnabled(false);
		
		
		
		
		
		JLabel lblSelect_name = new JLabel("");
		lblSelect_name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSelect_name.setBounds(478, 361, 143, 23);
		frmComfortParking.getContentPane().add(lblSelect_name);
		
		JLabel lblStart = new JLabel("");
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStart.setBounds(411, 424, 45, 23);
		frmComfortParking.getContentPane().add(lblStart);
		
		
		JLabel cost_2 = new JLabel("");
		cost_2.setHorizontalAlignment(SwingConstants.CENTER);
		cost_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cost_2.setBounds(466, 635, 166, 23);
		frmComfortParking.getContentPane().add(cost_2);
		
	//// query  /////////
		
		
		Vector Items1 = new Vector();
		try {
			Connection c =ConnectDB();
			PreparedStatement st = c.prepareStatement("SELECT * FROM  check_car ");
			ResultSet rs = st.executeQuery();
//			String sql = "SELECT * FROM  check_car ";
//			
//			ResultSet rs=c.createStatement().executeQuery(sql);
			
			while((rs!=null) && (rs.next()))
            {		
				Items1.add(rs.getString("Car") );
            }

			rs.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		
		}
		
		JComboBox comboBox_edit = new JComboBox();
		comboBox_edit.setBackground(new Color(255, 105, 180));
		comboBox_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkcobobox = true;
				
			}
		});
		comboBox_edit.setFont(new Font("TH Sarabun New", Font.PLAIN, 15));
		comboBox_edit.setModel(new DefaultComboBoxModel(Items1));
		comboBox_edit.setBounds(466, 394, 166, 23);
		frmComfortParking.getContentPane().add(comboBox_edit);
		comboBox_edit.setEnabled(false);
		
		JRadioButton rdbtnsave = new JRadioButton("Save");
	
		rdbtnsave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnsave.setBackground(new Color(221, 160, 221));
		rdbtnsave.setBounds(466, 323, 65, 23);
		frmComfortParking.getContentPane().add(rdbtnsave);
		
		JRadioButton rdbtnEditName = new JRadioButton("Edit name");
	
		rdbtnEditName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnEditName.setBackground(new Color(221, 160, 221));
		rdbtnEditName.setBounds(549, 323, 103, 23);
		frmComfortParking.getContentPane().add(rdbtnEditName);
		
		final ButtonGroup group = new ButtonGroup();
		group.add(rdbtnEditName);
		group.add(rdbtnsave);
		
		
		
		
		
	
		Vector Items = new Vector();
	
		try {
			Connection c =ConnectDB();

			PreparedStatement st = c.prepareStatement("SELECT * FROM  check_car ");
			ResultSet rs = st.executeQuery();
			
//			String sql = "SELECT * FROM  check_car ";
//			
//			ResultSet rs=c.createStatement().executeQuery(sql);
			
			while((rs!=null) && (rs.next()))
            {		
				Items.add(rs.getString("Car") );
            }

			rs.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		
		}
		

		JComboBox comboBox_2 = new JComboBox(new DefaultComboBoxModel(Items) );
		comboBox_2.setFont(new Font("TH Sarabun New", Font.PLAIN, 15));
		comboBox_2.setBackground(new Color(218, 112, 214));
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

		         try{

			             String sql="UPDATE check_car SET Time_Out = CURRENT_TIMESTAMP WHERE Car = '"+comboBox_2 .getSelectedItem().toString()+"'";
			         try{
			             Connection c= ConnectDB(); //เชื่อมต่อฐานข้อมูล
			             Statement stm=c.createStatement();//สร้าง statement
			             stm.executeUpdate(sql);//นำมาดำเนินการ
			             System.out.println("แก้ไขข้อมูลเรียบร้อย");
			             
			         }catch(Exception e1){
			             System.out.println(e1.getMessage());
			         }
			        	 
		        	 
		        	 
		        	 
		        	 String sql2="SELECT * FROM check_car WHERE Car ='"+comboBox_2 .getSelectedItem().toString()+"'";
		             Connection c=ConnectDB(); //เชื่อมฐานข้อมูล
		             ResultSet rs=c.createStatement().executeQuery(sql2);		        
		       
		             if(rs.next()){
		       	
		            		time_start= rs.getString("Time_Start");
		            		time_out= rs.getString("Time_Out");	 
		            	 

		             DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		     		
		         	try {
		     			Date start = df.parse(time_start);
		     			Date end = df.parse(time_out);
		     			
		     			long diff = end.getTime() - start.getTime();
		     			
		     			int dayDiff = (int) (diff / (24 * 60 * 60 * 1000));
		     			int hourDiff = (int) (diff / (60 * 60 * 1000) % 24);
		     			int minuteDiff = (int) (diff / (60 * 1000) % 60);
		     			int secondDiff = (int) (diff / 1000 % 60);
		     			
		     			System.out.println("Day Diff = " + dayDiff);
		     			System.out.println("Hour Diff = " + hourDiff);
		     		
		     			if(dayDiff==0 && hourDiff ==0 ) {
		     				cost_2.setText("10");
		     			}else {
		     			cost_2.setText(Integer.toString( (dayDiff*240)+(hourDiff*10) ));
		     			}
		         		
		             
		     		} catch (Exception e1) {
		     	
		     			e1.printStackTrace();
		     		}
		             
		             rs.close();
		             c.close();
		             }else {
		            	 JOptionPane.showMessageDialog(null,"Please enter a name to save.");
		             }
		         }catch(SQLException e2){
		             e2.printStackTrace();
		         }
				
			}
		});
		comboBox_2.setBounds(466, 580, 166, 21);
		frmComfortParking.getContentPane().add(comboBox_2);
		
		///////      save data and edit////////////
		
		
		
		JButton btnOk = new JButton("Save");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
			if(rdbtnsave.isSelected()) { 
				if(textstart.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Please enter a name to save.");
				}else {
					Items1.removeAllElements();
					Items.removeAllElements();
					String sql="insert into check_car(Car,Time_Start) value('"+textstart.getText()+"',CURRENT_TIMESTAMP)";
				 
			        try {
			           Connection c=ConnectDB();// เชื่อมต่อฐานข้อมูล
			           Statement stm=c.createStatement();// สร้าง Statement
			           stm.executeUpdate(sql);//นำคำสั่ง sql ไปดำเนินการ
			           System.out.println("บันทึกข้อมูลเรียบร้อย");
			       
		
			        } catch(SQLException e3) {
			        	textstart.setText("");
			           JOptionPane.showMessageDialog(null,"The registration number is duplicated with other cars.");
			        }
			        
			    	try {
	        			
	        			 
			    		 Connection c=ConnectDB();
	        			String sql3 = "SELECT Car FROM  check_car ";
	        			
	        			ResultSet rs2=c.createStatement().executeQuery(sql3);
	        			
	        			while((rs2!=null) && (rs2.next()))
	                    {		
	        				textstart.setText("");
	        				Items.add(rs2.getString("Car") );
	        				Items1.add(rs2.getString("Car") );
	        				comboBox_edit.setModel(new DefaultComboBoxModel(Items1));
		    				comboBox_edit.setModel(new DefaultComboBoxModel(Items));
		    				
	        				
	                    }

	        			rs2.close();

	        		} catch (Exception e3) {
	        			// TODO Auto-generated catch block
	        			JOptionPane.showMessageDialog(null, e3.getMessage());
	        			e3.printStackTrace();
	        		}
			    
					}
				}
			
			
			if(rdbtnEditName.isSelected()) {
				if(textstart.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Please enter a name to save.");
				}else {
					 cost_2.setText("");
				if(checkcobobox == true) {
					Items1.removeAllElements();
					Items.removeAllElements();
					
		        try{
		        	 Connection c= ConnectDB(); 

			        	String sql="UPDATE check_car SET Car = '"+textstart.getText()+"' WHERE Car = '"+comboBox_edit.getSelectedItem().toString()+"'";
			          
			            Statement stm=c.createStatement();
			            stm.executeUpdate(sql);
			        
		           
		            try {

		    			String sql2 = "SELECT Car FROM  check_car ";
		    			
		    			ResultSet rs2=c.createStatement().executeQuery(sql2);
		    			
		    			while((rs2!=null) && (rs2.next()))
		                {		
		    				Items1.add(rs2.getString("Car") );
		    				Items.add(rs2.getString("Car") );
		    				comboBox_edit.setModel(new DefaultComboBoxModel(Items1));
		    				comboBox_edit.setModel(new DefaultComboBoxModel(Items));
//		    				comboBox_edit.setSelectedIndex(0);
		    				textstart.setText("");
		    				
		    				
		                }

		    			

		    		} catch (Exception e3) {
		    			JOptionPane.showMessageDialog(null, e3.getMessage());
		    		
		    		}

		            
		        }catch(Exception e2){
		        	JOptionPane.showMessageDialog(null,  "This name is already!");
		        	textstart.setText("");
		        
		        	try {
		        		 Connection c= ConnectDB(); 
		    			String sql2 = "SELECT Car FROM  check_car ";
		    			
		    			ResultSet rs2=c.createStatement().executeQuery(sql2);
		    			
		    			while((rs2!=null) && (rs2.next()))
		                {		
		    				Items1.add(rs2.getString("Car") );
		    				Items.add(rs2.getString("Car") );
		    				comboBox_edit.setModel(new DefaultComboBoxModel(Items1));
		    				comboBox_edit.setModel(new DefaultComboBoxModel(Items));
//		    				comboBox_edit.setSelectedIndex(0);
		    				textstart.setText("");
		    				
		    				
		                }

		    			
		        	  }catch(Exception e4){
				        	JOptionPane.showMessageDialog(null,  "This name is already!");
				        	textstart.setText("");
				        }
		        }
		        
				}else {
					JOptionPane.showMessageDialog(null,"Please select to Edit");
					
				}
				
				
				}	
			}
			
			}
		});
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnOk.setBackground(new Color(255, 192, 203));
		btnOk.setBounds(466, 503, 166, 30);
		frmComfortParking.getContentPane().add(btnOk);
		btnOk.setEnabled(false);
	
		textstart.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					btnOk.doClick();
				}
			}
		});

		JButton btnNewButton_2 = new JButton("Payment");
		btnNewButton_2.setBackground(new Color(255, 192, 203));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(cost_2.getText().equals("")) {		
				JOptionPane.showMessageDialog(null,"Please select name ");

				}else {
					
				Items1.removeAllElements();
				Items.removeAllElements();
				 
			        try{
			        	 Connection c= ConnectDB(); 
			         	String sql1 ="SELECT Car from check_car WHERE Car ='"+comboBox_2.getSelectedItem().toString()+"'  ";
			           
			         	 ResultSet rs=c.createStatement().executeQuery(sql1);
			         	if(rs.next()) {

				        	String sql ="DELETE FROM check_car WHERE Car = '"+comboBox_2.getSelectedItem().toString()+"'";
				        
				            Statement stm=c.createStatement();//สร้าง statement
				            stm.executeUpdate(sql);//นำมาดำเนินการ
				            System.out.println("ลบข้อมูลเรียบร้อย");
				            JOptionPane.showMessageDialog(null,"Payment Successful ");
				            cost_2.setText("");
				         }else {
				        	 JOptionPane.showMessageDialog(null,"Name is not correct");
				         }
			        } catch(Exception e1){
			        	JOptionPane.showMessageDialog(null, e1.getMessage());
			        }
			        
			        
			        try {
			        	
			        	Connection c= ConnectDB();
		    			String sql2 = "SELECT * FROM  check_car ";
		    			
		    			ResultSet rs=c.createStatement().executeQuery(sql2);
		    			
		    			while((rs!=null) && (rs.next()))
		                {		
		    				
		    				Items1.add(rs.getString("Car") );
		    				Items.add(rs.getString("Car") );
		    				comboBox_edit.setModel(new DefaultComboBoxModel(Items1));
		    				comboBox_edit.setModel(new DefaultComboBoxModel(Items));
		    			
		    				
		    				
		                }

		    			rs.close();

		    		} catch (Exception e3) {
		    			JOptionPane.showMessageDialog(null, e3.getMessage());
		    		
		    		}
			        
				}
			        
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_2.setBounds(466, 691, 166, 30);
		frmComfortParking.getContentPane().add(btnNewButton_2);
		
		comboBox_2.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					btnNewButton_2.doClick();
				}
			}
		});
		
		rdbtnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnsave.isSelected()) {
					comboBox_edit.setEnabled(false);
					textstart.setEnabled(true);
					btnOk.setEnabled(true);
					lblSelect_name.setText("");
					lblStart.setText("Start");
				}
			}
		});
		rdbtnEditName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnEditName.isSelected()) {
					lblSelect_name.setText("Select name for edit");
					comboBox_edit.setEnabled(true);
					textstart.setEnabled(true);
					lblStart.setText("Edit to");
					btnOk.setEnabled(true);
					
					
				}
			}
		});
		
		

		JLabel car1 = new JLabel("");
		car1.setBounds(743, 344, 194, 325);
		frmComfortParking.getContentPane().add(car1);
		
		
		JLabel car2 = new JLabel("");
		car2.setBounds(1235, 344, 194, 325);
		frmComfortParking.getContentPane().add(car2);
		
	
	
		
		
		JLabel Status1_1 = new JLabel("STATUS");
		Status1_1.setBounds(1283, 294, 100, 40);
		Status1_1.setForeground(new Color(0, 0, 0));
		Status1_1.setHorizontalAlignment(SwingConstants.CENTER);
		Status1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frmComfortParking.getContentPane().add(Status1_1);
		
		Panel panel = new Panel();
		panel.setBounds(721, 294, 16, 401);
		panel.setBackground(new Color(199, 21, 133));
		frmComfortParking.getContentPane().add(panel);
		
		JLabel Status1_1_1 = new JLabel("STATUS");
		Status1_1_1.setBounds(778, 294, 120, 40);
		Status1_1_1.setForeground(new Color(0, 0, 0));
		Status1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	
		Status1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frmComfortParking.getContentPane().add(Status1_1_1);
		
		JLabel lblFile = new JLabel("File");
		lblFile.setBounds(177, 299, 517, 14);
		lblFile.setForeground(new Color(0, 0, 0));
		frmComfortParking.getContentPane().add(lblFile);
		
		JLabel lblNewLabel = new JLabel("           QRcode");
		lblNewLabel.setBounds(44, 323, 337, 335);
		lblNewLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		frmComfortParking.getContentPane().add(lblNewLabel);

		
		JButton btnCheck = new JButton("CheckStatusParking");
		btnCheck.setBounds(1001, 478, 177, 40);
		btnCheck.setBackground(new Color(255, 192, 203));
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			      String sql="select * from nodemcu_ultra1";
			         try{
			             Connection c=ConnectDB(); //เชื่อมฐานข้อมูล
			             ResultSet rs=c.createStatement().executeQuery(sql); //ดึงข้อมูลทั้งหมดออกมาใส่ที่ ResultSet
			             while(rs.next()){
			            	 rsshow=rs.getString(2);
			                 System.out.println(rs.getString(2));
			                
			             }
			          
			             
			             rs.close();
			             c.close();
			             
			         }catch(Exception e1){
			             e1.printStackTrace();
			         }
			         
			         String sql2="select * from nodemcu_ultra2";
			         try{
			             Connection c=ConnectDB(); //เชื่อมฐานข้อมูล
			             ResultSet rs2=c.createStatement().executeQuery(sql2); //ดึงข้อมูลทั้งหมดออกมาใส่ที่ ResultSet
			             while(rs2.next()){
			            	 rsshow2=rs2.getString(2);
			                 System.out.println(rs2.getString(2));
			                
			             }
			          
			             
			             rs2.close();
			             c.close();
			             
			         }catch(Exception e2){
			             e2.printStackTrace();
			         }
			         
			         
			         
				if (rsshow.equals("FULL")) {
					Status1_1_1.setText("Full");
					ImageIcon ii=new ImageIcon("D:\\Codes\\Project oopandcomarg\\car2.png");  
					Image image= ii.getImage().getScaledInstance(194, 325, Image.SCALE_SMOOTH);
					car1.setBounds(743, 344, 194, 325);
					car1.setIcon(new ImageIcon(image));
					
					
				}else if(rsshow.equals("EMPTY")) {
					Status1_1_1.setText("Empty");
					car1.setBounds(2000, 2000, 194, 325);
				}
				if (rsshow2.equals("FULL")) {
					Status1_1.setText("Full");
					ImageIcon aa=new ImageIcon("D:\\Codes\\Project oopandcomarg\\car2.png");  
					Image image2= aa.getImage().getScaledInstance(194, 325, Image.SCALE_SMOOTH);
					car2.setBounds(1235, 344, 194, 325);
					car2.setIcon(new ImageIcon(image2));
					
					
				}else if(rsshow2.equals("EMPTY")) {
					car2.setBounds(2000, 2000, 194, 325);
					Status1_1.setText("Empty");
				}
				
			}
		});
		frmComfortParking.getContentPane().add(btnCheck);
		
		JButton buttonchoose = new JButton("Choose photo");
		buttonchoose.setBounds(44, 289, 123, 23);
		buttonchoose.setBackground(Color.PINK);
		buttonchoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Choose file");

                if (ret == JFileChooser.APPROVE_OPTION) {
                	lblFile.setText(fileopen.getSelectedFile().toString());
                	image = fileopen.getSelectedFile().toString();
                	lblNewLabel.setIcon(new ImageIcon(image));
              
                }
			}
			
		});
		frmComfortParking.getContentPane().add(buttonchoose);
		
		Panel panel_1 = new Panel();
		panel_1.setBounds(943, 294, 16, 401);
		panel_1.setBackground(new Color(199, 21, 133));
		frmComfortParking.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_7 = new JLabel("");
		panel_1.add(lblNewLabel_7);
		lblNewLabel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		Panel panel_2 = new Panel();
		panel_2.setBounds(1214, 295, 16, 401);
		panel_2.setBackground(new Color(199, 21, 133));
		frmComfortParking.getContentPane().add(panel_2);
		
		Panel panel_3 = new Panel();
		panel_3.setBounds(1435, 295, 16, 401);
		panel_3.setBackground(new Color(199, 21, 133));
		frmComfortParking.getContentPane().add(panel_3);
		
		table2 = new JTable();
		table2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No", "Status", "Date", "Time"
			}
		));
		table2.setBounds(721, 271, 730, -198);
		DefaultTableModel modle2 = (DefaultTableModel)table2.getModel();
//		frmComfortParking.getContentPane().add(table2);
		
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No", "Status", "Date", "Time"
			}
		));
		DefaultTableModel modle = (DefaultTableModel)table.getModel();
		table.setBounds(44, 55, 1280, 201);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 44, 632, 237);
		frmComfortParking.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(721, 74, 730, 196);
		frmComfortParking.getContentPane().add(scrollPane2);
		scrollPane2.setViewportView(table2);
		
		
		JButton btnNewButton = new JButton("ShowParking 1");
		btnNewButton.setBounds(44, 11, 150, 23);
		btnNewButton.setBackground(new Color(255, 182, 193));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int counttable =  table.getRowCount ( );
					
					if(table.getRowCount ( ) >=1) {
						 modle.setRowCount(0);
					}
					
				
					Connection c=ConnectDB();
		            String sql = "select * from nodemcu_ultra1";
		             ResultSet rs=c.createStatement().executeQuery(sql); 
		             while(rs.next()) {
		            	String No =  rs.getString("No");
		            	String Status =  rs.getString("Status");
		            	String Date =  rs.getString("Date");
		            	String Time =  rs.getString("Time");
		            	 
		            	String tabledata[] = {No,Status,Date,Time};
		       
		            	modle.addRow(tabledata);

		             
					}
		            
				}catch(Exception e2){
					e2.printStackTrace();
				}
			}
		});
		frmComfortParking.getContentPane().add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("ShowParking 2");
		btnNewButton_1.setBounds(194, 11, 150, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					int counttable =  table.getRowCount ( );;
					
					if(table.getRowCount ( ) >=1) {
						 modle.setRowCount(0);
					}
					
									
					Connection c=ConnectDB();
		            String sql = "select * from nodemcu_ultra2";
		             ResultSet rs2=c.createStatement().executeQuery(sql); 
		             while(rs2.next()) {
			            	String No =  rs2.getString("No");
			            	String Status =  rs2.getString("Status");
			            	String Date =  rs2.getString("Date");
			            	String Time =  rs2.getString("Time");
			            	 
			            	String tabledata[] = {No,Status,Date,Time};
			            	
			            	modle.addRow(tabledata);
		             
			             }
				}catch(Exception e2){
					e2.printStackTrace();
				}
			}
		});	
		btnNewButton_1.setBackground(new Color(255, 182, 193));
		frmComfortParking.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(549, 409, 46, 14);
		frmComfortParking.getContentPane().add(lblNewLabel_1);
		
		
		JLabel lblNewLabel_4 = new JLabel("Parking 1");
		lblNewLabel_4.setBounds(792, 723, 100, 30);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 22));
		frmComfortParking.getContentPane().add(lblNewLabel_4);
		
		JComboBox comboBoxday = new JComboBox();
		comboBoxday.setBounds(1032, 42, 53, 21);
		comboBoxday.setBackground(new Color(219, 112, 147));
		comboBoxday.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBoxday.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		frmComfortParking.getContentPane().add(comboBoxday);
		
		
		JComboBox comboBoxyear = new JComboBox();
		comboBoxyear.setBounds(832, 42, 53, 21);
		comboBoxyear.setBackground(new Color(219, 112, 147));
		comboBoxyear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxyear.setModel(new DefaultComboBoxModel(new String[] {"2021", "2022", "2023", "2024", "2025"}));
		frmComfortParking.getContentPane().add(comboBoxyear);
		
		JComboBox comboBoxmonth = new JComboBox();
		comboBoxmonth.setBounds(930, 42, 53, 21);
		comboBoxmonth.setBackground(new Color(219, 112, 147));
		comboBoxmonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(comboBoxmonth.getSelectedIndex()) {
				case 0:
					comboBoxday.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
					break;
				case 1:
					comboBoxday.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29"}));
					break;
				case 2:
					comboBoxday.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
					break;
				case 3:
					comboBoxday.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"}));
					break;
				case 4:
					comboBoxday.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
					break;
				case 5:
					comboBoxday.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"}));
					break;
				case 6:
					comboBoxday.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
					break;
				case 7:
					comboBoxday.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
					break;
				case 8:
					comboBoxday.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"}));
					break;
				case 9:
					comboBoxday.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
					break;
				case 10:
					comboBoxday.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"}));
					break;
				case 11:
					comboBoxday.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
					break;
				}
			}
		});
		comboBoxmonth.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBoxmonth.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		frmComfortParking.getContentPane().add(comboBoxmonth);
		

		JLabel lblNewLabel_4_1 = new JLabel("Parking 2");
		lblNewLabel_4_1.setBounds(1294, 723, 100, 30);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		frmComfortParking.getContentPane().add(lblNewLabel_4_1);
		JComboBox comboparking = new JComboBox();
		comboparking.setBounds(721, 42, 77, 21);
		comboparking.setBackground(new Color(219, 112, 147));
		comboparking.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboparking.setModel(new DefaultComboBoxModel(new String[] {"Parking 1", "Parking 2"}));
		frmComfortParking.getContentPane().add(comboparking);
		
		
		
		JButton btnsearch = new JButton("Search");
		btnsearch.setBounds(1130, 37, 100, 29);
		btnsearch.setBackground(new Color(255, 192, 203));
		btnsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(comboparking.getSelectedIndex()) {
					case 0:
						switch(comboBoxyear.getSelectedIndex()) {
						case 0:
							checkyear ="2021";
							break;
						case 1:
							checkyear ="2022";
							break;
						case 2:
							checkyear ="2023";
							break;
						case 3:
							checkyear ="2024";
							break;
						case 4:
							checkyear ="2025";
							break;
						}
						
						switch(comboBoxmonth.getSelectedIndex()) {
							case 0:
								checkmonth = "01";
								break;
							case 1:
								checkmonth = "02";
								break;
							case 2:
								checkmonth = "03";
								break;
							case 3:
								checkmonth = "04";
								break;
							case 4:
								checkmonth = "05";
								break;
							case 5:
								checkmonth = "06";
								break;
							case 6:
								checkmonth = "07";
								break;
							case 7:
								checkmonth = "08";
								break;
							case 8:
								checkmonth = "09";
								break;
							case 9:
								checkmonth = "10";
								break;
							case 10:
								checkmonth = "11";
								break;
							case 11:
								checkmonth = "12";
								break;
							
						}
						switch(comboBoxday.getSelectedIndex()) {
							case 0:
								checkday = "01";
								break;
							case 1:
								checkday = "02";
								break;
							case 2:
								checkday = "03";
								break;
							case 3:
								checkday = "04";
								break;
							case 4:
								checkday = "05";
								break;
							case 5:
								checkday = "06";
								break;
							case 6:
								checkday = "07";
								break;
							case 7:
								checkday = "08";
								break;
							case 8:
								checkday = "09";
								break;
							case 9:
								checkday = "10";
								break;
							case 10:
								checkday = "11";
								break;
							case 11:
								checkday = "12";
								break;
							case 12:
								checkday = "13";
								break;
							case 13:
								checkday = "14";
								break;
							case 14:
								checkday = "15";
								break;
							case 15:
								checkday = "16";
								break;
							case 16:
								checkday = "17";
								break;
							case 17:
								checkday = "18";
								break;
							case 18:
								checkday = "19";
								break;
							case 19:
								checkday = "20";
								break;
							case 20:
								checkday = "21";
								break;
							case 21:
								checkday = "22";
								break;
							case 22:
								checkday = "23";
								break;
							case 23:
								checkday = "24";
								break;
							case 24:
								checkday = "25";
								break;
							case 25:
								checkday = "26";
								break;
							case 26:
								checkday = "27";
								break;
							case 27:
								checkday = "28";
								break;
							case 28:
								checkday = "29";
								break;
							case 29:
								checkday = "30";
								break;
							case 30:
								checkday = "31";
								break;

								
						}
						
						
					try {
						
						int counttable =  table2.getRowCount ( );
						
						if(table2.getRowCount ( ) >=1) {
							 modle2.setRowCount(0);
						}
						Connection c=ConnectDB();
			            String sql = "SELECT * FROM nodemcu_ultra1 WHERE Date = '"+checkyear+"-"+checkmonth+"-"+checkday+"'";
			             ResultSet rs2=c.createStatement().executeQuery(sql); 
			             while(rs2.next()) {
				            	String No =  rs2.getString("No");
				            	String Status =  rs2.getString("Status");
				            	String Date =  rs2.getString("Date");
				            	String Time =  rs2.getString("Time");
				            	 
				            	String tabledata2[] = {No,Status,Date,Time};
				       
				            	modle2.addRow(tabledata2);
			            	 
			             }
			             
			   
					}catch(SQLException e2){
						JOptionPane.showMessageDialog(null,e2.getMessage());
					}
					break;	
				case 1:
					
					switch(comboBoxyear.getSelectedIndex()) {
					case 0:
						checkyear ="2021";
						break;
					case 1:
						checkyear ="2022";
						break;
					case 2:
						checkyear ="2023";
						break;
					case 3:
						checkyear ="2024";
						break;
					case 4:
						checkyear ="2025";
						break;
					}
					
					switch(comboBoxmonth.getSelectedIndex()) {
						case 0:
							checkmonth = "01";
							break;
						case 1:
							checkmonth = "02";
							break;
						case 2:
							checkmonth = "03";
							break;
						case 3:
							checkmonth = "04";
							break;
						case 4:
							checkmonth = "05";
							break;
						case 5:
							checkmonth = "06";
							break;
						case 6:
							checkmonth = "07";
							break;
						case 7:
							checkmonth = "08";
							break;
						case 8:
							checkmonth = "09";
							break;
						case 9:
							checkmonth = "10";
							break;
						case 10:
							checkmonth = "11";
							break;
						case 11:
							checkmonth = "12";
							break;
						
					}
					switch(comboBoxday.getSelectedIndex()) {
						case 0:
							checkday = "01";
							break;
						case 1:
							checkday = "02";
							break;
						case 2:
							checkday = "03";
							break;
						case 3:
							checkday = "04";
							break;
						case 4:
							checkday = "05";
							break;
						case 5:
							checkday = "06";
							break;
						case 6:
							checkday = "07";
							break;
						case 7:
							checkday = "08";
							break;
						case 8:
							checkday = "09";
							break;
						case 9:
							checkday = "10";
							break;
						case 10:
							checkday = "11";
							break;
						case 11:
							checkday = "12";
							break;
						case 12:
							checkday = "13";
							break;
						case 13:
							checkday = "14";
							break;
						case 14:
							checkday = "15";
							break;
						case 15:
							checkday = "16";
							break;
						case 16:
							checkday = "17";
							break;
						case 17:
							checkday = "18";
							break;
						case 18:
							checkday = "19";
							break;
						case 19:
							checkday = "20";
							break;
						case 20:
							checkday = "21";
							break;
						case 21:
							checkday = "22";
							break;
						case 22:
							checkday = "23";
							break;
						case 23:
							checkday = "24";
							break;
						case 24:
							checkday = "25";
							break;
						case 25:
							checkday = "26";
							break;
						case 26:
							checkday = "27";
							break;
						case 27:
							checkday = "28";
							break;
						case 28:
							checkday = "29";
							break;
						case 29:
							checkday = "30";
							break;
						case 30:
							checkday = "31";
							break;
					
							
					}
					
					try {	
					int counttable =  table2.getRowCount ( );
					
					if(table2.getRowCount ( ) >=1) {
						 modle2.setRowCount(0);
					}
						Connection c=ConnectDB();
			            String sql = "SELECT * FROM nodemcu_ultra2 WHERE Date = '"+checkyear+"-"+checkmonth+"-"+checkday+"'";
			             ResultSet rs2=c.createStatement().executeQuery(sql); 
			             while(rs2.next()) {
				            	String No =  rs2.getString("No");
				            	String Status =  rs2.getString("Status");
				            	String Date =  rs2.getString("Date");
				            	String Time =  rs2.getString("Time");
				            	 
				            	String tabledata2[] = {No,Status,Date,Time};
				       
				            	modle2.addRow(tabledata2);
			            	 
			             }
			             
					}catch(SQLException e2){
						e2.printStackTrace();
					}
					break;
				}
				
				
				
				
				
			}
		});
		btnsearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frmComfortParking.getContentPane().add(btnsearch);
		
		comboBoxyear.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					btnsearch.doClick();
				}
			}
		});
		comboBoxmonth.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					btnsearch.doClick();
				}
			}
		});
		comboBoxday.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					btnsearch.doClick();
				}
			}
		});
	
		
		JLabel lblNewLabel_6 = new JLabel("                     Year             Month             Day ");
		lblNewLabel_6.setBounds(735, 10, 468, 20);
		lblNewLabel_6.setForeground(new Color(0, 0, 0));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmComfortParking.getContentPane().add(lblNewLabel_6);
		

		
		JLabel lblNewLabel_5 = new JLabel("Car registration or First name");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(450, 470, 202, 23);
		frmComfortParking.getContentPane().add(lblNewLabel_5);
		
	
		
		JLabel lblNewLabel_9 = new JLabel("End");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_9.setBounds(423, 576, 33, 23);
		frmComfortParking.getContentPane().add(lblNewLabel_9);
	
		
		JLabel lblNewLabel_3_2 = new JLabel("Cost");
		lblNewLabel_3_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3_2.setBounds(423, 635, 33, 23);
		frmComfortParking.getContentPane().add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Baht");
		lblNewLabel_3_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3_1_1.setBounds(643, 635, 33, 23);
		frmComfortParking.getContentPane().add(lblNewLabel_3_1_1);
		

		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(218, 112, 214));
		frmComfortParking.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("About");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Author 1");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"by Chaiyod Malingam  ID.633020278-2");
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Author 2");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"by Natakorn Rotchan ID.633020291-0");
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Author 3");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"by Buransak Saenphuwa ID.633020305-5");
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Author 4");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"by Sirothon Phosaard ID.633020332-2");
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Author 5");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"by Rapeepong kodchawat ID.633020321-7");
			}
		});
		mnNewMenu.add(mntmNewMenuItem_4);
	
	
		
	}
}
