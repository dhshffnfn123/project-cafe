package swing.frame;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import action.AddConfirmBtn;
import action.StockCancelBtn;
import action.UpdateConfirmBtn;

public class StockUpdateFrame extends DefaultFrame {
	
	private JTable table;
	private JTextField count_tf, tf;
	private JLabel idL, nameL, countL, updateL;
	private JButton confirm_btn, cancel_btn;
	private JPanel info_panel;
	private String name;
	private int id, count;
	private Color y_color = new Color(163, 148, 132);

	private Font font = new Font("맑은 고딕", Font.BOLD, 18);
	private Font small_font = new Font("맑은 고딕", Font.BOLD, 15);
	
	public StockUpdateFrame(String name, String id, int count, JTable table) {
		
		setTitle("Stock Update");
		setLayout(null);
		setLocation(1000, 400);
		setSize(450, 280);
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setVisible(true);
		
		info_panel = new JPanel();
		info_panel.setBounds(10, 10, 410, 150);
		info_panel.setBackground(Color.WHITE);
		info_panel.setBorder(new TitledBorder(new LineBorder(new Color(3, 102, 53), 4), "INFO"));
		info_panel.setLayout(null);
		
		add(info_panel);
		
		// 라벨
		idL = new JLabel();
		idL.setText("ID     :  " + id);
		idL.setBounds(30, 20, 300, 30);
		idL.setFont(font);
		info_panel.add(idL);

		nameL = new JLabel("이름  :  " + name);
		nameL.setBounds(30, 60, 450, 30);
		nameL.setFont(font);
		info_panel.add(nameL);

		countL = new JLabel("수량  :  " + count);
		countL.setFont(font);
		countL.setBounds(30, 100, 300, 30);
		info_panel.add(countL);

		updateL = new JLabel("재고 수량");
		updateL.setFont(small_font);
		updateL.setBounds(20, 160, 300, 30);
		add(updateL);
		// 카운트 텍스트 필드
		count_tf = new JTextField(10);
		count_tf.setBounds(18, 190, 230, 30);
		add(count_tf);

		confirm_btn = new JButton("수정");
		confirm_btn.setFont(small_font);
		confirm_btn.setBounds(260, 190, 70, 30);
		confirm_btn.setBackground(new Color(3, 102, 53));
		confirm_btn.setForeground(Color.WHITE);
		add(confirm_btn);

		cancel_btn = new JButton("취소");
		cancel_btn.setFont(small_font);
		cancel_btn.setBounds(345, 190, 70, 30);
		cancel_btn.setBackground(new Color(3, 102, 53));
		cancel_btn.setForeground(Color.WHITE);
		
		add(cancel_btn);
		
		cancel_btn.addActionListener(new StockCancelBtn(this));
		confirm_btn.addActionListener(new UpdateConfirmBtn(count_tf, table, this, name));
		
		setVisible(true);
		
	}

}
