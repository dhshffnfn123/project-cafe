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

public class AccumulatedTextFieldButton extends MouseAdapter implements ActionListener{
	
	RoundJTextField textField;
	ArrayList<JButton> numBtns;
	
	//적립금텍스트필드리스너 생성자에서 텍스트필드 매개변수로 받기
	public AccumulatedTextFieldButton(RoundJTextField textField, ArrayList<JButton> numBtns) {
		this.textField = textField;
		this.numBtns = numBtns;
	}

	public void actionPerformed(ActionEvent e) {
		String btnNum = ((JButton)e.getSource()).getText();
		
		
		
	}
	
	//텍스트필드 클릭시 기존에 있던 "전화번호를 입력하세요" 텍스트를 사라지게 만듦.
	public void mouseClicked(MouseEvent e) { 
		textField.setText(null); 

	}
}







