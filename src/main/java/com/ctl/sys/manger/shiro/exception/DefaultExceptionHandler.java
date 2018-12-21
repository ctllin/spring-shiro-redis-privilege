package com.ctl.sys.manger.shiro.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
/**
 * <p>User: ctl
 * <p>Date: 20181221
 * <p>Version: 1.0
 */
@ControllerAdvice
public class DefaultExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    /**
     * 没有权限 异常@RequiresRoles  @RequiresPermissions
     * 后续根据不同的需求定制即可
     */
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e);
        mv.setViewName("unauthorized");
        //login name
        String loginname = request.getRemoteUser();
        //project name
        String contextPath = request.getContextPath();
        //ServletWebRequest: uri=/manager/user/dataGrid;client=0:0:0:0:0:0:0:1;session=shiro-session-aed72488-cd8e-44a6-b15d-1ec69356efb7;user=ctl
        logger.error("用户[{}],没有权限访问{}的权限" ,loginname,request.toString().split(";")[0].split("\\:")[1].trim(),e);
        return mv;
    }
}
