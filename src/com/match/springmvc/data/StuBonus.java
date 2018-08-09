package com.match.springmvc.data;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class StuBonus {
	
	private String Stuid;
	private String Stuname;
	private String Studepartment;
	private String Stuclass;
	
	private String Awlevel;
	private String Sclevel;
	
	private BigDecimal Bonus;
	private String Stutele;
	private String Stucard;
	
	public String getStuid() {
		return Stuid;
	}
	public void setStuid(String stuid) {
		Stuid = stuid;
	}
	public String getStuname() {
		return Stuname;
	}
	public void setStuname(String stuname) {
		Stuname = stuname;
	}
	public String getStudepartment() {
		return Studepartment;
	}
	public String getStuclass() {
		return Stuclass;
	}
	public void setStuclass(String stuclass) {
		Stuclass = stuclass;
	}
	public void setStudepartment(String studepartment) {
		Studepartment = studepartment;
	}
	public String getAwlevel() {
		return Awlevel;
	}
	public void setAwlevel(String awlevel) {
		Awlevel = awlevel;
	}
	public String getSclevel() {
		return Sclevel;
	}
	public void setSclevel(String sclevel) {
		Sclevel = sclevel;
	}
	public BigDecimal getBonus() {
		return Bonus;
	}
	public void setBonus(BigDecimal bonus) {
		Bonus = bonus;
	}
	public String getStutele() {
		return Stutele;
	}
	public void setStutele(String stutele) {
		Stutele = stutele;
	}
	public String getStucard() {
		return Stucard;
	}
	public void setStucard(String stucard) {
		Stucard = stucard;
	}
	
}
