package com.kyawn.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.kyawn.dao.StuDao;
import com.kyawn.dao.impl.StuDaoImpl;
import com.kyawn.util.ConnectionFactory;

public class ListStu {

	private StuDao stuDao = new StuDaoImpl();

	int studentid;
	String sid;
	String name;
	String sex;
	String phone;
	String bname;
	String dname;
	String className;
	int year;
	String department;

	public JSONObject listStu() {
		JSONObject jo = new JSONObject();
		Map<String, String> map = new HashMap<String, String>();
		JSONArray ja = new JSONArray();

		Connection conn = null;
		try {
			conn = ConnectionFactory.getInstance().makeConnection();
			conn.setAutoCommit(false);

			ResultSet resultSet = stuDao.get(conn);
			while (resultSet.next()) {
				studentid = resultSet.getInt(1);
				sid = resultSet.getString(2);
				name = resultSet.getString(3);
				sex = resultSet.getString(4);
				phone = resultSet.getString(5);
				bname = resultSet.getString(6);
				dname = resultSet.getString(7);
				className = resultSet.getString(8);
				year = resultSet.getInt(9);
				department = resultSet.getString(10);

				map.put("studentid", String.valueOf(studentid));
				map.put("sid", sid);
				map.put("name", name);
				map.put("sex", sex);
				map.put("phone", phone);
				map.put("bname", bname);
				map.put("dname", dname);
				map.put("className", className);
				map.put("year", String.valueOf(year));
				map.put("department", department);

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

		return jo;
	}

}
