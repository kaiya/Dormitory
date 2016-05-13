package com.kyawn.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.kyawn.dao.BuildingDao;
import com.kyawn.dao.impl.BuildingDaoImpl;
import com.kyawn.util.ConnectionFactory;

public class ListBuildings {

	private BuildingDao buildingDao = new BuildingDaoImpl();
	int bid;
	String bname;
	String badmin;
	String bphone;

	public JSONObject listBuildings() {

		JSONObject jo = new JSONObject();
		Map<String, String> map = new HashMap<String, String>();
		JSONArray ja = new JSONArray();

		Connection conn = null;
		try {
			conn = ConnectionFactory.getInstance().makeConnection();
			conn.setAutoCommit(false);

			ResultSet resultSet = buildingDao.get(conn);
			while (resultSet.next()) {
				bid = resultSet.getInt(1);
				bname = resultSet.getString(2);
				badmin = resultSet.getString(3);
				bphone = resultSet.getString(4);
				map.put("bid", String.valueOf(bid));
				map.put("bname", bname);
				map.put("badmin", badmin);
				map.put("bphone", bphone);
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
