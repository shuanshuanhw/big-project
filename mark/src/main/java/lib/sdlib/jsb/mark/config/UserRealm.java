package lib.sdlib.jsb.mark.config;

/**
 * 类名： UserRealm
 * 描述 TODO：
 * 创建时间： 2022/5/31 16:51
 * 创建人： Administrator
 */

import lib.sdlib.jsb.mark.dao.RoleMapper;
import lib.sdlib.jsb.mark.dao.RoleMenuMapper;
import lib.sdlib.jsb.mark.dao.UserRoleMapper;
import lib.sdlib.jsb.mark.entity.User;
import lib.sdlib.jsb.mark.entity.UserRole;
import lib.sdlib.jsb.mark.exception.*;
import lib.sdlib.jsb.mark.service.ISysRoleService;
import lib.sdlib.jsb.mark.service.SysLoginService;
import lib.sdlib.jsb.mark.utils.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义Realm 处理登录 权限
 *
 * @author ruoyi
 */
public class UserRealm extends AuthorizingRealm
{
    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

//    @Autowired
//    private ISysMenuService menuService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private SysLoginService loginService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0)
    {
        User user = ShiroUtils.getSysUser();
        // 角色列表
        Set<String> roles = new HashSet<String>();
        // 功能列表
    //    Set<String> menus = new HashSet<String>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 管理员拥有所有权限
        if (user.isAdmin())
        {
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        }
        else
        {
            List<String> userRoles = userRoleMapper.selectRoleByUserId(user.getUser_id());
            for(String userRole: userRoles)
            {
                roles.add(userRole);
            }
  //          roles = roleService.selectRoleKeys(user.getUser_id());

//            menus = menuService.selectPermsByUserId(user.getUserId());
            // 角色加入AuthorizationInfo认证对象
            info.setRoles(roles);
//            // 权限加入AuthorizationInfo认证对象
//            info.setStringPermissions(menus);
        }
        return info;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = "";
        if (upToken.getPassword() != null)
        {
            password = new String(upToken.getPassword());
        }

        User user = null;
        try
        {
            user = loginService.login(username, password);
        }
        catch (CaptchaException e)
        {
            throw new AuthenticationException(e.getMessage(), e);
        }
        catch (UserNotExistsException e)
        {
            throw new UnknownAccountException(e.getMessage(), e);
        }
        catch (UserPasswordNotMatchException e)
        {
            throw new IncorrectCredentialsException(e.getMessage(), e);
        }
        catch (UserPasswordRetryLimitExceedException e)
        {
            throw new ExcessiveAttemptsException(e.getMessage(), e);
        }
        catch (UserBlockedException e)
        {
            throw new LockedAccountException(e.getMessage(), e);
        }
        catch (RoleBlockedException e)
        {
            throw new LockedAccountException(e.getMessage(), e);
        }
        catch (Exception e)
        {
            log.info("对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());

        return info;
    }

    /**
     * 清理指定用户授权信息缓存
     */
    public void clearCachedAuthorizationInfo(Object principal)
    {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        this.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清理所有用户授权信息缓存
     */
    public void clearAllCachedAuthorizationInfo()
    {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null)
        {
            for (Object key : cache.keys())
            {
                cache.remove(key);
            }
        }
    }
}