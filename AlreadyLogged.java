
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

public class AlreadyLogged implements ActionListener{

	public AlreadyLogged(String s) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		this();
		this.username=s;
		System.out.println("Welcome : "+this.username);
	}
	String username=null;
	java.sql.Connection con;
	JFrame frame;
	JPanel panel;
	JLabel lpreventions;
	JButton bgetresult,newcheck,logout,preventions;
	AlreadyLogged() throws ClassNotFoundException, SQLException
	{
		initilize();
		bgetresult.addActionListener(this);
		newcheck.addActionListener(this);
		logout.addActionListener(this);
		preventions.addActionListener(this);
		addtion();
		
		Database b=new Database();
		con=b.getConnection();
	}
	
	
	void initilize() {
		frame=new JFrame("Welcome Again");
		panel=new JPanel();
		bgetresult =new JButton("Get last result");
		newcheck=new JButton("New Checkup?");
		lpreventions=new JLabel("check out the Basic preventive measures by clicking on the Preventions Button");
		preventions=new JButton("Preventions");
		logout=new JButton("Logout");
	}
	void addtion() {
		panel.add(bgetresult);
		panel.add(newcheck);
		panel.add(lpreventions);
		panel.add(preventions);
		panel.add(logout);
		frame.add(panel);
		frame.setSize(500,300);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==preventions)
		{
			new Prevention();
		}
		if(e.getSource()==logout)
		{
			JOptionPane.showConfirmDialog(frame, "Are you Sure you want to logout");
			int a=0;
			if(a==JOptionPane.YES_OPTION)
			{
				JOptionPane.showMessageDialog(frame, "Thank You for taking the test!!");
				frame.dispose();
				new UI_app();
			}
			
		}
		
		if(e.getSource()==bgetresult) {
			try {
				new Result(this.username,"result");
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			frame.dispose();
		}
		
		else if(e.getSource()==newcheck) {
			
			JOptionPane.showMessageDialog(frame, "Your previous result will be deleted");
			int a=JOptionPane.YES_OPTION;
			if(a==0) {
			try {
				PreparedStatement pst=con.prepareStatement("update  registered set result=? where username =?");
				pst.setString(1, "NA");
				pst.setString(2, username);
				pst.executeUpdate();
				frame.dispose();
				new Stage1_Diagnosis(username);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					frame.dispose();
			}
		}
	}
}
