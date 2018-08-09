package com.match.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.springmvc.dao.ICompetitionDAO;
import com.match.springmvc.entities.Competition;
import com.match.springmvc.entities.Student;
import com.match.springmvc.entities.Teacher;
import com.match.springmvc.entities.Team;

@Service
public class CompetitionService implements ICompetitionService {
	
	@Autowired
	private ICompetitionDAO competitionDAO;
	
	@Override
	public void insertCompetition(Competition competition) {
		// TODO Auto-generated method stub
		competitionDAO.insertCompetition(competition);
	}
	
	@Override
	public List<Competition> findCompetitionByName(String Compname) {
		// TODO Auto-generated method stub
		return competitionDAO.findCompetitionByName(Compname);
	}
	
	@Override
	public List<Competition> findCompetitionById(Integer Compid) {
		// TODO Auto-generated method stub
		return competitionDAO.findCompetitionById(Compid);
	}
	
	@Override
	public long findStudentNumById(Integer Compid) {
		// TODO Auto-generated method stub
		return competitionDAO.findStudentNumById(Compid);
	}
	
	@Override
	public long findTeamNum(Integer Compid) {
		// TODO Auto-generated method stub
		return competitionDAO.findTeamNum(Compid);
	}
	
	@Override
	public long findTeacherNum(Integer Compid) {
		// TODO Auto-generated method stub
		return competitionDAO.findTeacherNum(Compid);
	}
	
	@Override
	public List<Team> findTeamById(Integer Compid) {
		// TODO Auto-generated method stub
		return competitionDAO.findTeamById(Compid);
	}
	
	@Override
	public List<Student> findStudentByCompId(Integer Compid) {
		// TODO Auto-generated method stub
		return competitionDAO.findStudentByCompId(Compid);
	}
	
	@Override
	public List<Teacher> findTeacherByCompId(Integer Compid) {
		// TODO Auto-generated method stub
		return competitionDAO.findTeacherByCompId(Compid);
	}
	
	@Override
	public boolean updateCompetition(Competition competition) {
		// TODO Auto-generated method stub
		return competitionDAO.updateCompetition(competition);
	}
	
	@Override
	public boolean deleteCompetitionByCompId(Integer Compid) {
		// TODO Auto-generated method stub
		return competitionDAO.deleteCompetitionByCompId(Compid);
	}
	
	@Override
	public List<Competition> findCompetitionByHouTianId(Integer Id) {
		// TODO Auto-generated method stub
		return competitionDAO.findCompetitionByHouTianId(Id);
	}
	
	@Override
	public long findStudentNumByCompidDepart(Integer Compid, String Department) {
		// TODO Auto-generated method stub
		return competitionDAO.findStudentNumByCompidDepart(Compid, Department);
	}
	
	@Override
	public long findTeacherNumByCompidDepart(Integer Compid, String Department) {
		// TODO Auto-generated method stub
		return competitionDAO.findTeacherNumByCompidDepart(Compid, Department);
	}
	
	@Override
	public List<Student> findStudentByCompIdDepart(Integer Compid, String Department) {
		// TODO Auto-generated method stub
		return competitionDAO.findStudentByCompIdDepart(Compid, Department);
	}
	
	@Override
	public List<Teacher> findTeacherByCompIdDepart(Integer Compid, String Department) {
		// TODO Auto-generated method stub
		return competitionDAO.findTeacherByCompIdDepart(Compid, Department);
	}
	
}
