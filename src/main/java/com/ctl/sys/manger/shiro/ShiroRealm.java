package com.ctl.sys.manger.shiro;

import com.ctl.sys.manger.mapper.SysRoleMapper;
import com.ctl.sys.manger.mapper.SysUserMapper;
import com.ctl.sys.manger.model.sys.Tresource;
import com.ctl.sys.manger.model.sys.Trole;
import com.ctl.sys.manger.po.SysUser;
import com.ctl.sys.manger.po.SysUserExample;
import com.ctl.sys.manger.shiro.util.SystemConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.*;

/**
 * @Author: ouyangan
 * @Date : 2016/10/7
 * @Description 自定义realm实现
 */
public class ShiroRealm extends AuthorizingRealm {
    private static Logger log = LoggerFactory.getLogger(ShiroRealm.class);
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 鉴权信息 重新登录后会重新调用此接口
     * 表示根据用户身份获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.debug("开始查询授权信息");
        SysUser sysUser = null;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String loginStr = (String) principalCollection.getPrimaryPrincipal();
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andLoginnameEqualTo(loginStr);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if(sysUsers!=null&&sysUsers.size()>=1){
            sysUser=sysUsers.get(0);
        }
        Set<String> permissions = new TreeSet<>();
        Set<String> roles = new TreeSet<>();
        List<Trole> rolesByUserId = sysUserMapper.getRolesByUserId(sysUser.getId());
        List<Tresource> permissionsByUserId = sysUserMapper.getPermissionsByUserId(sysUser.getId());
        for(Trole role:rolesByUserId){
            roles.add(role.getName());
        }
        for(Tresource tresource:permissionsByUserId) {
            if (tresource.getUrl() != null && !"".equals(tresource.getUrl())) {
                int index = tresource.getUrl().lastIndexOf("/");
                tresource.setUrl(tresource.getUrl().substring(0, index) + ":" + tresource.getUrl().substring(index + 1));
                permissions.add(tresource.getUrl());
            }
        }
//        SysUser user = sysUserMapper.selectUserByLoginName(loginStr);
//        List<SysUserPermission> userPermissions = sysUserPermissionMapper.selectByUserId(user.getId());
//        Set<String> permissions = new HashSet<>();
//        Set<String> roles = new HashSet<>();
//        for (SysUserPermission userPermission : userPermissions) {
//            SysPermission sysPermission = sysPermissionMapper.selectById(userPermission.getSysPermissionId());
//            permissions.add(sysPermission.getCode());
//        }
//        List<SysUserRoleOrganization> userRoleOrganizations = sysUserRoleOrganizationMapper.selectByUserId(user.getId());
//        for (SysUserRoleOrganization sysUserRoleOrganization : userRoleOrganizations) {
//            SysRoleOrganization sysRoleOrganization = sysRoleOrganizationMapper.selectById(sysUserRoleOrganization.getSysRoleOrganizationId());
//            SysRole sysRole = sysRoleMapper.selectById(sysRoleOrganization.getSysRoleId());
//            roles.add(sysRole.getName());
//            List<SysRolePermission> sysRolePermissions = sysRolePermissionMapper.selectByRoleId(sysRole.getId());
//            for (SysRolePermission sysRolePermission : sysRolePermissions) {
//                SysPermission sysPermission = sysPermissionMapper.selectById(sysRolePermission.getSysPermissionId());
//                permissions.add(sysPermission.getCode());
//            }
//        }
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        log.debug("角色信息: \n {}", Arrays.deepToString(roles.toArray()));
        log.debug("权限信息: \n{}", Arrays.deepToString(permissions.toArray()));
        return info;
    }

    /**
     * 登录验证
     * 表示获取身份验证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.debug("登录验证");
        String loginName = (String) authenticationToken.getPrincipal();
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager)securityManager.getSessionManager();
        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();//获取当前已登录的用户session列表
        for(Session session:sessions){
            //清除该用户以前登录时保存的session
            if(loginName.equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)))) {
                sessionManager.getSessionDAO().delete(session);
            }
        }
        SysUser sysUser = null;//sysUserMapper.selectUserByLoginName(loginName);
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andLoginnameEqualTo(loginName);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if(sysUsers!=null&&sysUsers.size()>=1){
            sysUser=sysUsers.get(0);
        }
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(loginName, sysUser.getPassword(), ByteSource.Util.bytes(sysUser.getPassword_salt()), getName());
        return authenticationInfo;
    }

    @Override
    protected void doClearCache(PrincipalCollection principals) {
        redisTemplate.delete(SystemConstant.shiro_cache_prefix + principals.getPrimaryPrincipal().toString());
    }

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        log.debug("clearCachedAuthorizationInfo");
        super.clearCachedAuthorizationInfo(principals);
        //this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
