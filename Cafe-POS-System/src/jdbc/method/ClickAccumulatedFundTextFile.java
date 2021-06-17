package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;


import jdbc.hikari.HikariCP;
import swing.frame.AccumulatedFundFrame;
import swing.method.RoundJTextField;

//적립 클릭시 오는 클래스
public class ClickAccumulatedFundTextFile {
	private AccumulatedFundFrame accumulatedFundFrame;
	private RoundJTextField textField;
	private String fieldTesxt;
	private String sql = "SELECT guest_name FROM guest_table";

	public ClickAccumulatedFundTextFile(RoundJTextField textField, AccumulatedFundFrame accumulatedFundFrame) {
		this.accumulatedFundFrame = accumulatedFundFrame;
		this.textField = textField;
		fieldTesxt = textField.getText();
		selectDB();
	}
	
	private void selectDB() {
		numberComparison();
		
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();	
			){
			while (rs.next()) {
				if(rs.getString(1).equals(fieldTesxt)) {
					new UpdatePointCoupon(fieldTesxt, accumulatedFundFrame);
					return;
				}
			}
			//값이 일치하지 않을 때 팝업창 클래스 불러오기
			new AccumulatedFundJOptionPane(textField);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//정규표현식으로 번호에 -(하이픈)을 추가하는 메서드
	private void numberComparison() {
		if (Pattern.matches("0\\d{2}\\d{3,4}\\d{4}", fieldTesxt)) {
			//()는 하나의 그룹으로 만든다.그래서 $숫자를 통해 그룹 단위로 하이픈을 추가한다
			//replaceAll을 해야지 전부 대체가 된다(예외가 안뜬다).아마 그룹으로 나눠서 각각 따로 보기 때문인 듯
			fieldTesxt = fieldTesxt.replaceAll("(0\\d{2})(\\d{3,4})(\\d{4})","$1-$2-$3");
		}
	}
}


























