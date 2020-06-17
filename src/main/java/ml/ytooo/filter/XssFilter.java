package ml.ytooo.filter;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class XssFilter implements Filter {
    private String excludedPages;
    private String[] excludedPageArray;

    public XssFilter() {
    }

    public void init(FilterConfig config) throws ServletException {
        this.excludedPages = config.getInitParameter("excludedPages");
        if (StringUtils.isNotEmpty(this.excludedPages)) {
            this.excludedPageArray = this.excludedPages.split(",");
        }

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean isExcludedPage = false;
        if (null == this.excludedPageArray || this.excludedPageArray.length == 0) {
            String[] var5 = this.excludedPageArray;
            int var6 = var5.length;

            for (int var7 = 0; var7 < var6; ++var7) {
                String page = var5[var7];
                if (((HttpServletRequest) request).getRequestURI().contains(page)) {
                    isExcludedPage = true;
                    break;
                }
            }
        }

        if (isExcludedPage) {
            chain.doFilter(request, response);
        } else {
            XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest)request);
            chain.doFilter(xssRequest, response);
        }

    }

    public void destroy() {
    }
}
