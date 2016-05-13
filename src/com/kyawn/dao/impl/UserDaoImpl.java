package com.kyawn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kyawn.dao.UserDao;
import com.kyawn.entity.User;
import com.kyawn.util.md5;

public class UserDaoImpl implements UserDao {

	public static final String salt_key = "7551kaiya@$q.com";
	md5 getMD5 = new md5();

	/**
	 * 保存用户信息
	 */
	@Override
	public void save(Connection conn, User user) throws SQLException {

		String encryptedPwd = getMD5.GetMD5Code(user.getPassword() + salt_key);
		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO users(uname,upwd,email,isAdmin,isVerified,token)VALUES(?,?,?,?,?,?)");
		ps.setString(1, user.getName());
		ps.setString(2, encryptedPwd);
		ps.setString(3, user.getEmail());
		ps.setInt(4, 0);
		ps.setInt(5, 0);
		ps.setString(6, user.getTokenString());
		ps.executeUpdate();
	}

	/**
	 * 根据用户指定的id更新用户信息
	 */
	@Override
	public void update(Connection conn, Long id, User user) throws SQLException {

		String updateSql = "UPDATE tbl_user SET name = ?,password = ? WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(updateSql);
		ps.setString(1, user.getName());
		ps.setString(2, user.getPassword());
		ps.setLong(4, id);
		ps.execute();

	}

	/**
	 * 删除指定的用户信息
	 */
	@Override
	public void delete(Connection conn, User user) throws SQLException {

		PreparedStatement ps = conn.prepareStatement("DELETE FROM tbl_user WHERE id = ?");
		ps.setLong(1, user.getId());
		ps.execute();
	}

	@Override
	public ResultSet get(Connection conn, User user) throws SQLException {

		String encryptedPwd = getMD5.GetMD5Code(user.getPassword() + salt_key);
		PreparedStatement ps = conn
				.prepareStatement("SELECT * FROM users WHERE uname = ? AND upwd = ? AND isadmin = ?");
		ps.setString(1, user.getName());
		ps.setString(2, encryptedPwd);
		if (user.getRole().equals("Admin")) {
			ps.setInt(3, 1);
		} else {
			ps.setInt(3, 0);
		}
		return ps.executeQuery();
	}

	@Override
	public ResultSet getTokenString(Connection conn, String email) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT token FROM users WHERE email = ?");
		ps.setString(1, email);
		return ps.executeQuery();
	}

	@Override
	public void updateVerifyStatus(Connection conn, String email) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("UPDATE users SET isVerified = 1 WHERE email = ?");
		ps.setString(1, email);
		ps.execute();
	}

	@Override
	public ResultSet getVerifyStatus(Connection conn, User user) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT isVerified FROM users WHERE uname = ?");
		ps.setString(1, user.getName());
		return ps.executeQuery();
	}
}
