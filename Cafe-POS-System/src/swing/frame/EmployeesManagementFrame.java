package swing.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import action.CurrentTimeClock;
import action.EmployeeInfoAddButton;
import action.EmployeeInfoDelButton;
import action.EmployeeInfoUpdateButton;
import action.GetTableInfoForMouse;
import jdbc.method.SelectEmployeeInfo;

public class StaffManagementFrame extends DefaultFrame {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StaffManagementFrame() {
      setLayout(new BorderLayout());
      setTitle("Employees Management");
      
      // -- CENTER
      // 가운데 공간에 패널 생성(1행 2열)
      JPanel center = new JPanel(new GridLayout());
      
      // -- CENTER-LEFT
      // 스태프 정보테이블 왼쪽에 추가(1, 1)번째
      JTable staff_info = new SelectEmployeeInfo().getEmployeeInfo();
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
      header.setBackground(new Color(161, 192, 90));
      // 테이블 갯수 넘어가면 스크롤바로 변경됨
      JScrollPane scorll_add_staff_info = new JScrollPane(staff_info);
      // 테이블에 값 선택하면 값 가져오기 이벤트 설정
      staff_info.addMouseListener(new GetTableInfoForMouse(staff_info));
      
      
      // -- CENTER-RIGHT
      // 센터 패널의 오른쪽 공간 생성(1, 2)번째
      // JTabbedPane() 탭 만들기 -> 오른쪽 패널 탭으로 만들기
      JTabbedPane right_panel_tab = new JTabbedPane();
      right_panel_tab.setFont(new Font("맑은 고딕", Font.BOLD, 30));
      right_panel_tab.setBackground(new Color(161, 192, 90));
      right_panel_tab.addTab("등록", createTab("등록"));
      right_panel_tab.addTab("수정", createTab("수정"));
      right_panel_tab.addTab("삭제", createTab("삭제"));
      
      // -- CENTER-TOP 뒤로 가기 버튼
      JPanel top_panel_body = new JPanel(new GridLayout());
      JButton back_btn = new JButton("<<");
      back_btn.setPreferredSize(new Dimension(100, 80));
      back_btn.setFont(new Font("궁서", Font.BOLD, 30));
      back_btn.setBackground(new Color(0, 60, 0));
      back_btn.setForeground(Color.WHITE);
      top_panel_body.add(back_btn, BorderLayout.WEST);
      // 가운데 시스템시계
      JLabel clock = new CurrentTimeClock().setClock();
      clock.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
      clock.setHorizontalAlignment(JLabel.CENTER);
      clock.setOpaque(true);
      clock.setBackground(Color.pink);
      top_panel_body.add(clock, BorderLayout.CENTER);
      // 오른쪽에 로그인한 사람 정보 뜨게할려고 일단 임시
      JLabel login_name = new JLabel("예림");
      login_name.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
      login_name.setHorizontalAlignment(JLabel.CENTER);
      login_name.setOpaque(true);
      login_name.setBackground(Color.cyan);
      top_panel_body.add(login_name, BorderLayout.EAST);

      // CENTER에 패널들 add
      center.add(scorll_add_staff_info);
      center.add(right_panel_tab);
      
      // Frame에 패널 위치 선정하고 추가
      add(top_panel_body, BorderLayout.NORTH);
      add(center, BorderLayout.CENTER);
      
      setVisible(true);
   }
   
   public JPanel createTab (String tab_name) {
      JPanel right_panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 50));
      ArrayList<JLabel> labels = new ArrayList<>();
      ArrayList<TextField> fields = new ArrayList<>();
      String[] labels_name = {"이　　름", "패스워드"};
      
      for (int i = 0; i < labels_name.length; i++) {
         labels.add(new JLabel(labels_name[i]));
         // 라벨 가운데 정렬
         labels.get(i).setHorizontalAlignment(JLabel.CENTER);
         labels.get(i).setFont(new Font("맑은 고딕", Font.BOLD, 30));
         // setOpaque(true) 후 -> setBackground(Color)채워짐
         labels.get(i).setOpaque(true);
         labels.get(i).setBackground(new Color(161, 192, 90));
         // 사이즈 지정
         labels.get(i).setPreferredSize(new Dimension(200, 80));
         // 라벨 튀어나오게 설정
         labels.get(i).setBorder(new BevelBorder(BevelBorder.RAISED));
         
         fields.add(new TextField(20));
         fields.get(i).setFont(new Font("맑은 고딕", Font.PLAIN, 30));
         
         right_panel.add(labels.get(i));
         right_panel.add(fields.get(i));
         
      }
      // 직급 라벨은 콤보박스때문에 따로 추가
      JLabel grade = new JLabel("직　　급");
      grade.setHorizontalAlignment(JLabel.CENTER);
      grade.setFont(new Font("맑은 고딕", Font.BOLD, 30));
      grade.setPreferredSize(new Dimension(200, 80));
      grade.setBorder(new BevelBorder(BevelBorder.RAISED));
      grade.setOpaque(true);
      grade.setBackground(new Color(161, 192, 90));
      right_panel.add(grade);
      // 직급 콤보박스
      String[] grade_list = {"BARISTA", "MANAGER"};
      JComboBox<String> grade_box = new JComboBox<>(grade_list);
      grade_box.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
      grade_box.setPreferredSize(new Dimension(365, 80));
      right_panel.add(grade_box);
      
      JButton btn = new JButton(tab_name);
      btn.setPreferredSize(new Dimension(200, 80));
      btn.setFont(new Font("맑은 고딕", Font.BOLD, 30));

      
      // 버튼에 등록, 수정, 삭제 액션 추가
      switch (tab_name) {
      case "등록":
    	  btn.addActionListener(new EmployeeInfoAddButton(fields, grade_box));
    	  break;
      case "수정":
    	  btn.addActionListener(new EmployeeInfoUpdateButton(fields, grade_box));
    	  break;
      case "삭제":
    	  btn.addActionListener(new EmployeeInfoDelButton(fields, grade_box));
    	  break;
      }
      
      right_panel.add(btn, BorderLayout.SOUTH);
      
      
      return right_panel;
   }
   
   public static void main(String[] args) {
      new StaffManagementFrame();
   }
}