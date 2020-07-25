/**
 * 文件名：ShiroConfig
 * 作者：liuzeming
 * 时间：2020/6/16 20:32
 * 描述：
 */

package com.order.system.realm;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @Package: com.order.system.realm
 * @ClassName: ShiroConfig
 * @Author: liuzeming
 * @Description: ${description}  
 * @Date: 2020/6/16 20:32
 */

@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     */

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //关联SecurityManager
       bean.setSecurityManager(securityManager);
       //添加shiro内置的过滤器
        /**
         * anon 无需认证   authc 必须认证  user 拥有 记住我 功能才能访问
         * perms 拥有对某个资源权限  role 拥有某个角色的权限
         */
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/login","anon");
        filterMap.put("/fore/**","anon");
        filterMap.put("/islogout","logout");
        filterMap.put("/static/**","anon");
        filterMap.put("/css/**","anon");
        filterMap.put("/js/**","anon");
        filterMap.put("/assets/**","anon");
        filterMap.put("/lib/**","anon");
        filterMap.put("/images/**","anon");
        //filterMap.put("/**","url");
        bean.setLoginUrl("/login");
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("realm")AdminDatabaseRealm realm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //关联realm
        manager.setRealm(realm);
        return manager;
    }

    /**
     * 创建Realm
     */
    @Bean(name = "realm")
    public AdminDatabaseRealm getRealm(){
        return new AdminDatabaseRealm();
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        //Shiro自带加密
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //散列算法使用md5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //散列次数，2表示md5加密两次
        credentialsMatcher.setHashIterations(2);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

}
