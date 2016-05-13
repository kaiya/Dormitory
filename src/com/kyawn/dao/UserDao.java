package com.kyawn.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kyawn.entity.User;

public interface UserDao {

	public void save(Connection conn, User user) throws SQLException;

	public void update(Connection conn, Long id, User user) throws SQLException;

	public void delete(Connection conn, User user) throws SQLException;

	public ResultSet get(Connection conn, User user) throws SQLException;

	public ResultSet getTokenString(Connection conn, String email) throws SQLException;

	public void updateVerifyStatus(Connection conn, String email) throws SQLException;
	
	public ResultSet getVerifyStatus(Connection conn,User user) throws SQLException;

}
