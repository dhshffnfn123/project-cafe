package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.hikari.HikariCP;

// 영수증 시퀀스 생성
public class CreateSequenceReceiptInfo {
	private String sql = "CREATE SEQUENCE receipt_id_seq START WITH 1 INCREMENT BY 1 MAXVALUE 999 CYCLE NOCYCLE";
	public CreateSequenceReceiptInfo() {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
