
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RegisForm implements ActionListener  {
	JFrame frame;
	JPanel panel;
	JLabel lname,lmobile,lemail,lpassword,lalreadyexisting,lusername;
	JPasswordField tpassword;
	JTextField tname,tmobile,temail,tusername;
	JButton signup,login;
	void initialize()
	{
		
		frame=new JFrame("Online COVID-19 Detection Portal :Registration Form ");
	//	frame.getContentPane().setBackground(Color.blue);
		
		panel=new JPanel();
		lname=new JLabel("Name :");
		lmobile=new JLabel("Mobile :");
		lemail=new JLabel("Email-id :");
		lusername=new JLabel("Username :");
		lpassword=new JLabel("Password :");
		lalreadyexisting=new JLabel("Already Registered :");
		lalreadyexisting.setFont(new Font("Arial", Font.PLAIN, 13));
		lalreadyexisting.setHorizontalAlignment(SwingConstants.CENTER); // set the horizontal alignement on the x axis !
		lalreadyexisting.setVerticalAlignment(SwingConstants.CENTER); // set
		tname=new JTextField(15);
		tmobile=new JTextField(15);
		temail=new JTextField(15);
		tusername=new JTextField(12);
		tpassword=new JPasswordField(12);
		signup=new JButton("Sign Up");
		signup.addActionListener(this);
		login=new JButton("Login");
		login.addActionListener(this);
		
	}
	void addition()
	{
		panel.add(lname);
		panel.add(tname);
		panel.add(lmobile);
		panel.add(tmobile);
		panel.add(lemail);
		panel.add(temail);
		panel.add(lusername);
		panel.add(tusername);
		panel.add(lpassword);
		panel.add(tpassword);
		panel.add(lalreadyexisting);
		panel.add(login);
		panel.add(signup);
		frame.add(panel);
		frame.setSize(250,250);
		panel.setBackground(Color.yellow);
		frame.setVisible(true);
	}
	RegisForm()
	{
		 initialize();
		 addition();
		
	}
	public static void main(String[] args) {
		new RegisForm();
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String ename=null,emobile=null,eemail=null,eusername=null,epassword=null;
		ename=tname.getText();
		emobile=tmobile.getText();
		eemail=temail.getText();
		eusername=tusername.getText();
		epassword=tpassword.getText();
		
	
	if(e.getSource()==login)
	{
		System.out.println("Redirecting you to the Login Page !!");
		new UI_app();
		frame.dispose();
	}
			
		else if(checkEmptyFields()) {
		
		if(e.getSource()==signup)
		{	
			
			System.out.println(ename+" "+emobile+" "+eemail+" "+eusername+" "+epassword);
			JOptionPane.showConfirmDialog(frame, "Are you Sure about your entered info");
			int a=JOptionPane.YES_OPTION;
		
			//Add data to registration table 
			if(a==0)
			{
				Database base = null;
				try {
					base=new Database();
					java.sql.Connection con=base.getConnection();
					PreparedStatement pst=con.prepareStatement("insert into registered values(?,?,?,?,?)");
					pst.setString(1, ename);
					pst.setString(2, emobile);
					pst.setString(3, eemail);
					pst.setString(4,eusername);
					pst.setString(5, "NA");
					pst.executeUpdate();
					
					//add to account table
					pst=con.prepareStatement("insert into Account values(?,?,?)");
					pst.setString(1,eemail);
					pst.setString(2, epassword);
					pst.setString(3, eusername);
					pst.executeUpdate();
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
				JOptionPane.showMessageDialog(frame, "Congrats !!! You are Successfully Registered ");
				
				}		
			}
		}
		
	}
	boolean checkEmptyFields() {
		// TODO Auto-generated method stub
		if(tname.getText().equals("")) {
			JOptionPane.showMessageDialog(frame, "Name Cannot be empty !");
			return false;
		}
		else if(tmobile.getText().equals("")) {
			 JOptionPane.showMessageDialog(frame, "Mobile Number Cannot be empty !");
			 return false;
		 }
		else if(temail.getText().equals("")) {
			 JOptionPane.showMessageDialog(frame, "Email Cannot be empty !");
			 return false;
		 }
		else if(tusername.getText().equals("")) {
			 JOptionPane.showMessageDialog(frame, "Username Cannot be empty !");
			 return false;
		 }
		else if(tpassword.getText().equals("")) {
			 JOptionPane.showMessageDialog(frame, "Password Cannot be empty !");
			 return false;
		 }	

		return  true;
	}
	
}

// mene puri entry hi delete kar di hai  

