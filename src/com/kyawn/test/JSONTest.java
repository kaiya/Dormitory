package com.kyawn.test;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kyawn.service.ListNews;

public class JSONTest {

	public static void main(String[] args) {
		
		JSONObject jo = new JSONObject();
		Map<String, String> map = new HashMap<String, String>();
        map.put("name", "Edward");
        map.put("sex", "male");
        map.put("age", "24");
        JSONArray ja = new JSONArray();
        ja.put(map);
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("name", "KevinHsiun");
        map2.put("sex", "male");
        map2.put("age", "22");
        ja.put(map2);
        try {
			jo.put("data", ja);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//        System.out.println("json object:"+jo);
//        ListBuildings lb = new ListBuildings();
        ListNews ln = new ListNews();
        jo = ln.listNews();
//        System.out.println("json object :"+jo);
        
	}
}
