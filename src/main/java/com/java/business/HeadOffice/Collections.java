package com.java.business.HeadOffice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Collections {

	public static void main(String[] args) {
		List<String> a = new ArrayList<>();
		a.add("sanjay");
		a.add("Kavin");
		System.out.println(a);
		a.add(1, "neelu");
		System.out.println(a);
		System.out.println(a.get(0));
		List<String> b = new ArrayList<>();
		b.addAll(a);
		System.out.println(b);
		for (String c : a) {
			System.out.println(c);
		}
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(2));
		}
		for (String c : a) {
			if (c.equals("sanjay")) {
				System.out.println("Yes");
			}
		}
		if (a.contains("kavin")) {
			System.out.println("Yes ");
		}

		Map<String, Object> d = new HashMap<>();
		d.put("1", "sanjay");
		d.put("2", "Kavin");
		d.put("dasda", "neelu");
		System.out.println(d);
		d.put("dasda", "sanjay paithiyam");
		System.out.println(d);

		Map<String, Object> e = new HashMap<>();
		e.putAll(d);
		System.out.println(e);

		System.out.println(d.get("dasda"));

		if (d.containsKey("dasda")) {
			System.out.println("Present");
		}
		if (d.containsValue("sweety")) {
			System.out.println("Present Value");
		}
		for (Entry<String, Object> f : d.entrySet()) {
			System.out.println("Key : " + f.getKey() + " Value : " + f.getValue());
		}

		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("1", "a");
		map.put("2", "b");
		map.put("3", "c");
		list.add(map);
		System.out.println(list);
		map = new HashMap<>();
		map.put("1", "c");
		map.put("2", "b");
		map.put("3", "a");
		list.add(map);
		System.out.println(list);
		map = new HashMap<>();
		map.put("1", "d");
		map.put("2", "e");
		map.put("3", "f");
		list.add(map);
		System.out.println(list);

		List<Map<String, Object>> returnList = new ArrayList<>();
		for (Map<String, Object> dataMap : list) {
			Map<String, Object> returnMap = new HashMap<>();
			for (Entry<String, Object> g : dataMap.entrySet()) {
				returnMap.put(g.getValue().toString(), g.getKey());
			}
			returnList.add(returnMap);
		}
		System.out.println(returnList);
	}

}
