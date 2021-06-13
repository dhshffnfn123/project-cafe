package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jdbc.hikari.HikariCP;

public class SelectEmployeeInfo {
	
	private String sql = "SELECT * FROM employees_table ORDER BY employee_id";
	private DefaultTableModel model;
	private JTable table;
	private String[] title = {"직원 아이디", "이　　름", "패스워드", "직　　급"};
	private String[][] data = new String[0][0];
	
	public JTable getEmployeeInfo() {
		inputData();
		selectDB();
		
		return table;
	}
	
	public DefaultTableModel getModel() {
		model = new DefaultTableModel(data, title) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		selectDB();

		return model;
	}
	
	private void inputData() {
		// isCellEditable() @Override해서 더블클릭 수정 금지
		model = new DefaultTableModel(data, title) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(model);
		// 변경된 데이터를 반영
		table.setModel(model);
		model.fireTableDataChanged();
		table.updateUI();
	}
	
	private void selectDB() {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String[] row = {Integer.toString(rs.getInt(1)), rs.getString(2), 
						rs.getString(3), rs.getString(4)};
				model.addRow(row);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
