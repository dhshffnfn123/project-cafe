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
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import action.ExitAccumulatedResults;

public class AccumulatedResultsFrame extends DefaultFrame{
	
	public AccumulatedResultsFrame(AccumulatedFundFrame accumulatedFundFrame,
			String phoneNum, int point, int coupon) {
		accumulatedFundFrame.dispose();
		
		//기본 프레임 설정
		setTitle("회원 정보");
		setSize(330,430);
		setLocation(500,180);
		setResizable(false);
		getContentPane().setBackground(new Color(243, 235, 207));
		
		//서브 패널 넣을 메인 패널
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0,0,330,310);
		mainPanel.setLayout(new GridLayout(3,1,10,10));
		mainPanel.setBackground(new Color(243, 235, 207));
		
		
		//서브패널,라벨 생성
		ArrayList<JPanel> subPanelArr = new ArrayList<>();
		ArrayList<JLabel> labelArr = new ArrayList<>();
		
		JLabel label1 = new JLabel(String.format(" 전화번호%20s", phoneNum));
		JLabel label2 = new JLabel(String.format(" 보유포인트%20s%s", point, "p"));
		JLabel label3 = new JLabel(String.format(" 보유쿠폰%20s%s", coupon, "개"));
		
		JPanel subPanel;
		for(int i = 0; i < 3; i++) {
			//서브패널
			subPanelArr.add(new JPanel());
			JPanel panel = subPanelArr.get(i);
			panel.setBackground(new Color(255,255,255));
			
			mainPanel.add(panel);
			
			//라벨 리스트에 라벨 추가
			labelArr.add(label1);
			labelArr.add(label2);
			labelArr.add(label3);
			
			subPanel = subPanelArr.get(i);
			//서브패널 레이아웃을 null 지정(setBounds 하기 위해)
			subPanel.setLayout(null);
			
			//서브패널에 라벨 추가
			subPanel.add(labelArr.get(i));
	
		}
		
		//라벨 위치랑 폰트 스타일 적용
		labelStyle(label1);
		labelStyle(label2);
		labelStyle(label3);
		
		//버튼
		JButton btn = new JButton("확인");
		btn.setFont(new Font("맑은 고딕",Font.PLAIN,16));
		btn.setBackground(new Color(115,185,112));
		btn.setForeground(Color.white);
		btn.setBounds(110, 325, 90, 50);
		btn.setBorderPainted(false);

		//확인 누를시 종료
		btn.addActionListener(new ExitAccumulatedResults());
		
		add(mainPanel);
		add(btn);
	
		setVisible(true);
		repaint();
	
	}
	
	//라벨 스타일 메서드
	private void labelStyle(JLabel label) {
		label.setBounds(60, 0, 330, 100);
		label.setForeground(Color.GRAY);
		label.setFont(new Font("맑은 고딕",Font.PLAIN,14));
		
	}
}












