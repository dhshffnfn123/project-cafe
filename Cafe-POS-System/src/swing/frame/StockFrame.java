package swing.frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import action.ExitButtonListener;
import action.StockAddBtnListener;
import action.StockGetTableVal;
import action.StockTableAddData;
import action.StockUpdateBtnListener;
import jdbc.model.Stock;
import tool.RoundJTextField;
import tool.RoundedButton;

public class StockFrame extends DefaultFrame {

	JFrame add_frame;
	JPanel main_panel, table_panel, table_panel2;
	JTable table;
	JLabel title_label;
	JScrollPane scrollPane;
	RoundedButton add_btn, update_btn, delete_btn, exit_btn;
	JComboBox cate_combox;
	RoundJTextField find_tf;
	JTableHeader table_header;
	Container contentPane;
	DefaultTableModel table_model;
	DefaultTableCellRenderer dtcr_center, dtcr_right; // cell 위치 정렬을 위한
	String search, name;
	int id, count;

	Font bigger_font = new Font("맑은 고딕", Font.BOLD, 50);
	Font big_font = new Font("맑은 고딕", Font.BOLD, 30);
	Font nomal_font = new Font("맑은 고딕", Font.BOLD, 20);
	Font small_font = new Font("맑은 고딕", Font.BOLD, 15);

	public StockFrame() {
		table = new StockTableAddData().getStockTable();	// DB값 받아온 거
		// ====================================================== main panel
		main_panel = new JPanel();
		scrollPane = new JScrollPane(main_panel);
		main_panel.setBackground(new Color(208, 227, 166));
		main_panel.setLayout(null);
		setContentPane(main_panel);

		// ======================================================= title label
		title_label = new JLabel("  재고 현황");
		title_label.setBounds(50, 20, 300, 100);
		title_label.setBackground(Color.black);
		title_label.setLayout(null);
		title_label.setFont(bigger_font);
		title_label.setBorder(new TitledBorder(new LineBorder(new Color(107, 153, 0), 5), null));

		main_panel.add(title_label);

		// ======================================================= table panel
		table_panel = new JPanel();
		scrollPane = new JScrollPane(table_panel);
		table_panel.setBackground(Color.white);
		table_panel.setBounds(50, 250, 1200, 650);
		table_panel.setBorder(new TitledBorder(new LineBorder(Color.orange, 10), "STOCK_TABLE"));
		table_panel.setLayout(null);

		main_panel.add(table_panel);

		// ======================================================= 버튼 분류
		add_btn = new RoundedButton("ADD");
		update_btn = new RoundedButton("UPDATE");
		delete_btn = new RoundedButton("DELETE");
		exit_btn = new RoundedButton("EXIT");

		// ======================================================= all_btn
		add_btn.setFont(big_font);
		add_btn.setBounds(1270, 250, 200, 100);

		main_panel.add(add_btn);
		
		add_btn.addActionListener(new StockAddBtnListener(table));

		// ==================================== 갱신 (update) 버튼
		update_btn.setFont(big_font);
		update_btn.setBounds(1270, 370, 200, 100);

		main_panel.add(update_btn);
		
		//업데이트에 테이블에서 선택한 값 가져오기
		id = new StockGetTableVal(table).GetStockIdData();
		name = new StockGetTableVal(table).GetStockNameData();
		count = new StockGetTableVal(table).GetStockCountData();
		update_btn.addActionListener(new StockUpdateBtnListener(table, id, name, count));

		// ==================================== delete 버튼
		delete_btn.setFont(big_font);
		delete_btn.setBounds(1270, 490, 200, 100);

		main_panel.add(delete_btn);


		// ==================================== EXIT (뒤로가기) 버튼
		exit_btn.setFont(big_font);
		exit_btn.setBounds(1270, 800, 200, 100);
		exit_btn.setBackground(new Color(184, 41, 41));
		exit_btn.setForeground(Color.black);

		main_panel.add(exit_btn);
		
		exit_btn.addActionListener(new ExitButtonListener(this));

		// ============================================== 테이블

		// 테이블 설정

		// 
		
		table.setOpaque(true);
		table.setBackground(new Color(204, 153, 255));
		table.setRowHeight(40);
		JScrollPane scrollpane = new JScrollPane(table);
		// 스크롤바 크기 설정
		scrollpane.setPreferredSize(new Dimension(430, 400));
		// 테이블 출력위치 설정
		scrollpane.setLocation(20, 20);
		scrollpane.setSize(1160, 610);

		
		table.getTableHeader().setReorderingAllowed(false); // 테이블 헤더 이동 안되게 하기
		table.getTableHeader().setBackground(Color.pink);// 컬럼의 색상을 설정
		table.getTableHeader().setFont(small_font);
		table.getTableHeader().setForeground(Color.black);

		String[] header = new StockTableAddData().give_header();

		table.getColumn(header[0]).setPreferredWidth(100); // 컬럼당 넓이 설정인데 모든 컬럼을 테이블의 넓이에 '얼추' 맞게 설정해야함
		table.getColumn(header[1]).setPreferredWidth(900);
		table.getColumn(header[2]).setPreferredWidth(160);
		table.setFont(nomal_font);

		dtcr_center = new DefaultTableCellRenderer();
		dtcr_right = new DefaultTableCellRenderer();

		dtcr_center.setHorizontalAlignment(SwingConstants.CENTER); // dtcr_center의 위치를 center로 지정
		dtcr_right.setHorizontalAlignment(SwingConstants.RIGHT);
		
		TableColumnModel ts = table.getColumnModel(); // 정렬할 테이블의 columnModel을 가져옴
		ts.getColumn(0).setCellRenderer(dtcr_center);// product_id 컬럼을 센터 정렬
		ts.getColumn(1).setCellRenderer(dtcr_center);
		ts.getColumn(2).setCellRenderer(dtcr_center);

		// ================================================ 검색 기능
		
		table.addMouseListener(new StockGetTableVal(table));
		table_panel.add(scrollpane);

		repaint();
		setVisible(true);

	}

	public static void main(String[] args) {
		new StockFrame();
	}

}




















//		// ============================================== 검색 텍스트필드
//		find_tf = new RoundJTextField(10);
//		find_tf.setFont(nomal_font);
//		find_tf.setBounds(880, 200, 300, 40);
//
//		//main_panel.add(find_tf);
//
//		// ============================================== 검색 버튼
//		ImageIcon image = new ImageIcon("./image/돋보기.jpeg");
//		find_btn = new JButton(image);
//		find_btn.setBounds(1190, 200, 60, 40);
//		
//		
//		//main_panel.add(find_btn);
//