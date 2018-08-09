package com.match.springmvc.entities;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

/**
 * 队伍实体类
 * @author lenovo
 *
 */
@Component
public class Team {
	
	private String Teamid;
	private String Smens;
	private String Tmens;
	private String Awlevel;
	private String Sclevel;

//	private Float Credit; // 学生学分
//	private Integer Bonus; // 学生奖金
//	private Integer Workload; // 教师工作量
	
	private Competition competition; // 双向1-N关联
	private String Tmcname; // 参赛队伍 参加的 比赛名称
	
	private Set<StuTeam> stuteam = new HashSet<StuTeam>();
	private Set<TrTeam> trteam = new HashSet<>();
	
	public String getTeamid() {
		return Teamid;
	}
	public void setTeamid(String teamid) {
		Teamid = teamid;
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
	//	public Float getCredit() {
//		return Credit;
//	}
//	public void setCredit(Float credit) {
//		Credit = credit;
//	}
//	public Integer getBonus() {
//		return Bonus;
//	}
//	public void setBonus(Integer bonus) {
//		Bonus = bonus;
//	}
//	public Integer getWorkload() {
//		return Workload;
//	}
//	public void setWorkload(Integer workload) {
//		Workload = workload;
//	}
	public Competition getCompetition() {
		return competition;
	}
	public void setCompetition(Competition competition) {
		this.competition = competition;
	}
	
	public String getTmcname() {
		return Tmcname;
	}
	public void setTmcname(String tmcname) {
		Tmcname = tmcname;
	}
	
	public Set<StuTeam> getStuteam() {
		return stuteam;
	}
	public void setStuteam(Set<StuTeam> stuteam) {
		this.stuteam = stuteam;
	}
	public Set<TrTeam> getTrteam() {
		return trteam;
	}
	public void setTrteam(Set<TrTeam> trteam) {
		this.trteam = trteam;
	}
	
	
}
