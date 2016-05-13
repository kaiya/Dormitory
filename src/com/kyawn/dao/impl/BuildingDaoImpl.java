package com.kyawn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kyawn.dao.BuildingDao;
import com.kyawn.entity.Building;

public class BuildingDaoImpl implements BuildingDao {

	@Override
	public void save(Connection conn, Building building) throws SQLException {
		String saveSql = "INSERT INTO buildings (Bname,Badmin,Bphone)VALUES(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(saveSql);
		ps.setString(1, building.getBuildingname());
		ps.setString(2, building.getBuildingadmin());
		ps.setString(3, building.getBuildingphone());
		ps.execute();

	}

	@Override
	public void update(Connection conn, Long bid, String row, String rowNewValue) throws SQLException {
		String updateSql = "UPDATE buildings SET " + row + " = ? where bid = ?";
		PreparedStatement ps = conn.prepareStatement(updateSql);
		ps.setString(1, rowNewValue);
		ps.setLong(2, bid);
		ps.execute();
	}

	@Override
	public void delete(Connection conn, Long bid) throws SQLException {
		String deleteSql = "DELETE FROM buildings WHERE bid = ?";
		PreparedStatement ps = conn.prepareStatement(deleteSql);
		ps.setLong(1, bid);
		ps.execute();

	}

	@Override
	public ResultSet get(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM buildings");
		return ps.executeQuery();
	}

}
