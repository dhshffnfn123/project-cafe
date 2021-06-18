package swing.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import action.ChangePageActionForChooseFrame;
import action.CurrentTimeClock;
import action.StockAddBtnListener;
import action.StockDeleteBtnListener;
import action.StockGetTableVal;
import action.StockTableAddData;
import action.StockUpdateBtnListener;

public class StockManagementFrame extends DefaultFrame {

	private JPanel topPanel;
	private JPanel center;
	private JPanel right_panel;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel title_label;
	private JButton add_btn, update_btn, delete_btn, null_btn;
	private DefaultTableCellRenderer dtcr_center, dtcr_right;

	Font bigger_font = new Font("맑은 고딕", Font.BOLD, 40);
	Font big_font = new Font("맑은 고딕", Font.BOLD, 30);
	Font nomal_font = new Font("맑은 고딕", Font.PLAIN, 15);
	Font small_font = new Font("맑은 고딕", Font.BOLD, 15);

	public StockManagementFrame() {
		setLayout(new BorderLayout());
		setTitle("Stock Management");

		// [TOP]
		topPanel = new JPanel(new GridLayout(1, 3));
		// 뒤로 가기 버튼
		JButton back_btn = new JButton("<<");
		back_btn.setPreferredSize(new Dimension(100, 70));
		back_btn.setFont(new Font("궁서", Font.BOLD, 30));
		back_btn.setBackground(new Color(3, 102, 53));
		back_btn.setForeground(Color.WHITE);
		back_btn.setBorderPainted(false);
		topPanel.add(back_btn, BorderLayout.WEST);
		back_btn.setHorizontalAlignment(SwingConstants.LEFT);
		back_btn.addActionListener(new ChangePageActionForChooseFrame(this));

		// 가운데 시스템시계
		JLabel clock = new CurrentTimeClock().setClock();
		clock.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		clock.setHorizontalAlignment(JLabel.CENTER);
		clock.setOpaque(true);
		clock.setBackground(new Color(3, 102, 53));
		clock.setForeground(Color.WHITE);
		topPanel.add(clock, BorderLayout.CENTER);
		// 오른쪽에 로그인한 사람 정보 뜨게할 예정
		JLabel login_name = new JLabel("직원 정보");
		login_name.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		login_name.setForeground(Color.WHITE);
		login_name.setHorizontalAlignment(JLabel.CENTER);
		login_name.setOpaque(true);
		login_name.setBackground(new Color(3, 102, 53));
		topPanel.add(login_name, BorderLayout.EAST);

		// [CENTER]
		center = new JPanel(new BorderLayout());
		center.setBackground(Color.WHITE);
		// 테이블 생성
		table = new StockTableAddData().getStockTable();
		// ============================================== 테이블
		// 테이블 설정
		table.setOpaque(true);
		table.setRowHeight(40);
		// 스크롤바 크기 설정
		// 테이블 출력위치 설정
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(1000, 500));
		scrollPane.setLocation(20, 20);
		scrollPane.setSize(1160, 610);
		scrollPane.setBorder(BorderFactory.createEmptyBorder()); // 테이블 테두리 삭제

		table.getTableHeader().setPreferredSize(new Dimension(100, 50));
		table.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 25));
		table.getTableHeader().setReorderingAllowed(false); // 테이블 헤더 이동 안되게 하기
		table.getTableHeader().setBackground(new Color(0, 66, 56));// 컬럼의 색상을 설정
		table.getTableHeader().setForeground(Color.WHITE);
		table.setRowHeight(40);

		String[] header = new StockTableAddData().give_header();

		table.getColumn(header[0]).setPreferredWidth(160); // 컬럼당 넓이 설정인데 모든 컬럼을 테이블의 넓이에 '얼추' 맞게 설정해야함
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

		table.addMouseListener(new StockGetTableVal(table));

		// [CENTER-TOP]
		// ======================================================= title label
//		title_label = new JLabel("  재고 현황");
//		title_label.setBackground(Color.BLACK);
//		title_label.setFont(bigger_font);

		// [CENTER-RIGHT]
		// ======================================================= 버튼 분류
		// CENTER-RIGHT에 버튼 추가할 라벨 생성
		right_panel = new JPanel(new GridLayout(4, 1));
		null_btn = new JButton("");
		add_btn = new JButton("ADD");
		update_btn = new JButton("UPDATE");
		delete_btn = new JButton("DELETE");

		// ======================================================= null_btn
		null_btn.setPreferredSize(new Dimension(250, 100));
		null_btn.setEnabled(false);
		null_btn.setBorderPainted(false);
		null_btn.setBackground(Color.WHITE);
		// ======================================================= all_btn
		add_btn.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		add_btn.setPreferredSize(new Dimension(250, 100));
		add_btn.setBackground(new Color(53, 84, 0));
		add_btn.setForeground(Color.WHITE);
		add_btn.addActionListener(new StockAddBtnListener(table));
		// ==================================== 갱신 (update) 버튼
		update_btn.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		update_btn.setPreferredSize(new Dimension(250, 100));
		update_btn.addActionListener(new StockUpdateBtnListener(table));
		update_btn.setBackground(new Color(95, 148, 153));
		update_btn.setForeground(Color.WHITE);
		// ==================================== delete 버튼
		delete_btn.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		delete_btn.setPreferredSize(new Dimension(250, 100));
		delete_btn.addActionListener(new StockDeleteBtnListener(table));
		delete_btn.setBackground(new Color(232, 114, 36));
		delete_btn.setForeground(Color.WHITE);

		right_panel.add(null_btn);
		right_panel.add(add_btn);
		right_panel.add(update_btn);
		right_panel.add(delete_btn);

//		center.add(title_label, BorderLayout.NORTH);
		center.add(scrollPane, BorderLayout.CENTER);
		center.add(right_panel, BorderLayout.EAST);

		add(topPanel, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);

		setVisible(true);
	}

	public static void main(String[] args) {
		new StockManagementFrame();
	}

}
