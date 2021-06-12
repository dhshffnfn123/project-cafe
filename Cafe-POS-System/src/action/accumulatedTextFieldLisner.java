package action;

import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import swing.frame.RoundJTextField;
import swing.frame.accumulatedFundFrame;

public class accumulatedTextFieldLisner extends MouseAdapter{
	
	RoundJTextField textField;
	
	//적립금텍스트필드리스너 생성자에서 텍스트필드 매개변수로 받기
	public accumulatedTextFieldLisner(RoundJTextField textField) {
		this.textField = textField;
	}	
	
	public void mouseClicked(MouseEvent e) { 
		//텍스트필드 클릭시 기존에 있던 "전화번호를 입력하세요" 텍스트를 사라지게 만듦.
		textField.setText(null); 
	}
	
}







