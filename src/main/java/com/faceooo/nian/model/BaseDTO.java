package com.faceooo.nian.model;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BaseDTO {


	public JSONObject getDtoToJson() {
		JSONObject objectDto = new JSONObject();
		try {
			for (Field dd : this.getClass().getFields()) {
				if (StringUtils.isNotEmpty((String) dd.get(this))) {
					objectDto.put(dd.getName(), dd.get(this));
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return objectDto;

	}

	public static void main(String arg[]) throws NoSuchMethodException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		UserinfoDTO user = new UserinfoDTO();
		JSONObject objectDto = new JSONObject();
		JSONArray jsonArrayList = new JSONArray();
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("abc", "123456");
		map.put("def", "hmm");
		map.put("defddf", "ftffff");
		user.setId("11111");
		jsonArrayList.add(map);
		jsonArrayList.add(user.getDtoToJson());

		System.out.println(jsonArrayList.toString());

		for (Field dd : user.getClass().getFields()) {
			System.out.println(dd.getName().toString());
			if (StringUtils.isNotEmpty((String) dd.get(user))) {
				System.out.println(dd.get(user));
			}

		}
	}
}
