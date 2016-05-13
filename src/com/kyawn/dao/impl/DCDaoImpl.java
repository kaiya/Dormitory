package com.kyawn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kyawn.dao.DCDao;

public class DCDaoImpl implements DCDao {

	@Override
	public ResultSet get(Connection conn, String bname, String dname) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM students WHERE Bname=? AND Dname=?");
		ps.setString(1, bname);
		ps.setString(2, dname);
		return ps.executeQuery();
	}

	@Override
	public void update(Connection conn, Long studentid, String row, String rowNewValue) throws SQLException {
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
}
