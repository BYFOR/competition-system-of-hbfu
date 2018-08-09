package com.match.springmvc.data;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class TeamInfoStu {
	
	private String Teamid; // 队伍编号
	private String Compname; // 竞赛名称
	private String Smens; // 参赛学生
	private String Tmens; // 指导教师
	private String Awlevel;	// 获奖等级
	private String Sclevel; // 校内认定
	
	private Double Credit; // 学生学分（个人）
	private BigDecimal Bonus; // 学生奖金（个人） // 奖金类型
	
	public String getTeamid() {
		return Teamid;
	}
	public void setTeamid(String teamid) {
		Teamid = teamid;
	}
	public String getCompname() {
		return Compname;
	}
	public void setCompname(String compname) {
		Compname = compname;
	}
	public String getSmens() {
		return Smens;
	}
	public void setSmens(String smens) {
		Smens = smens;
	}
	public String getTmens() {
		return Tmens;
	}
	public void setTmens(String tmens) {
		Tmens = tmens;
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
	public Double getCredit() {
		return Credit;
	}
	public void setCredit(Double credit) {
		Credit = credit;
	}
	public BigDecimal getBonus() {
		return Bonus;
	}
	public void setBonus(BigDecimal bonus) {
		Bonus = bonus;
	}
	
}
