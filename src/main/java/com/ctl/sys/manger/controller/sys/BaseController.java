package com.ctl.sys.manger.controller.sys;

import com.ctl.sys.manger.shiro.util.ResponseCode;
import com.ctl.sys.manger.shiro.util.Result;
import net.sf.json.JSONObject;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 基础controller,方便统一异常处理
 *
 * @Author: ouyangan
 * @Date : 2016/10/8
 */
public class BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    //根据请求类型,响应不同类型
    @ExceptionHandler(Exception.class)
    public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        logger.error("exception occur : \n {}", StringUtil.exceptionDetail(exception));
        if (request.getHeader("Accept").contains("application/json")) {
            logger.debug("qingqiu");
            Result result = Result.error();
            if (exception instanceof IncorrectCredentialsException) {
                result = Result.instance(ResponseCode.password_incorrect.getCode(), ResponseCode.password_incorrect.getMsg());
                //账号不存在
            } else if (exception instanceof UnknownAccountException) {
                result = Result.instance(ResponseCode.unknown_account.getCode(), ResponseCode.unknown_account.getMsg());
                //未授权
            } else if (exception instanceof UnauthorizedException) {
                result = Result.instance(ResponseCode.unauthorized.getCode(), ResponseCode.unauthorized.getMsg());
                //未登录
            } else if (exception instanceof UnauthenticatedException) {
                result = Result.instance(ResponseCode.unauthenticated.getCode(), ResponseCode.unauthenticated.getMsg());
                //缺少参数
            } else if (exception instanceof MissingServletRequestParameterException) {
                result = Result.instance(ResponseCode.missing_parameter.getCode(), ResponseCode.missing_parameter.getMsg());
                //参数格式错误
            } else if ((exception instanceof MethodArgumentTypeMismatchException)) {
                result = Result.instance(ResponseCode.param_format_error.getCode(), ResponseCode.param_format_error.getMsg());
                //ip限制
            } else if (exception.getCause().getMessage().contains("system.exception.ForbiddenIpException")) {
                result = Result.instance(ResponseCode.forbidden_ip.getCode(), ResponseCode.forbidden_ip.getMsg());
                //其他错误
            }

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().append(JSONObject.fromObject(result).toString());
            response.getWriter().flush();
            response.getWriter().close();
        } else {
            String basePath ="/";
            String url = "/error/internalError";

            if (exception instanceof UnauthorizedException) {
                url = "/error/unAuthorization";
            }
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.sendRedirect(basePath + url);
        }
    }
}
