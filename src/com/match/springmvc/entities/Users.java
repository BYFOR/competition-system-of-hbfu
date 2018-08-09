package com.match.springmvc.entities;

public class Users {
	
	private String Usid;
	private String Uspwd;
	
	private String Usdepartment;
	
	public String getUsid() {
		return Usid;
	}
	
	public void setUsid(String usid) {
		Usid = usid;
	}
	
	public String getUspwd() {
		return Uspwd;
	}
	
	public void setUspwd(String uspwd) {
		Uspwd = uspwd;
	}
	
	public String getUsdepartment() {
		return Usdepartment;
	}
	
	public void setUsdepartment(String usdepartment) {
		Usdepartment = usdepartment;
	}
}
