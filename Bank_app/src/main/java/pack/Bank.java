package pack;

public class Bank {
		
	private String act_num;
	private String name;
	private float balance;
	
	public Bank(String act_num, String name, float balance){
		this.act_num=act_num;
		this.name=name;
		this.balance=balance;
	}
	public String getActNo() {
		return act_num;
	}
	public String getName() {
		return name;
	}
	public float getBal() {
		return balance;
	}
}
