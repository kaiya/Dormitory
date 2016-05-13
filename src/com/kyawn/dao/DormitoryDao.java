package com.kyawn.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kyawn.entity.Dorm;

public interface DormitoryDao {

	public void save(Connection conn, Dorm dorm) throws SQLException;

	public void update(Connection conn, Long did, String row,String rowNewValue) throws SQLException;

	public void delete(Connection conn, Long did) throws SQLException;

	public ResultSet get(Connection conn) throws SQLException;
}
