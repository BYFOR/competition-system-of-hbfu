package com.match.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.match.springmvc.data.TeacherInfo;
import com.match.springmvc.data.TeamInfoTr;
import com.match.springmvc.data.TrWorkload;
import com.match.springmvc.entities.Teacher;

@Repository
public class TeacherDAO implements ITeacherDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<TeacherInfo> countTrTpartNum() {
		// TODO Auto-generated method stub
		String hql="select ttt.Trid,tt.Tname,tt.Tdepartment,count(ttc),sum(tt.Workload) from TrTeam tt left join tt.competition ttc left join tt.teacher ttt group by ttt.Trid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Object[]> list = query.list();
		List<TeacherInfo> tinfolist = new ArrayList<TeacherInfo>();
		
		for(int i=0;i<list.size();i++) {
			Object[] obs = list.get(i);
			TeacherInfo trinfo = new TeacherInfo();
			trinfo.setTrid((String)obs[0]);
			trinfo.setTrname((String)obs[1]);
			trinfo.setTrdepartment((String)obs[2]);
			trinfo.setTrTpartnum((Long)obs[3]);
			trinfo.setTotalworkload((Long) obs[4]);
			tinfolist.add(trinfo);
		}
		return tinfolist;
	}
	
	@Override
	public List<TeamInfoTr> findAllTeamByTrid(String Trid) {
		// TODO Auto-generated method stub
		String hql="select  t.Teamid,t.Tmcname,t.Smens,t.Tmens,t.Awlevel,t.Sclevel,ttt.Workload from Team t left join t.trteam ttt left join ttt.teacher tttt where tttt.Trid=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0,Trid);
		List<Object[]> list = query.list();
		List<TeamInfoTr> teaminfotrlist = new ArrayList<TeamInfoTr>();
		
		for(int i=0;i<list.size();i++) {
			Object[] obs = list.get(i);
			TeamInfoTr teaminfotr = new TeamInfoTr();
			teaminfotr.setTeamid((String) obs[0]);
			teaminfotr.setCompname((String) obs[1]);
			teaminfotr.setSmens((String) obs[2]);
			teaminfotr.setTmens((String) obs[3]);
			teaminfotr.setAwlevel((String) obs[4]);
			teaminfotr.setSclevel((String) obs[5]);
			teaminfotr.setWorkload((Integer) obs[6]);
			teaminfotrlist.add(teaminfotr);
		}
		if(teaminfotrlist.size()!=0) {
			return teaminfotrlist;
		}
		return null;
	}
	
	@Override
	public List<TeacherInfo> findTrById(String Trid) {
		// TODO Auto-generated method stub
//		String hql="select ttt.Trid,tt.Tname,tt.Tdepartment,count(ttc) from TrTeam tt left join tt.competition ttc left join tt.teacher ttt where ttt.Trid=?";
		String hql="select tt.teacher.Trid,tt.Tname,tt.Tdepartment,count(ttc),sum(tt.Workload) from TrTeam tt left join tt.competition ttc where tt.teacher.Trid=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0,Trid);
		
		List<Object[]> list = query.list();
		
		List<TeacherInfo> trinfosingle = new ArrayList<TeacherInfo>();
		
		for(int i=0;i<list.size();i++) {
			Object[] obs = list.get(i);
			TeacherInfo trinfo = new TeacherInfo();
			trinfo.setTrid((String)obs[0]);
			trinfo.setTrname((String)obs[1]);
			trinfo.setTrdepartment((String)obs[2]);
			trinfo.setTrTpartnum((Long)obs[3]);
			trinfo.setTotalworkload((Long) obs[4]);
			trinfosingle.add(trinfo);
		}
		if(trinfosingle.get(0).getTrid()!=null) {
			return trinfosingle;
		}
		return null;
	}
	
	@Override
	public List<Teacher> findTrInfoByTrid(String Trid) {
		// TODO Auto-generated method stub
		List<Teacher> list=null;
		String hql="from Teacher t where t.Trid=?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, Trid);
		list=query.list();
		
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
	@Override
	public boolean updateTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		String hql="update Teacher set Trname=?,Trdepartment=?,Trtele=? where Trid=?";
		Query query1 = sessionFactory.getCurrentSession().createQuery(hql);
		query1.setString(0,teacher.getTrname());
		query1.setString(1,teacher.getTrdepartment());
		query1.setString(2, teacher.getTrtele());
		query1.setString(3,teacher.getTrid());
		
		String hql1="update TrTeam set Tdepartment=? where teacher.Trid=? ";
		Query query2 = sessionFactory.getCurrentSession().createQuery(hql1);
		query2.setString(0, teacher.getTrdepartment());
		query2.setString(1, teacher.getTrid());
		
		if(query1.executeUpdate()>0&&query2.executeUpdate()>0) {
			return true;
		}
		return false;
	}
	
	// 根据 竞赛编号 查询 所有 指导教师的信息(工号，姓名，所在单位，获奖级别，认定级别，工作量，电话)
	@Override
	public List<TrWorkload> findAllTrWorkloadById(Integer Compid) {
		// TODO Auto-generated method stub
		String hql="select tt.teacher.Trid,tt.Tname,tt.Tdepartment,tt.team.Awlevel,tt.team.Sclevel,sum(tt.Workload),tt.teacher.Trtele from TrTeam tt left join tt.competition ttc where ttc.Compid=? group by tt.teacher.Trid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, Compid);
		
		List<Object[]> list = query.list();
		List<TrWorkload> trworkloadlist = new ArrayList<TrWorkload>();
		
		for(int i=0;i<list.size();i++) {
			Object[] obs = list.get(i);
			TrWorkload trworkload = new TrWorkload();
			trworkload.setTrid((String)obs[0]);
			trworkload.setTrname((String)obs[1]);
			trworkload.setTrdepartment((String)obs[2]);
			trworkload.setAwlevel((String) obs[3]);
			trworkload.setSclevel((String) obs[4]);
			trworkload.setWorkload((Long) obs[5]);
			trworkload.setTrtele((String) obs[6]);
			trworkloadlist.add(trworkload);
		}
		if(trworkloadlist.size()!=0) {
			return trworkloadlist;
		}
		return null;
	}
	
	@Override
	public List<TrWorkload> findAllTrWorkloadByIdDepart(Integer Compid, String Department) {
		// TODO Auto-generated method stub
		String hql="select tt.teacher.Trid,tt.Tname,tt.Tdepartment,tt.team.Awlevel,tt.team.Sclevel,sum(tt.Workload),tt.teacher.Trtele from TrTeam tt left join tt.competition ttc where ttc.Compid=? and tt.Tdepartment=? group by tt.teacher.Trid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, Compid);
		query.setString(1, Department);
		List<Object[]> list = query.list();
		List<TrWorkload> trworkloadlist = new ArrayList<TrWorkload>();
		
		for(int i=0;i<list.size();i++) {
			Object[] obs = list.get(i);
			TrWorkload trworkload = new TrWorkload();
			trworkload.setTrid((String)obs[0]);
			trworkload.setTrname((String)obs[1]);
			trworkload.setTrdepartment((String)obs[2]);
			trworkload.setAwlevel((String) obs[3]);
			trworkload.setSclevel((String) obs[4]);
			trworkload.setWorkload((Long) obs[5]);
			trworkload.setTrtele((String) obs[6]);
			trworkloadlist.add(trworkload);
		}
		if(trworkloadlist.size()!=0) {
			return trworkloadlist;
		}
		return null;
	}
	
	@Override
	public boolean deleteTeacherByStuid(String  Trid) {
		// TODO Auto-generated method stub
		String hql="delete from teacher where Trid=? ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0,Trid);
		if(query.executeUpdate()>0) {
			return true;
		}
		return false;
	}
	
	
}
