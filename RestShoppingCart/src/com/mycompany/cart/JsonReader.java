package com.mycompany.cart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {

	/**
	 * @param args
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException,
			IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("phones.json"));
		//phones.json is in angularjs tutorial project
		JSONArray ja = (JSONArray) obj;
		// System.out.println(ja.toString());
		List<Phone> phones = new ArrayList<>();

		for (int i = 0; i < ja.size(); i++) {
			JSONObject jo = (JSONObject) ja.get(i);
			try {
				phones.add(convertJsonToPhone(jo));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Set<String> keys = jo.keySet();
			// System.out.println(keys.toString());
			// for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
			// String key = (String) iterator.next();
			//
			// System.out.println(jo.get(key));
			// }
			// System.out.println(jo.get("id"));
			// System.out.println(jo.get("name"));
			// System.out.println(jo.get("age"));
			// System.out.println(jo.get(""));
			// System.out.println(jo.get("id"));
		}
		System.out.println(phones.size()+""+phones.get(0).getName());
	}

	public static Phone convertJsonToPhone(JSONObject jo) {
		Phone phone = new Phone();
		
		for (Field field : Phone.class.getDeclaredFields()){
				String key = field.getName();
				field.setAccessible(true);
				if(jo.containsKey(key)){
				String fieldValue = jo.get(key).toString();
				if(fieldValue!= null && !fieldValue.isEmpty()){
				if(key.equals("age"))
					try {
						field.set(phone, Integer.parseInt(fieldValue));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				else
					try {
						field.set(phone, jo.get(key));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		return phone;
	}
	
	public List<Phone> getPhones()
	{
		ObjectMapper mapper = new ObjectMapper();
		JsonFactory jf = new JsonFactory();
		ArrayList<Phone> phones = null;
		
		try {
			JsonParser parser = jf.createJsonParser(new File("phones.json"));
			TypeReference<ArrayList<Phone>> tr = new TypeReference<ArrayList<Phone>>(){};
			phones = mapper.readValue(new File("phones.json"), tr);
			System.out.println(phones.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phones;
	}
}
