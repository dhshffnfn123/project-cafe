package action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import swing.method.RoundJTextField;

public class AccumulatedTextFiel extends MouseAdapter {

	RoundJTextField textField;

	public AccumulatedTextFiel(RoundJTextField textField) {
		this.textField = textField;
	}

	// 텍스트필드 클릭시 기존에 있던 "전화번호를 입력하세요" 텍스트를 사라지게 만듦.
	public void mouseClicked(MouseEvent e) {
		textField.setText(null);
	}

}
