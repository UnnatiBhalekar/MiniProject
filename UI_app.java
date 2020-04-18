import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class UI_app implements ActionListener {
	JFrame frame;
	JPanel panel;
	JLabel luser,lpass;
	JTextField tusername;
	JPasswordField tpassword;
	JButton button,forget,signup;
	UI_app()
	{
		initilize();
		button.addActionListener(this);
		signup.addActionListener(this);
		forget.addActionListener(this);
		addtion();
	}
	
	
	void initilize() {
		frame=new JFrame("Login Window");
		panel=new JPanel();
		tusername=new JTextField(10);
		tpassword=new JPasswordField(10);
 		luser=new JLabel("Username: ");
		lpass=new JLabel("Password: ");
		button=new JButton("Login");
		forget =new JButton("Forgot Password ??");
		signup=new JButton("Register");
	}
	void addtion() {
		panel.add(luser);
		panel.add(tusername);
		panel.add(lpass);
		panel.add(tpassword);
		panel.add(button);
		panel.add(signup);
		panel.add(forget);
		frame.add(panel);
		frame.setSize(250,300);
		frame.setVisible(true);
	}
	
	
	
	// Main method
	public static void main(String args[]) {
		new UI_app();
		
	}
	@Override
	public void actionPerformed(ActionEvent ac) {
		// TODO Auto-generated method stub
		String username=null,password=null;
		username=tusername.getText();
		password=tpassword.getText();
		Database base = null;
		
		
		 if(ac.getActionCommand().equals("Register")) {
				
				frame.dispose();
				new RegisForm();
			}
			
			else if(ac.getActionCommand().equals("Forgot Password ??")) {
				frame.dispose();
				new Forgot();			
			}	
		
	
			else if(checkEmptyFields()) {
					if(ac.getActionCommand().equals("Login")) {
		
			try {
				base=new Database();
			
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			java.sql.Connection con=base.getConnection();
			
			Statement stm = null;
			try {
				stm = con.createStatement();
				ResultSet res=stm.executeQuery("select * from Account");
				int flag=0;
				while(res.next()) {
					if(res.getString(3).equals(username)) {
						flag=1;
						if(res.getString("password").equals(password)) {
							JOptionPane.showMessageDialog(frame, "welcome!!!");
							
							System.out.println(CheckifAlreadyPresient(res.getString(3),con));
							
							if(CheckifAlreadyPresient(res.getString(3),con)) {
								frame.dispose();
								new Stage1_Diagnosis(res.getString(3));
							}
							else
							{
								frame.dispose();
								new AlreadyLogged(res.getString(3));
							}				
						
							break;
						}
						else {
							JOptionPane.showMessageDialog(frame, "Wrong Password");
						}
					}
					
				}
				if(flag==0) {
					JOptionPane.showMessageDialog(frame, "User Does not Exist");
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		}
	}
	boolean checkEmptyFields() {
		// TODO Auto-generated method stub
		 
		 if(tusername.getText().equals("")) {
			 JOptionPane.showMessageDialog(frame, "Username Cannot be empty !");
			 return false;
		 }
		 if(tpassword.getText().equals("")) {
			 JOptionPane.showMessageDialog(frame, "Password Cannot be empty !");
			 return false;
		 }		 
		return  true;
	}
	boolean CheckifAlreadyPresient(String s,java.sql.Connection con) throws SQLException {
		// TODO Auto-generated method stub
		
		 Statement stm=con.createStatement();
		 ResultSet res=stm.executeQuery("select * from registered");
		 while(res.next()) {		 
			 if(res.getString(4).equals(s)) {
				 if(res.getString(5).equals("NA")) {		 
					 return true;
				 }	 
			 }
		 }	
		return false;
	}
}
