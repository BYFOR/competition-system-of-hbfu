package com.match.springmvc.dao;

import java.util.List;

import com.match.springmvc.data.StuBonus;
import com.match.springmvc.data.StudentInfo;
import com.match.springmvc.data.TeamInfoStu;
import com.match.springmvc.entities.Student;

public interface IStudentDAO {
	
	// 统计 学生 的 参赛数 以及 其他信息
	public List<StudentInfo> countStuTpartNum();
	// 查询学生 参加的 所有比赛的 队伍信息(队伍编号，竞赛名称，参赛队伍，指导教师，获奖级别，认定级别，个人所获学分，奖金数)(单支队伍)
	public List<TeamInfoStu> findAllTeamByStuid(String Stuid);
	// 根据学生 学号 查询 相应 学生信息
	public List<StudentInfo> findStuById(String Stuid);
	// 根据学生 学号 查询 学生信息
	public List<Student> findStuInfoByStuid(String Stuid);
	// 根据 学生编号 更新相应 学生信息
	public boolean updateStudent(Student student);
	// 根据 竞赛编号 查询 所有 参赛学生的信息(学号，姓名，系别，获奖级别，认定级别，奖金数，电话，银行卡号)
	public List<StuBonus> findAllStuBonusById(Integer Compid);
	
	public List<StuBonus> findAllStuBonusByIdDepart(Integer Compid,String Department);
	
	// 根据 竞赛编号 查询 所有 队长的信息(学号，姓名，系别，班级，获奖级别，认定级别，队伍奖金数，电话，银行卡号)
	public List<StuBonus> findAllTeamBonusById(Integer Compid);
	
	// 根据 竞赛编号 系别 查询 系 所有 队长的奖金信息(学号，姓名，系别，班级，获奖级别，认定级别，队伍奖金数，电话，银行卡号)
	public List<StuBonus> findAllTeamBonusByIdDepart(Integer Compid,String Department);
	
	// 根据学号 删除 相应学生
	public boolean deleteStudentByStuid(String  Stuid);
}
