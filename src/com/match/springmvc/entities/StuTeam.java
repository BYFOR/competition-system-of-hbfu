package com.match.springmvc.entities;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

/**
 * 学生-比赛-队伍
 * @author lenovo
 *
 */
@Component
public class StuTeam {
	
	private Integer SCTid;
	
	private Student student;
	private String Sname;
	private String Sdepartment;
	private String Sclass;
	
	private Competition competition;
	private String Cname;
	
	private Team team;
	
	private Double Credit; // 学生学分（个人）
	private BigDecimal Bonus; // 学生奖金（个人） // 奖金类型
	
	// 队长  队伍奖金
	private String Header; // 队长
	private BigDecimal Teambonus; // 队伍奖金
	
	
	public Integer getSCTid() {
		return SCTid;
	}
	
	public void setSCTid(Integer sCTid) {
		SCTid = sCTid;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public String getSname() {
		return Sname;
	}
	
	public void setSname(String sname) {
		Sname = sname;
	}
	
	public String getSdepartment() {
		return Sdepartment;
	}
	
	public void setSdepartment(String sdepartment) {
		Sdepartment = sdepartment;
	}
	
	public String getSclass() {
		return Sclass;
	}
	
	public void setSclass(String sclass) {
		Sclass = sclass;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
	
	public Competition getCompetition() {
		return competition;
	}
	
	public void setCompetition(Competition competition) {
		this.competition = competition;
	}
	
	public String getCname() {
		return Cname;
	}
	
	public void setCname(String cname) {
		Cname = cname;
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
	
	// 
	public String getHeader() {
		return Header;
	}
	
	public void setHeader(String header) {
		Header = header;
	}
	
	public BigDecimal getTeambonus() {
		return Teambonus;
	}
	
	public void setTeambonus(BigDecimal teambonus) {
		Teambonus = teambonus;
	}
	
}
