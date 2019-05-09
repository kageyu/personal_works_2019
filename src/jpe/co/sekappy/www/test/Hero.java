package jpe.co.sekappy.www.test;

public class Hero {

	String name;
	String agi;
	String atk;
	String hp;
	String nowhp;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAgi() {
		return agi;
	}
	public void setAgi(String agi) {
		this.agi = agi;
	}
	public String getAtk() {
		return atk;
	}
	public void setAtk(String atk) {
		this.atk = atk;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getNowhp() {
		return nowhp;
	}
	public void setNowhp(String nowhp) {
		this.nowhp = nowhp;
	}
	@Override
	public String toString() {
		return "Hero [name=" + name + ", agi=" + agi + ", atk=" + atk + ", hp=" + hp + ", nowhp=" + nowhp + "]";
	}


}
