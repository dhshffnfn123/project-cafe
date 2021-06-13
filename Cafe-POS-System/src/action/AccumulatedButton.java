package action;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;

import swing.frame.RoundJTextField;
import swing.frame.AccumulatedFundFrame;

public class AccumulatedButton extends MouseAdapter implements ActionListener{
	
	RoundJTextField textField;
	ArrayList<JButton> numBtns;
	String btnNum;
	
	//적립금텍스트필드리스너 생성자에서 텍스트필드 매개변수로 받기
	public AccumulatedButton(RoundJTextField textField, ArrayList<JButton> numBtns) {
		this.textField = textField;
		this.numBtns = numBtns;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		btnNum = ((JButton)e.getSource()).getText();
		System.out.println(btnNum);
	}
	


}








