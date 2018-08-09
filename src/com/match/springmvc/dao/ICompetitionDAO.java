package com.match.springmvc.dao;

import java.util.List;

import com.match.springmvc.entities.Competition;
import com.match.springmvc.entities.Student;
import com.match.springmvc.entities.Teacher;
import com.match.springmvc.entities.Team;

public interface ICompetitionDAO {
	
	// 添加竞赛
	public void insertCompetition(Competition competition);
	// 根据 竞赛名称 查询竞赛信息
	public List<Competition> findCompetitionByName(String Compname);
	// 根据Id查询竞赛信息
	public List<Competition> findCompetitionByHouTianId(Integer Id);
	// 根据竞赛编号查询竞赛信息
	public List<Competition> findCompetitionById(Integer Compid);
	// 根据竞赛编号查询 相应竞赛 参赛人数
	public long findStudentNumById(Integer Compid);
	// 根据竞赛编号查询 相应竞赛 参赛队伍数
	public long findTeamNum(Integer Compid);
	// 根据竞赛编号查询 相应竞赛 指导教师数
	public long findTeacherNum(Integer Compid);
	// 以下 3个方法 用于 单个竞赛信息 查询
	// 根据竞赛编号查询 相应竞赛 的 所有队伍
	public List<Team> findTeamById(Integer Compid);
	// 根据竞赛编号查询 相应竞赛 的 所有参赛学生
	public List<Student> findStudentByCompId(Integer Compid);
	// 根据竞赛编号查询 相应竞赛 的 所有参赛学生
	public List<Teacher> findTeacherByCompId(Integer Compid);
	// 根据竞赛编号 更新 相应竞赛
	public boolean updateCompetition(Competition competition);
	// 根据竞赛编号 删除 相应竞赛
	public boolean deleteCompetitionByCompId(Integer Compid);
	
	// 根据 竞赛编号 院系名称  查询 院系参赛学生人数
	public long findStudentNumByCompidDepart(Integer Compid,String Department);
	// 根据 竞赛编号 院系名称  查询 院系 指导教师人数
	public long findTeacherNumByCompidDepart(Integer Compid,String Department);
	// 根据竞赛编号查询 相应竞赛 的 所有队伍
//	public List<Team> findTeamByIdDepart(Integer Compid,String Department);
	// 根据竞赛编号查询 相应竞赛 的 所有参赛学生
	public List<Student> findStudentByCompIdDepart(Integer Compid,String Department);
	// 根据竞赛编号查询 相应竞赛 的 所有参赛学生
	public List<Teacher> findTeacherByCompIdDepart(Integer Compid,String Department);
	
}
