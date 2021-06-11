package jdbc.model;

public class Employees {
	
	private int employee_id;
	private String employee_name;
	private int employee_pw;
	private String employee_grade;
	
	public int getEmployee_id() {
		return employee_id;
	}
	
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	
	public String getEmployee_name() {
		return employee_name;
	}
	
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	
	public int getEmployee_pw() {
		return employee_pw;
	}
	
	public void setEmployee_pw(int employee_pw) {
		this.employee_pw = employee_pw;
	}
	
	public String getEmployee_grade() {
		return employee_grade;
	}
	
	public void setEmployee_grade(String employee_grade) {
		this.employee_grade = employee_grade;
	}

}
