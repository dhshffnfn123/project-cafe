package test;

import javax.swing.*;

import jdbc.hikari.HikariCP;
import sun.jvm.hotspot.oops.ObjArrayKlass;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Test_Table extends JFrame {
	
	public String[] col_name = {"COL1","COL2"};
	
	public Object[][] data = new Object[50][2];
	
	JTextField name;
	
	 public Default_JTable() {
		  final JFrame f = new JFrame();
		     Container cp = f.getContentPane();
		     
		  cp.setLayout(new FlowLayout());
		  name = new JTextField("",10);
		  name.setCaretColor(Color.black);
		  cp.add(new JLabel("검색조건 : "));
		  	cp.add(name);
		  	
		  	
		  	name.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					for(int j = 0; j < 50; j++) {
						data[j][0] = "";
						data[j][1] = "";
					}
						dataGet();
						f.repaint();
						}
		  			}
		);
		  JTable table = new JTable(data,col_name);
		  JScrollPane scrollPane = new JScrollPane(table);
		  cp.add(scrollPane, BorderLayout.CENTER);
		  
		  f.setSize(500, 500);
		  f.setVisible(true);

		
	
	
	
	
}


