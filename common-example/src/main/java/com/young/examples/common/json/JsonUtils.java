package com.young.examples.common.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JsonUtils {

	public static List<HashMap<String,String>> jsonToObject(String json)
	{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(json, new TypeReference<ArrayList<HashMap<String,String>>>() {});
	}

	public static String toJson(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
	
}
