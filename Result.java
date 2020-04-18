
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.BrokenBarrierException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Result implements ActionListener{
	String username=null,fresult=null;
	java.sql.Connection con;
	JFrame frame;
	JPanel panel;
	JLabel lprevention;
	JTextArea result;
	JButton bback,preventions;
	void initialize()
	{
		
		frame=new JFrame("Online COVID-19 Detection Portal :Result Frame ");
		panel=new JPanel();
		bback=new JButton("BACK");
		bback.addActionListener(this);
		
	}
//ye kaise hua??
	void addition()
	{
		result=new JTextArea("Name : "+username+"\n******* Result: ******* "+fresult);
		result.setBackground(Color.yellow);
		result.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(result);
		panel.add(bback);
		frame.add(panel);	
		frame.setSize(700,250);
		panel.setBackground(Color.RED);
		frame.setVisible(true);
	}
	Result()
	{
		 initialize();
			
	}
	
	
	Result(String username){
		this();
		this.username=username;
	}

	public Result(String username,String result) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		this(username);
		this.username=username;
		this.fresult=getResult(username);
		 addition();
			 displayResult();
	}
	private String getResult(String username2) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Database base =new Database();
		con=base.getConnection();
		
		Statement stm=con.createStatement();
		ResultSet res=stm.executeQuery("select * from registered");
		
		while(res.next()) {
			
			if(res.getString(4).equals(this.username)) {
				return res.getString(5);
			}
			
		}

		return null;
	}
	private void displayResult() {
		// TODO Auto-generated method stub
	System.out.println("Working result"+fresult);
	//yaha tak aa hi nahi raha wpoh haa
	
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==preventions)
		{
			new Prevention();
		}
		if(e.getSource()==bback) {
			try {
				frame.dispose();
				new AlreadyLogged(username);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}
