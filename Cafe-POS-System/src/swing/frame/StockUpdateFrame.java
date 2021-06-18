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

public class StockUpdateFrame extends JFrame {
	
	private JTable table;
	private JTextField count_tf, tf;
	private JLabel idL, nameL, countL, updateL;
	private JButton confirm_btn, cancel_btn;
	private JPanel info_panel;
	private String name,id;
	private int  count;
	private Color y_color = new Color(163, 148, 132);

	private Font font = new Font("맑은 고딕", Font.BOLD, 20);
	private Font nomal_font = new Font("맑은 고딕", Font.BOLD, 20);
	private Font small_font = new Font("맑은 고딕", Font.BOLD, 15);
	
	public StockUpdateFrame(String name, String id, int count, JTable table) {
		
		setTitle("STOCK_UPDATE");
		setLayout(null);
		setLocation(200, 200);
		setSize(300, 300);
		setResizable(false);
		getContentPane().setBackground(y_color);
		setVisible(true);
		
		info_panel = new JPanel();
		info_panel.setBounds(10, 10, 265, 150);
		info_panel.setBackground(y_color);
		info_panel.setBorder(new TitledBorder(new LineBorder(Color.white, 5), "INFO"));
		info_panel.setLayout(null);
		
		add(info_panel);
		
		
		// 라벨
		idL = new JLabel();
		idL.setText("ID     :  " + id);
		idL.setBounds(30, 20, 300, 30);
		idL.setFont(font);
		info_panel.add(idL);

		nameL = new JLabel("이름  :  " + name);
		nameL.setBounds(30, 60, 300, 30);
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
		count_tf.setBounds(20, 190, 250, 30);
		add(count_tf);

		confirm_btn = new JButton("수정");
		confirm_btn.setFont(small_font);
		confirm_btn.setBounds(120, 225, 70, 30);
		confirm_btn.setBackground(new Color(125, 178, 183));
		add(confirm_btn);

		cancel_btn = new JButton("취소");
		cancel_btn.setFont(small_font);
		cancel_btn.setBounds(200, 225, 70, 30);
		cancel_btn.setBackground(new Color(125, 178, 183));
		add(cancel_btn);
		
		cancel_btn.addActionListener(new StockCancelBtn(this));
		confirm_btn.addActionListener(new UpdateConfirmBtn(count_tf, table, this, name));
		
		setVisible(true);
		
	}

}
