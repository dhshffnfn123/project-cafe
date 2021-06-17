package action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;

import jdbc.method.ClickAccumulatedFundTextFile;
import swing.frame.AccumulatedFundFrame;
import swing.method.RoundJTextField;

public class AccumulatedFundButton extends MouseAdapter {
	private AccumulatedFundFrame accumulatedFundFrame;
	private RoundJTextField textField;
	private ArrayList<JButton> numBtns;
	private String btnNum;
	private StringBuilder phoneNum;

	// 적립금텍스트필드리스너 생성자에서 텍스트필드 매개변수로 받기
	public AccumulatedFundButton(AccumulatedFundFrame accumulatedFundFrame, RoundJTextField textField,
			ArrayList<JButton> numBtns, StringBuilder phoneNum) {
		this.accumulatedFundFrame = accumulatedFundFrame;
		this.textField = textField;
		this.numBtns = numBtns;
		this.phoneNum = phoneNum;
	}

	@Override // mouseReleased를 사용해야지 버튼들이 잘눌림
	public void mouseReleased(MouseEvent e) {
		btnNum = ((JButton) e.getSource()).getText();
		int phoneLeng = phoneNum.length() - 1;

		// 0 ~ 9까지 그리고 ←,적립 글자를 비교하기 위해 for문 돌리는 것.
		for (int i = 0; i < numBtns.size(); i++) {
			String strNum = Integer.toString(i);

			// 스트링빌더에 저장해서 이어나오게끔하기
			if (btnNum.equals(strNum)) {
				phoneNum.append(strNum);
				textField.setText(phoneNum.toString());
			} else if (btnNum.equals("←")) {
				// 하나를 지우면 그 다음 인덱스도 같이 체크돼버려서 StringIndexOutOfBoundsException에러가 뜸 그래서 예외처리.
				// 실제 동작은 문제 없음.
				try {
					phoneNum.deleteCharAt(phoneLeng);
					textField.setText(phoneNum.toString());
				} catch (Exception e1) {
					return;
				}

				// 텍스트 필드에 입력했던 번호를 전부 지울시에 기본 문구 나오게 설정
				int fieldGetTextLeng = textField.getText().length();
				if (fieldGetTextLeng == 0) {
					textField.setText("전화번호를 입력하세요");
				}

			} else if (btnNum.equals("적립")) {
				new ClickAccumulatedFundTextFile(textField, accumulatedFundFrame);
				break; // 적립을 누르면 끝(break안하면 텍스트필드 값 여러번 뜸)
			}

		}

	}

}
