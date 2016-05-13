package com.kyawn.service;

import java.sql.Connection;

import com.kyawn.dao.UserDao;
import com.kyawn.dao.impl.UserDaoImpl;
import com.kyawn.entity.User;
import com.kyawn.util.ConnectionFactory;

public class AddUserService {

	private UserDao userDao = new UserDaoImpl();

	public boolean addUser(User user) {
		Connection conn = null;

		try {
			conn = ConnectionFactory.getInstance().makeConnection();
			userDao.save(conn, user);

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
		return true;
	}
}
