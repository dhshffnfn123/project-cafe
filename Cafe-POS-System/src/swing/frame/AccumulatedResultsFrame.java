package swing.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import swing.method.AccrualCompletionJOP;

public class AccumulatedResultsFrame{
	private String order_name, grade;
	
	public AccumulatedResultsFrame(String phoneNum, int point, int coupon, 
			ArrayList<JPanel> panelR, String grade, String order_name) {
		
		this.order_name = order_name;
		this.grade = grade;
		
		//기존 정보 지우기
		for (JPanel panel : panelR) {			
			panel.removeAll();
		}
		
		JLabel phoneLabel = new JLabel(phoneNum);
		JLabel pointLabel = new JLabel(Integer.toString(point));
		JLabel couponLabel = new JLabel(Integer.toString(coupon));
		
		label_style(phoneLabel);
		label_style(pointLabel);
		label_style(couponLabel);
		
		pointLabel.setBounds(167, 0, 250, 65);
		couponLabel.setBounds(167, 0, 250, 65);
		
		panelR.get(0).add(phoneLabel);
		panelR.get(1).add(pointLabel);
		panelR.get(2).add(couponLabel);
		
		for (JPanel panel : panelR) {			
			panel.repaint();
		}
		
		new AccrualCompletionJOP().showMessageDialog(null, "적립이 완료되었습니다.", 
				"완료", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void label_style(JLabel label) {
		label.setBounds(65, 0, 250, 65);
		label.setForeground(new Color(94,94,94));
		label.setFont(new Font("돋움체",Font.BOLD,17));
	}

}
