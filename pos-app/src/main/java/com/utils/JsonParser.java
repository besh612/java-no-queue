package com.utils;

import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonParser {

	public static JSONArray getDataList() throws Exception {
		JSONParser jsonParser = new JSONParser();
		Reader reader = new FileReader("pos-app/src/main/resources/datas/db.json");
		JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
		return (JSONArray) jsonObject.get("lists");
	}

	public static void main(String[] args) throws Exception {
		getDataList();
	}
}
