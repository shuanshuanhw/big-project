package lib.sdlib.jsb.mark.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

import lib.sdlib.jsb.mark.common.Constants;
import lib.sdlib.jsb.mark.filter.*;
import lib.sdlib.jsb.mark.utils.CipherUtils;
import lib.sdlib.jsb.mark.utils.CustomShiroFilterFactoryBean;
import lib.sdlib.jsb.mark.utils.StringUtils;
import net.sf.ehcache.CacheManager;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 权限配置加载
 * 
 * @author ruoyi
 */
@Configuration
public class ShiroConfig
{
    /**
     * Session超时时间，单位为毫秒（默认30分钟）
     */
    @Value("${shiro.session.expireTime}")
    private int expireTime;

    /**
     * 相隔多久检查一次session的有效性，单位毫秒，默认就是10分钟
     */
    @Value("${shiro.session.validationInterval}")
    private int validationInterval;

    /**
     * 同一个用户最大会话数
     */
    @Value("${shiro.session.maxSession}")
    private int maxSession;

    /**
     * 踢出之前登录的/之后登录的用户，默认踢出之前登录的用户
     */
    @Value("${shiro.session.kickoutAfter}")
    private boolean kickoutAfter;

    /**
     * 验证码开关
     */
    @Value("${shiro.user.captchaEnabled}")
    private boolean captchaEnabled;

    /**
     * 验证码类型
     */
    @Value("${shiro.user.captchaType}")
    private String captchaType;

    /**
     * 设置Cookie的域名
     */
    @Value("${shiro.cookie.domain}")
    private String domain;

    /**
     * 设置cookie的有效访问路径
     */
    @Value("${shiro.cookie.path}")
    private String path;

    /**
     * 设置HttpOnly属性
     */
    @Value("${shiro.cookie.httpOnly}")
    private boolean httpOnly;

    /**
     * 设置Cookie的过期时间，秒为单位
     */
    @Value("${shiro.cookie.maxAge}")
    private int maxAge;

    /**
     * 设置cipherKey密钥
     */
    @Value("${shiro.cookie.cipherKey}")
    private String cipherKey;

    /**
     * 登录地址
     */
    @Value("${shiro.user.loginUrl}")
    private String loginUrl;

    /**
     * 权限认证失败地址
     */
    @Value("${shiro.user.unauthorizedUrl}")
    private String unauthorizedUrl;

    /**
     * 是否开启记住我功能
     */
    @Value("${shiro.rememberMe.enabled: false}")
    private boolean rememberMe;

    /**
     * 缓存管理器 使用Ehcache实现
     */
    @Bean
    public EhCacheManager getEhCacheManager()
    {
        CacheManager cacheManager = CacheManager.getCacheManager("ruoyi");
        EhCacheManager em = new EhCacheManager();
        if (StringUtils.isNull(cacheManager))
        {
            // 如果cacheManager对象为空
            em.setCacheManager(new CacheManager(getCacheManagerConfigFileInputStream()));
            return em;
        }
        else
        {
            em.setCacheManager(cacheManager);
            return em;
        }
    }

    /**
     * 返回配置文件流 避免ehcache配置文件一直被占用，无法完全销毁项目重新部署
     */
    protected InputStream getCacheManagerConfigFileInputStream()
    {
        String configFile = "classpath:ehcache/ehcache-shiro.xml";
        InputStream inputStream = null;
        try
        {
            inputStream = ResourceUtils.getInputStreamForPath(configFile);
            byte[] b = IOUtils.toByteArray(inputStream);
            InputStream in = new ByteArrayInputStream(b);
            return in;
        }
        catch (IOException e)
        {
            throw new ConfigurationException(
                    "Unable to obtain input stream for cacheManagerConfigFile [" + configFile + "]", e);
        }
        finally
        {
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 自定义Realm
     */
    @Bean
    public UserRealm userRealm(EhCacheManager cacheManager)
    {
        UserRealm userRealm = new UserRealm();
        userRealm.setAuthorizationCacheName(Constants.SYS_AUTH_CACHE);
        userRealm.setCacheManager(cacheManager);
        return userRealm;
    }

    /**
     * 自定义sessionDAO会话
     */
    @Bean
    public OnlineSessionDAO sessionDAO()
    {
        OnlineSessionDAO sessionDAO = new OnlineSessionDAO();
        return sessionDAO;
    }

    /**
     * 自定义sessionFactory会话
     */
    @Bean
    public OnlineSessionFactory sessionFactory()
    {
        OnlineSessionFactory sessionFactory = new OnlineSessionFactory();
        return sessionFactory;
    }

    /**
     * 会话管理器
     */
    @Bean
    public OnlineWebSessionManager sessionManager()
    {
        OnlineWebSessionManager manager = new OnlineWebSessionManager();
        // 加入缓存管理器
        manager.setCacheManager(getEhCacheManager());
        // 删除过期的session
        manager.setDeleteInvalidSessions(true);
        // 设置全局session超时时间
        manager.setGlobalSessionTimeout(expireTime * 60 * 1000);
        // 去掉 JSESSIONID
        manager.setSessionIdUrlRewritingEnabled(false);
        // 定义要使用的无效的Session定时调度器
   //     manager.setSessionValidationScheduler(SpringUtils.getBean(SpringSessionValidationScheduler.class));
        // 是否定时检查session
        manager.setSessionValidationSchedulerEnabled(true);
        // 自定义SessionDao
        manager.setSessionDAO(sessionDAO());
        // 自定义sessionFactory
        manager.setSessionFactory(sessionFactory());
        return manager;
    }

    /**
     * 安全管理器
     */
    @Bean
    public SecurityManager securityManager(UserRealm userRealm)
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(userRealm);
        // 记住我
        securityManager.setRememberMeManager(rememberMe ? rememberMeManager() : null);
        // 注入缓存管理器;
        securityManager.setCacheManager(getEhCacheManager());
        // session管理器
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * 退出过滤器
     */
    public LogoutFilter logoutFilter()
    {
        // 若依定义了自己的退出过滤器，继承了系统的退出过滤器，但是使用系统给的退出过滤器更方便
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl(loginUrl);
//        logoutFilter.setLoginUrl(loginUrl);
        return logoutFilter;
    }

    /**
     * Shiro过滤器配置
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager)
    {
        CustomShiroFilterFactoryBean shiroFilterFactoryBean = new CustomShiroFilterFactoryBean();
        // Shiro的核心安全接口,这个属性是必须的
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 身份认证失败，则跳转到登录页面的配置
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        // 权限认证失败，则跳转到指定页面
        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
        // Shiro连接约束配置，即过滤链的定义
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 对静态资源设置匿名访问
        filterChainDefinitionMap.put("plugins/**","anon");
        filterChainDefinitionMap.put("/moment/**","anon");
        filterChainDefinitionMap.put("/image/**","anon");
        filterChainDefinitionMap.put("/api/**","anon");
        filterChainDefinitionMap.put("/layui/**","anon");
        filterChainDefinitionMap.put("/plugins/**","anon");
        filterChainDefinitionMap.put("/favicon.ico**", "anon");
        filterChainDefinitionMap.put("/ruoyi.png**", "anon");
        filterChainDefinitionMap.put("/html/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/docs/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/ajax/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/ruoyi/**", "anon");

        filterChainDefinitionMap.put("/loginTest", "anon");
        filterChainDefinitionMap.put("/index", "anon");

        filterChainDefinitionMap.put("/captcha/captchaImage**", "anon");
        // 退出 logout地址，shiro去清除session
        filterChainDefinitionMap.put("/logout", "logout");
        // 不需要拦截的访问
        filterChainDefinitionMap.put("/agv/login", "anon");
        filterChainDefinitionMap.put("/agv/**", "anon");
        filterChainDefinitionMap.put("/xq","anon");
        filterChainDefinitionMap.put("/jg","anon");
        filterChainDefinitionMap.put("/agv/mark","anon");
//        filterChainDefinitionMap.put("/agv/login", "anon,captchaValidate");
        filterChainDefinitionMap.put("/login","anon");
        // 注册相关
        filterChainDefinitionMap.put("/register", "anon");
//        filterChainDefinitionMap.put("/register", "anon,captchaValidate");
        // 系统权限列表
        // filterChainDefinitionMap.putAll(SpringUtils.getBean(IMenuService.class).selectPermsAll());

        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        filters.put("onlineSession", onlineSessionFilter());
        filters.put("syncOnlineSession", syncOnlineSessionFilter());
//        filters.put("captchaValidate", captchaValidateFilter());
        filters.put("kickout", kickoutSessionFilter());
        // 注销成功，则跳转到指定页面
        filters.put("logout", logoutFilter());
        shiroFilterFactoryBean.setFilters(filters);

        // 所有请求需要认证
        /*
        rememberMe并且在有效期内登录过，则会允许访问其路径，但不会允许访问authc的路径。user是介于，anon和authc直之间的。
        而“/authenticated= user”表示访问该地址的用户是身份验证通过或RememberMe 登录的都可以。或者说，某个页面需要登录才能看，
        但这个页面信息又不太重要，就可已使用这个
         */
        filterChainDefinitionMap.put("/**", "user,onlineSession,syncOnlineSession");
//        filterChainDefinitionMap.put("/**", "user,kickout,onlineSession,syncOnlineSession");
//        filterChainDefinitionMap.put("/agv/**", "user,kickout,onlineSession,syncOnlineSession");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }


    // 以下一个配置可以让@RequiresRoles等五个权限控制注解有效
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }



    /**
     * 自定义在线用户处理过滤器
     */
    public OnlineSessionFilter onlineSessionFilter()
    {
        OnlineSessionFilter onlineSessionFilter = new OnlineSessionFilter();
        onlineSessionFilter.setLoginUrl(loginUrl);
        onlineSessionFilter.setOnlineSessionDAO(sessionDAO());
        return onlineSessionFilter;
    }

    /**
     * 自定义在线用户同步过滤器
     */
    public SyncOnlineSessionFilter syncOnlineSessionFilter()
    {
        SyncOnlineSessionFilter syncOnlineSessionFilter = new SyncOnlineSessionFilter();
        syncOnlineSessionFilter.setOnlineSessionDAO(sessionDAO());
        return syncOnlineSessionFilter;
    }

    /**
     * 自定义验证码过滤器
     */
    public CaptchaValidateFilter captchaValidateFilter()
    {
        CaptchaValidateFilter captchaValidateFilter = new CaptchaValidateFilter();
        captchaValidateFilter.setCaptchaEnabled(captchaEnabled);
        captchaValidateFilter.setCaptchaType(captchaType);
        return captchaValidateFilter;
    }

    /**
     * cookie 属性设置
     */
    public SimpleCookie rememberMeCookie()
    {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setHttpOnly(httpOnly);
        cookie.setMaxAge(maxAge * 24 * 60 * 60);
        return cookie;
    }

    /**
     * 记住我
     */
    public CookieRememberMeManager rememberMeManager()
    {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());

        // 如果配置表没有配置密钥，就产生随机的密钥
        if (StringUtils.isNotEmpty(cipherKey))
        {
            cookieRememberMeManager.setCipherKey(Base64.decode(cipherKey));
        }
        else
        {
            cookieRememberMeManager.setCipherKey(CipherUtils.generateNewKey(128, "AES").getEncoded());
        }
        return cookieRememberMeManager;
    }

    /**
     * 同一个用户多设备登录限制
     */
    public KickoutSessionFilter kickoutSessionFilter()
    {
        KickoutSessionFilter kickoutSessionFilter = new KickoutSessionFilter();
        kickoutSessionFilter.setCacheManager(getEhCacheManager());
        kickoutSessionFilter.setSessionManager(sessionManager());
        // 同一个用户最大的会话数，默认-1无限制；比如2的意思是同一个用户允许最多同时两个人登录
        kickoutSessionFilter.setMaxSession(maxSession);
        // 是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；踢出顺序
        kickoutSessionFilter.setKickoutAfter(kickoutAfter);
        // 被踢出后重定向到的地址；
        kickoutSessionFilter.setKickoutUrl("/login?kickout=1");
        return kickoutSessionFilter;
    }

    /**
     * thymeleaf模板引擎和shiro框架的整合
     */
    @Bean
    public ShiroDialect shiroDialect()
    {
        return new ShiroDialect();
    }

    /**
     * 开启Shiro注解通知器
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") SecurityManager securityManager)
    {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
