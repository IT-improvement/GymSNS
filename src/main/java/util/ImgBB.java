package util;

import org.json.JSONObject;

import java.net.URLEncoder;

public class ImgBB {
    private static String API_URL = "https://api.imgbb.com/1/upload";
    private static String KEY = "d375f432d534ff8a8a0d08bb246f8e1b";
    private static String DEFAULT_PARAMS = "key=" + KEY + "&image=";

    private ImgBB() { }

    public static String convertImageObjToBase64(String objStr) {
        try {
            return objStr.split(":")[1].replace("\"", "").replace("}", "").trim();
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public static String getParams(String imageBase64) {
        return DEFAULT_PARAMS + URLEncoder.encode(imageBase64);
    }

    public static String getImageUrl(String body) {
        try {
            JSONObject responseObj = new JSONObject(body);
            JSONObject dataObj = responseObj.getJSONObject("data");

            return dataObj.getString("url");
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public static String uploadImage(String imageBase64) {
        try {
            HttpRequestManager requestManager = HttpRequestManager.getInstance();

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
