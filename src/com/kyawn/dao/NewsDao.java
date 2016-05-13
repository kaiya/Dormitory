package com.kyawn.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kyawn.entity.News;

public interface NewsDao {

	public void save(Connection conn, News news) throws SQLException;

	public void update(Connection conn, Long id, News news) throws SQLException;

	public void delete(Connection conn, News news) throws SQLException;

	public ResultSet get(Connection conn) throws SQLException;
}
