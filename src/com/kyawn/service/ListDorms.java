package com.kyawn.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.kyawn.dao.DormitoryDao;
import com.kyawn.dao.impl.DormitoryDaoImpl;
import com.kyawn.util.ConnectionFactory;

public class ListDorms {

	private DormitoryDao dormitoryDao = new DormitoryDaoImpl();

	int did;
	String dname,bname;
	int dstorage;
	int dleft;
	String dadmin;
	String dphone;

	public JSONObject listDorms() {
		JSONObject jo = new JSONObject();
		Map<String, String> map = new HashMap<String, String>();
		JSONArray ja = new JSONArray();

		Connection conn = null;
		try {
			conn = ConnectionFactory.getInstance().makeConnection();
			conn.setAutoCommit(false);

			ResultSet resultSet = dormitoryDao.get(conn);
			while (resultSet.next()) {
				did = resultSet.getInt(1);
				dname = resultSet.getString(2);
				bname = resultSet.getString(3);
				dstorage = resultSet.getInt(4);
				dleft = resultSet.getInt(5);
				dadmin = resultSet.getString(6);
				dphone = resultSet.getString(7);
				
				map.put("did", String.valueOf(did));
				map.put("dname", dname);
				map.put("bname", bname);
				map.put("dstorage", String.valueOf(dstorage));
				map.put("dleft", String.valueOf(dleft));
				map.put("dadmin", dadmin);
				map.put("dphone", dphone);
				
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
