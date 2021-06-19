package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.hikari.HikariCP;

// 영수증의 시퀀스 제거 후 바로 생성
public class DropSequenceReceiptInfo {

	private String sql = "DROP SEQUENCE receipt_id_seq";

	public DropSequenceReceiptInfo() {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
			) {
			new CreateSequenceReceiptInfo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}