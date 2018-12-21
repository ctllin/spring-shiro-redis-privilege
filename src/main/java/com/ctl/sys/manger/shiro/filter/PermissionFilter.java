package com.ctl.sys.manger.shiro.filter;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 权限拦截器
 * @author
 * @since 1.0.0
 */
public class PermissionFilter extends AccessControlFilter {
	Logger logger = LoggerFactory.getLogger(PermissionFilter.class);
	
	/**即是否允许访问，返回 true 表示允许；
	 * <p>表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；</p>
	 * <p>如[hanshow-root:yes],[hanshow-root:no]</p>
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

		Subject subject = getSubject(request, response);
		RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		CacheManager cacheManager = securityManager.getCacheManager();

		HttpServletRequest httpRequest = ((HttpServletRequest) request);

		String uri = httpRequest.getRequestURI();// 获取URI
		String basePath = httpRequest.getContextPath();// 获取basePath
		if (null != uri && uri.startsWith(basePath)) {
			uri = uri.replaceFirst(basePath, "");
			int index = uri.lastIndexOf("/");
			uri=uri.substring(0, index) + ":" + uri.substring(index + 1);
		}
		if (subject.isPermitted(uri)) {
			return Boolean.TRUE;
		}
		
		logger.debug(subject.getSession().getId().toString() + "没有访问"+uri+"的权限！");
		return Boolean.FALSE;
	}
	
	/**
	 * 表示访问拒绝时是否自己处理，如果返回 true 表示自己不处理且继续拦 截器链执行，返回 false 表示自己已经处理了（比如重定向到另一个页面）
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//ctl modify 首页地址
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		return Boolean.FALSE;
	}

}
