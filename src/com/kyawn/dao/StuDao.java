package com.kyawn.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kyawn.entity.student;

public interface StuDao {

	public void save(Connection conn, student stu) throws SQLException;

	public void update(Connection conn, Long studentid, String row,String rowNewValue) throws SQLException;

	public void delete(Connection conn, Long studentid) throws SQLException;

	public ResultSet get(Connection conn) throws SQLException;
}
