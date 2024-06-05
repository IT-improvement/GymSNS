package util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLEncoder;

public class ImgBB {
    public static String API_URL = "https://api.imgbb.com/1/upload";
    private static String KEY = "d375f432d534ff8a8a0d08bb246f8e1b";
    private static String DEFAULT_PARAMS = "key=" + KEY + "&image=";

    private ImgBB() { }

    public static String convertImageObjToBase64(String objStr) throws IOException {
        return objStr.split(":")[1].replace("\"", "").replace("}", "").trim();
    }

    public static String getParams(String imageBase64) throws IOException {
        return DEFAULT_PARAMS + URLEncoder.encode(imageBase64);
    }

    public static String getImageUrl(String body) {
        String[] arr = body.split("url");
        String[] arr2 = arr[2].split("\"");

        return arr2[2].replace("\\", "");
    }

    public static void upload() {

    }
}
