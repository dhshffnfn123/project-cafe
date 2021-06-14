package test;

public class test_table1 {

}

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import tool.RoundJTextField;
import tool.RoundedButton;

public class Stock_Frame extends DefaultFrame {
	
	JPanel main_panel, table_panel, table_panel2;
	JLabel title_label;
	JScrollPane scrollPane;
	RoundedButton all_btn, drink_btn, product_btn, rtd_btn, exit_btn;
	JButton find_btn;
	JComboBox cate_combox;
	RoundJTextField find_tf;
	JTable table;
	JTableHeader table_header;
	Container contentPane;
	DefaultTableModel table_model;
	DefaultTableCellRenderer dtcr_center, dtcr_right; // cell 위치 정렬을 위한
	
	
	Font bigger_font = new Font("맑은 고딕", Font.BOLD, 50);
	Font big_font = new Font("맑은 고딕", Font.BOLD, 30);
	Font nomal_font = new Font("맑은 고딕", Font.BOLD, 20);
	Font small_font = new Font("맑은 고딕", Font.BOLD, 15);
	
	
	public Stock_Frame() {
	
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
		title_label.setBorder(new TitledBorder(new LineBorder(new Color(107, 153, 0),5 ), null));
		
		main_panel.add(title_label);
		
		// ======================================================= table panel
		table_panel = new JPanel();
		scrollPane = new JScrollPane(table_panel);
		table_panel.setBackground(Color.white);
		table_panel.setBounds(50 , 250, 1200, 650);
		table_panel.setBorder(new TitledBorder(new LineBorder(Color.orange, 10), "STOCK_TABLE"));
		table_panel.setLayout(null);
		
		main_panel.add(table_panel);
		
		
		// ======================================================= 버튼 분류
		all_btn = new RoundedButton("ALL");
		drink_btn = new RoundedButton("DRINK");
		product_btn = new RoundedButton("FOOD");
		rtd_btn = new RoundedButton("RTD");
		exit_btn = new RoundedButton("EXIT");
		
		// ======================================================= all_btn
		all_btn.setFont(big_font);
		all_btn.setBounds(1270, 250, 200, 100);
		
		main_panel.add(all_btn);
		
		// ==================================== 제품(product) 버튼
		product_btn.setFont(big_font);
		product_btn.setBounds(1270, 370, 200, 100);
				
		main_panel.add(product_btn);
				
		// ==================================== RTD 버튼
		rtd_btn.setFont(big_font);
		rtd_btn.setBounds(1270, 490, 200, 100);
		
		main_panel.add(rtd_btn);
		
		// ==================================== drink 버튼
		drink_btn.setFont(big_font);
		drink_btn.setBounds(1270, 610, 200, 100);
				
		main_panel.add(drink_btn);
		
		
		// ==================================== EXIT (뒤로가기) 버튼
		exit_btn.setFont(big_font);
		exit_btn.setBounds(1270, 800, 200, 100);
		exit_btn.setBackground(new Color(184, 41, 41));
		exit_btn.setForeground(Color.black);
		
		main_panel.add(exit_btn);
		
		// ============================================== 검색 텍스트필드
		find_tf = new RoundJTextField(10);
		find_tf.setFont(nomal_font);
		find_tf.setBounds(880, 200, 300, 40);
		
		main_panel.add(find_tf);
		
		// ============================================== 검색 버튼
		ImageIcon image = new ImageIcon("./image/돋보기.jpeg");
		find_btn = new JButton(image);
		find_btn.setBounds(1190, 200, 60, 40);
		
		
		main_panel.add(find_btn);
		
		// ============================================== 테이블에 데이터 넣기 실험용 (삭제예정)
		
		String header[] = {"PRODUCT_ID","PRODUCT_NAME","STOCK"};
		String contents[][] = {
				{"1","아메리카노","220"},
				{"2","처음처럼","120"},
				{"3","에스프레소","210"}
		};
		
		table_model = new DefaultTableModel(contents, header);
		table = new JTable(table_model);
		
		
		
		
		
		// ============================================== 테이블
		
		table_panel.setPreferredSize(new Dimension(430, 400));
		table = new JTable(table_model);
		JScrollPane table_scrollpane = new JScrollPane(table);
		
		scrollPane.setLocation(20, 20);
		scrollPane.setSize(1160, 610);
		table.setFont(nomal_font);
		table.setRowHeight(40);	// 테이블 열 높이 설정
		
		table.getTableHeader().setReorderingAllowed(false); // 테이블 헤더 이동 안되게 하기
		table.getTableHeader().setBackground(Color.pink);// 컬럼의 색상을 설정
		table.getTableHeader().setFont(small_font);
		table.getTableHeader().setForeground(Color.black);;
		
		// ------------------------------------------------------- 테이블 선택은 가능하지만 수정은 안되게 하기
//		table.setModel(new DefaultTableModel() {
//			public boolean isCellEditable(int row, int column) {
//				if(column == 2) { // column == 2는 stock이므로 stock만 수정이 가능하게 함 
//					return true; // 수정 가능
//				} else {
//				return false; // 불가능
//			}
//		}
//		});
		table.getColumn(header[0]).setPreferredWidth(100); // 컬럼당 넓이 설정인데 모든 컬럼을 테이블의 넓이에 '얼추' 맞게 설정해야함
		table.getColumn(header[1]).setPreferredWidth(900); 
		table.getColumn(header[2]).setPreferredWidth(110);
		
		// ------------------------------------------------------- 컬럼 정렬
		dtcr_center = new DefaultTableCellRenderer();
		dtcr_right = new DefaultTableCellRenderer();
		
		dtcr_center.setHorizontalAlignment(SwingConstants.CENTER); // dtcr_center의 위치를 center로 지정
		dtcr_right.setHorizontalAlignment(SwingConstants.RIGHT);
		
		TableColumnModel ts = table.getColumnModel(); // 정렬할 테이블의 columnModel을 가져옴
		ts.getColumn(0).setCellRenderer(dtcr_center);// product_id 컬럼을 센터 정렬
		ts.getColumn(1).setCellRenderer(dtcr_center);
		ts.getColumn(2).setCellRenderer(dtcr_center);
		
		//table_panel.add(table);
		table_panel.add(table_scrollpane);
		//table_panel.add(table);
		
		//테이블에서 선택한 값 가져오기
		//table.getColumn(header[2]).getCellEditor();
		
		
		
		repaint();
		setVisible(true);
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		new Stock_Frame();
	}

}























