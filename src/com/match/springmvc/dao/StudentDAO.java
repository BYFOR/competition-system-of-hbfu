package com.match.springmvc.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.match.springmvc.data.StuBonus;
import com.match.springmvc.data.StudentInfo;
import com.match.springmvc.data.TeamInfoStu;
import com.match.springmvc.entities.Student;

@Repository
public class StudentDAO implements IStudentDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<StudentInfo> countStuTpartNum() {
		// TODO Auto-generated method stub
		String hql="select stst.Stuid,st.Sname,st.Sdepartment,st.Sclass,count(stc),sum(st.Credit),sum(st.Bonus) from StuTeam st left join st.competition stc left join st.student stst group by stst.Stuid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Object[]> list = query.list();
		List<StudentInfo> sinfolist = new ArrayList<StudentInfo>();
		
		for(int i=0;i<list.size();i++) {
			Object[] obs = list.get(i);
			StudentInfo stuinfo = new StudentInfo();
			stuinfo.setStuid((String)obs[0]);
			stuinfo.setStuname((String)obs[1]);
			stuinfo.setStudepartment((String)obs[2]);
			stuinfo.setStuclass((String) obs[3]);
			stuinfo.setTpartnum((Long)obs[4]);
			stuinfo.setTotalcredits((Double) obs[5]);
			stuinfo.setTotalbonus((BigDecimal) obs[6]);
			sinfolist.add(stuinfo);
		}
		return sinfolist;
	}
	
	// 查询学生 参加的 所有比赛的 队伍信息(队伍编号，竞赛名称，参赛队伍，指导教师，获奖级别，认定级别，个人所获学分，奖金数)(单支队伍)
	@Override
	public List<TeamInfoStu> findAllTeamByStuid(String Stuid) {
		// TODO Auto-generated method stub
		String hql="select t.Teamid,t.Tmcname,t.Smens,t.Tmens,t.Awlevel,t.Sclevel,tst.Credit,tst.Bonus from Team t left join t.stuteam tst left join tst.student tsts where tsts.Stuid=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0,Stuid);
		List<Object[]> list = query.list();
		List<TeamInfoStu> teaminfostulist = new ArrayList<TeamInfoStu>();
		
		for(int i=0;i<list.size();i++) {
			Object[] obs = list.get(i);
			TeamInfoStu teaminfostu = new TeamInfoStu();
			teaminfostu.setTeamid((String) obs[0]);
			teaminfostu.setCompname((String) obs[1]);
			teaminfostu.setSmens((String) obs[2]);
			teaminfostu.setTmens((String) obs[3]);
			teaminfostu.setAwlevel((String) obs[4]);
			teaminfostu.setSclevel((String) obs[5]);
			teaminfostu.setCredit((Double) obs[6]);
			teaminfostu.setBonus((BigDecimal) obs[7]);
			teaminfostulist.add(teaminfostu);
		}
		if(teaminfostulist.size()!=0) {
			return teaminfostulist;
		}
		return null;
	}
	
	@Override
	public List<StudentInfo> findStuById(String Stuid) {
		// TODO Auto-generated method stub
//		String hql="select stst.Stuid,st.Sname,st.Sdepartment,count(stc) from StuTeam st left join st.competition stc left join st.student stst where stst.Stuid=?";
		String hql="select st.student.Stuid,st.Sname,st.Sdepartment,st.Sclass,count(stc),sum(st.Credit),sum(st.Bonus) from StuTeam st left join st.competition stc where st.student.Stuid=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0,Stuid);
		
		List<Object[]> list = query.list();
		List<StudentInfo> sinfosingle = new ArrayList<StudentInfo>();
		
		for(int i=0;i<list.size();i++) {
			Object[] obs = list.get(i);
			StudentInfo stuinfo = new StudentInfo();
			stuinfo.setStuid((String)obs[0]);
			stuinfo.setStuname((String)obs[1]);
			stuinfo.setStudepartment((String)obs[2]);
			stuinfo.setStuclass((String) obs[3]);
			stuinfo.setTpartnum((Long)obs[4]);
			stuinfo.setTotalcredits((Double) obs[5]);
			stuinfo.setTotalbonus((BigDecimal) obs[6]);
			sinfosingle.add(stuinfo);
		}
		if(sinfosingle.get(0).getStuid()!=null) {
			return sinfosingle;
		}
		return null;
	}
	
	@Override
	public List<Student> findStuInfoByStuid(String Stuid) {
		// TODO Auto-generated method stub
		List<Student> list=null;
		String hql="from Student s where s.Stuid=?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, Stuid);
		list=query.list();
		
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
	// 根据 学生编号 更新相应 学生信息
	@Override
	public boolean updateStudent(Student student) {
		// TODO Auto-generated method stub
		String hql="update Student set Stuname=?,Studepartment=?,Stuclass=?,Stutele=?,Stucard=? where Stuid=?";
		Query query1 = sessionFactory.getCurrentSession().createQuery(hql);
		query1.setString(0,student.getStuname());
		query1.setString(1,student.getStudepartment());
		query1.setString(2, student.getStuclass());
		query1.setString(3,student.getStutele());
		query1.setString(4,student.getStucard());
		query1.setString(5,student.getStuid());
		
		String hql1="update StuTeam set Sdepartment=?,Sclass=? where student.Stuid=? ";
		Query query2 = sessionFactory.getCurrentSession().createQuery(hql1);
		query2.setString(0, student.getStudepartment());
		query2.setString(1, student.getStuclass());
		query2.setString(2, student.getStuid());
		
		if(query1.executeUpdate()>0&&query2.executeUpdate()>0) {
			return true;
		}
		return false;
	}
	
	// 根据 竞赛编号 查询 所有 参赛学生的信息(学号，姓名，系别，班级，获奖级别，认定级别，奖金数，电话，银行卡号)
	@Override
	public List<StuBonus> findAllStuBonusById(Integer Compid) {
		// TODO Auto-generated method stub
		String hql="select st.student.Stuid,st.Sname,st.Sdepartment,st.Sclass,st.team.Awlevel,st.team.Sclevel,sum(st.Bonus),st.student.Stutele, st.student.Stucard from StuTeam st left join st.competition stc where stc.Compid=? group by st.student.Stuid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, Compid);
		
		List<Object[]> list = query.list();
		List<StuBonus> stubonuslist = new ArrayList<StuBonus>();
		
		for(int i=0;i<list.size();i++) {
			Object[] obs = list.get(i);
			StuBonus stubonus = new StuBonus();
			stubonus.setStuid((String)obs[0]);
			stubonus.setStuname((String)obs[1]);
			stubonus.setStudepartment((String)obs[2]);
			stubonus.setStuclass((String) obs[3]);
			stubonus.setAwlevel((String) obs[4]);
			stubonus.setSclevel((String) obs[5]);
			stubonus.setBonus((BigDecimal) obs[6]);
			stubonus.setStutele((String) obs[7]);
			stubonus.setStucard((String) obs[8]);
			stubonuslist.add(stubonus);
		}
		if(stubonuslist.size()!=0) {
			return stubonuslist;
		}
		return null;
	}
	
	@Override
	public List<StuBonus> findAllStuBonusByIdDepart(Integer Compid, String Department) {
		// TODO Auto-generated method stub
		String hql="select st.student.Stuid,st.Sname,st.Sdepartment,st.Sclass,st.team.Awlevel,st.team.Sclevel,sum(st.Bonus),st.student.Stutele, st.student.Stucard from StuTeam st left join st.competition stc where stc.Compid=? and st.Sdepartment=? group by st.student.Stuid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, Compid);
		query.setString(1, Department);
		
		List<Object[]> list = query.list();
		List<StuBonus> stubonuslist = new ArrayList<StuBonus>();
		
		for(int i=0;i<list.size();i++) {
			Object[] obs = list.get(i);
			StuBonus stubonus = new StuBonus();
			stubonus.setStuid((String)obs[0]);
			stubonus.setStuname((String)obs[1]);
			stubonus.setStudepartment((String)obs[2]);
			stubonus.setStuclass((String) obs[3]);
			stubonus.setAwlevel((String) obs[4]);
			stubonus.setSclevel((String) obs[5]);
			stubonus.setBonus((BigDecimal) obs[6]);
			stubonus.setStutele((String) obs[7]);
			stubonus.setStucard((String) obs[8]);
			stubonuslist.add(stubonus);
		}
		if(stubonuslist.size()!=0) {
			return stubonuslist;
		}
		return null;
	}
	
	// 根据 竞赛编号 查询 所有 队长的信息(学号，姓名，系别，班级，获奖级别，认定级别，奖金数，电话，银行卡号)
	@Override
	public List<StuBonus> findAllTeamBonusById(Integer Compid) {
		// TODO Auto-generated method stub
		String hql="select st.student.Stuid,st.Sname,st.Sdepartment,st.Sclass,st.team.Awlevel,st.team.Sclevel,st.Teambonus,st.student.Stutele, st.student.Stucard from StuTeam st left join st.competition stc where stc.Compid=? and st.Header=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, Compid);
		query.setString(1, "队长");
		
		List<Object[]> list = query.list();
		List<StuBonus> stubonuslist = new ArrayList<StuBonus>();
		
		for(int i=0;i<list.size();i++) {
			Object[] obs = list.get(i);
			StuBonus stubonus = new StuBonus();
			stubonus.setStuid((String)obs[0]);
			stubonus.setStuname((String)obs[1]);
			stubonus.setStudepartment((String)obs[2]);
			stubonus.setStuclass((String) obs[3]);
			stubonus.setAwlevel((String) obs[4]);
			stubonus.setSclevel((String) obs[5]);
			stubonus.setBonus((BigDecimal) obs[6]);
			stubonus.setStutele((String) obs[7]);
			stubonus.setStucard((String) obs[8]);
			stubonuslist.add(stubonus);
		}
		if(stubonuslist.size()!=0) {
			return stubonuslist;
		}
		return null;
	}
	
	// 根据 竞赛编号 系别 查询 系 所有 队长的奖金信息(学号，姓名，系别，班级，获奖级别，认定级别，队伍奖金数，电话，银行卡号)
	@Override
	public List<StuBonus> findAllTeamBonusByIdDepart(Integer Compid, String Department) {
		// TODO Auto-generated method stub
		String hql="select st.student.Stuid,st.Sname,st.Sdepartment,st.Sclass,st.team.Awlevel,st.team.Sclevel,st.Teambonus,st.student.Stutele, st.student.Stucard from StuTeam st left join st.competition stc where stc.Compid=? and st.Sdepartment=? and st.Header=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, Compid);
		query.setString(1, Department);
		query.setString(2, "队长");
		
		List<Object[]> list = query.list();
		List<StuBonus> stubonuslist = new ArrayList<StuBonus>();
		
		for(int i=0;i<list.size();i++) {
			Object[] obs = list.get(i);
			StuBonus stubonus = new StuBonus();
			stubonus.setStuid((String)obs[0]);
			stubonus.setStuname((String)obs[1]);
			stubonus.setStudepartment((String)obs[2]);
			stubonus.setStuclass((String) obs[3]);
			stubonus.setAwlevel((String) obs[4]);
			stubonus.setSclevel((String) obs[5]);
			stubonus.setBonus((BigDecimal) obs[6]);
			stubonus.setStutele((String) obs[7]);
			stubonus.setStucard((String) obs[8]);
			stubonuslist.add(stubonus);
		}
		if(stubonuslist.size()!=0) {
			return stubonuslist;
		}
		return null;
	}
	
	@Override
	public boolean deleteStudentByStuid(String  Stuid) {
		// TODO Auto-generated method stub
		String hql="delete from Student where Stuid=? ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString (0,Stuid);
		if(query.executeUpdate()>0) {
			return true;
		}
		return false;
	}
	
}
