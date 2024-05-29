package util;

import org.json.JSONObject;

public class ApiResponseManager {
	private ApiResponseManager() { }
	
	public static JSONObject getStatusObject(int statusNumber) {
		JSONObject resObj = new JSONObject();
		String message = "";

		resObj.put("status", statusNumber);
		
		switch (statusNumber) {
			case 200:
				message = "OK";
				break;
			case 400:
				message = "Bad Request";
				break;
			case 401:
				message = "Unauthorized";
				break;
			case 404:
				message = "Not Found";
				break;
			case 500:
				message = "Server Error";
				break;
		}

		resObj.put("message", message);
		
		return resObj;
	}
	
	public static JSONObject getStatusObject(int statusNumber, String message) {
		JSONObject resObj = new JSONObject();

		resObj.put("status", statusNumber);
		resObj.put("message", message);
		
		return resObj;
	}
}