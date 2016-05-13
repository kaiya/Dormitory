package com.kyawn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kyawn.dao.NewsDao;
import com.kyawn.entity.News;

public class NewsDaoImpl implements NewsDao {

	@Override
	public void save(Connection conn, News news) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Connection conn, Long id, News news) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Connection conn, News news) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public ResultSet get(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM news inner join comments on news.nid=comments.nid; ");
		return ps.executeQuery();
	}

}
