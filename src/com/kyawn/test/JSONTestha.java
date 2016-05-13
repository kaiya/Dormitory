package com.kyawn.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kyawn.dao.DCDao;
import com.kyawn.dao.impl.DCDaoImpl;
import com.kyawn.util.ConnectionFactory;

public class JSONTestha {

	public static DCDao dcDao = new DCDaoImpl();

	public static void main(String[] args) throws SQLException, JSONException {

		Connection conn = null;
		String name,sex,phone,dname,bname,classname,sid,dept;
		int studentid;
		Long year;
		JSONObject jo = new JSONObject();
		Map<String, String> map = new HashMap<String, String>();
		JSONArray ja = new JSONArray();
		conn = ConnectionFactory.getInstance().makeConnection();
		conn.setAutoCommit(false);
		ResultSet resultSet = dcDao.get(conn, "二区一栋", "501");
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
			dept = resultSet.getString(10);
			map.put("studentid", String.valueOf(studentid));
			map.put("sid", sid);
			map.put("name", name);
			map.put("sex", sex);
			map.put("phone", phone);
			map.put("bname", bname);
			map.put("dname", dname);
			map.put("classname", classname);
			map.put("year", String.valueOf(year));
			map.put("dept", dept);

			ja.put(map);
			map.clear();
		}
		jo.put("rows", ja);
		System.out.println(jo);
	}
}
