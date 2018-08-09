package com.match.springmvc.data;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class StudentInfo {
	
	private String Stuid;
	private String Stuname;
	private String Studepartment;
	private String Stuclass;
//	private String Stutele;
//	private String Stucard;
	
	private Long Tpartnum; // 学生 参赛数
	
	private Double Totalcredits; // 总学分
	private BigDecimal Totalbonus; // 总奖金
	
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
	
	public String getStuclass() {
		return Stuclass;
	}
	
	public void setStuclass(String stuclass) {
		Stuclass = stuclass;
	}
	
	public String getStudepartment() {
		return Studepartment;
	}
	
	public void setStudepartment(String studepartment) {
		Studepartment = studepartment;
	}

//	public String getStutele() {
//		return Stutele;
//	}
//
//	public void setStutele(String stutele) {
//		Stutele = stutele;
//	}
//
//	public String getStucard() {
//		return Stucard;
//	}
//
//	public void setStucard(String stucard) {
//		Stucard = stucard;
//	}
	
	public Long getTpartnum() {
		return Tpartnum;
	}
	
	public void setTpartnum(Long tpartnum) {
		Tpartnum = tpartnum;
	}
	
	public Double getTotalcredits() {
		return Totalcredits;
	}
	
	public void setTotalcredits(Double totalcredits) {
		Totalcredits = totalcredits;
	}
	
	public BigDecimal getTotalbonus() {
		return Totalbonus;
	}
	
	public void setTotalbonus(BigDecimal totalbonus) {
		Totalbonus = totalbonus;
	}
	
}
