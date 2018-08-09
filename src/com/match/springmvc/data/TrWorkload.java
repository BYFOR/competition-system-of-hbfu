package com.match.springmvc.data;

import org.springframework.stereotype.Component;

@Component
public class TrWorkload {
	
	private String Trid;
	private String Trname;
	private String Trdepartment; // 所在单位
	private String Awlevel;
	private String Sclevel;
	
	private Long Workload;
	private String Trtele;
	
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
	public Long getWorkload() {
		return Workload;
	}
	public void setWorkload(Long workload) {
		Workload = workload;
	}
	public String getTrtele() {
		return Trtele;
	}
	public void setTrtele(String trtele) {
		Trtele = trtele;
	}
	
}
