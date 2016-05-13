package com.kyawn.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.kyawn.entity.User;
import com.kyawn.service.AddUserService;
import com.kyawn.util.ValidateEmail;

/**
 * Servlet implementation class SignupServlet
 */
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AddUserService aus = new AddUserService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignupServlet() {
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

		String uname = "";
		String passwd = "";
		String email = "";
		String tokenString = RandomStringUtils.randomAlphanumeric(50);
		String fileRootPath = getServletContext().getRealPath("/");
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
			email = obj.getString("email");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		if (uname == null || passwd == null) {
			out.print("1");// 用户名或密码为空
			out.close();
		} else {
			User user = new User();
			user.setName(uname);
			user.setPassword(passwd);
			user.setEmail(email);
			user.setTokenString(tokenString);
			boolean bool = aus.addUser(user);

			if (bool) {
				out.print("2");// 注册成功
				out.close();
				// 展示时由于没有网，所以发送邮件可以注释掉。

				ValidateEmail ve = new ValidateEmail();
				if (ve.SendSimpleMessage(email, tokenString, fileRootPath).getStatus() == 200) {
					// Email sent
				} else {
					// Email send error
				}

			} else {
				out.print("3");// 发生未知错误
				out.close();

			}
		}
	}

}
