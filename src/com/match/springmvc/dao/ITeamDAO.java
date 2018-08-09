package com.match.springmvc.dao;

import java.util.List;

import com.match.springmvc.entities.StuTeam;
import com.match.springmvc.entities.Team;
import com.match.springmvc.entities.TrTeam;

public interface ITeamDAO {
	
	// 添加队伍
	public void insertTeam(Team team);
	// 根据队伍编号 查找队伍
	public List<Team> findTeamByTeamid(String Teamid);
	// 批量添加 学生队伍 信息
	public void insertStuTeam(StuTeam stuteam);
	// 批量添加 教师队伍 信息
	public void insertTrTeam(TrTeam trteam);
	// 根据 队伍编号 删除 相应队伍
	public boolean deleteTeamByTeamid(String Teamid);
	// 根据 队伍编号 统计 队伍中的 参赛学生数
	public long findTeamStuNum(String Teamid);
	// 根据 队伍编号 统计 队伍中的 指导教师数
	public long findTeamTrNum(String Teamid);
	// 根据 竞赛编号 查出 所有 学生队伍（StuTeam）
	public List<StuTeam> findAllStuTeamById(Integer Compid);
	// 根据 竞赛编号 查出 所有 教师队伍（TrTeam）
	public List<TrTeam> findAllTrTeamById(Integer Compid);
	
	// 根据 队伍编号 更新队伍 获奖级别
	public boolean updateTeamAwlevelByTeamid(Team team,Double credit,Double bonus,Double singleb,Integer singlew);
	
	// 根据 队伍编号 查询 所有 学生队伍集合
	public List<StuTeam> findStuTeamByTeamid(String Teamid);
	// 根据 队伍编号 查询 所有 教师指导队伍集合
	public List<TrTeam> findTrTeamByTeamid(String Teamid);
	

	
}
