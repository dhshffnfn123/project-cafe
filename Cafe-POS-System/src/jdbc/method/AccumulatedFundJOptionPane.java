package jdbc.method;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import swing.frame.RoundJTextField;

//입력 번호가 틀릴시 뜨는 경고 팝업창
public class AccumulatedFundJOptionPane extends JOptionPane{

	public AccumulatedFundJOptionPane(RoundJTextField textField) {
		
		// 팝업창 더 깔끔하게 만들기
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		// 번호 입력이 없을 때와 있을 때를 구분지어 놓음
		if(textField.getText().equals("전화번호를 입력하세요")) {
			jOptionPaneUI();
			showMessageDialog(null, "번호를 입력해주세요", 
					"오류", JOptionPane.OK_CANCEL_OPTION);
		}else {
			jOptionPaneUI();    
			showMessageDialog(null, "일치하는 내용이 없습니다", 
					"오류", JOptionPane.OK_CANCEL_OPTION);
		}
	}
	
	// 팝업창 글꼴 설정
	private void jOptionPaneUI() {
		UIManager.put("OptionPane.messageFont",new Font("맑은 고딕",Font.PLAIN,12));
	}

}


