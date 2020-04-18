
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Forgot implements ActionListener {
	java.sql.Connection con;
	JFrame frame;
	JPanel panel;
	JLabel luser;
	JTextField tusername;
	JButton button,back;
	Forgot()
	{
		initilize();
		button.addActionListener(this);
		back.addActionListener(this);
		addtion();
	}
	
	
	void initilize() {
		frame=new JFrame("Forget Window");
		panel=new JPanel();
		tusername=new JTextField(10);
 		luser=new JLabel("Enter email : ");
		button=new JButton("Generate Password");
		back =new JButton("Login page");
		

	
	}
	void addtion() {
		panel.add(luser);
		panel.add(tusername);
		panel.add(button);
		panel.add(back);
		frame.add(panel);
		frame.setSize(250,300);
		frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		 if(e.getSource()==back) {
			frame.dispose();
			new UI_app();
		}
		
		
		else if(tusername.getText().equals("")) {
			JOptionPane.showMessageDialog(frame,"Email Cannot be Empty");											
		}
		else	if(e.getSource()==button) {
			String email=tusername.getText();
			try {
				Database base=new Database();
				con=base.getConnection();
				Statement stm = con.createStatement();
				ResultSet res=stm.executeQuery("select * from Account ");
				int flag=0;
				while(res.next()) {
					if(res.getString("email").equals(email)) {
						flag=1;
						String i=JOptionPane.showInputDialog(panel, "For Security Purpose Enter Mobile Number");							
						if(getNumberVerified(con, i,email))
							JOptionPane.showMessageDialog(frame,"Pasword Retrived Successful "+"\nUser name :"+res.getString(3)+"    Password : "+res.getString(2));										
						else
						JOptionPane.showMessageDialog(frame,"Pasword Retrived Failed !! Make your Brain work ");							
						new UI_app();
							frame.dispose();
							break;
					}
					
				}
				if(flag==0) {
					JOptionPane.showMessageDialog(frame,"Email Not Found !!");							

				}
				
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		}
		
		
	}
	
		boolean getNumberVerified(java.sql.Connection con,String mobile,String email) throws SQLException {
			Statement stm=con.createStatement();
			ResultSet res2 =stm.executeQuery("select * from registered ");
			
			while(res2.next()) {
				if(res2.getString(3).equals(email)) {
				if(res2.getString(2).equals(mobile)) {
					return true;
				}
				}
			}
			return false;

		}
}
