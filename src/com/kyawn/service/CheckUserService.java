package com.kyawn.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kyawn.dao.UserDao;
import com.kyawn.dao.impl.UserDaoImpl;
import com.kyawn.entity.User;
import com.kyawn.util.ConnectionFactory;

public class CheckUserService {

	private UserDao userDao = new UserDaoImpl();

	public boolean check(User user) {
		Connection conn = null;
		try {
			conn = ConnectionFactory.getInstance().makeConnection();
			conn.setAutoCommit(false);
			ResultSet resultSet = userDao.get(conn, user);
			while (resultSet.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		}
		return false;
	}

	public boolean isVerified(User user) {
		Connection conn = null;
		try {
			conn = ConnectionFactory.getInstance().makeConnection();
			ResultSet resultSet = userDao.getVerifyStatus(conn, user);
			while (resultSet.next()) {
				if (resultSet.getInt(1) == 1) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
