package jdbc.hikari;

import java.sql.Connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * hikariCP DB를 연결시켜주는 클래스입니다. DB에서 값을 불러올 때 사용하면 됩니다. <br>(다량의 세션 발생을 예방하기 위함)
 * <br><br>
 * [사용 방법]
 * HikariCP import 후 getConnection() 메서드 사용. Auto Close방식으로 사용
 * <br>
 * EX) 
 * <br><pre>
 * try (
 * 	Connection conn = HikariCP.getConnection();
 * 	PreparedStatement pstmt = conn.prepareStatement(sql);
 * ) {
 * 
 * 	원하는 명령문 작성.
 * 
 * } catch (SQLException e1) {
 * 	e1.printStackTrace();
 * }
 * </pre>
 * 
 * */
public class HikariCP {
	
	private static HikariConfig config;
	private static HikariDataSource ds;
	
	static {
		config = new HikariConfig("./data/hikari.properties");
		config.setConnectionTestQuery("SELECT 1 FROM dual");
		
		ds = new HikariDataSource(config);
	};
	
	public static Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (Exception e) {
			System.out.println("[ERROR] HikariCP Class getConnection");
			return null;
		}
	}
	
}
