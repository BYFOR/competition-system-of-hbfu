package com.match.springmvc.service;

import java.util.List;

import com.match.springmvc.data.TeacherInfo;
import com.match.springmvc.data.TeamInfoTr;
import com.match.springmvc.data.TrWorkload;
import com.match.springmvc.entities.Teacher;

public interface ITeacherService {
	
	// 统计 教师 的 指导竞赛数  以及 其他信息
	public List<TeacherInfo> countTrTpartNum();
	// 查询教师  指导的 所有比赛的 队伍信息(队伍编号，竞赛名称，参赛队伍，指导教师，获奖级别，认定级别，教师工作量(个人))
	public List<TeamInfoTr> findAllTeamByTrid(String Trid);
	// 根据教师 工号 查询 相应 信息(包含统计 指导竞赛数目)
	public List<TeacherInfo> findTrById(String Trid);
	// 根据 教师 工号 查询 相应 教师基本信息
	public List<Teacher> findTrInfoByTrid(String Trid);
	// 更新 教师基本信息
	public boolean updateTeacher(Teacher teacher);
	// 根据 竞赛编号 查询 所有 指导教师的信息(工号，姓名，所在单位，获奖级别，认定级别，工作量，电话)
	public List<TrWorkload> findAllTrWorkloadById(Integer Compid);
	
	public List<TrWorkload> findAllTrWorkloadByIdDepart(Integer Compid, String Department);
	
	// 根据竞赛编号 删除 相应竞赛
	public boolean deleteTeacherByTrid(String  Trid);
}
