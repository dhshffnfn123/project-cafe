package swing.frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import action.CurrentTimeClock;
import action.MenuListNextButton;
import action.MenuListPrevButton;
import jdbc.model.MenuButton;
import swing.view.DrinkView;
import swing.view.ProductView;
import swing.view.RtdView;

public class OrderFrame extends DefaultFrame {

   private JPanel center;
   private JPanel top;
   private JTable table;
   private JScrollPane scrollPane;
   private JTabbedPane menu;
   private JTabbedPane drinks;
   private JTabbedPane products;
   private JTabbedPane rtds;
   private ArrayList<String> name;
   private ArrayList<Integer> price;
   private ArrayList<MenuButton> btns;
   private JPanel menuPanelBase;
   private JPanel menuPanelBase_center;
   private JPanel menuPanelBase_south;
   private ArrayList<JPanel> menuPanels;
   

   public orderview() {
      setLayout(new BorderLayout());
      setTitle("Point-Of-Sale");

      // [CENTER]패널 생성
      center = new JPanel(new GridLayout());
      center.setBackground(new Color(243, 243, 243));

      // [TOP]패널 생성
      top = new JPanel(new GridLayout());

      // [CENTER-LEFT]테이블 생성
      String[] header = { "번호", "상품명", "상품수량", "가격" };
      DefaultTableModel model = new DefaultTableModel(header, 0);
      table = new JTable(model);
      // 테이블 셀의 높이 변경
      table.setRowHeight(50);

      // 헤더 색상, 높이 너비 변경
      JTableHeader hd = table.getTableHeader();
      hd.setPreferredSize(new Dimension(100, 50));
      hd.setFont(new Font("맑은 고딕", Font.BOLD, 25));
      hd.setBackground(new Color(163, 148, 132));
      hd.setForeground(Color.WHITE);

      scrollPane = new JScrollPane(table);
      scrollPane.setPreferredSize(new Dimension(750, 500));
      scrollPane.getViewport().setBackground(Color.WHITE); // 테이블 배경 하얗게 만들기
      scrollPane.setBorder(BorderFactory.createEmptyBorder()); // 이거는 테이블 테두리 없애줌

      // [CENTER-RIGHT]메뉴 탭 생성
      menu = new JTabbedPane();
      menu.setFont(new Font("맑은 고딕", Font.BOLD, 30));
      menu.setBackground(new Color(95, 148, 153));
      menu.setForeground(Color.WHITE);
      // 음료 탭 생성
      drinks = new JTabbedPane();
      // 프로덕트 탭 생성
      products = new JTabbedPane();
      products.setFont(new Font("맑은 고딕", Font.BOLD, 30));
      products.setBackground(new Color(95, 148, 153));
      products.setForeground(Color.WHITE);
      // RTD 탭 생성
      rtds = new JTabbedPane();

      // 탭에 생성한 항목들 추가
      menu.addTab("음료", drinks);
      menu.addTab("푸드", products);
      menu.addTab("RTD", rtds);

      // 음료 버튼들 추가(타입 10번 ~ 100번까지)
      for (int i = 0; i <= 11; i++) {
         String[] typeName = {"<HTML>리저브<br>에스프레소</HTML>", "<HTML>리저브<br>드립</HTML>",
               "콜드 브루", "블론드", "에스프레소", "디카페인", "프라푸치노", "블렌디드", "피지오", "티바나", "기타", "옵션"};
         drinks.addTab(typeName[i], makeItemButtons("drink",10 * (i + 1)));
         drinks.setFont(new Font("맑은 고딕", Font.BOLD, 13));
      }

      // 푸드 버튼들 추가 (타입 140번 ~ 200번까지)
      for (int i = 0; i <= 6; i++) {
         String[] typeName = { "브레드", "케이크/미니 디저트", "샌드위치/샐러드", "따뜻한 푸드", "과일/요거트", "스낵", "아이스크림" };
         products.addTab(typeName[i], makeItemButtons("product", 140 + (10 * i)));
         products.setFont(new Font("맑은 고딕", Font.BOLD, 13));
      }
      products.addTab("옵션", makeItemButtons("product", 120)); // 푸드에 옵션항목은 따로 추가. 반복문 하기에는 번호가 따로 있어서

      // RTD버튼 추가 (타입 210)
      rtds.addTab("RTD", makeItemButtons("RTD", 210));

      // [TOP]시계 생성
      JLabel time = new CurrentTimeClock().setClock();
      time.setFont(new Font("맑은 고딕", Font.BOLD, 30));
      time.setHorizontalAlignment(JLabel.CENTER);
      time.setOpaque(true);
      time.setBackground(new Color(110, 88, 68));
      time.setForeground(Color.WHITE);
      top.add(time);

      center.add(scrollPane);
      center.add(menu);

      add(top, BorderLayout.NORTH);
      add(center, BorderLayout.CENTER);

      setVisible(true);
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
      }

      // 버튼도 같이 ArrayList<>()로 받아서 저장해줄 것. MenuButton(이름, 가격)
      btns = new ArrayList<>();
      for (int i = 0; i < name.size(); i++) {
         btns.add(new MenuButton(name.get(i), price.get(i)));
         btns.get(i).setPreferredSize(new Dimension(180, 180));
         btns.get(i).setFont(new Font("맑은 고딕", Font.BOLD, 15));
         btns.get(i).setForeground(Color.white);
         btns.get(i).setBackground(new Color(161, 161, 161));
      }
      
      btns.get(0).getName();
      return btns;
   }
   
   private JPanel makeItemButtons(String itemType, int typeNum) {
      // 메뉴 탭들 안에 들어갈 베이스 패널 만들기
      menuPanelBase = new JPanel(new BorderLayout
            ());
      // 베이스의 센터에 들어갈 패널에 카드 레이아웃 적용하기
      menuPanelBase_center = new JPanel(new CardLayout());
      // 베이스의 밑에 버튼이 들어갈 패널 만들기
      menuPanelBase_south = new JPanel(new GridLayout());
      menuPanelBase_south.setBackground(new Color(243, 243, 243));
      // 베이스 센터에 들어갈 패널들 리스트로 만들기
      menuPanels = new ArrayList<>();
      
      // 상품 타입이 있는 항목 버튼으로 모두 생성
      btns = itemButtons(itemType, typeNum);
      // <카드로 만들어질 패널 생성> 버튼 개수를 받아서 계산 (4 x 4)기준
      for (int i = 0; i < btns.size() / 16 + 1; i++) {
         menuPanels.add(new JPanel(new GridLayout(4, 5)));
         menuPanels.get(i).setBackground(new Color(243, 243, 243));
      }
      
      // 버튼들 개수 인식해서 16배수마다 끊어주기 .... 일단 더 좋은 방법 있을 것 같은데 아직 최선은 이거에요..
      for (int i = 0; i < btns.size(); i++) {
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
      prev.setFont(new Font("맑은 고딕", Font.BOLD, 30));
      prev.setHorizontalAlignment(SwingConstants.RIGHT);
      prev.setContentAreaFilled(false);
      prev.setBorderPainted(false);
      JLabel pageNum = new JLabel("페이지 번호");
      pageNum.setFont(new Font("맑은 고딕", Font.BOLD, 30));
      JButton next = new JButton(">>");
      next.setFont(new Font("맑은 고딕", Font.BOLD, 30));
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
   
   public static void main(String[] args) {
      new OrderFrame();
   }

}