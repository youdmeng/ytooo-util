package ml.ytooo.filter;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    HttpServletRequest orgRequest;
    private static final HTMLFilter htmlFilter = new HTMLFilter();
    private static final SQLFilter sqlFilter = new SQLFilter();

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.orgRequest = request;
    }

    public ServletInputStream getInputStream() throws IOException {
        String type = super.getHeader("Content-Type");
        if (StringUtils.indexOfIgnoreCase(type, "application/json") < 0) {
            return super.getInputStream();
        } else {
            String json = IOUtils.toString(super.getInputStream(), StandardCharsets.UTF_8);
            if (StringUtils.isBlank(json)) {
                return super.getInputStream();
            } else {
                json = this.xssSqlEncode(json);
                final ByteArrayInputStream bis = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
                return new ServletInputStream() {
                    public boolean isFinished() {
                        return true;
                    }

                    public boolean isReady() {
                        return true;
                    }

                    public void setReadListener(ReadListener readListener) {
                    }

                    public int read() throws IOException {
                        return bis.read();
                    }
                };
            }
        }
    }

    public String getParameter(String name) {
        String value = super.getParameter(this.xssSqlEncode(name));
        if (StringUtils.isNotBlank(value)) {
            value = this.xssSqlEncode(value);
        }

        return value;
    }

    public String[] getParameterValues(String name) {
        String[] parameters = super.getParameterValues(name);
        if (parameters != null && parameters.length != 0) {
            for(int i = 0; i < parameters.length; ++i) {
                parameters[i] = this.xssSqlEncode(parameters[i]);
            }

            return parameters;
        } else {
            return null;
        }
    }

    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> map = new LinkedHashMap();
        Map<String, String[]> parameters = super.getParameterMap();
        Iterator var3 = parameters.keySet().iterator();

        while(var3.hasNext()) {
            String key = (String)var3.next();
            String[] values = (String[])parameters.get(key);

            for(int i = 0; i < values.length; ++i) {
                values[i] = this.xssSqlEncode(values[i]);
            }

            map.put(key, values);
        }

        return map;
    }

    public String getHeader(String name) {
        String value = super.getHeader(this.xssSqlEncode(name));
        if (StringUtils.isNotBlank(value)) {
            value = this.xssSqlEncode(value);
        }

        return value;
    }

    private String xssSqlEncode(String input) {
        String htmlOutput = htmlFilter.filter(input);
        SQLFilter var10000 = sqlFilter;
        return SQLFilter.sqlInject(htmlOutput);
    }

    public HttpServletRequest getOrgRequest() {
        return this.orgRequest;
    }

    public static HttpServletRequest getOrgRequest(HttpServletRequest request) {
        return request instanceof XssHttpServletRequestWrapper ? ((XssHttpServletRequestWrapper)request).getOrgRequest() : request;
    }
}
