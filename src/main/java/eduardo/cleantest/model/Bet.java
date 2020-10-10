package eduardo.cleantest.model;

import java.io.Serializable;

public class Bet implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String rouletteId;
	private String type;
	private String betValue;
	private float money;
	
	
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public String getRouletteId() {
		return rouletteId;
	}
	public void setRouletteId(String rouletteId) {
		this.rouletteId = rouletteId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBetValue() {
		return betValue;
	}
	public void setBetValue(String betValue) {
		this.betValue = betValue;
	}
	private boolean validateType() {
		return this.type.equals("color")||this.type.equals("numero");
	}
	private boolean validateColorBet() {
		return this.betValue.equals("negro")||this.betValue.equals("rojo");
	}
	private boolean validateNumBet() {
		char[] ch=this.betValue.toCharArray();
		boolean check=ch.length<=2;
		for(int i=0;i<ch.length;i++) {
			check=check&&Character.isDigit(ch[i]);
		}
		if(check) {
			int num =Integer.parseInt(this.betValue);
			return (num<=36 && num>=0);
		}
		else {
			return false;
		}
	}
	private boolean validateMoney() {
		return this.money<=10000;
	}
	public boolean validateBet() {
		if(validateType()) {
			switch (this.type) {
			case "color":
				return validateColorBet()&&validateMoney();
			case "numero":
				return validateNumBet()&&validateMoney();
			default:
				return false;				
			}
		}
		else {
			return false;
		}
	}
	
	public void evalBet(int win) {
		if(this.type.equals("color")) {
			evalColorBet(win);
		}
		if(this.type.equals("numero")) {
			evalNumBet(win);
		}

	}
	public void evalColorBet(int win) {
		if((this.betValue.equals("negro") && win%2>0)||(this.betValue.equals("rojo") && win%2==0)) {
			this.money=(float) (this.money*1.8);
		}
		else{
			this.money=(float) (this.money*-1);
		}
	}
	public void evalNumBet(int win) {
		if(win==Integer.parseInt(this.betValue)) {
			this.money=this.money*5;
		}
		else {
			this.money=this.money*-1;
		}
	}
	
}