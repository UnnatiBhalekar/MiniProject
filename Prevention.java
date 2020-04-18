import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Prevention implements ActionListener {
	JFrame frame;
	JPanel panel;
	JTextArea m1,m2,m3,m4,m5;
	JLabel m6;
	JButton BACK;
	void initialize()
	{
		frame=new JFrame("Preventions and Precautions");
		panel=new JPanel();
		m1=new JTextArea("#Wash you Hands Frequently.");
		m1.setBackground(Color.yellow);
		m2=new JTextArea("#Maintain Social Distancing.");
		m2.setBackground(Color.yellow);
		m3=new JTextArea("#Avoid touching eyes, nose, and mouth.");
		m3.setBackground(Color.yellow);
		m4=new JTextArea("#Maintain Respiratory Hygiene.");
		m4.setBackground(Color.yellow);
		m5=new JTextArea("#If you have fever, cough, difficulty in breathing seek medical care early");
		m5.setBackground(Color.yellow);
		m6=new JLabel("Take Care, Be Healthy, and MAKE INDIA CORONA FREE!!     ");
		BACK=new JButton("EXIT");
		BACK.addActionListener(this);
		
	}
	void addition()
	{
		panel.add(m1);
		panel.add(m2);
		panel.add(m3);
		panel.add(m4);
		panel.add(m5);
		panel.add(m6);
		panel.add(BACK);
		frame.add(panel);
		frame.setSize(400,400);
		panel.setBackground(Color.white);
		frame.setVisible(true);
		
	}
	Prevention()
	{
		initialize();
		addition();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==BACK)
		{
			frame.dispose();
				
			
		}
	
}
}
