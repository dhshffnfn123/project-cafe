
package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import jdbc.hikari.HikariCP;
import swing.frame.AccumulatedFundFrame;
import swing.frame.AccumulatedResultsFrame;

//전화번호가 일치했을 때 쿠폰과 포인트 값을 가져오고 DB에 업데이트 시키는 클래스
public class UpdatePointCoupon {
	private String fieldTesxt, phoneNum;
	private String sql = "SELECT guest_point,coupon FROM guest_table WHERE guest_name = ?";
	private String updateSql = "UPDATE guest_table SET guest_point = ?,coupon = ? "
			+ "WHERE guest_name = ?";	
	int point, coupon;
	String name;
	AccumulatedFundFrame accumulatedFundFrame;
	private ArrayList<JPanel> panelR;
	
	public UpdatePointCoupon(String fieldTesxt, ArrayList<JPanel> panelR, String grade, String order_name) {
		this.panelR = panelR;
		phoneNum = fieldTesxt;
		
		getPointCoupon();
		//쿠폰이랑 포인트 DB에 업데이트시키기(try 바깥쪽에서 불러야지 다른 sql문을 실행할 수 있음)
		DBUpdate();
		new AccumulatedResultsFrame(phoneNum, point, coupon, panelR, grade, order_name);
	}
	
	private void getPointCoupon() {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
								
			){
			pstmt.setString(1, phoneNum);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				point = rs.getInt(1);
				coupon = rs.getInt(2);
				
				//적립했으니까 point 1씩 올려주기(여기서 해야지 point가 제대로 누적 됨)
				point++;
			}
			//point가 10이 되면 쿠폰으로 변환하기(그리고 point는 0으로 비워줌)
			if (point >= 10) {
				coupon += point/10;
				point = 0;
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void DBUpdate() {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(updateSql);
				
				){

			pstmt.setInt(1, point);
			pstmt.setInt(2, coupon);
			pstmt.setString(3, phoneNum);
			
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
}




