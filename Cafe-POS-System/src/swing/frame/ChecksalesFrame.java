package swing.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import action.ChecksalesSelectButton;
import jdbc.method.TotalInfo;

public class ChecksalesFrame extends DefaultFrame{		
	
	public ChecksalesFrame() {
		// 부모 프레임 생성 및 기본 스타일 지정 실시
		
		setTitle("매출관리");

		// 대분류 ---------------------------------------------------
		JLabel sortation_txt = new JLabel("구 분");
		sortation_txt.setBounds(0, 68, 100, 40);
		setJLabelStyle(sortation_txt);
				
		ImageIcon icon_logo = new ImageIcon("./data/Logo1.png");
		Image img_logo = icon_logo.getImage();
		Image cimg_logo = img_logo.getScaledInstance(1500, 68, Image.SCALE_FAST);
		ImageIcon cicon_logo = new ImageIcon(cimg_logo);
		
		JLabel imglb = new JLabel(cicon_logo);
		imglb.setOpaque(true);
		imglb.setBounds(0, 0, 1500, 68);
		
		String combo_arr[] = {"","연 매출", "월 매출", "일 매출"};
		JComboBox<String> select_combo = new JComboBox<String>(combo_arr);
		select_combo.setBounds(100, 68, 1300, 40);
		setJComboBoxStyle(select_combo);
		
		DefaultTableModel model = new TotalInfo().getTotalInfo();
		JTable jt = new JTable(model);
		model.fireTableDataChanged();
		jt.updateUI();
		JScrollPane jscroll = new JScrollPane(jt);
		jscroll.setBounds(0, 110, 500, 650);				
		
		DefaultTableModel total_model = new TotalInfo().getTotalInfoTotal();
		JTable jt2 = new JTable(total_model);
		total_model.fireTableDataChanged();
		jt2.updateUI();
		JScrollPane jscroll2 = new JScrollPane(jt2);
		jscroll2.setBounds(0, 800, 500, 50);					
				
		JButton select_btn = new JButton("조회");			
		select_btn.setBounds(1400, 68, 100, 40);
		setJButtonStyle(select_btn); // 기본 버튼 스타일 지정 메소드 호출
		select_btn.addActionListener(
				new ChecksalesSelectButton(
						this,select_btn, select_combo, model, jt));		
		// if ((big_combo.getSelectedItem().equals("연 매출")) && (e.getSource() == big_select_btn)) {

		// -----------------------------------------------------------
				

				
				
		getContentPane().add(imglb);
		getContentPane().add(sortation_txt);
		getContentPane().add(select_combo);
		getContentPane().add(select_btn);
		getContentPane().add(jscroll, BorderLayout.CENTER);
		getContentPane().add(jscroll2, BorderLayout.CENTER);
				
		this.repaint();
		
	}
				
			
	// JLabel 기본 스타일 지정 메소드
	public static void setJLabelStyle(JLabel txt) {		
				
		Color color = new Color(0x1E821E);
		txt.setOpaque(true); // setBackground 적용하기 위한 선행 설정
		txt.setBackground(color); // 백그라운드 색상 정의
		txt.setForeground(Color.white); // 텍스트 색상 정의
		txt.setFont(new Font("견고딕", Font.BOLD, 25)); // 폰트 정의
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
		btn.setFont(new Font("조회", Font.BOLD, 20)); // 폰트 정의
		btn.setHorizontalAlignment(JLabel.CENTER); // 텍스트 센터 표시 설정
	}
	
	public static void main(String[] args) {
		new ChecksalesFrame();
		
	}
}
