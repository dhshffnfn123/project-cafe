package swing.method;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import jdbc.method.SelectEmployeeInfo;

public class RenewalToTable {
	
	public RenewalToTable(JTable table) {
		// 1. 갱신되기 전 테이블을 매개변수로 받아 모델로 타입캐스팅
		DefaultTableModel originModel = (DefaultTableModel)table.getModel();
		// 2. 갱신된 모델을 SelectEmployeeInfo()클래스의 getModel()메서드를 통해 받아오기
		DefaultTableModel updateModel = new SelectEmployeeInfo().getModel();
		// 3. 갱신 전 모델 setRowCount(0)메서드로 화면에서 삭제
		originModel.setRowCount(0);
		// 4. 매개변수로 받은 테이블에 다시 갱신된 모델 반영
		table.setModel(updateModel);
		updateModel.fireTableDataChanged();
		
		// 5. 원래 테이블을 삭제했다가 새로 띄워주는 것이므로 다시 가운데 정렬 해줘야함
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
	}
}
