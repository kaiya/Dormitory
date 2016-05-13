package com.kyawn.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.kyawn.dao.NewsDao;
import com.kyawn.dao.impl.NewsDaoImpl;
import com.kyawn.util.ConnectionFactory;

public class ListNews {

	private NewsDao newsDao = new NewsDaoImpl();
	int nid;
	String author;
	String content;
	String title;
	Date ctime;
	String cmauthor;
	String comment;
	
	public JSONObject listNews() {
		JSONObject jo = new JSONObject();
		Map<String, String> map = new HashMap<String, String>();
		JSONArray ja = new JSONArray();

		Connection conn = null;
		try {
			conn = ConnectionFactory.getInstance().makeConnection();
			conn.setAutoCommit(false);

			ResultSet resultSet = newsDao.get(conn);
			while (resultSet.next()) {
				nid = resultSet.getInt(1);
				author = resultSet.getString(2);
				title = resultSet.getString(3);
				content = resultSet.getString(4);
				ctime = resultSet.getDate(5);
				cmauthor = resultSet.getString(7);
				comment = resultSet.getString(8);
				
				map.put("nid", String.valueOf(nid));
				map.put("author", author);
				map.put("title", title);
				map.put("content", content);
				map.put("ctime", ctime.toString());
				map.put("cmauthor", cmauthor);
				map.put("comment", comment);
				
				ja.put(map);
				map.clear();
			}
			jo.put("newsdata", ja);
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
