package com.match.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.springmvc.dao.ITeacherDAO;
import com.match.springmvc.data.TeacherInfo;
import com.match.springmvc.data.TeamInfoTr;
import com.match.springmvc.data.TrWorkload;
import com.match.springmvc.entities.Teacher;

@Service
public class TeacherService implements ITeacherService {
	
	@Autowired
	private ITeacherDAO teacherDAO;
	
	@Override
	public List<TeacherInfo> countTrTpartNum() {
		// TODO Auto-generated method stub
		return teacherDAO.countTrTpartNum();
	}
	
	@Override
	public List<TeamInfoTr> findAllTeamByTrid(String Trid) {
		// TODO Auto-generated method stub
		return teacherDAO.findAllTeamByTrid(Trid);
	}
	
	@Override
	public List<TeacherInfo> findTrById(String Trid) {
		// TODO Auto-generated method stub
		return teacherDAO.findTrById(Trid);
	}
	
	@Override
	public List<Teacher> findTrInfoByTrid(String Trid) {
		// TODO Auto-generated method stub
		return teacherDAO.findTrInfoByTrid(Trid);
	}
	
	@Override
	public boolean updateTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherDAO.updateTeacher(teacher);
	}
	
	@Override
	public List<TrWorkload> findAllTrWorkloadById(Integer Compid) {
		// TODO Auto-generated method stub
		return teacherDAO.findAllTrWorkloadById(Compid);
	}
	
	@Override
	public List<TrWorkload> findAllTrWorkloadByIdDepart(Integer Compid, String Department) {
		// TODO Auto-generated method stub
		return teacherDAO.findAllTrWorkloadByIdDepart(Compid, Department);
	}
	
	@Override
	public boolean deleteTeacherByTrid(String  Trid) {
		// TODO Auto-generated method stub
		return teacherDAO.deleteTeacherByStuid(Trid);
	}
}
