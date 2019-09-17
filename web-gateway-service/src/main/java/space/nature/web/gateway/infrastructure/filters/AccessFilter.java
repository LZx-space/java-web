package space.nature.web.gateway.infrastructure.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String requestURI = request.getRequestURI();
        System.out.println(requestURI + "---------------------------");
//        String token = request.getHeader("token");
//        if (token == null) {
//            context.setSendZuulResponse(false);
//            context.setResponseStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
//        }
        return null;
    }
}
