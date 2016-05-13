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

import com.kyawn.dao.BuildingDao;
import com.kyawn.dao.impl.BuildingDaoImpl;
import com.kyawn.entity.Building;
import com.kyawn.util.ConnectionFactory;

public class EditBuildServlet extends HttpServlet {

	private static final long serialVersionUID = 4229288769646775083L;

	private BuildingDao bd = new BuildingDaoImpl();
	Connection conn = ConnectionFactory.getInstance().makeConnection();

	public EditBuildServlet() {
		super();
	}

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
		Long bid;
		String bname = "";
		String badmin = "";
		String bphone = "";
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
				bid = data.getLong("bid");
				bname = data.getString("bname");
				badmin = data.getString("badmin");
				bphone = data.getString("bphone");
				rowNewValue = data.getString(row);
				/*
				 * System.out.println("action:" + action + "row" + row + "bid:"
				 * + bid + "bname:" + bname + "badmin:" + badmin + "bphone:" +
				 * bphone); System.out.println("row:"+row+"row value:"
				 * +rowNewValue);
				 */
				// update
				bd.update(conn, bid, row, rowNewValue);

			} else if (action.equals("delete")) {
				bid = obj.getLong("bid");
				bd.delete(conn, bid);
				// delete
			} else if (action.equals("insert")) {
				// insert
				data = obj.getJSONObject("data");
				bname = data.getString("bname");
				badmin = data.getString("badmin");
				bphone = data.getString("bphone");

				Building building = new Building();
				building.setBuildingname(bname);
				building.setBuildingadmin(badmin);
				building.setBuildingphone(bphone);

				bd.save(conn, building);

			}

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
