/*
 * Copyright (c) 2019, LZx
 */

package space.nature.util;

import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public abstract class HttpServletHelper {

    /**
     * 设置下载文件的HTTP Header，IETF RFC 6266 & 5987
     *
     * @param response 请求的响应
     * @param filename 目标文件名
     */
    public static void setFileDownloadHeader(HttpServletResponse response, String filename) {
        String encodeFilename = URLEncoder.encode(filename, StandardCharsets.UTF_8);
        String disposition = "attachment; filename=" +
                encodeFilename + "; filename*=utf-8'zh-cn'" +
                encodeFilename;
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, disposition);
        // for IE8 bug
        response.setHeader(HttpHeaders.CACHE_CONTROL, "must-revalidate");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "post-check=0");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "pre-check=0");
    }

}
