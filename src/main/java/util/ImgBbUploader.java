package util;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ImgBbUploader {
    private static HttpRequestManager requestManager = null;
    private ImgBbUploader() { }

    public static String getImageBase64FromRequestBody(HttpServletRequest request) throws IOException {
        requestManager = HttpRequestManager.getInstance();

        String requestBody = requestManager.getRequestBodyFromClientRequest(request);
        String imageBase64 = ImgBB.convertImageObjToBase64(requestBody);

        return imageBase64;
    }

    public static String uploadImage(String imageBase64) throws IOException {
        requestManager = HttpRequestManager.getInstance();

        requestManager.connect(ImgBB.API_URL);
        requestManager.sendParams(ImgBB.getParams(imageBase64));
        String body = requestManager.getResponseBodyFromRequest();

        return ImgBB.getImageUrl(body);
    }
}
