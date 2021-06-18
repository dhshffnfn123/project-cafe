package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.hikari.HikariCP;
// 영수증 번호 생성 1 ~ 99 99가 되면 1로 초기화
public class ReceiptUpNumber {
	private String sql = "INSERT INTO receipt_table (receipt_id) VALUES (receipt_id_seq.nextval)";
	public ReceiptUpNumber() {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, 
			             ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = pstmt.executeQuery();

		){
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
