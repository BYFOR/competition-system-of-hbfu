package com.match.springmvc.entities;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

/**
 * 竞赛实体类
 * @author lenovo
 *
 */
@Component
public class Competition {
	
	private Integer Id;
	
	private Integer Compid;
	private String Compname;
	private String Sponsor;
	private String Starttime;
	private String Endtime;
	private String Remark;
	
	private Set<Team> teams = new HashSet<Team>();
	
	private Set<StuTeam> stuteam = new HashSet<StuTeam>();
	private Set<TrTeam> trteam = new HashSet<>();
	
	public Integer getId() {
		return Id;
	}
	
	public void setId(Integer id) {
		Id = id;
	}
	
	public Integer getCompid() {
		return Compid;
	}
	
	public void setCompid(Integer compid) {
		Compid = compid;
	}
	
	public String getCompname() {
		return Compname;
	}
	
	public void setCompname(String compname) {
		Compname = compname;
	}
	
	public String getSponsor() {
		return Sponsor;
	}
	
	public void setSponsor(String sponsor) {
		Sponsor = sponsor;
	}
	
	public String getStarttime() {
		return Starttime;
	}
	
	public void setStarttime(String starttime) {
		Starttime = starttime;
	}
	
	public String getEndtime() {
		return Endtime;
	}
	
	public void setEndtime(String endtime) {
		Endtime = endtime;
	}
	
	public String getRemark() {
		return Remark;
	}
	
	public void setRemark(String remark) {
		Remark = remark;
	}
	
	public Set<Team> getTeams() {
		return teams;
	}
	
	public void setTeams(Set<Team> teams) {
		this.teams = teams;
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
