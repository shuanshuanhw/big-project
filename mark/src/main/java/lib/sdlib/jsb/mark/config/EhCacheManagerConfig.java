package lib.sdlib.jsb.mark.config;

import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 类名： EhCacheManagerConfig
 * 描述 TODO：
 * 创建时间： 2022/6/6 11:17
 * 创建人： Administrator
 */
//@Configuration
public class EhCacheManagerConfig {

    @Bean
    public EhCacheManager getEhCacheManager11()
    {
        CacheManager cacheManager = CacheManager.getCacheManager("shuanshuan");
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManager(cacheManager);

        return ehCacheManager;
    }
}
