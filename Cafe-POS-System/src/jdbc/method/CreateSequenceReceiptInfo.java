package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.hikari.HikariCP;

// 영수증 시퀀스 생성
public class CreateSequenceReceiptInfo {
	private String sql = "CREATE SEQUENCE receipt_id_seq START WITH 1 INCREMENT BY 1 MAXVALUE 99 CYCLE NOCACHE";
	public CreateSequenceReceiptInfo() {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				) {
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
