package com.match.springmvc.data;

import org.springframework.stereotype.Component;

@Component
public class TeacherInfo {
	
	private String Trid; // 工号
	private String Trname; // 教师姓名
	private String Trdepartment; // 所在部门
	
	private Long TrTpartnum; // 教师 指导 的 竞赛数目
	
	private Long Totalworkload; // 教师总工作量
	
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
	
	public Long getTrTpartnum() {
		return TrTpartnum;
	}
	
	public void setTrTpartnum(Long trTpartnum) {
		TrTpartnum = trTpartnum;
	}
	
	public Long getTotalworkload() {
		return Totalworkload;
	}
	
	public void setTotalworkload(Long totalworkload) {
		Totalworkload = totalworkload;
	}
	
}
