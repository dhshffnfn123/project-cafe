package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jdbc.hikari.HikariCP;
import jdbc.method.TotalInfo;

public class CheckSalesSelectButton implements ActionListener {

	private JFrame check_frame;
	private JTable select_table, total_table;
	private JButton button;
	private JComboBox<String> ybox;
	private JComboBox<String> mbox, dbox;
	private JScrollPane table_scroll;
	private String year, month, day;

	private String sql = "SELECT TO_CHAR(order_time, 'YYYY'), COUNT(order_total), SUM(order_total)"
			+ "FROM order_table " + "GROUP BY TO_CHAR(order_time, 'YYYY')" + "ORDER BY TO_CHAR(order_time, 'YYYY')";

	private String sql2 = "SELECT TO_CHAR(order_time, 'YYYY-MM'), COUNT(order_total), SUM(order_total)"
			+ "FROM order_table " + "GROUP BY TO_CHAR(order_time, 'YYYY-MM')"
			+ "ORDER BY TO_CHAR(order_time, 'YYYY-MM')";

	private String sql_year_total = "select COUNT(*), to_char(sum(order_total), '999,999,999L') from order_table where to_char(order_time, 'yyyy') like ?";

	private String sql_month_total = "select count(*), to_char(sum(order_total), '999,999,999L') from order_table"
			+ " where (to_char(order_time, 'yyyy') like ? and to_char(order_time, 'mm') like ?)";

	private String sql_total = "select count(*), to_char(sum(order_total), '999,999,999L') from order_table";

	private String sql_year = "SELECT TO_CHAR(order_time, 'YYYY'), COUNT(order_total), SUM(order_total) FROM order_table "
			+ "WHERE TO_CHAR(order_time, 'YYYY') LIKE ? "
			+ "GROUP BY TO_CHAR(order_time, 'YYYY')ORDER BY TO_CHAR(order_time, 'YYYY')";

	private String sql_month = "SELECT TO_CHAR(order_time, 'YYYY-MM-DD'), COUNT(order_total), SUM(order_total) FROM order_table "
			+ "WHERE (TO_CHAR(order_time, 'YYYY') LIKE ? AND TO_CHAR(order_time, 'MM') LIKE ?) "
			+ "GROUP BY TO_CHAR(order_time, 'YYYY-MM-DD') ORDER BY TO_CHAR(order_time, 'YYYY-MM-DD')";

	private String sql_month_all = "SELECT TO_CHAR(order_time, 'YYYY-MM'), COUNT(order_total), SUM(order_total) FROM order_table "
			+ "WHERE TO_CHAR(order_time, 'YYYY-MM') LIKE ? "
			+ "GROUP BY TO_CHAR(order_time, 'YYYY-MM')ORDER BY TO_CHAR(order_time, 'YYYY-MM')";

	private DefaultTableModel model, total_model;
	private Object[][] data = new String[0][0];
	private String date, un, total_result;
	private int result = 0;
	private int count = 0, total_count = 0;
	private TotalInfo total;

	public CheckSalesSelectButton(JFrame check_frame, JButton button, DefaultTableModel model,
			DefaultTableModel total_model, JTable select_table, JTable total_table, JComboBox<String> ybox,
			JComboBox<String> mbox) {
		this.check_frame = check_frame;
		this.button = button;
		this.ybox = ybox;
		this.mbox = mbox;
		this.model = model;
		this.total_model = total_model;
		this.select_table = select_table;
		this.total_table = total_table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		model.setNumRows(0);
		total_model.setNumRows(0);
		try (Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql); // 전체 연 매출
				PreparedStatement pstmt2 = conn.prepareStatement(sql2); // 전체 월 매출

				PreparedStatement pstmt_y = conn.prepareStatement(sql_year);
				PreparedStatement pstmt_m = conn.prepareStatement(sql_month);
				PreparedStatement pstmt_ma = conn.prepareStatement(sql_month_all);

				PreparedStatement pstmt_year_total = conn.prepareStatement(sql_year_total); // 총 매출 계산
				PreparedStatement pstmt_month_total = conn.prepareStatement(sql_month_total); // 총 매출 계산
				PreparedStatement pstmt_total = conn.prepareStatement(sql_total); // 총 매출 계산

				ResultSet rs = pstmt.executeQuery();
				ResultSet rs2 = pstmt2.executeQuery();

				ResultSet rs_total = pstmt_total.executeQuery();

		) {

			year = ybox.getSelectedItem().toString();
			month = mbox.getSelectedItem().toString();

			// 모든년도 검색
			if (year.equals("전체 년도") && month.equals("월 선택")) {
				while (rs.next()) {
					date = rs.getString(1);
					count = rs.getInt(2);
					result = rs.getInt(3);
					Object[] row = { date, count, result };
					model.addRow(row);
				}
				// 총매출 건수와 총매출
				while (rs_total.next()) {
					total_count = rs_total.getInt(1);
					total_result = rs_total.getString(2);
					Object[] row2 = { total_count, total_result };
					total_model.addRow(row2);
				}

			// 모든 년도, 월, 일 검색
			} else if (year.equals("전체 년도") && month.equals("전체 월")) {
				while (rs2.next()) {
					date = rs2.getString(1);
					count = rs2.getInt(2);
					result = rs2.getInt(3);
					Object[] row = { date, count, result };
					model.addRow(row);
				}
				// 총매출 건수와 총매출
				while (rs_total.next()) {
					total_count = rs_total.getInt(1);
					total_result = rs_total.getString(2);
					Object[] row2 = { total_count, total_result };
					total_model.addRow(row2);
				}

			// 특정 년도 모든 월 조회
			} else if ((!(year.equals("전체 년도") && year.equals("년도 선택"))) && (month.equals("전체 월"))) {

				pstmt_ma.setString(1, String.format("%%%s%%", year));
				pstmt_ma.executeUpdate();
				ResultSet rs_ma = pstmt_ma.executeQuery();

				pstmt_year_total.setString(1, String.format("%%%s%%", year));
				pstmt_year_total.executeUpdate();
				ResultSet rs_year_total = pstmt_year_total.executeQuery();

				while (rs_ma.next()) {
					date = rs_ma.getString(1);
					count = rs_ma.getInt(2);
					result = rs_ma.getInt(3);
					Object[] row = { date, count, result };
					model.addRow(row);
				}
				// 특정 년도 모든 매출
				while (rs_year_total.next()) {
					total_count = rs_year_total.getInt(1);
					total_result = rs_year_total.getString(2);
					Object[] row2 = { total_count, total_result };
					total_model.addRow(row2);
				}
				rs_ma.close();
				pstmt_m.close();
				rs_year_total.close();
				pstmt_year_total.close();

			// 특정년도 특정월 모든 일 조회
			} else if ((!(year.equals("전체 년도") && year.equals("년도 선택")))
					&& (!(month.equals("전체 월") && month.equals("월 선택")))) {
				System.out.println("월 조회됨");

				pstmt_m.setString(1, String.format("%%%s%%", year)); //where문에 콤보박스 year값 삽입
				pstmt_m.setString(2, String.format("%%%s%%", month)); //where문에 콤보박스 year값 삽입

				pstmt_m.executeUpdate();
				ResultSet rs_m = pstmt_m.executeQuery();

				pstmt_month_total.setString(1, String.format("%%%s%%", year));
				pstmt_month_total.setString(2, String.format("%%%s%%", month));
				pstmt_month_total.executeUpdate();
				ResultSet rs_month_total = pstmt_month_total.executeQuery();
				
				while (rs_m.next()) { 
					date = rs_m.getString(1);
					count = rs_m.getInt(2);
					result = rs_m.getInt(3);
					Object[] row = { date, count, result };
					model.addRow(row);
				}
				// 특정년도 특정 월의 모든 매출
				while (rs_month_total.next()) {
					total_count = rs_month_total.getInt(1);
					total_result = rs_month_total.getString(2);
					Object[] row2 = { total_count, total_result };
					total_model.addRow(row2);
				}
				rs_m.close();
				pstmt_m.close();
				rs_month_total.close();
				pstmt_month_total.close();
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}
}