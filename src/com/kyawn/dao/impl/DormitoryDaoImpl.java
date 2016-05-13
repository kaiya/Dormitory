package com.kyawn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kyawn.dao.DormitoryDao;
import com.kyawn.entity.Dorm;

public class DormitoryDaoImpl implements DormitoryDao {

	@Override
	public void save(Connection conn, Dorm dorm) throws SQLException {

		// TODO Auto-generated method stub
		String saveSql = "INSERT INTO dorms (Dname,Bname,Dstorge,Dleft,Dadmin,Dphone) VALUES(?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(saveSql);
		ps.setString(1, dorm.getDormName());
		ps.setString(2, dorm.getBname());
		ps.setLong(3, dorm.getDormStorage());
		ps.setLong(4, dorm.getDormLeft());
		ps.setString(5, dorm.getDormAdmin());
		ps.setString(6, dorm.getDormPhone());
		ps.execute();
	}

	@Override
	public void update(Connection conn, Long did, String row, String rowNewValue) throws SQLException {
		String updateSql = "UPDATE dorms SET " + row + " = ? where did = ?";
		PreparedStatement ps = conn.prepareStatement(updateSql);
		ps.setString(1, rowNewValue);
		ps.setLong(2, did);
		ps.execute();
	}

	@Override
	public void delete(Connection conn, Long did) throws SQLException {
		String deleteSql = "DELETE FROM dorms WHERE did = ?";
		PreparedStatement ps = conn.prepareStatement(deleteSql);
		ps.setLong(1, did);
		ps.execute();

	}

	@Override
	public ResultSet get(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM dorms");
		return ps.executeQuery();
	}

}
