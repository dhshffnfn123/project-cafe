package swing.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.ColorConvertOp;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class accumulatedFundFrame extends DefaultFrame{
	
	/*
	 * setSize(), setPreferredSize() 둘다 크기를 지정해주는 메서드.
	 * 하지만 layout에서 지정을 할때는 setPreferredSize(new Dimension(x,y))를 써야한다.
	 * 
	 * */
	
	public accumulatedFundFrame() {
	
		//기본 프레임 설정
		setTitle("적립 화면");
		setSize(450,610);
		setLocation(700,300);
		setResizable(false);
		getContentPane().setBackground(new Color(245, 235, 208));
		
		//입력 필드 설정, 글씨 설정
		Font font = new Font("맑은 고딕", Font.PLAIN, 18);
		RoundJTextField textField = new RoundJTextField(30);
		textField.setFont(font);
		textField.setBounds(95,70,250,70);
		textField.setForeground(new Color(180, 180, 180));
		
		//번호 버튼이 들어갈 패널 추가
		JPanel num_panel = new JPanel();
		num_panel.setPreferredSize(new Dimension(350,400));
		num_panel.setBounds(70,170,300,330);
		num_panel.setLayout(new GridLayout(4,3,8,8));
		num_panel.setBackground(new Color(245, 235, 208));
		
		
		//번호 버튼 생성
		ArrayList<JButton> numBtns = new ArrayList<>();
		
		
		for (int i = 0; i < 12; i++) {
			if (i == 10) {
				numBtns.add(new JButton("0"));
				num_button_style(numBtns,i);
			}else if (i == 9){
				numBtns.add(new JButton("←"));
				num_button_style(numBtns,i);
				numBtns.get(i).setFont(new Font("궁서체",Font.BOLD,30));
				
			}else if (i == 11) {
				numBtns.add(new JButton("적립"));
				num_button_style(numBtns,i);
				numBtns.get(i).setBackground(new Color(115,185,112));
				numBtns.get(i).setForeground(Color.white);
				
			}else {
				numBtns.add(new JButton(Integer.toString(i + 1)));
				num_button_style(numBtns,i);
			}
		}
		
		for (JButton btn : numBtns) {
			num_panel.add(btn);	
		}
		
		
		textField.setText("전화번호를 입력하세요");  //마우스가 올라갔을 때 액션으로 글자 없애기,글자색 진하게 바꾸기
		
		
		add(num_panel);
		add(textField);

		setVisible(true);
		repaint();
	}
	
	//버튼 스타일 메서드
	void num_button_style(ArrayList<JButton> numBtns, int num) {
		numBtns.get(num).setFont(new Font("맑은 고딕",Font.PLAIN,20));
		numBtns.get(num).setBackground(new Color(255,255,255));
		numBtns.get(num).setForeground(Color.GRAY);
		numBtns.get(num).setBorderPainted(false);
		
	}
	
	public static void main(String[] args) {
		new accumulatedFundFrame();
		
	}

}
