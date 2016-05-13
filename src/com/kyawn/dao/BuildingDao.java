package com.kyawn.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kyawn.entity.Building;

public interface BuildingDao {

	public void save(Connection conn, Building building) throws SQLException;

	public void update(Connection conn, Long bid, String row,String rowNewValue) throws SQLException;

	public void delete(Connection conn, Long bid) throws SQLException;

	public ResultSet get(Connection conn) throws SQLException;
}
