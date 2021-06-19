package swing.frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import action.BackButtonMouseActionForOrderFrame;
import action.MenuButtonAction;
import action.MenuListNextButton;
import action.MenuListPrevButton;
import action.MenuMinusButton;
import action.MenuOptionButtonAction;
import action.MenuPlusButton;
import action.PaymentFinishButton;
import action.TableAllDelete;
import jdbc.model.MenuButton;
import jdbc.model.MenuButtonData;
import jdbc.model.OptionButton;
import swing.method.BackButtonImgScale;
import swing.method.CurrentTimeClock;
import swing.method.LoginEmployeeInfoLabel;
import swing.view.DrinkView;
import swing.view.OptionView;
import swing.view.ProductView;
import swing.view.RtdView;

public class OrderFrame extends DefaultFrame {
	private String grade;
	private JPanel center;
	private JPanel top;
	private JTable table;
	private JScrollPane scrollPane;
	private JTabbedPane menu;
	private JTabbedPane drinks;
	private JTabbedPane products;
	private JTabbedPane rtds;
	private JTabbedPane option;
	private ArrayList<String> name;
	private ArrayList<Integer> price;
	private ArrayList<MenuButton> btns;
	private JPanel menuPanelBase;
	private JPanel menuPanelBase_center;
	private JPanel menuPanelBase_south;
	private ArrayList<JPanel> menuPanels;
	private static DefaultTableModel model;
	private static int tablemoney = 0;
	private static int totalPrice;
	private static ArrayList<MenuButtonData> tableInfo;
	private static JLabel totalmoney = new JLabel("");
	private static Map<String, Integer> MenuHash = new HashMap<>();

	static {
		tableInfo = new ArrayList<MenuButtonData>();
	}

	public static JLabel getTotalmoney() {
		return totalmoney;
	}

	public static DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public static void setTotalmoney(JLabel totalmoney) {
		OrderFrame.totalmoney = totalmoney;
	}

	public static int getTablemoney() {
		return tablemoney;
	}

	public static void setTablemoney(int tablemoney) {
		OrderFrame.tablemoney = tablemoney;
	}

	public static Map<String, Integer> GetMenuHash() {
		return MenuHash;
	}

	public static ArrayList<MenuButtonData> GettableInfo() {
		return tableInfo;
	}

	public OrderFrame(String grade, String order_name) {
		this.grade = grade;
		setLayout(new BorderLayout());
		setTitle("Point Of Sale");

		// [CENTER] 패널 생성
		center = new JPanel(new GridLayout());
		center.setBackground(Color.WHITE);

		// [TOP] 패널 생성
		top = new JPanel(new GridLayout(1, 3));

		JButton payment = new JButton("결제");
		payment.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		payment.setBackground(new Color(3, 102, 53));
		payment.setForeground(Color.WHITE);

		// table 밑 총 합계 라벨
		JLabel total = new JLabel("총 합계");
		total.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		total.setHorizontalAlignment(JLabel.CENTER);
		totalmoney.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		totalmoney.setHorizontalAlignment(JLabel.CENTER);

		// 뒤로가기 버튼
//		JButton choosepage = new JButton("<<");
		JButton choosepage = new BackButtonImgScale().getBackBtn();
		choosepage.setOpaque(true);
		choosepage.setBackground(new Color(3, 102, 53));
		choosepage.setBorderPainted(false);
		choosepage.setHorizontalAlignment(JButton.LEFT);
		choosepage.setForeground(Color.WHITE);
		top.add(choosepage);

		// infoTime
		JLabel infoTime = new CurrentTimeClock().setClock();
		infoTime.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		infoTime.setBounds(260, 10, 540, 80);
		infoTime.setHorizontalAlignment(JLabel.CENTER);
		infoTime.setOpaque(true);
		infoTime.setBackground(new Color(3, 102, 53));
		infoTime.setForeground(Color.WHITE);
		top.add(infoTime);

		// info
		JPanel empInfo = new JPanel(new GridLayout(2, 1));
		empInfo.setBackground(new Color(3, 102, 53));
		
		JLabel label = new JLabel();
		label.setText("* 환영합니다. 현재 로그인 *");
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.WHITE);
		label.setBackground(new Color(3, 102, 53));
		
		JLabel login_name = LoginEmployeeInfoLabel.getLabel();
		login_name.setFont(new Font("맑은 고딕", Font.BOLD, 19));
		login_name.setForeground(Color.WHITE);
		login_name.setHorizontalAlignment(JLabel.CENTER);
		login_name.setOpaque(true);
		login_name.setBackground(new Color(3, 102, 53));

		empInfo.add(label);
		empInfo.add(login_name);
		
		top.add(empInfo);

		// ============================================== Menu Plus, Minus, Size 버튼 생성
		JPanel plusMinus = new JPanel(new GridLayout(1, 2));
		JPanel size = new JPanel(new GridLayout(1, 3));
		JButton plusBtn = new JButton("+");
		plusBtn.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		plusBtn.setBackground(Color.WHITE);
		JButton MinusBtn = new JButton("-");
		MinusBtn.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		MinusBtn.setBackground(Color.WHITE);
		// ============================================== 사이즈 버튼 따로 생성
		ArrayList<String> sizeName = new OptionView(130).getName();
		ArrayList<Integer> sizePrice = new OptionView(130).getPrice();
		ArrayList<OptionButton> sizeBtn = new ArrayList<>();
		for (int i = 0; i < sizeName.size(); i++) {
			sizeBtn.add(new OptionButton(sizeName.get(i), sizePrice.get(i)));
			sizeBtn.get(i).setBackground(Color.WHITE);
		}
		// ============================================== 전체 메뉴 취소하는 버튼 생성
		JButton allDelBtn = new JButton("전체 취소");
		allDelBtn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		allDelBtn.setBackground(new Color(3, 102, 53));
		allDelBtn.setForeground(Color.WHITE);
		

		// [CENTER-LEFT] 패널 생성
		JPanel center_left = new JPanel(new BorderLayout());

		// [CENTER-LEFT-SOUTH] 패널 생성 // 패널마다 클래스 나눠서
		JPanel left_south = new JPanel(new GridLayout(3, 2));
		left_south.setBackground(Color.WHITE);

		// [CENTER-LEFT-CENTER]테이블 생성 // 테이블 메서드 구현
		String[] header = { "구분", "메뉴", "수량", "금액" };
		// 테이블 더블클릭 수정 금지
		model = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		table = new JTable(model);
		// 테이블 셀의 높이 변경
		table.setRowHeight(35);
		table.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
		table.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
		// 테이블 컬럼 가운데 정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();

		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}

		// 헤더 색상, 높이 너비 변경
		JTableHeader hd = table.getTableHeader();
		hd.setPreferredSize(new Dimension(100, 45));
		hd.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		hd.setBackground(new Color(0, 66, 56));
		hd.setForeground(Color.WHITE);
		// 헤더 길이 변경
		table.getColumn(header[0]).setPreferredWidth(50);
		table.getColumn(header[1]).setPreferredWidth(350);
		table.getColumn(header[2]).setPreferredWidth(50);
		table.getColumn(header[3]).setPreferredWidth(100);

		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(750, 500));
		scrollPane.getViewport().setBackground(Color.WHITE); // 테이블 배경 하얗게 만들기
		scrollPane.setBorder(BorderFactory.createEmptyBorder()); // 이거는 테이블 테두리 없애줌
		scrollPane.getHorizontalScrollBar();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // 세로 스크롤바 계속 보이게하기
		scrollPane.setBackground(Color.WHITE);

		// [CENTER-RIGHT] 메뉴 탭 생성
		menu = new JTabbedPane();
		menu.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		menu.setUI(new BasicTabbedPaneUI()); // 탭 테두리 삭제되는 스타일로 변경
		menu.setBackground(Color.WHITE);

		// 음료 탭 생성
		drinks = new JTabbedPane();
		drinks.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		// 프로덕트 탭 생성
		products = new JTabbedPane();
		products.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		// RTD 탭 생성
		rtds = new JTabbedPane();
		rtds.setOpaque(true);
		rtds.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		// 옵션 탭 생성
		option = new JTabbedPane();
		option.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		// 탭에 생성한 항목들 추가
		menu.addTab("음료", drinks);
		menu.addTab("푸드", products);
		menu.addTab("RTD", rtds);
		menu.addTab("옵션", option);

		// 음료 버튼들 추가(타입 10번 ~ 100번까지)
		for (int i = 0; i < 11; i++) {
			String[] typeName = { "리저브 에스프레소", "리저브 드립", "콜드 브루", "블론드", "에스프레소", "디카페인", "프라푸치노", "블렌디드", "피지오", "티바나",
					"기타" };
			drinks.addTab(typeName[i], makeItemButtons("drink", 10 * (i + 1)));
			drinks.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			drinks.setOpaque(true);
			drinks.setBackground(Color.WHITE);
		}
		// 푸드 버튼들 추가 (타입 140번 ~ 200번까지)
		for (int i = 0; i <= 6; i++) {
			String[] typeName = { "브레드", "케이크/미니 디저트", "샌드위치/샐러드", "따뜻한 푸드", "과일/요거트", "스낵", "아이스크림" };
			products.addTab(typeName[i], makeItemButtons("product", 140 + (10 * i)));
			products.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			products.setOpaque(true);
			products.setBackground(Color.WHITE);
		}
		// RTD 버튼들 추가 (타입 210)
		rtds.addTab("RTD", makeItemButtons("RTD", 210));
		rtds.setOpaque(true);
		rtds.setBackground(Color.WHITE);
		rtds.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		// 옵션 버튼들 추가 (타입 120)
		option.addTab("옵션", makeItemButtons("option", 120));
		option.setOpaque(true);
		option.setBackground(Color.WHITE);
		option.setFont(new Font("맑은 고딕", Font.BOLD, 13));

		// 테이블삭제 버튼 기능 구현
		MinusBtn.addActionListener(new MenuMinusButton(table, totalmoney, tablemoney));
		allDelBtn.addActionListener(new TableAllDelete(table, tableInfo));
		plusBtn.addActionListener(new MenuPlusButton(table));
		choosepage.addMouseListener(new BackButtonMouseActionForOrderFrame(this, table, grade, order_name));
		payment.addActionListener(new PaymentFinishButton(table, order_name));
		

		plusMinus.add(plusBtn);
		plusMinus.add(MinusBtn);
		for (int i = 0; i < sizeBtn.size(); i++) {
			size.add(sizeBtn.get(i));
			sizeBtn.get(i).setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			sizeBtn.get(i).addActionListener(new MenuOptionButtonAction(table, sizeName.get(i), sizePrice.get(i)));
		}

		center_left.add(scrollPane, BorderLayout.CENTER);
		center_left.add(left_south, BorderLayout.SOUTH);
		
		left_south.add(total); // 총 합계
		left_south.add(totalmoney); // 총 금액
		left_south.add(plusMinus); // 플-마 버튼
		left_south.add(size); // 사이즈 버튼 3개
		left_south.add(allDelBtn); // 전체 취소 버튼
		left_south.add(payment); // 결제버튼


		center.add(center_left);
		center.add(menu);

		add(top, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);

		setVisible(true);
		repaint();
	}

	private ArrayList<MenuButton> itemButtons(String itemType, int typeNum) {
		// 타입을 받아서 바로 버튼 만들기(한번에 만들어봄 너무 여러개 되서 itemType받아오기)
		// View변경했는데 -> 이름/가격 따로 받아올 수 있어요
		if (itemType.equals("drink")) {
			name = new DrinkView(typeNum).getName();
			price = new DrinkView(typeNum).getPrice();
		} else if (itemType.equals("product")) {
			name = new ProductView(typeNum).getName();
			price = new ProductView(typeNum).getPrice();
		} else if (itemType.equals("RTD")) {
			name = new RtdView().getName();
			price = new RtdView().getPrice();
		} else if (itemType.equals("option")) {
			name = new OptionView(typeNum).getName();
			price = new OptionView(typeNum).getPrice();
		}

		// 버튼도 같이 ArrayList<>()로 받아서 저장해줄 것. MenuButton(이름, 가격)
		btns = new ArrayList<>();
		for (int i = 0; i < name.size(); i++) {
			btns.add(new MenuButton(name.get(i), price.get(i)));
			btns.get(i).setPreferredSize(new Dimension(182, 188));
			btns.get(i).setFont(new Font("맑은 고딕", Font.BOLD, 13));
//			btns.get(i).setBackground(new Color(213, 213, 213));
//			btns.get(i).setForeground(Color.WHITE);
			btns.get(i).setBackground(Color.WHITE);
			if (itemType.equals("option")) {
				btns.get(i).addActionListener(new MenuOptionButtonAction(table, name.get(i), price.get(i)));
			} else {
				btns.get(i).addActionListener(new MenuButtonAction(table, name.get(i), price.get(i)));
			}
		}

		return btns;
	}

	private JPanel makeItemButtons(String itemType, int typeNum) {
		// 메뉴 탭들 안에 들어갈 베이스 패널 만들기
		menuPanelBase = new JPanel(new BorderLayout());
		// 베이스의 센터에 들어갈 패널에 카드 레이아웃 적용하기
		menuPanelBase_center = new JPanel(new CardLayout());
		// 베이스의 밑에 버튼이 들어갈 패널 만들기
		menuPanelBase_south = new JPanel(new GridLayout());
		menuPanelBase_south.setBackground(Color.WHITE);
		// 베이스 센터에 들어갈 패널들 리스트로 만들기
		menuPanels = new ArrayList<>();

		// 상품 타입이 있는 항목 버튼으로 모두 생성
		btns = itemButtons(itemType, typeNum);
		// <카드로 만들어질 패널 생성> 버튼 개수를 받아서 계산 (4 x 4)기준
		for (int i = 0; i < btns.size() / 16 + 1; i++) {
			menuPanels.add(new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 1))); // 그냥 FlowLayout 버튼 크기 맞춰서 맞춤
			menuPanels.get(i).setBackground(Color.WHITE);
		}

		// 버튼들 개수 인식해서 16배수마다 끊어주기 .... 일단 더 좋은 방법 있을 것 같은데 아직 최선은 이거에요..
		for (int i = 0; i < btns.size(); ++i) {
			switch (i / 16) {
			case 0:
				menuPanels.get(0).add(btns.get(i));
				break;
			case 1:
				menuPanels.get(1).add(btns.get(i));
				break;
			case 2:
				menuPanels.get(2).add(btns.get(i));
				break;
			case 3:
				menuPanels.get(3).add(btns.get(i));
				break;
			}
		}

		// 만들어진 패널 개수만큼 CardLayout지정된 패널에 추가해준다.
		for (int i = 0; i < menuPanels.size(); i++) {
			menuPanelBase_center.add("page" + (i + 1), menuPanels.get(i));
		}

		// 하단에 페이지 넘길 버튼 추가
		JButton prev = new JButton("<<");
		prev.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		prev.setHorizontalAlignment(SwingConstants.RIGHT);
		prev.setContentAreaFilled(false);
		prev.setBorderPainted(false);
		JLabel pageNum = new JLabel("Page1");
		pageNum.setHorizontalAlignment(JLabel.CENTER);
		pageNum.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		JButton next = new JButton(">>");
		next.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		next.setContentAreaFilled(false);
		next.setBorderPainted(false);
		next.setHorizontalAlignment(SwingConstants.LEFT);
		
		prev.addActionListener(new MenuListPrevButton(menuPanelBase_center, pageNum, menuPanels));
		next.addActionListener(new MenuListNextButton(menuPanelBase_center, pageNum, menuPanels));

		menuPanelBase_south.add(prev);
		menuPanelBase_south.add(pageNum);
		menuPanelBase_south.add(next);

		menuPanelBase.add(menuPanelBase_center, BorderLayout.CENTER);
		menuPanelBase.add(menuPanelBase_south, BorderLayout.SOUTH);

		return menuPanelBase;
	}
}