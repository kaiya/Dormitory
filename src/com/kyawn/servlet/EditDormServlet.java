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

import com.kyawn.dao.DormitoryDao;
import com.kyawn.dao.impl.DormitoryDaoImpl;
import com.kyawn.entity.Dorm;
import com.kyawn.util.ConnectionFactory;

public class EditDormServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8921641327918612988L;
	private DormitoryDao dd = new DormitoryDaoImpl();
	Connection conn = ConnectionFactory.getInstance().makeConnection();

	public EditDormServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = "";
		String row = "";
		Long did, dstorage, dleft;
		String dname = "";
		String bname = "";
		String dadmin = "";
		String dphone = "";

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
				did = data.getLong("did");
				bname = data.getString("bname");

				dname = data.getString("dname");
				dstorage = data.getLong("dstorage");
				dleft = data.getLong("dleft");
				dadmin = data.getString("dadmin");
				dphone = data.getString("dphone");
				rowNewValue = data.getString(row);

				/*System.out.println("action:" + action + "row" + row + "bid:" + bid + "dname:" + dname + "dadmin:"
						+ dadmin + "dphone:" + dphone);
				System.out.println("row:" + row + "row value:" + rowNewValue);*/

				// update
				 dd.update(conn, did, row, rowNewValue);

			} else if (action.equals("delete")) {

				// delete
				did = obj.getLong("did");
				dd.delete(conn, did);

			} else if (action.equals("insert")) {
				// insert
				data = obj.getJSONObject("data");
				dname = data.getString("dname");
				bname = data.getString("bname");
				dstorage = data.getLong("dstorage");
				dleft = data.getLong("dleft");
				dadmin = data.getString("dadmin");
				dphone = data.getString("dphone");

				Dorm dorm = new Dorm();
				dorm.setDormName(dname);
				dorm.setBname(bname);
				dorm.setDormStorage(dstorage);
				dorm.setDormLeft(dleft);
				dorm.setDormAdmin(dadmin);
				dorm.setDormPhone(dphone);
				dd.save(conn, dorm);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
