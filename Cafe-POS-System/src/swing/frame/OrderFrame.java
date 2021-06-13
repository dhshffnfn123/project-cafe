package swing.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import action.CurrentTimeClock;
import jdbc.hikari.HikariCP;
import jdbc.view.button.DrinkButton;
import jdbc.view.button.EspressoButton;

public class OrderFrame extends DefaultFrame {
	
	static int i=0;
	public OrderFrame() {
		
		// 기본프레임설정 
		setTitle("Cafe Project");
		setLayout(null);
		getContentPane().setBackground(new Color(245, 235, 208));
		
		// 프레임아이콘 설정 
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("./image/icon.png");
		setIconImage(img);
		
		// 테이블 설정  
		String[] header =new String[]{"주문번호","상품명","상품수량","가격","옵션"};
		DefaultTableModel model = new DefaultTableModel(header,0); //테이블 데이터
		JTable table =new JTable(model);
		table.setOpaque(true);
		table.setBackground(new Color(62,110,255));
		table.setRowHeight(45);
		JScrollPane scrollpane = new JScrollPane(table);
		// 스크롤바 크기 설정
		scrollpane.setPreferredSize(new Dimension(620,620));
		//테이블 출력위치 설정 
		scrollpane.setLocation(10,110);
		scrollpane.setSize(620,620);
		add(scrollpane);
		
        // info 
        ImageIcon infoImg = new ImageIcon("./image/icon.png");
        JLabel info = new JLabel(infoImg); 
        info.setBounds(10, 10, 250, 80);
        info.setHorizontalAlignment(JLabel.CENTER);
        add(info);
        
        // infoTime 
        JLabel infoTime = new CurrentTimeClock().setClock();
        infoTime.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        infoTime.setBounds(260, 10, 540, 80);
        infoTime.setHorizontalAlignment(JLabel.CENTER);
        infoTime.setOpaque(true); 
        infoTime.setBackground(new Color(0,110,63));
        infoTime.setForeground(new Color(255,255,255));
        add(infoTime);
        
        // info
        JLabel infouser = new JLabel(" 사용자 : 관리자 ");
        infouser.setOpaque(true);
        infouser.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        infouser.setBounds(800, 10, 540, 80);
        infouser.setHorizontalAlignment(JLabel.CENTER);
        infouser.setBackground(new Color(0,110,63));
        infouser.setForeground(new Color(255,255,255));
        add(infouser);
        
        //뒤로가기 버튼 
        ImageIcon backbtn = new ImageIcon("./image/backbtn.jpg");
        JButton choosepage = new JButton(backbtn);
        choosepage.setOpaque(true);
        choosepage.setBounds(1340, 10, 140, 80);
        choosepage.setBackground(new Color(0,110,63));
        choosepage.setBorderPainted(false);
        add(choosepage);
        
        // Menu 탭 생성 
        JTabbedPane Menu = new JTabbedPane();  //JTabbedPane생성
        Menu.setFont(new Font("맑은 고딕", Font.BOLD, 30));
         
        
        //음료탭 설정 
        JTabbedPane drinkmenu = new JTabbedPane();  //JTabbedPane생성
        drinkmenu.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        
       
        Menu.addTab("음료", drinkmenu);
        
        JPanel drink1 = new JPanel(); //JPanel 생성
        JPanel drink2 = new JPanel(); //JPanel 생성
        drink1.setLayout(new GridLayout(3,3));
        drink2.setLayout(new GridLayout(3,3));
        
        // 리저브에스프레소 버튼 
        drinkmenu.addTab("리저브에스프레소", drink1);	
		String 리저브에스프레소 = "SELECT * FROM drink_table WHERE M_TYPE_ID = 10";
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(리저브에스프레소);
				ResultSet rs = pstmt.executeQuery();
				) {
			while (rs.next()) {
				JButton 리저브에스프레소버튼= new DrinkButton(rs.getString(3), rs.getString(4));
				drink1.add(리저브에스프레소버튼);
				
				// 리저브에스프레소버튼액션 리스너
				리저브에스프레소버튼.addActionListener( new ActionListener(){ 
		           public void actionPerformed(ActionEvent e) {     	   
		
		        	   String[] row = new String[5];
		        	   
		        	   //{"주문번호","상품명","상품수량","가격","옵션"
		        	   row[0]= "A00000000000000"+i;
		        	   i++;
		        	   row[1]=  "1";
		        	   row[2]= ((DrinkButton)e.getSource()).getMenuName();
		        	   row[3]= ((DrinkButton)e.getSource()).getPrice();
		        	   model.addRow(row);  
		        	   
		           }
		           	
		       });
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		 // 리저브드립 버튼 
        drinkmenu.addTab("리저브드립", drink2);	
		String 리저브드립 = "SELECT * FROM drink_table WHERE M_TYPE_ID = 20";
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(리저브드립);
				ResultSet rs = pstmt.executeQuery();
				) {
			while (rs.next()) {
				JButton 리저브드립버튼= new DrinkButton(rs.getString(3), rs.getString(4));
				drink2.add(리저브드립버튼);
				
				// 리저브에스프레소버튼액션 리스너
				리저브드립버튼.addActionListener( new ActionListener(){ 
		           public void actionPerformed(ActionEvent e) {     	   
		
		        	   String[] row = new String[5];
		        	   
		        	   //{"주문번호","상품명","상품수량","가격","옵션"
		        	   row[0]= "A00000000000000"+i;
		        	   i++;
		        	   row[1]= ((DrinkButton)e.getSource()).getMenuName();
		        	   row[2]=  "1";
		        	   row[3]= ((DrinkButton)e.getSource()).getPrice();
		        	   row[4]= "";
		        	   model.addRow(row);  
		        	   
		           }
		           	
		       });
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
       
        Menu.setBounds(660, 110, 800, 500);
        add(Menu);
        
        
        //결제 버튼 설정 
		JButton 결제= new JButton("결제");
		결제.setBounds(670, 630, 100, 100);
		add(결제);
		
		//결제 버튼 설정 
		JButton 삭제 = new JButton("삭제");
		삭제.setBounds(780, 630, 100, 100);
		add(삭제);
		
		//삭제 액션 리스너
		삭제.addActionListener( new ActionListener(){ 
		           public void actionPerformed(ActionEvent e) {
		        	   
		        	   if( table.getSelectedRow() != -1){
		        		   model.removeRow(table.getSelectedRow());
		        	   }
		        	   
		           }  	
		 });
	
		
		setVisible(true);
		repaint();
	}
	
	
	public static void main(String[] args) {
		new OrderFrame();
	}
}
