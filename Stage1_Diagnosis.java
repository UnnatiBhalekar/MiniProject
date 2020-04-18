import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;

public class Stage1_Diagnosis implements ActionListener{
	
	String ages[] 
	        = { "1", "2", "3", "4", "5", 
	            "6", "7", "8", "9", "10", 
	            "11", "12", "13", "14", "15", 
	            "16", "17", "18", "19", "20", 
	            "21", "22", "23", "24", "25", 
	            "26", "27", "28", "29", "30", 
	            "31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46"
	            ,"47","48","49","50","51","52","53","54","55","56","57","58","59","60","61","62","63","64","65","66",
	            "67","68","69","70","71","72","73","74","75","76","77","78","79","80"}; 
	
	String temps[]= {"Normal(96°F-98.6°F","Fever(98.6°F-102°F)","High Fever(>102°F)"};
	String answer[]= {"Yes","No"};
	class MyAdjustmentListener implements AdjustmentListener {
		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			// TODO Auto-generated method stub
			frame.repaint();
			
			
		}
    }
	JFrame frame;
	JPanel panel;
	JLabel about,age,gender,temp,symptoms,travel,historyofdiseases;
	JRadioButton male, female;
	JCheckBox fever,cough,respiratory,Diabetes,bp,HeartDisease,LungDisease;
	JComboBox cage,ctemp,ans;
	JButton CheckResult,clear;
	JScrollBar hbar=new JScrollBar(JScrollBar.HORIZONTAL, 30, 20, 0, 250);
    JScrollBar vbar=new JScrollBar(JScrollBar.VERTICAL, 30, 40, 0, 250);
    ImageIcon icon = new ImageIcon("‪C:\\Users\\ASUS\\Desktop\\project\\11.png");
	JLabel label = new JLabel(icon);
	String username=null,output=null;
	void initialize()
	{
	
		frame= new JFrame("Stage-1 Diagnosis of COVID-19");
		panel=new JPanel();
		about=new JLabel("Please enter your details here-");
		age=new JLabel("Age :",SwingConstants.CENTER );
		cage=new JComboBox(ages);
		gender=new JLabel("Gender :");
		male=new JRadioButton("Male");
		female=new JRadioButton("Female");
		ButtonGroup bg=new ButtonGroup();
		bg.add(male);
		bg.add(female);
		temp=new JLabel("  Current Body Temperature :");
		ctemp=new JComboBox(temps);
		ctemp.setSize(200, ctemp.getPreferredSize().height);
		ctemp.setSize(800,800);
		symptoms=new JLabel("                              Symptoms :");
		fever=new JCheckBox("Fever");
		cough=new JCheckBox("Cough");
		respiratory=new JCheckBox("Respiratory Problem");
		travel=new JLabel("        Travel Hisory :");
		ans=new JComboBox(answer);
		historyofdiseases=new JLabel("History of Diseases :");
		Diabetes=new JCheckBox("Diabetes");
		bp=new JCheckBox("Blood Pressure");
		HeartDisease=new JCheckBox("Heart Disease");
		LungDisease=new JCheckBox("Lung Disease     ");
		CheckResult=new JButton("CHECK RESULTS");
		clear=new JButton("CLEAR");	
	}
	void addition() 
	{
		panel.add(about);
		panel.add(age);
		panel.add(cage);
		panel.add(gender);
		panel.add(male);
		panel.add(female);
		panel.add(temp);
		JPanel wrapper = new JPanel();
		wrapper.add( ctemp );
		panel.add( wrapper );
		panel.add(symptoms);
		panel.add(fever);
		panel.add(cough);
		panel.add(respiratory);
		panel.add(travel);
		panel.add(ans);
		panel.add(historyofdiseases);
		panel.add(Diabetes);
		panel.add(bp);
		panel.add(HeartDisease);
		panel.add(LungDisease);
		panel.add(CheckResult);
		panel.add(clear);
		frame.add(label);
		hbar.addAdjustmentListener(new MyAdjustmentListener( ));
        vbar.addAdjustmentListener(new MyAdjustmentListener( ));
		frame.getContentPane().add(hbar, BorderLayout.SOUTH);
        frame.getContentPane().add(vbar, BorderLayout.EAST);
		frame.add(panel);
		frame.setSize(500,300);
		panel.setBackground(Color.white);
		frame.setVisible(true);
	}
	void answerSelected() throws ClassNotFoundException, SQLException
	{
		String eage=null,egender=null,etemperature=null,esymptoms=null,etravelhistory=null,ehistoryofdisease=null;
		eage=(String) cage.getItemAt(cage.getSelectedIndex());
		
		if(male.isSelected())
			egender="Male";
		if(female.isSelected())
			egender="Female";
		
		etemperature=(String) ctemp.getItemAt(ctemp.getSelectedIndex());
		//if multiple slected values will get updated
		if(fever.isSelected())
			esymptoms="Fever";
		if(cough.isSelected())
			esymptoms="Cough";
		if(respiratory.isSelected())
			esymptoms="Respiratory Problem";
		
		//this code will get reduce okay woh change kar dungi okay wait
		if(fever.isSelected()&&cough.isSelected())
			esymptoms="Fever and Cough";
		if(fever.isSelected()&&respiratory.isSelected() )
			esymptoms="Fever and Respiratory Problem";
		if(cough.isSelected()&&respiratory.isSelected())
			esymptoms="Cough and Respiratory Problem";
		
		if(fever.isSelected()&&cough.isSelected()&&respiratory.isSelected())
			esymptoms="Fever, Cough and Respiratory Problem";
		
		etravelhistory=(String)ans.getItemAt(ans.getSelectedIndex());
		if(bp.isSelected())
			ehistoryofdisease="Blood Pressure";//count++
		if(Diabetes.isSelected())
			ehistoryofdisease="Diabetese";
		if(HeartDisease.isSelected()) 
			ehistoryofdisease="heart Disease";
		if(LungDisease.isSelected())
			ehistoryofdisease="Lung Disease";
		
		if(bp.isSelected()&&LungDisease.isSelected()&& HeartDisease.isSelected()&&Diabetes.isSelected())
			ehistoryofdisease="Blood Pressure, Lung and Heart Disease and Diabetese";
		
		if(bp.isSelected()&&LungDisease.isSelected())
			ehistoryofdisease="Blood Pressure and Lung Disease";
		if(bp.isSelected()&&Diabetes.isSelected())
			ehistoryofdisease="Blood Pressure and Diabetese ";
		if(LungDisease.isSelected()&& HeartDisease.isSelected())
			ehistoryofdisease="Lung and Heart Disease";
		if(LungDisease.isSelected()&& Diabetes.isSelected())
			ehistoryofdisease="Lung Disease and Diabetese";
		if(HeartDisease.isSelected()&& Diabetes.isSelected())
			ehistoryofdisease="Heart Disease and Diabetese";
		if(bp.isSelected()&& HeartDisease.isSelected())
			ehistoryofdisease="Blood Pressure and Heart Disease";
		if(bp.isSelected()&&LungDisease.isSelected()&&Diabetes.isSelected())
			ehistoryofdisease="Blood Pressure, Lung Disease and Diabetese";
		if(bp.isSelected()&& HeartDisease.isSelected()&&Diabetes.isSelected())
			ehistoryofdisease="Blood Pressure, Heart Disease and Diabetese";
		
		if((new Integer(eage)>new Integer("50")) && (etemperature=="High Fever(>102°F)") && (esymptoms=="Fever, Cough and Respiratory Problem") && (etravelhistory=="Yes") && (ehistoryofdisease=="Blood Pressure, Lung Disease and Diabetese")) {
			output="\n#HIGH RISK OF COVID_19#"
					+ "\n1.We advice you to seek immediate medical attention!"
					+ "\n2.COVID-19 testing is highly recommended for you from your nearby hospital."
					+ "\n3.Monitor your symptoms and Isolate yourself"
					+ "\n4.Your Health matters to us if you have more Questions Call on this Helpline Number-9852417631";			
		}
		// ta
		else if((new Integer(eage)>new Integer("50")) || (new Integer(eage)<new Integer("10")) && (etemperature=="Fever(98.6°F-102°F)") &&(esymptoms=="Fever and Respiratory Problem") &&  (etravelhistory=="No")  && (ehistoryofdisease=="Blood Pressure and Lung Disease") ) {
			output="\n#MEDIUM RISK OF COVID_19#"
					+ "\n1.We advice you to consult a Physician and Start Home Isolation immediately!"
					+ "\n2.COVID-19 test may be required for you from your nearby hospital."
					+ "\n3.Monitor your symptoms and get medical attention if your Situation gets Worsen"
					+ "\n4.Your Health matters to us if you have more Questions Call on this Helpline Number-9852417631";

		}
		// o \ wala na wait tum karo
		else
		{
			output="\n#LOW RISK OF COVID_19#"
					+ "\n1.We advice you to stay at Home and Take Care of and start Home Isolation"
					+ "\n2.COVID-19 test is not required for you."
					+ "\n3.Monitor your symptoms and get medical attention if your Situation gets Worsen"
					+ "\n4.Your Health matters to us if you have more Questions Call on this Helpline Number-9852417631";
			
		}
		
	updateResult(output);
		
	}
	
	private void updateResult(String output) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Database base=new Database();
		
		java.sql.Connection con=base.getConnection();
		
		
		PreparedStatement pst=con.prepareStatement("update registered set result=? where username=?");
		pst.setString(1, output);
		pst.setString(2, username);
		pst.executeUpdate();
		
		
	}
	
	Stage1_Diagnosis() 
	{
		initialize();
		CheckResult.addActionListener(this);
		addition();
	}
	Stage1_Diagnosis(String s){
		this();	
		this.username=s;
		System.out.println("HELLO :" + this.username);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==CheckResult) {
			try {
				answerSelected();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//first two condtions
			frame.dispose();
			try {
				new Result(this.username,output);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//iske toh udhar username match karrake re
			
		}
		else {
			System.out.println("bye");
		}
		
	}

}


