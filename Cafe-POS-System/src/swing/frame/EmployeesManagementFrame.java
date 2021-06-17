package swing.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import action.ChangePageButton;
import action.ChangePageButtonForBackBtn;
import action.CurrentTimeClock;
import action.EmployeeInfoAddButton;
import action.EmployeeInfoDelButton;
import action.EmployeeInfoUpdateButton;
import action.GetTableInfoForMouse;
import jdbc.method.SelectEmployeeInfo;

public class EmployeesManagementFrame extends DefaultFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel center;
	private JTable staff_info;
	private JScrollPane scroll_add_staff_info;
	private JTabbedPane right_panel_tab;
	private JPanel top_panel_body;
	private JPanel right_panel;
	private ArrayList<JLabel> labels;
	private ArrayList<TextField> fields;
	private JButton btn;

	public EmployeesManagementFrame() {
		setLayout(new BorderLayout());
		setTitle("Employees Management");

		// -- [CENTER] -- 가운데 공간에 패널 생성
		center = new JPanel(new GridLayout());

		// 직원 정보 테이블 생성(DB에서 정보 불러오기)
		staff_info = new SelectEmployeeInfo().getEmployeeInfo();
		// 테이블 글씨체 변경
		staff_info.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		// 테이블 컬럼들 이동 불가
		staff_info.getTableHeader().setReorderingAllowed(false);
		// 테이블 셀의 높이 변경
		staff_info.setRowHeight(50);
		// 테이블 가운데로 정렬하기
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = staff_info.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
		// 테이블 헤더 높이, 글씨체, 배경색 변경
		JTableHeader header = staff_info.getTableHeader();
		header.setPreferredSize(new Dimension(100, 50));
		header.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		header.setBackground(new Color(163, 148, 132));
		header.setForeground(Color.WHITE);
		// 테이블 갯수 넘어가면 스크롤바로 변경됨
		scroll_add_staff_info = new JScrollPane(staff_info);
		// 테이블 배경색상 변경하려면 getViewport() 후 setBackground()사용
		scroll_add_staff_info.getViewport().setBackground(Color.WHITE);
		// JScrollPane테두리 없애기
		scroll_add_staff_info.setBorder(BorderFactory.createEmptyBorder());
		
		
		// 센터 패널의 오른쪽에 추가할 패널 생성 -> 오른쪽 패널 탭으로 만들기
		right_panel_tab = new JTabbedPane();
		right_panel_tab.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		right_panel_tab.setBackground(new Color(95, 148, 153));
		right_panel_tab.setForeground(Color.WHITE);
		right_panel_tab.addTab("등　록", enrollTab());
		right_panel_tab.addTab("수　정", updateTab());
		right_panel_tab.addTab("삭　제", delTab());
		// ?? 이거하니까 테두리 없어졌음
		right_panel_tab.setUI(new BasicTabbedPaneUI());

		// -- [CENTER-TOP] --
		top_panel_body = new JPanel(new GridLayout());
		// 뒤로 가기 버튼
		ImageIcon btnImage = new ImageIcon("./image/BackButton.png");
		Image img = btnImage.getImage();
		Image changeImg = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		JButton back_btn = new JButton(changeIcon);
		back_btn.setPreferredSize(new Dimension(100, 80));
		back_btn.setFont(new Font("궁서", Font.BOLD, 30));
		// GridLayout에 맞춘 버튼 글씨 왼쪽 정렬
		back_btn.setHorizontalAlignment(SwingConstants.LEFT);
		back_btn.setBackground(new Color(110, 88, 68));
		back_btn.setForeground(Color.WHITE);
		// 버튼 테두리 없애기
		back_btn.setBorderPainted(false);
		// 이미지 넣어서 MouseListener로 변경.
		back_btn.addMouseListener(new ChangePageButtonForBackBtn(this));
		
		top_panel_body.add(back_btn, BorderLayout.WEST);
		// 가운데 시스템시계
		JLabel clock = new CurrentTimeClock().setClock();
		clock.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		clock.setHorizontalAlignment(JLabel.CENTER);
		clock.setOpaque(true);
		clock.setBackground(new Color(110, 88, 68));
		clock.setForeground(Color.WHITE);
		top_panel_body.add(clock, BorderLayout.CENTER);
		
		// 오른쪽에 로그인한 사람 정보 뜨게할 예정
		JLabel login_name = new JLabel("직원 정보");
		login_name.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		login_name.setForeground(Color.WHITE);
		login_name.setHorizontalAlignment(JLabel.CENTER);
		login_name.setOpaque(true);
		login_name.setBackground(new Color(110, 88, 68));
		top_panel_body.add(login_name, BorderLayout.EAST);

		
		// [CENTER에 패널들 add]
		// -- [CENTER-LEFT] --
		center.add(scroll_add_staff_info);
		// -- [CENTER-RIGHT] --
		center.add(right_panel_tab);

		// Frame에 패널 위치 선정하면서 추가
		add(top_panel_body, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);

		setVisible(true);
	}
	
	public JButton makeButton(String btn_name) {
		btn = new JButton(btn_name);
		btn.setPreferredSize(new Dimension(200, 80));
		btn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		btn.setBackground(new Color(232, 114, 36));
		btn.setForeground(Color.WHITE);
		btn.setBorderPainted(false);
		
		return btn;
	}
	
	public void makeLabelAndField(String[] labels_name) {
		for (int i = 0; i < labels_name.length; i++) {
			labels.add(new JLabel(labels_name[i]));
			// 라벨 가운데 정렬
			labels.get(i).setHorizontalAlignment(JLabel.CENTER);
			labels.get(i).setFont(new Font("맑은 고딕", Font.BOLD, 30));
			// setOpaque(true) 후 -> setBackground(Color)채워짐
			labels.get(i).setOpaque(true);
			labels.get(i).setBackground(new Color(95, 148, 153));
			labels.get(i).setForeground(Color.WHITE);
			// 사이즈 지정
			labels.get(i).setPreferredSize(new Dimension(200, 80));
			// 라벨 튀어나오게 설정
			labels.get(i).setBorder(new BevelBorder(BevelBorder.RAISED));

			fields.add(new TextField(12));
			fields.get(i).setFont(new Font("맑은 고딕", Font.PLAIN, 50));
			fields.get(i).setBackground(new Color(161, 161, 161));
			fields.get(i).setForeground(Color.WHITE);

			right_panel.add(labels.get(i));
			right_panel.add(fields.get(i));
		}
	}

	// [직원 정보 등록 탭]
	public JPanel enrollTab() {
		right_panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 50));
		labels = new ArrayList<>();
		fields = new ArrayList<>();
		String[] labels_name = {"이　　름", "패스워드"};
		
		JLabel notice = new JLabel("<HTML>※ 환영합니다. 직원 정보를 등록해주세요.<br>&nbsp;&nbsp;&nbsp;"
				+ "(직원 아이디는 자동 생성 됩니다.)</HTML>");
		notice.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		notice.setForeground(Color.DARK_GRAY);
		notice.setPreferredSize(new Dimension(490, 45));
		notice.setHorizontalAlignment(JLabel.CENTER);
		right_panel.add(notice);
		
		makeLabelAndField(labels_name);
		
		// 직급 라벨은 콤보박스때문에 따로 추가
		JLabel grade = new JLabel("직　　급");
		grade.setHorizontalAlignment(JLabel.CENTER);
		grade.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		grade.setPreferredSize(new Dimension(200, 80));
		grade.setBorder(new BevelBorder(BevelBorder.RAISED));
		grade.setOpaque(true);
		grade.setBackground(new Color(95, 148, 153));
		grade.setForeground(Color.WHITE);
		right_panel.add(grade);
		// 직급 콤보박스
		String[] grade_list = { "BARISTA", "MANAGER" };
		JComboBox<String> grade_box = new JComboBox<>(grade_list);
		grade_box.setFont(new Font("맑은 고딕", Font.PLAIN, 50));
		grade_box.setPreferredSize(new Dimension(360, 80));
		grade_box.setBackground(new Color(161, 161, 161));
		grade_box.setForeground(Color.WHITE);
		right_panel.add(grade_box);

		btn = makeButton("등　　록");
		btn.addActionListener(new EmployeeInfoAddButton(fields, grade_box, staff_info));
		
		right_panel.add(btn, BorderLayout.SOUTH);

		return right_panel;
	}
	
	// [직원 정보 수정 탭]
	public JPanel updateTab() {
		right_panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 50));
		labels = new ArrayList<>();
		fields = new ArrayList<>();
		String[] labels_name = {"직원 아이디", "이　　름", "패스워드"};
		
		makeLabelAndField(labels_name);
		
		// 직급 라벨은 콤보 박스때문에 따로 추가
		JLabel grade = new JLabel("직　　급");
		grade.setHorizontalAlignment(JLabel.CENTER);
		grade.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		grade.setPreferredSize(new Dimension(200, 80));
		grade.setBorder(new BevelBorder(BevelBorder.RAISED));
		grade.setOpaque(true);
		grade.setBackground(new Color(95, 148, 153));
		grade.setForeground(Color.WHITE);
		right_panel.add(grade);
		// 직급 콤보박스
		String[] grade_list = { "BARISTA", "MANAGER" };
		JComboBox<String> grade_box = new JComboBox<>(grade_list);
		grade_box.setFont(new Font("맑은 고딕", Font.PLAIN, 50));
		grade_box.setPreferredSize(new Dimension(360, 80));
		grade_box.setBackground(new Color(161, 161, 161));
		grade_box.setForeground(Color.WHITE);
		right_panel.add(grade_box);
		
		btn = makeButton("수　　정");
		btn.addActionListener(new EmployeeInfoUpdateButton(fields, grade_box, staff_info));
		
		right_panel.add(btn, BorderLayout.SOUTH);
		
		// 테이블에 값 선택하면 값 가져오기 이벤트 설정
		staff_info.addMouseListener(new GetTableInfoForMouse(staff_info, fields));
		
		// 정보 수정 시 직원 번호, 이름 수정 불가
		fields.get(0).setEditable(false);
		fields.get(1).setEditable(false);
		
		return right_panel;
	}

	// [직원 정보 삭제 탭]
	public JPanel delTab() {
		right_panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 50));
		labels = new ArrayList<>();
		fields = new ArrayList<>();
		String[] labels_name = {"직원 아이디", "이　　름", "패스워드", "직　　급"};

		makeLabelAndField(labels_name);
		
		// 반복문으로 사용하지 못하게 하니까 탁해져서 따로 변경..
		fields.get(0).setEditable(false);
		fields.get(1).setEditable(false);
		fields.get(2).setEditable(false);
		fields.get(3).setEditable(false);
		
		btn = makeButton("삭　　제");
		btn.setBackground(new Color(202, 64, 27));
		btn.addActionListener(new EmployeeInfoDelButton(fields, staff_info));
		
		right_panel.add(btn, BorderLayout.SOUTH);
		
		staff_info.addMouseListener(new GetTableInfoForMouse(staff_info, fields));

		return right_panel;
	}

	public static void main(String[] args) {
		new EmployeesManagementFrame();
	}
}