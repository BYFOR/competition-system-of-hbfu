package com.match.springmvc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.match.springmvc.entities.StuTeam;
import com.match.springmvc.entities.Team;
import com.match.springmvc.entities.TrTeam;

@Repository
public class TeamDAO implements ITeamDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insertTeam(Team team) {
		// TODO Auto-generated method stub
		System.out.println("DAO Excel测试"+team.getTeamid()+" "+team.getCompetition().getCompid());
		sessionFactory.getCurrentSession().save(team);
	}
	
	@Override
	public List<Team> findTeamByTeamid(String Teamid) {
		// TODO Auto-generated method stub
		List<Team> list=null;
		String hql="from Team tm where tm.Teamid=?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, Teamid);
		list=query.list();
		
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
	@Override
	public void insertTrTeam(TrTeam trteam) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(trteam);
	}
	
	@Override
	public void insertStuTeam(StuTeam stuteam) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(stuteam);
	}
	
	@Override
	public boolean deleteTeamByTeamid(String Teamid) {
		// TODO Auto-generated method stub
		String hql="delete from Team where Teamid=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0,Teamid);
		if(query.executeUpdate()>0) {
			return true;
		}
		return false;
	}
	
	// 根据 队伍编号 统计 队伍中的 参赛学生数
	@Override
	public long findTeamStuNum(String Teamid) {
		// TODO Auto-generated method stub
		String hql="select count(*) from StuTeam st left join st.team stt where stt.Teamid=?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, Teamid);
		long count = ((Number)query.uniqueResult()).intValue();
		
		System.out.println("查询队伍中  参赛学生数："+count);
		return count;
	}
	
	// 根据 队伍编号 统计 队伍中的 指导教师数
	public long findTeamTrNum(String Teamid) {
		String hql="select count(*) from TrTeam tt left join tt.team ttt where ttt.Teamid=?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, Teamid);
		long count = ((Number)query.uniqueResult()).intValue();
		
		System.out.println("查询队伍中  指导教师数："+count);
		return count;
	}
	
	// 根据 竞赛编号 查出 所有 学生队伍（StuTeam）
	@Override
	public List<StuTeam> findAllStuTeamById(Integer Compid) {
		// TODO Auto-generated method stub
		String hql="select st from StuTeam st left join st.competition stc where stc.Compid=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0,Compid);
		List<StuTeam> list = query.list();
		
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
	// 根据 竞赛编号 查出 所有 教师队伍（TrTeam）
	@Override
	public List<TrTeam> findAllTrTeamById(Integer Compid) {
		// TODO Auto-generated method stub
		String hql="select tt from TrTeam tt left join tt.competition ttc where ttc.Compid=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0,Compid);
		List<TrTeam> list = query.list();
		
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
	@Override
	public List<StuTeam> findStuTeamByTeamid(String Teamid) {
		// TODO Auto-generated method stub
		String hql="select st from StuTeam st where st.team.Teamid=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, Teamid);
		List<StuTeam> list = query.list();
		
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
	@Override
	public List<TrTeam> findTrTeamByTeamid(String Teamid) {
		// TODO Auto-generated method stub
		String hql="select tt from TrTeam tt where tt.team.Teamid=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, Teamid);
		List<TrTeam> list = query.list();
		
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
	@Override
	public boolean updateTeamAwlevelByTeamid(Team team,Double credit,Double bonus,Double singleb,Integer singlew) {
		// TODO Auto-generated method stub
		boolean f1=false,f2=false,f3=false,f4=false;
		String hql0 = "update Team t set t.Awlevel=?,t.Sclevel=? where t.Teamid=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql0);
		
		System.out.println("测试：TeamDAO：获奖级别："+team.getAwlevel()+"认定级别："+team.getSclevel());
		query.setString(0, team.getAwlevel());
		query.setString(1, team.getSclevel());
		query.setString(2,team.getTeamid());
		
		if(query.executeUpdate()>0) {
			f1 = true;
		}
		
		// 开始----------
		String hql1 = "update StuTeam st set st.Credit=?,st.Bonus=? where st.team.Teamid=?";
		Query query1 = sessionFactory.getCurrentSession().createQuery(hql1);
		
		query1.setDouble(0, credit);
		query1.setDouble(1, singleb);
		query1.setString(2,team.getTeamid());
		
		if(query1.executeUpdate()>0) {
			f2 = true;
		}
		
		String hql2 = "update StuTeam st set st.Teambonus=? where st.team.Teamid=? and st.Header=?";
		Query query2 = sessionFactory.getCurrentSession().createQuery(hql2);
		
		query2.setDouble(0, bonus);
		query2.setString(1,team.getTeamid());
		query2.setString(2,"队长");
		
		if(query2.executeUpdate()>0) {
			f3 = true;
		}
		
		String hql3 = "update TrTeam tt set tt.Workload=? where tt.team.Teamid=?";
		Query query3 = sessionFactory.getCurrentSession().createQuery(hql3);
		
		query3.setInteger(0, singlew);
		query3.setString(1,team.getTeamid());
		
		if(query3.executeUpdate()>0) {
			f4 = true;
		}
		// 结束
		if(f1==true&&f2==true&&f3==true&&f4==true) {
			return true;
		}else {
			return false;
		}
	}
}
