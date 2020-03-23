package com.ytooo.http;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

public class HttpUtil {
    private HttpUtil() {
        throw new IllegalStateException("Utility class, HttpUtil");
    }

    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    private static final String ACCEPT = "Accept";

    private static final String APPLICATION_JASON = "application/json";

    private static final String CONTENT_TYPE = "Content-Type";

    private static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";

    private static final String UTF_8 = "UTF-8";

    private static final String HTTP_REQUEST_FAILED_S = "http request failed %s";

    private static final String JSON_RESPONSE_IS_NULL = "jsonResponse is null";

    public static String httpSend(String url, Map<String, Object> param, boolean post) {
        String response = null;
        if (post) {
            response = post(url, param);
        } else {
            response = get(url, param);
        }
        return response;
    }

    public static String post(String baseUrl, Map<String, Object> paramMap) {
        return post(baseUrl, null, null, null, paramMap, null, false);
    }

    public static String post(String baseUrl, String json) {
        return post(baseUrl, null, null, null, null, json, false);
    }

    public static String post(String baseUrl, Map<String, String> headers, String json, boolean contentType) {
        return post(baseUrl, headers, null, null, null, json, contentType);
    }

    public static String post(String baseUrl, Map<String, String> headerMap, String routekey, String routevalue, Map<String, Object> paramMap,
            String body, boolean contentType) {
        HttpResponse<String> jsonResponse = null;
        HttpRequestWithBody httpRequestWithBody = null;
        try {
            if (contentType && !CollectionUtils.isEmpty(headerMap)) {
                httpRequestWithBody = Unirest.post(baseUrl).headers(headerMap);
            } else {
                httpRequestWithBody = Unirest.post(baseUrl).header("Accept", "application/json")
                        .header("Content-Type", "application/json;charset=UTF-8");
            }
            if (StringUtils.isNotEmpty(routekey))
                httpRequestWithBody.routeParam(routekey, routevalue);
            if (!CollectionUtils.isEmpty(paramMap))
                httpRequestWithBody.queryString(paramMap);
            if (StringUtils.isNotEmpty(body))
                httpRequestWithBody.body(body);
            jsonResponse = httpRequestWithBody.asString();
        } catch (UnirestException e) {
            log.error(String.format("http request failed %s", new Object[] { baseUrl }), (Throwable) e);
        }
        if (jsonResponse != null) {
            if (isOKStatusCode(jsonResponse.getStatus())) {
                log.info((String) jsonResponse.getBody());
                return (String) jsonResponse.getBody();
            }
            log.error("http request failed {}{}{}", new Object[] { baseUrl, Integer.valueOf(jsonResponse.getStatus()), jsonResponse.getBody() });
            return "";
        }
        log.error("jsonResponse is null");
        return "";
    }

    public static Map<String, Object> post(String baseUrl, Map<String, String> headerMap, String routekey, String routevalue,
            Map<String, Object> paramMap, String body) {
        String response = null;
        HttpRequestWithBody httpRequestWithBody = null;
        httpRequestWithBody = Unirest.post(baseUrl).header("Accept", "application/json").header("Content-Type", "application/json;charset=UTF-8");
        try {
            if (!CollectionUtils.isEmpty(headerMap))
                httpRequestWithBody = Unirest.post(baseUrl).headers(headerMap);
            if (StringUtils.isNotEmpty(routekey))
                httpRequestWithBody.routeParam(routekey, routevalue);
            if (!CollectionUtils.isEmpty(paramMap))
                httpRequestWithBody.queryString(paramMap);
            if (StringUtils.isNotEmpty(body))
                httpRequestWithBody.body(body);
            HttpResponse<String> httpResponse = httpRequestWithBody.asString();
            response = (String) httpResponse.getBody();
            log.info("http request success {}{}{}", new Object[] { baseUrl, Integer.valueOf(httpResponse.getStatus()), response });
            if (!JSON.isValid(response)) {
                Map<String, Object> result = new HashMap<>();
                result.put("status", "success");
                result.put("result", response);
                return result;
            }
        } catch (UnirestException e) {
            log.error("http request failed {}{}", baseUrl, response);
        }
        return (Map<String, Object>) JSON.parseObject(response, Map.class, new com.alibaba.fastjson.parser.Feature[0]);
    }

    public static String put(String baseUrl, Map<String, Object> paramMap) {
        return put(baseUrl, null, null, null, paramMap, null);
    }

    public static String put(String baseUrl, String json) {
        return put(baseUrl, null, null, null, null, json);
    }

    public static String put(String baseUrl, Map<String, String> headerMap, String routekey, String routevalue, Map<String, Object> paramMap,
            String body) {
        String response = null;
        HttpRequestWithBody httpRequestWithBody = null;
        httpRequestWithBody = Unirest.put(baseUrl).header("Accept", "application/json").header("Content-Type", "application/json;charset=UTF-8");
        try {
            if (!CollectionUtils.isEmpty(headerMap))
                httpRequestWithBody = Unirest.post(baseUrl).headers(headerMap);
            if (StringUtils.isNotEmpty(routekey))
                httpRequestWithBody.routeParam(routekey, routevalue);
            if (!CollectionUtils.isEmpty(paramMap))
                httpRequestWithBody.queryString(paramMap);
            if (StringUtils.isNotEmpty(body))
                httpRequestWithBody.body(body);
            HttpResponse<String> httpResponse = httpRequestWithBody.asString();
            if (isOKStatusCode(httpResponse.getStatus()))
                return (String) httpResponse.getBody();
        } catch (UnirestException e) {
            log.error(String.format("http request failed %s %s", new Object[] { baseUrl, response }));
        }
        return response;
    }

    public static String post(String baseUrl, String fileName, InputStream uploadFile, Map<String, Object> paramMap) {
        HttpResponse<String> jsonResponse = null;
        try {
            jsonResponse = Unirest.post(baseUrl).fields(paramMap).field(fileName, uploadFile, fileName).asString();
        } catch (UnirestException e) {
            log.error(String.format("http request failed %s", new Object[] { baseUrl }), (Throwable) e);
        }
        return checkJsonResponse(jsonResponse, baseUrl);
    }

    public static String post(String baseUrl, String fileName, InputStream uploadFile, Map<String, Object> paramMap, String routeKey,
            String routeValue) {
        Preconditions.checkArgument((
                !StringUtils.isEmpty(routeKey) || !StringUtils.isEmpty(routeValue)), "routeKey|routeValue can't be empty.");
        HttpResponse<String> jsonResponse = null;
        try {
            if (StringUtils.isEmpty(fileName) || null == uploadFile) {
                jsonResponse = Unirest.post(baseUrl).routeParam(routeKey, URLEncoder.encode(routeValue, "UTF-8")).fields(paramMap).asString();
            } else {
                jsonResponse = Unirest.post(baseUrl).routeParam(routeKey, URLEncoder.encode(routeValue, "UTF-8")).fields(paramMap)
                        .field(fileName, uploadFile, fileName).asString();
            }
        } catch (UnsupportedEncodingException | UnirestException e) {
            log.error(String.format("http request failed %s", new Object[] { baseUrl }), e);
        }
        return checkJsonResponse(jsonResponse, baseUrl);
    }

    public static String post(String baseUrl, Map<String, Object> paramMap, String routeKey, String routeValue) {
        return post(baseUrl, (String) null, (InputStream) null, paramMap, routeKey, routeValue);
    }

    public static String get(String baseUrl, Map<String, Object> paramMap) {
        return get(baseUrl, paramMap, null, null);
    }

    public static ByteArrayOutputStream download(String baseUrl, Map<String, Object> paramMap) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(baseUrl);
        HttpEntity entity = null;

        org.apache.http.HttpResponse response = httpClient.execute(httpGet);
        if (200 != response.getStatusLine().getStatusCode()) {
            log.error("文件 [" + baseUrl + "] 读取失败: " + response.getStatusLine().getReasonPhrase());
            return null;
        }

        entity = response.getEntity();
        InputStream in = entity.getContent();

        long length = entity.getContentLength();
        if (0 >= length) {
            log.error("文件 ["+baseUrl+"] 不存在");
            return null;
        }
        byte[] buffer = new byte[1024];
        int readLength = 0;


        while ((readLength = in.read(buffer)) > 0) {
            out.write(buffer, 0, readLength);
        }
        return out;
    }

    public static String get(String baseUrl, Map<String, Object> paramMap, String routeKey, String routeValue) {
        HttpResponse<String> jsonResponse = null;
        try {
            if (!StringUtils.isEmpty(routeKey) && !StringUtils.isEmpty(routeValue)) {
                jsonResponse = Unirest.get(baseUrl).header("Accept", "application/json").header("Content-Type", "application/json;charset=UTF-8")
                        .routeParam(routeKey, URLEncoder.encode(routeValue, "UTF-8")).queryString(paramMap).asString();
            } else {
                jsonResponse = Unirest.get(baseUrl).header("Accept", "application/json").header("Content-Type", "application/json;charset=UTF-8")
                        .queryString(paramMap).asString();
            }
        } catch (UnirestException | UnsupportedEncodingException e) {
            log.error(String.format("http request failed %s", new Object[] { baseUrl }), e);
        }
        return checkJsonResponse(jsonResponse, baseUrl);
    }

    public static String delete(String baseUrl, Map<String, Object> paramMap) {
        return delete(baseUrl, paramMap, null, null, null);
    }

    public static String delete(String baseUrl, String body) {
        return delete(baseUrl, null, body, null, null);
    }

    public static String delete(String baseUrl, Map<String, Object> paramMap, String body, String routeKey, String routeValue) {
        HttpResponse<String> jsonResponse = null;
        HttpRequestWithBody httpRequestWithBody = Unirest.delete(baseUrl).header("Accept", "application/json")
                .header("Content-Type", "application/json;charset=UTF-8");
        try {
            if (!StringUtils.isEmpty(routeKey) && !StringUtils.isEmpty(routeValue))
                httpRequestWithBody.routeParam(routeKey, URLEncoder.encode(routeValue, "UTF-8"));
            if (!CollectionUtils.isEmpty(paramMap))
                httpRequestWithBody.queryString(paramMap);
            if (StringUtils.isNotEmpty(body))
                httpRequestWithBody.body(body);
            jsonResponse = httpRequestWithBody.asString();
        } catch (UnsupportedEncodingException | UnirestException e) {
            log.error(String.format("http request failed %s", new Object[] { baseUrl }), e);
        }
        return checkJsonResponse(jsonResponse, baseUrl);
    }

    private static boolean isOKStatusCode(int statuscode) {
        switch (statuscode) {
        case 200:
            return true;
        case 202:
            return true;
        case 201:
            return true;
        case 302:
            return true;
        }
        return false;
    }

    private static String checkJsonResponse(HttpResponse<String> jsonResponse, String baseUrl) {
        if (jsonResponse != null) {
            if (isOKStatusCode(jsonResponse.getStatus()))
                return (String) jsonResponse.getBody();
            log.error("http post request failed {}{}", baseUrl, Integer.valueOf(jsonResponse.getStatus()));
            return "";
        }
        log.error("jsonResponse is null");
        return "";
    }
}
