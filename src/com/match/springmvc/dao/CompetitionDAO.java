package com.match.springmvc.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.match.springmvc.entities.Competition;
import com.match.springmvc.entities.Student;
import com.match.springmvc.entities.Teacher;
import com.match.springmvc.entities.Team;

@Repository
public class CompetitionDAO implements ICompetitionDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	// 获取当前Session
	private Session getSession() {
		Session session;
		try {
			System.out.println("sessionFactory:"+sessionFactory);
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
			session = sessionFactory.openSession();
			System.out.println("openSession:");
		}
		return session;
	}
	
	@Override
	public void insertCompetition(Competition competition) {
		// TODO Auto-generated method stub
		System.out.println("DAO：添加竞赛(事务)"+sessionFactory.getCurrentSession());
		sessionFactory.getCurrentSession().save(competition);
	}
	
	@Override
	public List<Competition> findCompetitionByName(String Compname) {
		// TODO Auto-generated method stub
		List<Competition> list=null;
		String hql="from Competition c where c.Compname like ?";
		System.out.println("DAO：查询竞赛(名称)(事务)"+sessionFactory.getCurrentSession());
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, "%"+Compname+"%");
		list=query.list();
		
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
	@Override
	public List<Competition> findCompetitionById(Integer Compid) {
		// TODO Auto-generated method stub
		List<Competition> list=null;
		String hql="from Competition c where c.Compid=?";
		System.out.println("DAO：查询竞赛(ID)(事务)"+sessionFactory.getCurrentSession());
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, Compid);
		list=query.list();
		
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
	@Override
	public long findStudentNumById(Integer Compid) {
		// TODO Auto-generated method stub
//		String hql="select count(*) from StuTeam st left join st.competition stc where stc.Compid=?";
		String hql="select count(distinct st.student.Stuid) from StuTeam st left join st.competition stc where stc.Compid=?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, Compid);
		long count = ((Number)query.uniqueResult()).intValue();
		
		System.out.println("查询参赛人数："+count);
		return count;
	}
	
	@Override
	public long findTeamNum(Integer Compid) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Team t left join t.competition tc where tc.Compid=?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, Compid);
		long count = ((Number)query.uniqueResult()).intValue();
		
		System.out.println("查询参赛队伍数："+count);
		return count;
	}
	
	@Override
	public long findTeacherNum(Integer Compid) {
		// TODO Auto-generated method stub
//		String hql="select count(*) from TrTeam tt left join tt.competition ttc where ttc.Compid=?";
		String hql="select ttc.Compid,count(distinct ttt.Trid) from TrTeam tt left join tt.competition ttc left join tt.teacher ttt group by ttc.Compid";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
//		query.setInteger(0, Compid);
//		long count = ((Number)query.uniqueResult()).intValue();
		long count = 0;
		
		List<Object[]> list = query.list();
		for(int i=0;i<list.size();i++) {
			Object[] obs = list.get(i);
			if((Integer)obs[0]==Compid) {
				count = (long)obs[1];
			}
		}
		System.out.println("查询指导教师数："+count);
		return count;
	}
	
	@Override
	public List<Team> findTeamById(Integer Compid) {
		// TODO Auto-generated method stub
		List<Team> list=null;
		String hql="select t from Team t left join t.competition tc where tc.Compid=?";
		System.out.println("DAO:查询所有队伍(ID)(事务)"+sessionFactory.getCurrentSession());
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, Compid);
		list=query.list();
		// 添加 select t 问题解决
		
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
	@Override
	public List<Student> findStudentByCompId(Integer Compid) {
		// TODO Auto-generated method stub
		List<Student> list=null;
		String hql="select distinct s from Student s left join s.stuteam sst left join sst.competition sstc where sstc.Compid=? ";
		System.out.println("DAO:查询所有学生(ID)(相应竞赛)(事务)"+sessionFactory.getCurrentSession());
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, Compid);
		list=query.list();
		// 添加 select t 问题解决
//		System.out.println("DAO:查询所有学生(相应竞赛)"+list.get(0).getStuid());
		
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
	@Override
	public List<Teacher> findTeacherByCompId(Integer Compid) {
		// TODO Auto-generated method stub
		List<Teacher> list=null;
		String hql="select distinct t from Teacher t left join t.trteam ttr left join ttr.competition ttrc where ttrc.Compid=? ";
		System.out.println("DAO:查询所有教师(ID)(相应竞赛)(事务)"+sessionFactory.getCurrentSession());
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, Compid);
		list=query.list();
		// 添加 select t 问题解决
//		System.out.println("DAO:查询所有教师(相应竞赛)"+list.get(0).getTrid());
		
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
	@Override
	public boolean updateCompetition(Competition competition) {
		// TODO Auto-generated method stub
		String hql="update Competition set Compid=?,Compname=?,Sponsor=?,Starttime=?,Endtime=?,Remark=? where Id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, competition.getCompid());
		query.setString(1,competition.getCompname());
		query.setString(2,competition.getSponsor());
		query.setString(3,competition.getStarttime());
		query.setString(4,competition.getEndtime());
		query.setString(5,competition.getRemark());
		query.setInteger(6,competition.getId());
		
		if(query.executeUpdate()>0) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean deleteCompetitionByCompId(Integer Compid) {
		// TODO Auto-generated method stub
		String hql="delete from Competition where Compid=? ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0,Compid);
		if(query.executeUpdate()>0) {
			return true;
		}
		return false;
	}
	
	@Override
	public List<Competition> findCompetitionByHouTianId(Integer Id) {
		// TODO Auto-generated method stub
		List<Competition> list=null;
		String hql="from Competition c where c.Id=?";
		System.out.println("DAO：查询竞赛(ID)(事务)"+sessionFactory.getCurrentSession());
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, Id);
		list=query.list();
		
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
	//----------院系----------
	
	// 根据 竞赛编号 院系名称  查询 院系参赛学生人数  // 有待改正
	@Override
	public long findStudentNumByCompidDepart(Integer Compid, String Department) {
		// TODO Auto-generated method stub
//		String hql="select count(*) from StuTeam st left join st.competition stc where stc.Compid=? and st.Sdepartment=?";
		String hql="select count(distinct st.student.Stuid) from StuTeam st left join st.competition stc where stc.Compid=? and st.Sdepartment=?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, Compid);
		query.setString(1, Department);
		long count = ((Number)query.uniqueResult()).intValue();
		
		System.out.println("查询系参赛人数："+count);
		return count;
	}
	// 根据 竞赛编号 院系名称  查询 院系 指导教师人数
	@Override
	public long findTeacherNumByCompidDepart(Integer Compid, String Department) {
		// TODO Auto-generated method stub
//		String hql="select count(*) from TrTeam tt left join tt.competition ttc where ttc.Compid=?";
		String hql="select count(distinct ttt.Trid) from TrTeam tt left join tt.competition ttc left join tt.teacher ttt where ttc.Compid=? and tt.Tdepartment=?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, Compid);
		query.setString(1, Department);
		long count = ((Number)query.uniqueResult()).intValue();
		
		System.out.println("查询系指导教师数："+count);
		return count;
	}

//	@Override
//	public List<Team> findTeamByIdDepart(Integer Compid, String Department) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
	public List<Student> findStudentByCompIdDepart(Integer Compid, String Department) {
		// TODO Auto-generated method stub
		List<Student> list=null;
		String hql="select distinct s from Student s left join s.stuteam sst left join sst.competition sstc where sstc.Compid=? and sst.Sdepartment=? ";
		System.out.println("DAO:查询所有学生(ID)(相应竞赛)(事务)"+sessionFactory.getCurrentSession());
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, Compid);
		query.setString(1, Department);
		list=query.list();
		// 添加 select t 问题解决
//		System.out.println("DAO:查询所有学生(相应竞赛)"+list.get(0).getStuid());
		
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
	@Override
	public List<Teacher> findTeacherByCompIdDepart(Integer Compid, String Department) {
		// TODO Auto-generated method stub
		List<Teacher> list=null;
		String hql="select distinct t from Teacher t left join t.trteam ttr left join ttr.competition ttrc where ttrc.Compid=? and ttr.Tdepartment=? ";
//		System.out.println("DAO:查询所有教师(ID)(相应竞赛)(事务)"+sessionFactory.getCurrentSession());
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, Compid);
		query.setString(1, Department);
		
		list=query.list();
		// 添加 select t 问题解决
//		System.out.println("DAO:查询所有教师(相应竞赛)"+list.get(0).getTrid());
		
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
}
