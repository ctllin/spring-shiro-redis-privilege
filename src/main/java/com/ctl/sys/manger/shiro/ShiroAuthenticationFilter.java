package com.ctl.sys.manger.shiro;

import com.ctl.sys.manger.shiro.util.ResponseCode;
import com.ctl.sys.manger.shiro.util.Result;
import net.sf.json.JSONObject;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author ouyangan
 * @Date 2016/11/1/19:32
 * @Description 登录过滤器
 */
public class ShiroAuthenticationFilter extends PassThruAuthenticationFilter {
    private static Logger log = LoggerFactory.getLogger(ShiroAuthorizationFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            saveRequest(request);
            if (((HttpServletRequest) request).getHeader("Accept").contains("application/json")) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                Result result = new Result(ResponseCode.unauthenticated.getCode(), ResponseCode.unauthenticated.getMsg());
                response.getWriter().append(JSONObject.fromObject(result).toString());
                response.getWriter().flush();
                response.getWriter().close();
            } else {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                //ctl modify 首页地址
                //((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath()+"/");
                request.getRequestDispatcher("/").forward(request,response);
            }
            return false;
        }
    }
}
