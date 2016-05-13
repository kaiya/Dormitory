package com.kyawn.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kyawn.entity.User;
import com.kyawn.service.CheckUserService;

import org.json.*;

/*
 * 约定 1.name or pwd is null
 * 2.name or passwor is wrong.
 * 3.login as user
 * 4.login as admin
 */

/**
 * Servlet implementation class CheckServlet
 */
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CheckUserService cku = new CheckUserService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String uname = "";
		String passwd = "";
		String role = "";
		JSONObject obj;
		PrintWriter out = response.getWriter();

		// 1. get received JSON data from request
		request.setCharacterEncoding("utf-8");
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
		String json = "";
		if (br != null) {
			json = br.readLine();
		}
		try {
			obj = new JSONObject(json);
			uname = obj.getString("uname");
			passwd = obj.getString("upwd");
			role = obj.getString("role");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		if (uname == null || passwd == null) {
			out.print(1);
		} else {
			User user = new User();
			user.setName(uname);
			user.setPassword(passwd);
			user.setRole(role);
			boolean success = cku.check(user);
			if (success) {
				if (role.equals("Admin")) {
					out.print(4);//login as admin
				} else if (role.equals("User")) {
					if (cku.isVerified(user)) {
						out.print(3);// login as user
					} else {
						out.print(5);// Email not Verified!
					}
				}
				session.setAttribute("user", uname);
				session.setAttribute("role", role);
				session.setMaxInactiveInterval(3600);
			} else {
				out.print(2);// username or password incorrect
			}
		}
	}
}
