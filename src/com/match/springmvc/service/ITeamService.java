package com.match.springmvc.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.match.springmvc.entities.Team;

public interface ITeamService {
	
	// 批量导入数据
	public boolean insertBatchTeamInfo(String filename,MultipartFile file);
	// 添加 队伍（注意：竞赛编号 的 添加）
	public void insertTeam(Team team);
	// 根据队伍编号 查找队伍
	public List<Team> findTeamByTeamid(String Teamid);
	// 删除 队伍
	public boolean deleteTeamByTeamid(String Teamid);
	// 更新 队伍
	public boolean updateTeamByTeamid(Team team);
}
