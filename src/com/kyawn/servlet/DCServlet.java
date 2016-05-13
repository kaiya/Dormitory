package com.kyawn.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kyawn.dao.DCDao;
import com.kyawn.dao.impl.DCDaoImpl;
import com.kyawn.util.ConnectionFactory;

@WebServlet("/DC.do")
public class DCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DCDao dcDao = new DCDaoImpl();
	Connection conn = ConnectionFactory.getInstance().makeConnection();

	public DCServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String bname, dname, name, sex, phone, classname, sid, department;
		int studentid;
		Long year;
		bname = request.getParameter("bname");
		dname = request.getParameter("dname");
		JSONObject jo = new JSONObject();
		Map<String, String> map = new HashMap<String, String>();
		JSONArray ja = new JSONArray();

		Connection conn = null;
		try {
			conn = ConnectionFactory.getInstance().makeConnection();
			conn.setAutoCommit(false);

			ResultSet resultSet = dcDao.get(conn, bname, dname);
			while (resultSet.next()) {
				studentid = resultSet.getInt(1);
				sid = resultSet.getString(2);
				name = resultSet.getString(3);
				sex = resultSet.getString(4);
				phone = resultSet.getString(5);
				bname = resultSet.getString(6);
				dname = resultSet.getString(7);
				classname = resultSet.getString(8);
				year = resultSet.getLong(9);
				department = resultSet.getString(10);
				map.put("studentid", String.valueOf(studentid));
				map.put("sid", sid);
				map.put("name", name);
				map.put("sex", sex);
				map.put("phone", phone);
				map.put("bname", bname);
				map.put("dname", dname);
				map.put("classname", classname);
				map.put("year", String.valueOf(year));
				map.put("dept", department);

				ja.put(map);
				map.clear();
			}
			jo.put("rows", ja);
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
		response.setCharacterEncoding("utf-8");// 注意，十分重要，设置响应字符编码格式，否则可能乱码
		PrintWriter out = response.getWriter();
		out.print(jo);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("post");
		String action = "";
		String row = "";
		Long studentid, year;
		String sid = "";
		String name = "";
		String sex = "";
		String phone = "";
		String bname = "";
		String dname = "";
		String className = "";
		String department = "";

		String rowNewValue = "";
		JSONObject obj;
		JSONObject data;
		// PrintWriter out = response.getWriter();

		// 1. get received JSON data from request
		request.setCharacterEncoding("utf-8");
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
		String json = "";
		if (br != null) {
			json = br.readLine();
		}
		try{
		obj = new JSONObject(json);
		action = obj.getString("action");

		if (action.equals("update")) {
			row = obj.getString("row");
			data = obj.getJSONObject("data");

			studentid = data.getLong("studentid");
			name = data.getString("name");
			sex = data.getString("sex");
			phone = data.getString("phone");
			bname = data.getString("bname");
			dname = data.getString("dname");
			className = data.getString("classname");
			year = data.getLong("year");
			department = data.getString("dept");
			rowNewValue = data.getString(row);
			System.out.println(action+row);

			// update
			dcDao.update(conn, studentid, row, rowNewValue);

		} else if (action.equals("delete")) {

			// delete
			studentid = obj.getLong("studentid");
			dcDao.delete(conn, studentid);
		} else if (action.equals("insert")) {
			// insert

		}
		}catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
