package com.match.springmvc.data;

import org.springframework.stereotype.Component;

@Component
public class TeamInfoTr {
	
	private String Teamid; // 队伍编号
	private String Compname; // 竞赛名称
	private String Smens; // 参赛学生
	private String Tmens; // 指导教师
	private String Awlevel;	// 获奖等级
	private String Sclevel; // 校内认定
	
	private Integer Workload; // 教师工作量（个人）
	
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
	
	public Integer getWorkload() {
		return Workload;
	}
	
	public void setWorkload(Integer workload) {
		Workload = workload;
	}
	
}
