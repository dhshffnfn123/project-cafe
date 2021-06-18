package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.hikari.HikariCP;

// 결제완료 후 영수증 테이블 정보  Delete 후 시퀀스 Drop 후 시퀀스 Create
public class DeletereceiptInfo {
	private String sql = "DELETE FROM receipt_table";
	
	public DeletereceiptInfo() {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.executeUpdate();
			new DropSequenceReceiptInfo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}