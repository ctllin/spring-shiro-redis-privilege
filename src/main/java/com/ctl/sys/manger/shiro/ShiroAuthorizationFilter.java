package com.ctl.sys.manger.shiro;

import com.ctl.sys.manger.shiro.util.ResponseCode;
import com.ctl.sys.manger.shiro.util.Result;
import com.ctl.sys.manger.utils.ConfigUtils;
import net.sf.json.JSONObject;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Author ouyangan
 * @Date 2016/11/1/19:35
 * @Description 权限过滤器 未启用
 */
public class ShiroAuthorizationFilter extends AuthorizationFilter {
    private static Logger logger = LoggerFactory.getLogger(ShiroAuthorizationFilter.class);
    @Autowired
    private RedisTemplate redisTemplate;
    private JedisConnectionFactory jedisFactory;
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse response1 = (HttpServletResponse) response;
        Subject subject = getSubject(request, response);
        if (subject.getPrincipal() == null) {
            //未登录跳转至登陆页面
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
                ((HttpServletResponse) response).sendRedirect("/");
            }
        } else {
            //已登录未授权
            if (((HttpServletRequest) request).getHeader("Accept").contains("application/json")) {
                logger.debug("授权认证:未通过:json"+((HttpServletRequest) request).getRequestURL());
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                Result result = new Result(ResponseCode.unauthorized.getCode(), ResponseCode.unauthorized.getMsg());
                response.getWriter().append(JSONObject.fromObject(result).toString());
                response.getWriter().flush();
                response.getWriter().close();
            } else {
                logger.debug("授权认证:未通过:web"+((HttpServletRequest) request).getRequestURL());
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                ((HttpServletResponse) response).sendRedirect("/error/unAuthorization");
            }
        }
        return false;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        String[] perms = (String[]) mappedValue;
        boolean isPermitted = true;
        if (perms != null && perms.length > 0) {
            if (perms.length == 1) {
                if (!subject.isPermitted(perms[0])) {
                    logger.debug("授权认证:未通过");
                    isPermitted = false;
                }
            } else {
                if (!subject.isPermittedAll(perms)) {
                    logger.debug("授权认证:未通过");
                    isPermitted = false;
                }
            }
        }
        try {//认证成功后每次刷新权限有效时长
            Boolean expire = redisTemplate.expire(RedisCache.shiro_cache_prefix + subject.getPrincipal(), Integer.parseInt(ConfigUtils.getType("shiro.cache.timeout")), TimeUnit.SECONDS);
        } catch (NumberFormatException e) {
            logger.error("用户[{}]刷新权限cache失败", subject.getPrincipal(), e);
        }
        return isPermitted;
    }
}
