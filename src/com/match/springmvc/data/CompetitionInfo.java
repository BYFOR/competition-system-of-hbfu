package com.match.springmvc.data;

import org.springframework.stereotype.Component;

@Component
public class CompetitionInfo {
	
	private Integer Id;
	private Integer Compid;
	private String Compname;
	private String Sponsor;
	private String Starttime;
	private String Endtime;
	private String Remark;
	
	private long TeamNum; // 参赛队伍数
	private long StudentNum; // 参赛人数
	private long TeacherNum; // 指导教师数


//	private List<Team> Teams;
//	private List<Student> Students;
//	private List<Teacher> Teachers;
	
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
	public long getTeamNum() {
		return TeamNum;
	}
	public void setTeamNum(long teamNum) {
		TeamNum = teamNum;
	}
	public long getStudentNum() {
		return StudentNum;
	}
	public void setStudentNum(long studentNum) {
		StudentNum = studentNum;
	}
	public long getTeacherNum() {
		return TeacherNum;
	}
	public void setTeacherNum(long teacherNum) {
		TeacherNum = teacherNum;
	}
//	public List<Team> getTeams() {
//		return Teams;
//	}
//	public void setTeams(List<Team> teams) {
//		Teams = teams;
//	}
//	public List<Student> getStudents() {
//		return Students;
//	}
//	public void setStudents(List<Student> students) {
//		Students = students;
//	}
//	public List<Teacher> getTeachers() {
//		return Teachers;
//	}
//	public void setTeachers(List<Teacher> teachers) {
//		Teachers = teachers;
//	}
	
	
}
