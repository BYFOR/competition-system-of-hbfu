package com.match.springmvc.entities;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

/**
 * 学生实体类
 * @author lenovo
 *
 */
@Component
public class Student {
	
	private String Stuid;
	private String Stuname;
	private String Studepartment;
	
	private String Stuclass;
	
	private String Stutele;
	private String Stucard;
	
	private Set<StuTeam> stuteam = new HashSet<StuTeam>();
	
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
	
	public void setStudepartment(String studepartment) {
		Studepartment = studepartment;
	}
	
	public String getStuclass() {
		return Stuclass;
	}
	
	public void setStuclass(String stuclass) {
		Stuclass = stuclass;
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
	
	public Set<StuTeam> getStuteam() {
		return stuteam;
	}
	
	public void setStuteam(Set<StuTeam> stuteam) {
		this.stuteam = stuteam;
	}
	
	
}
