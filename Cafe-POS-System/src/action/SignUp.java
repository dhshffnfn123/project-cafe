package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import jdbc.hikari.HikariCP;
import jdbc.method.UpdatePointCoupon;
import swing.method.AccrualCompletionJOP;
import swing.method.RoundJTextField;

//회원등록 클래스
public class SignUp extends MouseAdapter {
	private String sql = "INSERT INTO guest_table (guest_id, guest_name) VALUES (guest_id_seq.nextval, ?)";
	private String guestNameSql = "SELECT guest_name FROM guest_table";
	private String guestName;
	RoundJTextField textField;
	ArrayList<JPanel> panelR;
	
	public SignUp(RoundJTextField textField, ArrayList<JPanel> panelR) {
		this.textField = textField;
		this.panelR = panelR;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {	
		guestName = textField.getText();
		//번호에 하이픈 추가
		if (Pattern.matches("0\\d{2}\\d{3,4}\\d{4}", guestName)) {
			guestName = guestName.replaceAll("(0\\d{2})(\\d{3,4})(\\d{4})","$1-$2-$3");
		}
		
		try (
				Connection conn = HikariCP.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(guestNameSql);
				ResultSet rs = pstmt.executeQuery();
			){
			
			while(rs.next()) {
				if (guestName.equals(rs.getString(1))) {
					new AccrualCompletionJOP().showMessageDialog(null, "이미 존재하는 번호입니다.", 
							"경고", JOptionPane.OK_CANCEL_OPTION);
					return;
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		 if (Pattern.matches("0\\d{2}-\\d{3,4}-\\d{4}", guestName)){
			 DBUpdate();
		}else {
			new AccrualCompletionJOP().showMessageDialog(null, "올바르지 않은 형식의 번호입니다. 다시 입력해 주세요", 
					"오류", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	private void DBUpdate() {
		// 입력 받은 회원정보 등록, 적립하기
		try (
				Connection conn = HikariCP.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			
			pstmt.setString(1, guestName);
			pstmt.executeUpdate();
			
			new AccrualCompletionJOP().showMessageDialog(null, "회원등록이 완료되었습니다.", 
					"완료", JOptionPane.INFORMATION_MESSAGE);
			new UpdatePointCoupon(guestName, panelR);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
