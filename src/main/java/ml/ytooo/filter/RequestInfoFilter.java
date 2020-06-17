package ml.ytooo.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RequestInfoFilter implements Filter {
    private static final Log logger = LogFactory.getLog(RequestInfoFilter.class);
    private static final List<String> ignoreList = new ArrayList();

    public RequestInfoFilter() {
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        ignoreList.add("js");
        ignoreList.add("css");
        ignoreList.add("png");
        ignoreList.add("ico");
        ignoreList.add("gif");
        ignoreList.add("jpg");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        try {
            long startTime = System.currentTimeMillis();
            String uri = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());
            if (!isIgnore(uri)) {
                logger.info("==================== RequestInfoFilter Start ====================");
                logger.info(request.getMethod() + " : " + uri);
                logger.info("session存活时间：" + request.getSession().getMaxInactiveInterval());
                this.logHeaders(request);
                this.logParams(request);
                chain.doFilter(request, response);
                long endTime = System.currentTimeMillis();
                logger.info(request.getMethod() + " 耗时：" + (endTime - startTime) + " ms");
                logger.info("==================== RequestInfoFilter End ====================");
            } else {
                chain.doFilter(request, response);
            }
        } catch (Exception var11) {
            logger.error("doFilter error", var11);
        }

    }

    private void logHeaders(HttpServletRequest request) {
        Map<String, String> headerMap = new HashMap();
        Enumeration headers = request.getHeaderNames();

        while(headers.hasMoreElements()) {
            String headName = (String)headers.nextElement();
            if (headName != null && !"".equals(headName)) {
                headerMap.put(headName, request.getHeader(headName));
            }
        }

        headerMap.put("RemoteHost", request.getRemoteHost() + ":" + request.getRemotePort());
        logger.info(headerMap);
    }

    private void logParams(HttpServletRequest request) {
        Map<String, String> maps = new HashMap();
        Enumeration keys = request.getParameterNames();

        while(keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            if (StringUtils.isNotEmpty(key)) {
                String values = request.getParameter(key);
                maps.put(key, values);
            }
        }

        logger.info(maps.toString());
    }

    private static final boolean isIgnore(String url) {
        boolean ignore = false;
        int index = url.lastIndexOf(46);
        if (index > 0) {
            String subFix = url.substring(index + 1, url.length());
            if (ignoreList.contains(subFix)) {
                ignore = true;
            }
        }

        return ignore;
    }
}
