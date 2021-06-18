package action;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import jdbc.hikari.HikariCP;

public class AddConfirmBtn implements ActionListener {

	private JFrame frame;
	private JTextField name, count;
	private String nameVal, countVal;
	private int countint;
	private JTable table;
	private DefaultTableCellRenderer dtcr_center;
	Font bigger_font = new Font("맑은 고딕", Font.BOLD, 50);
	Font big_font = new Font("맑은 고딕", Font.PLAIN, 30);
	Font nomal_font = new Font("맑은 고딕", Font.BOLD, 15);
	Font small_font = new Font("맑은 고딕", Font.BOLD, 15);
	private Color y_color = new Color(163, 148, 132);
	private String sql = "INSERT INTO stock_table VALUES ('A0000000' || TO_CHAR(stock_id_seq.nextval), ?, ?)";

	public AddConfirmBtn(JTextField name, JTextField count, JTable table, JFrame frame) {
		this.frame = frame;
		this.table = table;
		this.name = name;
		this.count = count;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		
		try (Connection conn = HikariCP.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			nameVal = name.getText();
			if (nameVal.isEmpty()) {
				JOptionPane.showMessageDialog(null, "이름을 입력해주세요.", "SYSTEM", JOptionPane.WARNING_MESSAGE);
			} else {
				countVal = count.getText();
				countint = Integer.parseInt(countVal);
				
				System.out.println(countint);
				System.out.println(nameVal);
				
				pstmt.setString(1, nameVal);
				pstmt.setString(2, countVal);
				ResultSet rs = pstmt.executeQuery();
				rs.close();
				DefaultTableModel originmodel = (DefaultTableModel) table.getModel();
				
				DefaultTableModel updatemodel = (DefaultTableModel) (new StockTableAddData().getStockTable().getModel());
				
				originmodel.setRowCount(0);
				
				table.setModel(updatemodel);
				
				table.getTableHeader().setReorderingAllowed(false); // 테이블 헤더 이동 안되게 하기
				table.getTableHeader().setBackground(y_color);// 컬럼의 색상을 설정
				table.getTableHeader().setFont(big_font);
				table.getTableHeader().setForeground(Color.white);
				
				String[] header = new StockTableAddData().give_header();
				
				table.getColumn(header[0]).setPreferredWidth(160); // 컬럼당 넓이 설정인데 모든 컬럼을 테이블의 넓이에 '얼추' 맞게 설정해야함
				table.getColumn(header[1]).setPreferredWidth(900);
				table.getColumn(header[2]).setPreferredWidth(160);
				table.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
				
				dtcr_center = new DefaultTableCellRenderer();
				
				dtcr_center.setHorizontalAlignment(SwingConstants.CENTER); // dtcr_center의 위치를 center로 지정
				
				TableColumnModel ts = table.getColumnModel(); // 정렬할 테이블의 columnModel을 가져옴
				ts.getColumn(0).setCellRenderer(dtcr_center);// product_id 컬럼을 센터 정렬
				ts.getColumn(1).setCellRenderer(dtcr_center);
				ts.getColumn(2).setCellRenderer(dtcr_center);
				
				updatemodel.fireTableDataChanged();
				
//			UIManager.put("OptionPane.messageFont", nomal_font);
				JOptionPane.showMessageDialog(null, "데이터가 추가되었습니다.", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
			}
		} catch (SQLDataException e3) {
//            UIManager.put("OptionPane.messageFont", system_font);
            JOptionPane.showMessageDialog(null, "입력 가능한 숫자를 벗어났습니다.", "SYSTEM", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e1) {
			e1.printStackTrace();
		} catch (NumberFormatException e2) {
//			UIManager.put("OptionPane.messageFont", nomal_font);
			JOptionPane.showMessageDialog(null, "수량을 잘못 입력하셨습니다.", "SYSTEM", JOptionPane.ERROR_MESSAGE);
			System.out.println(e2);
		}

	}

}