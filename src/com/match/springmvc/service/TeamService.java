package com.match.springmvc.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.match.springmvc.dao.IBonusscaleDAO;
import com.match.springmvc.dao.ICompetitionDAO;
import com.match.springmvc.dao.IJudgcriDAO;
import com.match.springmvc.dao.IStudentDAO;
import com.match.springmvc.dao.ITeacherDAO;
import com.match.springmvc.dao.ITeamDAO;
import com.match.springmvc.entities.Bonusscale;
import com.match.springmvc.entities.Competition;
import com.match.springmvc.entities.Judgcri;
import com.match.springmvc.entities.StuTeam;
import com.match.springmvc.entities.Student;
import com.match.springmvc.entities.Teacher;
import com.match.springmvc.entities.Team;
import com.match.springmvc.entities.TrTeam;
import com.match.springmvc.exception.InsertException;
import com.match.springmvc.tools.ReadExcel;

@Service
public class TeamService implements ITeamService {
	
	@Autowired
	private ICompetitionDAO competitionDAO;
	@Autowired
	private IStudentDAO studentDAO;
	@Autowired
	private ITeacherDAO teacherDAO;
	@Autowired
	private ITeamDAO teamDAO;
	
	// 评判标准
	@Autowired
	private IJudgcriDAO judgcriDAO;
	// 奖金比例
	@Autowired
	private IBonusscaleDAO bonusscaleDAO;
	
	// 批量导入 参赛信息数据
	@Override
	public boolean insertBatchTeamInfo(String filename, MultipartFile file) {
		// TODO Auto-generated method stub
		boolean flag = false;
		boolean f0 = false,f1 = false,f2 = false;
		Integer Compid = 0;
		
		// 开始 ---------- 获取评判标准----------获取奖金比例
		List<Judgcri> judgcrilist = judgcriDAO.findAllJudgcri();
		List<Bonusscale> bonusscalelist = bonusscaleDAO.findAllBonusscale();
		// 结束 ----------
		
		// 创建处理Excel
		ReadExcel readExcel = new ReadExcel();
		// 解析Excel 获取 队伍信息集合 学生队伍信息集合 教师队伍信息集合
		List<Team> teamlist = readExcel.getTeamInfo(filename, file);
		
		if(teamlist!=null) {
			f0 = true;
		}
		int i=0;
		for(Team team:teamlist) {
			Compid = team.getCompetition().getCompid();
			System.out.println("批量处理数据(队伍)："+team.getTeamid()+"--- "+team.getCompetition().getCompid());
			System.out.println("competitionDAO:"+competitionDAO);
			System.out.println(teamlist.size()+"---------------------------------------"+team.getStuteam());
			List<Competition> complist = competitionDAO.findCompetitionById(team.getCompetition().getCompid());
			
			if(complist==null) { // 没有该竞赛 返回时为空
				System.out.println("---解析队伍找不到竞赛---");
//				return false;
				throw new InsertException();
			}
			System.out.println("测试---"+complist.get(0).getCompid());
			team.setCompetition(complist.get(0));
			// 开始 ---------- 获奖等级和校内认定等级的对应
			for(Judgcri judgcri:judgcrilist) {
				if(team.getAwlevel().equals(judgcri.getAward())) { // 队伍的获奖等级与评判表的获奖等级匹配
					team.setSclevel(judgcri.getAffirmation());
					break;
				}
			}
			if(team.getSclevel()==null) {
				team.setSclevel("");
			}
			
			// 结束 ---------- 获奖等级和校内认定等级的对应
			List<Team> testhaveteam = teamDAO.findTeamByTeamid(team.getTeamid());
			if(testhaveteam!=null) { // 该队伍已经在表当中
//				return false;
				throw new InsertException();
			}
			teamDAO.insertTeam(team); // 注意！
			i+=1;
		}
		
		// 解析Excel 教师队伍信息集合
		List<TrTeam> trteamlist = readExcel.getTrTeamInfo(filename, file);
		if(trteamlist!=null) {
			f1 = true;
		}
		for(TrTeam trteam:trteamlist) {
			List<Competition> complist = competitionDAO.findCompetitionById(trteam.getCompetition().getCompid());
			if(complist ==null) { // 查找不到该竞赛
				System.out.println("-----解析教师队伍集合时查找不到竞赛！");
//				return false;
				throw new InsertException();
			}
			trteam.setCompetition(complist.get(0));
			List<Teacher> trlist = teacherDAO.findTrInfoByTrid(trteam.getTeacher().getTrid());
			if(trlist==null)  // 查找不到该教师
			{
				System.out.println("-----解析教师队伍集合时查找不到教师！");
//				return false;
				throw new InsertException();
			}
			trteam.setTeacher(trlist.get(0));
			List<Team> tmlist = teamDAO.findTeamByTeamid(trteam.getTeam().getTeamid());
			if(tmlist == null) { // 查找不到该队伍
				System.out.println("-----解析教师队伍集合时查找不到队伍！");
//				return false;
				throw new InsertException();
			}
			trteam.setTeam(tmlist.get(0));
			teamDAO.insertTrTeam(trteam);
		}
		
		// 解析Excel 学生队伍信息集合
		List<StuTeam> stuteamlist = readExcel.getStuTeamInfo(filename, file);
		if(stuteamlist!=null) {
			f2 = true;
		}
		for(StuTeam stuteam:stuteamlist) {
			System.out.println(stuteam.getStudent().getStuid()+"--------------------------------");
			List<Competition> complist = competitionDAO.findCompetitionById(stuteam.getCompetition().getCompid());
			if(complist == null) { // 查找不到该竞赛
				System.out.println("-----解析学生队伍集合时查找不到竞赛！");
//				return false;
				throw new InsertException();
			}
			stuteam.setCompetition(complist.get(0));
			List<Student> stulist = studentDAO.findStuInfoByStuid(stuteam.getStudent().getStuid());
			if(stulist == null) { // 查找不到该学生
				System.out.println("-----解析学生队伍集合时查找不到学生！");
//				return false;
				throw new InsertException(); // 抛出InsertException 重定向到 导入管理页
			}
			stuteam.setStudent(stulist.get(0));
			List<Team> tmlist = teamDAO.findTeamByTeamid(stuteam.getTeam().getTeamid());
			if(tmlist == null) { // 查找不到该队伍
				System.out.println("-----解析学生队伍集合时查找不到队伍！");
//				return false;
				throw new InsertException();
			}
			stuteam.setTeam(tmlist.get(0));
			for(Judgcri judgcri:judgcrilist) {
				if(stuteam.getTeam().getSclevel().equals(judgcri.getAffirmation())) {
					stuteam.setCredit(judgcri.getCricredit());
					break;
				}
				//------
				else if(stuteam.getTeam().getSclevel().equals("")) {
					stuteam.setCredit((double)0);
				}
				//------
			}
			if(stuteam.getTeam().getSclevel().equals("")) {
				stuteam.setCredit((double)0);
			}
			// 结束
			teamDAO.insertStuTeam(stuteam);
		}
		
		// 调整
		double bonus = 0;
		if(f0==true&&f1==true&&f2==true) {
			System.out.println("          "+Compid);
			List<StuTeam> stlist = teamDAO.findAllStuTeamById(Compid);
			for(StuTeam stuteam : stlist) {
				long teamstunum =teamDAO.findTeamStuNum(stuteam.getTeam().getTeamid());  // 统计该队伍的 参赛学生数
				
				for(Judgcri judgcri:judgcrilist) {
					if(stuteam.getTeam().getSclevel().equals(judgcri.getAffirmation())) {
						bonus = judgcri.getCribonus();
						break;
					}
					if(stuteam.getTeam().getSclevel().equals("")) {
						bonus = 0;
					}
				}
				if(stuteam.getTeam().getSclevel().equals("")) {
					bonus = 0;
				}
				// 开始----------判断 参赛学生数-----------奖金比例
				for(Bonusscale bonusscale:bonusscalelist) {
					if(bonusscale.getMinnumber() < teamstunum && teamstunum <= bonusscale.getMaxnumber())
					{
						bonus = bonusscale.getScale() * bonus;
						break;
					}
				}
				// 结束----------
				
				// 开始  // 队长 设置 队伍奖金
				if(stuteam.getHeader().equals("队长")) {
					stuteam.setTeambonus(BigDecimal.valueOf(bonus));
				}
				if(stuteam.getHeader().equals("队员")) {
					// 什么都不做
				}
				// 结束
				bonus = bonus / teamstunum;
				System.out.println("**********个人奖金**********："+bonus);
				stuteam.setBonus(BigDecimal.valueOf(bonus));
			}
			// 计算 教师工作量
			double workload = 0;
			List<TrTeam> ttlist = teamDAO.findAllTrTeamById(Compid);
			for(TrTeam trteam : ttlist) {
				long teamtrnum =teamDAO.findTeamTrNum(trteam.getTeam().getTeamid());  // 统计该队伍的指导教师数
				
				
				for(Judgcri judgcri:judgcrilist) {
					if(trteam.getTeam().getSclevel().equals(judgcri.getAffirmation())) {
						workload = judgcri.getCriworkload();
						break;
					}
				}
				if(trteam.getTeam().getSclevel() == null) { // 其他(判定标准中没有相应等级的)
					workload = 0;
				}
				
				// 结束 ---------- 判定 降级后 教师的工作量的问题
				workload = workload / teamtrnum;
				System.out.println("**********单名教师工作量**********："+workload);
				trteam.setWorkload((int) (workload*1+0.5)); // 教师 工作量 四舍五入 问题
			}
			flag = true;
		}
		return flag;
	}
	
	// 添加 队伍
	@Override
	public void insertTeam(Team team) {
		// TODO Auto-generated method stub
		teamDAO.insertTeam(team);
	}
	
	// 根据 队伍编号 查找队伍
	@Override
	public List<Team> findTeamByTeamid(String Teamid) {
		// TODO Auto-generated method stub
		return teamDAO.findTeamByTeamid(Teamid);
	}
	
	// 根据 队伍编号 删除队伍
	@Override
	public boolean deleteTeamByTeamid(String Teamid) {
		// TODO Auto-generated method stub
		return teamDAO.deleteTeamByTeamid(Teamid);
	}
	
	@Override
	public boolean updateTeamByTeamid(Team team) {
		// TODO Auto-generated method stub
		
		double credit=0,bonus=0,workload=0;
		double singleb=0;
		int singlew=0;
		// 开始 ---------- 获取评判标准----------获取奖金比例
		List<Judgcri> judgcrilist = judgcriDAO.findAllJudgcri();
		List<Bonusscale> bonusscalelist = bonusscaleDAO.findAllBonusscale();
		// 结束 ----------
		
		// 开始 ---------- 获奖等级和校内认定等级的对应
		for(Judgcri judgcri:judgcrilist) {
			if(team.getAwlevel().equals(judgcri.getAward())) { // 队伍的获奖等级与评判表的获奖等级匹配
				team.setSclevel(judgcri.getAffirmation());
				credit = judgcri.getCricredit(); // 个人学分
				bonus = judgcri.getCribonus(); // 队伍奖金
				workload = judgcri.getCriworkload(); // 队伍教师 工作量
				break;
			}
		}
		System.out.println("测试：TeamService：获奖等级："+team.getAwlevel()+"校内认定："+team.getSclevel());
		if(team.getSclevel()==null) {
			team.setSclevel("");
			credit = 0; // 个人学分
			bonus = 0; // 队伍奖金
			workload = 0;
		}
		
		// 开始----------判断 参赛学生数-----------奖金比例
		long teamstunum =teamDAO.findTeamStuNum(team.getTeamid());  // 统计该队伍的 参赛学生数
		long teamtrnum =teamDAO.findTeamTrNum(team.getTeamid());  // 统计该队伍的指导教师数
		
		for(Bonusscale bonusscale:bonusscalelist) {
			if(bonusscale.getMinnumber() < teamstunum && teamstunum <= bonusscale.getMaxnumber())
			{
				bonus = bonusscale.getScale() * bonus;
				break;
			}
		}
		singleb = bonus / teamstunum;
		workload = workload / teamtrnum;
		singlew = (int)(workload*1+0.5);
		// 结束----------
		
		// 结束 ---------- 获奖等级和校内认定等级的对应
		
		return teamDAO.updateTeamAwlevelByTeamid(team,credit,bonus,singleb,singlew);
	}
}
