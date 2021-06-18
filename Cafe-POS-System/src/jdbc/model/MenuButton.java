package jdbc.model;

import javax.swing.JButton;

public class MenuButton extends JButton {

   private String button_name;
   private Integer button_price;
 

   public MenuButton(String button_name, Integer button_price) {
      super("<HTML><body style='text-align:center;'>" + button_name + "<br>" + button_price + "¿ø</body></HTML>");
      this.setButton_name(button_name);
      this.setButton_price(button_price);
   }

   public String getButton_name() {
      return button_name;
   }

   public void setButton_name(String button_name) {
      this.button_name = button_name;
   }

   public Integer getButton_price() {
      return button_price;
   }

   public void setButton_price(Integer button_price) {
      this.button_price = button_price;
   }
}