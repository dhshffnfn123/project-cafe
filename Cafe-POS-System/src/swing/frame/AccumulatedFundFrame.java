package swing.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ColorConvertOp;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import action.AccumulatedFundButton;
import action.AccumulatedFundTextFiel;
import swing.method.RoundJTextField;


public class AccumulatedFundFrame extends DefaultFrame{
	
	/*
	 * setSize(), setPreferredSize() 둘다 크기를 지정해주는 메서드.
	 * 하지만 layout에서 지정을 할때는 setPreferredSize(new Dimension(x,y))를 써야한다.
	 * 
	 * */
	
	public AccumulatedFundFrame() {
	
		//(프레임)====================================================================
		
		//기본 프레임 설정
		setTitle("적립 화면");
		setSize(680,520);
		setLocation(500,100);
		setResizable(false);
		getContentPane().setBackground(new Color(243, 235, 207));
		
		//입력 필드 설정, 글씨 설정
		Font font = new Font("맑은 고딕", Font.PLAIN, 18);
		RoundJTextField textField = new RoundJTextField(30);
		textField.setFont(font);
		textField.setBounds(58,30,260,65);
		textField.setForeground(new Color(160, 160, 160));
		textField.setText("전화번호를 입력하세요");  
		
		//번호 버튼이 들어갈 패널 추가
		JPanel num_panel = new JPanel();
		num_panel.setBounds(36,120,300,330);
		num_panel.setLayout(new GridLayout(4,3,8,8));
		num_panel.setBackground(new Color(245, 235, 208));
		
		//번호,포인트,쿠폰 필드 오른쪽에 추가
		JPanel phone_panel = new JPanel();
		JPanel point_panel = new JPanel();
		JPanel coupon_panel = new JPanel();
		right_panel_style(phone_panel);
		right_panel_style(point_panel);
		point_panel.setBounds(380, 100, 250, 60);
		right_panel_style(coupon_panel);
		coupon_panel.setBounds(380, 170, 250, 60);
		
		//오른쪽 필드들에 라벨 추가
		JLabel phone_label = new JLabel("Phone");
		JLabel point_label = new JLabel("Point");
		JLabel coupon_label = new JLabel("Coupon");
		
		phone_label.setBounds(10, 0, 250, 70);
		phone_label.setForeground(new Color(181,181,181));
		phone_label.setFont(new Font("Yu Gothic Light",Font.BOLD,17));
		
		phone_panel.add(phone_label);
		point_panel.add(point_label);
		coupon_panel.add(coupon_label);
		
	
		ArrayList<JButton> numBtns = new ArrayList<>();
		StringBuilder phoneNum = new StringBuilder();  
		//텍스트 필드에서 입력 받는 숫자를 이어서 받기 위해 스트링빌더 준비
		
		
		//(액션)======================================================================
		
		//텍스트필드액션 불러오기
		textField.addMouseListener(new AccumulatedFundTextFiel(textField));
		//텍스트필드 비활성화 시켜서 키보드로 입력 못하게 막음.
		textField.setEditable(false); 
		
		//numBtns의 인덱스 9,10,11은 ←,0,적립으로 글자 변경 or 각각의 맞는 스타일로 다시 변경.
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
				numBtns.get(i).setForeground(Color.white);  //버튼 글자색 변경
			}else {
				numBtns.add(new JButton(Integer.toString(i + 1)));
				num_button_style(numBtns,i);
			}
			
			//버튼 클릭시 일어나는 액션
			numBtns.get(i).addMouseListener(new AccumulatedFundButton(this,textField, numBtns, phoneNum));
		
		}
		
		
		//(프레임에 추가하면서 마무리)========================================================
		
		//번호패널에 번호 버튼들 추가
		for (JButton btn : numBtns) {
			num_panel.add(btn);	
		}
		
		//프레임에 패널이랑 텍스트필드 추가
		////////////////////////////////////////////
		add(phone_panel);
		add(point_panel);
		add(coupon_panel);
		add(num_panel);
		add(textField);

		setVisible(true);
		repaint();
	}
	
	//버튼 스타일 메서드
	private void num_button_style(ArrayList<JButton> numBtns, int num) {
		numBtns.get(num).setFont(new Font("맑은 고딕",Font.PLAIN,20));
		numBtns.get(num).setBackground(new Color(255,255,255));
		numBtns.get(num).setForeground(Color.GRAY);
		numBtns.get(num).setBorderPainted(false);
		
	}
	
	private void right_panel_style(JPanel panel) {
		panel.setBounds(380,30,250,60);
		panel.setLayout(null);
		panel.setBackground(new Color(255, 255, 255));
	}

	public JFrame getAccumulatedFundFrame() {
		return this;
	}
	
	public static void main(String[] args) {
		new AccumulatedFundFrame();
		
	}

}
