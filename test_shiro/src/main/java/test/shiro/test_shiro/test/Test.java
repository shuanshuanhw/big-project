package test.shiro.test_shiro.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 * 类名： Test
 * 描述 TODO：
 * 创建时间： 2022/5/13 9:20
 * 创建人： Administrator
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        Subject currentUser = SecurityUtils.getSubject();
        if ( !currentUser.isAuthenticated() ) {
            //collect user principals and credentials in a gui specific manner
            //such as username/password html form, X509 certificate, OpenID, etc.
            //We'll use the username/password example here since it is the most common.
            //(do you know what movie this is from? ;)
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            //this is all you have to do to support 'remember me' (no config - built in!):
            token.setRememberMe(true);
            currentUser.login(token);
        }
        log.info("登陆之后，再取出登陆帐号");
        log.info(currentUser.getPrincipal().toString());
    }
}
