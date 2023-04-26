package com.marvin.web.resolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public interface CurrentRequestHeaderResolver {

    default Map<String, String> headers(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String header = enumeration.nextElement();
            headers.put(header, request.getHeader(header));
        }
        return headers;
    }
}
