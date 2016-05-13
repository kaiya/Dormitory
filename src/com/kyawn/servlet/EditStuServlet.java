package com.kyawn.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.kyawn.dao.StuDao;
import com.kyawn.dao.impl.StuDaoImpl;
import com.kyawn.entity.student;
import com.kyawn.util.ConnectionFactory;

public class EditStuServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2848739911826615343L;
	private StuDao sd = new StuDaoImpl();
	Connection conn = ConnectionFactory.getInstance().makeConnection();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
		try {
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
				className = data.getString("className");
				year = data.getLong("year");
				department = data.getString("department");
				rowNewValue = data.getString(row);

				/*
				 * System.out.println("action:" + action + "row" + row + "bid:"
				 * + bid + "dname:" + dname + "dadmin:" + dadmin + "dphone:" +
				 * dphone); System.out.println("row:" + row + "row value:" +
				 * rowNewValue);
				 */

				// update
				 sd.update(conn, studentid, row, rowNewValue);

			} else if (action.equals("delete")) {

				// delete
				studentid = obj.getLong("studentid");
				sd.delete(conn, studentid);
			} else if (action.equals("insert")) {
				// insert
				data = obj.getJSONObject("data");

				sid = data.getString("sid");
				name = data.getString("name");
				sex = data.getString("sex");
				phone = data.getString("phone");
				bname = data.getString("bname");
				dname = data.getString("dname");
				className = data.getString("className");
				year = data.getLong("year");
				department = data.getString("department");

				student student = new student();
				student.setSid(sid);
				student.setStudentName(name);
				student.setStudentSex(sex);
				student.setStudentPhone(phone);
				student.setBname(bname);
				student.setDname(dname);
				student.setStudentClass(className);
				student.setYear(year);
				student.setStudentDepartment(department);

				sd.save(conn, student);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
