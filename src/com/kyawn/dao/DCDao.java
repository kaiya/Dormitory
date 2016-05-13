package com.kyawn.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DCDao {

	public void update(Connection conn, Long studentid, String row,String rowNewValue) throws SQLException;

	public void delete(Connection conn, Long studentid) throws SQLException;

	public ResultSet get(Connection conn, String bname, String dname) throws SQLException;

}
