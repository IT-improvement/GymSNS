package util;

import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLEncoder;

public class ImgBB {
    public static String API_URL = "https://api.imgbb.com/1/upload";
    private static String KEY = "d375f432d534ff8a8a0d08bb246f8e1b";
    private static String DEFAULT_PARAMS = "key=" + KEY + "&image=";

    private ImgBB() { }

    public static String convertImageObjToBase64(String objStr) {
        return objStr.split(":")[1].replace("\"", "").replace("}", "").trim();
    }

    public static String getParams(String imageBase64) {
        return DEFAULT_PARAMS + URLEncoder.encode(imageBase64);
    }

    public static String getImageUrl(String body) {
        JSONObject responseObj = new JSONObject(body);
        JSONObject dataObj = responseObj.getJSONObject("data");

        return dataObj.getString("url");
    }

    public static String uploadImage(String imageBase64) {
        HttpRequestManager requestManager = HttpRequestManager.getInstance();

        try {
            requestManager = HttpRequestManager.getInstance();

            requestManager.connect(ImgBB.API_URL);
            requestManager.sendParams(ImgBB.getParams(imageBase64));
            String body = requestManager.getResponseBodyFromRequest();

            return getImageUrl(body);
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
}
