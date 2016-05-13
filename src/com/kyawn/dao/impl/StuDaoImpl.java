package com.kyawn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kyawn.dao.StuDao;
import com.kyawn.entity.student;

public class StuDaoImpl implements StuDao {

	@Override
	public void save(Connection conn, student stu) throws SQLException {
		String saveSql = "INSERT INTO students (sid,name,sex,phone,bname,dname,className,year,department) VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(saveSql);
		ps.setString(1, stu.getSid());
		ps.setString(2, stu.getStudentName());
		ps.setString(3, stu.getStudentSex());
		ps.setString(4, stu.getStudentPhone());
		ps.setString(5, stu.getBname());
		ps.setString(6, stu.getDname());
		ps.setString(7, stu.getStudentClass());
		ps.setLong(8, stu.getYear());
		ps.setString(9, stu.getStudentDepartment());
		ps.execute();
	}

	@Override
	public void update(Connection conn, Long studentid, String row, String rowNewValue) throws SQLException {
		// TODO Auto-generated method stub
		String updateSql = "UPDATE students SET " + row + " = ? where studentid = ?";
		PreparedStatement ps = conn.prepareStatement(updateSql);
		ps.setString(1, rowNewValue);
		ps.setLong(2, studentid);
		ps.execute();

	}

	@Override
	public void delete(Connection conn, Long studentid) throws SQLException {
		String deleteSql = "DELETE FROM students WHERE studentid = ?";
		PreparedStatement ps = conn.prepareStatement(deleteSql);
		ps.setLong(1, studentid);
		ps.execute();

	}

	@Override
	public ResultSet get(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM students");
		return ps.executeQuery();
	}

}
