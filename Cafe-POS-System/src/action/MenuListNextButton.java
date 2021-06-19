package action;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuListNextButton implements ActionListener {

   private JPanel cardPanel;
   private JLabel pageLabel;
   private ArrayList<JPanel> panels;
   
   
   public MenuListNextButton(JPanel cardPanel, JLabel pageLabel, ArrayList<JPanel> panels) {
      this.cardPanel = cardPanel;
      this.pageLabel = pageLabel;
      this.panels = panels;
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
	  int i = 1;
      // 카드 레이아웃을 넘기기 위해서는 레이아웃이 설정된 컨테이너의 인스턴스가 필요하다. 카드 레이아웃이 설정된 JPanel을 넘겨받음
      CardLayout card = (CardLayout)cardPanel.getLayout();
      
      // 페이지 넘버 입력할 라벨
      // 카드레이아웃인 요소 ArrayList들을 받아서, 마지막 인덱스가 isVisible()이 아닌 경우만 next되게 설정
      if (!panels.get(panels.size() - 1).isVisible()) {
         card.next(cardPanel);
         i++;
         pageLabel.setText("( " + i + " / " + panels.size() + " )");
      }
      
      if (panels.get(panels.size() - 1).isVisible()) {
    	  pageLabel.setText("( " + panels.size() + " / " + panels.size() + " )");
    	  i = 1;
      }
      
   }
   
}