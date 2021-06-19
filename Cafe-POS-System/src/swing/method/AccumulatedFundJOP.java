package swing.method;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

//입력 번호가 틀릴시 뜨는 경고 팝업창
public class AccumulatedFundJOP extends JOptionPane{
	ArrayList<JPanel> panelR;

	public AccumulatedFundJOP(RoundJTextField textField, ArrayList<JPanel> panelR) {
		this.panelR = panelR;
		initializePanel();
		
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
			showMessageDialog(null, "일치하는 정보가 없습니다.\n회원이 아닌 경우,\n회원등록을 진행해주세요.", 
					"오류", JOptionPane.OK_CANCEL_OPTION);
			
		}
	}
	
	// 팝업창 글꼴 설정
	private void jOptionPaneUI() {
		UIManager.put("OptionPane.messageFont",new Font("맑은 고딕",Font.PLAIN,12));
	}
	//경고창 뜰때 나와있던 회원 정보 초기화
	private void initializePanel() {
		for (JPanel panel : panelR) {
			panel.removeAll();
			
			panel.repaint();
		}
	}

}


