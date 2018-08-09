package com.match.springmvc.entities;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

/**
 * 教师实体类
 * @author lenovo
 *
 */
@Component
public class Teacher {
	
	private String Trid;
	private String Trname;
	private String Trdepartment;
	private String Trtele;
	
	private Set<TrTeam> trteam = new HashSet<>();
	
	public String getTrid() {
		return Trid;
	}
	
	public void setTrid(String trid) {
		Trid = trid;
	}
	
	public String getTrname() {
		return Trname;
	}
	
	public void setTrname(String trname) {
		Trname = trname;
	}
	
	public String getTrdepartment() {
		return Trdepartment;
	}
	
	public void setTrdepartment(String trdepartment) {
		Trdepartment = trdepartment;
	}
	
	public Set<TrTeam> getTrteam() {
		return trteam;
	}
	
	public void setTrteam(Set<TrTeam> trteam) {
		this.trteam = trteam;
	}
	
	public String getTrtele() {
		return Trtele;
	}
	
	public void setTrtele(String trtele) {
		Trtele = trtele;
	}
	
}
