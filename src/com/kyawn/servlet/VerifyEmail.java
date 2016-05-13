package com.kyawn.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kyawn.dao.UserDao;
import com.kyawn.dao.impl.UserDaoImpl;
import com.kyawn.util.ConnectionFactory;

public class VerifyEmail extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3761165611268722980L;
	private UserDao ud = new UserDaoImpl();

	Connection conn = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String tokenString = request.getParameter("token");
		conn = ConnectionFactory.getInstance().makeConnection();
		//Validate token
		try {

			ResultSet resultSet = ud.getTokenString(conn, email);
			while (resultSet.next()) {
				if (tokenString.equals(resultSet.getString(1))) {
					//set isVerified to 1
					ud.updateVerifyStatus(conn, email);
					response.sendRedirect("/login.jsp");
				} else {
					// Email does exist!
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
