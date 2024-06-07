package util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestManager {
	private static HttpURLConnection conn = null;

	private HttpRequestManager() {
	}

	public static HttpRequestManager getInstance() {
		return new HttpRequestManager();
	}

	public static void connect(String urlStr) {
		if (urlStr == null || urlStr.isEmpty())
			return;

		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			conn.setDoOutput(true);
			conn.setDoInput(true);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void sendParams(String params) {
		try {
			DataOutputStream os = new DataOutputStream(conn.getOutputStream());
			byte[] input = params.getBytes("UTF-8");
			os.write(input, 0, input.length);
			os.flush();
			os.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static String getRequestBodyFromClientRequest(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		String line;

		try (BufferedReader reader = request.getReader()) {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			System.out.println(e);
		}

		return sb.toString();
	}

	public static String getResponseBodyFromRequest() {
		StringBuilder responseBody = new StringBuilder();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String responseLine;

			while ((responseLine = br.readLine()) != null)
				responseBody.append(responseLine.trim());
		} catch (Exception e) {
			System.out.println(e);
		}

		return responseBody.toString();
	}
}
