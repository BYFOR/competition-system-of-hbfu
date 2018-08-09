package com.match.springmvc.entities;

import org.springframework.stereotype.Component;

/**
 * 教师-比赛-队伍
 * @author lenovo
 *
 */
@Component
public class TrTeam {
	
	private Integer TrCTid;
	
	private Teacher teacher;
	private String Tname;
	private String Tdepartment;
	
	private Competition competition;
	private String Coname;
	
	private Team team;
	
	private Integer Workload; // 教师工作量（个人）
	
	public Integer getTrCTid() {
		return TrCTid;
	}
	
	public void setTrCTid(Integer trCTid) {
		TrCTid = trCTid;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public String getTname() {
		return Tname;
	}
	
	public String getTdepartment() {
		return Tdepartment;
	}
	
	public void setTdepartment(String tdepartment) {
		Tdepartment = tdepartment;
	}
	
	public void setTname(String tname) {
		Tname = tname;
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
	
	public String getConame() {
		return Coname;
	}
	
	public void setConame(String coname) {
		Coname = coname;
	}
	
	public Integer getWorkload() {
		return Workload;
	}
	
	public void setWorkload(Integer workload) {
		Workload = workload;
	}
	
}
