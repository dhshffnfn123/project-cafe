package swing.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jdbc.method.TotalInfo;

public class ChecksalesFrame extends JFrame{		
	
	static DefaultTableModel model;
	
	
	public static void main(String[] args) {
		
		// 부모 프레임 생성 및 기본 스타일 지정 실시
		JFrame frm = new JFrame("주문 관리 프로그램");
		setJFrameStyle(frm);

		// 대분류 ---------------------------------------------------
		JLabel big_txt = new JLabel("★구분★");
		big_txt.setBounds(0, 0, 100, 50);
		setJLabelStyle(big_txt);
		
		String big_arr[] = {"연 매출", "월 매출", "일 매출", "총 매출"};
		JComboBox<String> big_combo = new JComboBox<String>(big_arr);
		big_combo.setBounds(100, 0, 1300, 50);
		setJComboBoxStyle(big_combo);
		
		JButton big_select_btn = new JButton("♥♥♥");			
		big_select_btn.setBounds(1400, 0, 100, 50);
		setJButtonStyle(big_select_btn); // 기본 버튼 스타일 지정 메소드 호출
		
		big_select_btn.addActionListener(new ActionListener() {
		// if ((big_combo.getSelectedItem().equals("연 매출")) && (e.getSource() == big_select_btn)) {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					
				
			}
		});
		// -----------------------------------------------------------
		

		
		JTable jt = new TotalInfo().getTotalInfo();
		jt.setBounds(0, 110, 1500, 850);					
		
	
		frm.getContentPane().add(big_txt);
		frm.getContentPane().add(big_combo);
		frm.getContentPane().add(big_select_btn);
		frm.getContentPane().add(jt, BorderLayout.CENTER);
		
		
		frm.setVisible(true);
		
	}
		
	
	// JFrame 부모 기본 스타일 지정 메소드
	public static void setJFrameStyle(JFrame frame) {
		frame.setSize(1500, 1000); // 부모 프레임 크기 설정 (가로, 세로) 및 배경색 지정
		frame.setBackground(Color.BLACK); // 부모 프레임을 화면 가운데에 배치		 
		frame.setLocationRelativeTo(null); // 부모 프레임을 닫았을 때 메모리에서 제거되도록 설정 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 부모 프레임 창 크기 고정 실시				
		frame.setResizable(false); // 부모 레이아웃 설정		 
		frame.getContentPane().setLayout(null);
	}	
	
	// JLabel 기본 스타일 지정 메소드
	public static void setJLabelStyle(JLabel txt) {						
		txt.setOpaque(true); // setBackground 적용하기 위한 선행 설정
		txt.setBackground(Color.GRAY); // 백그라운드 색상 정의
		txt.setForeground(Color.magenta); // 텍스트 색상 정의
		txt.setFont(new Font("궁서체", 3, 20)); // 폰트 정의
		txt.setHorizontalAlignment(JLabel.CENTER); // 텍스트 센터 표시 설정
	}
	
	// JComboBox 기본 스타일 지정 메소드
	public static void setJComboBoxStyle(JComboBox combo) {							
		combo.setBackground(Color.LIGHT_GRAY); // 백그라운드 색상 정의
		combo.setForeground(Color.WHITE); // 텍스트 색상 정의
		combo.setFont(new Font("궁서체", Font.BOLD, 20)); // 폰트 정의		
	}
	
	// JButton 기본 스타일 지정 메소드
	public static void setJButtonStyle(JButton btn) {
		btn.setBackground(Color.DARK_GRAY); // 백그라운드 색상 정의
		btn.setForeground(Color.pink); // 텍스트 색상 정의
		btn.setFont(new Font("궁서체", Font.BOLD, 20)); // 폰트 정의
		btn.setHorizontalAlignment(JLabel.CENTER); // 텍스트 센터 표시 설정
	}


}
