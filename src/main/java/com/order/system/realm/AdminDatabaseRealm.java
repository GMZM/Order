/**
 * 文件名：AdminDatabaseRealm
 * 作者：liuzeming
 * 时间：2020/6/10 17:35
 * 描述：
 */

package com.order.system.realm;

import com.order.system.pojo.Admin;
import com.order.system.service.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminDatabaseRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }


    //登陆的认证,return null的时候shiro底层会抛出一个UnknownAccountException
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //验证账户
        String username = userToken.getPrincipal().toString();
        Admin admin = adminService.findByName(username);
        if (admin == null) {
            System.out.println("-----------------AdminDatabaseRealm");
            //返回一个账户不存在
            return null;
        }
        String salt = admin.getSalt();
        //验证密码，返回一个AuthenticationInfo的子类，
        //为了安全不需要程序元去操作密码的匹配，直接将密码传入即可
        return new SimpleAuthenticationInfo("", "adminadmin",ByteSource.Util.bytes(salt), "");
    }
}
