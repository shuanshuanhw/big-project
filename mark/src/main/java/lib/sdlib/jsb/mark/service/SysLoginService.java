package lib.sdlib.jsb.mark.service;

import lib.sdlib.jsb.mark.common.ShiroConstants;
import lib.sdlib.jsb.mark.common.UserConstants;
import lib.sdlib.jsb.mark.common.UserStatus;
import lib.sdlib.jsb.mark.dao.UserMapper;
import lib.sdlib.jsb.mark.entity.User;
import lib.sdlib.jsb.mark.exception.*;
import lib.sdlib.jsb.mark.utils.DateUtils;
import lib.sdlib.jsb.mark.utils.ServletUtils;
import lib.sdlib.jsb.mark.utils.ShiroUtils;
import lib.sdlib.jsb.mark.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
@Slf4j
public class SysLoginService
{
    @Autowired
    private SysPasswordService passwordService;
//
//    @Autowired
//    private ISysUserService userService;

    @Autowired
    UserMapper userMapper;

    /**
     * 登录
     */
    public User login(String username, String password)
    {
        log.info("CURRENT_CAPTCHA:"+ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA));

        // 验证码校验
        // 一个鸡贼的设置
        // 一旦页面上的验证码输入框的值为空，或者 session 里的验证码和页面上传过来的不一致，在过滤器里设置req里的验证码值为ShiroConstants.CAPTCHA_ERROR
        // 再到这里来做处理
        if (ShiroConstants.CAPTCHA_ERROR.equals(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA)))
        {
        //    AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
      //      AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
       //     AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }

        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
      //      AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }

        // 查询用户信息
        User user = userMapper.selectUserByLoginName(username);

        /**
         if (user == null && maybeMobilePhoneNumber(username))
         {
         user = userService.selectUserByPhoneNumber(username);
         }

         if (user == null && maybeEmail(username))
         {
         user = userService.selectUserByEmail(username);
         }
         */

        if (user == null)
        {
    //        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.not.exists")));
            throw new UserNotExistsException();
        }

        if (UserStatus.DELETED.getCode().equals(user.getDel_flag()))
        {
        //    AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.delete")));
            throw new UserDeleteException();
        }

        if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
   //         AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.blocked", user.getRemark())));
            throw new UserBlockedException();
        }

        passwordService.validate(user, password);

    //    AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        recordLoginInfo(user.getUser_id());
        return user;
    }

    /**
     private boolean maybeEmail(String username)
     {
     if (!username.matches(UserConstants.EMAIL_PATTERN))
     {
     return false;
     }
     return true;
     }

     private boolean maybeMobilePhoneNumber(String username)
     {
     if (!username.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN))
     {
     return false;
     }
     return true;
     }
     */

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId)
    {
        User user = new User();
        user.setUser_id(userId);
        user.setLogin_ip(ShiroUtils.getIp());
        user.setLogin_date(DateUtils.getNowDate());
        // 这里应该是更新最后一次登陆时间，不需要
        // userService.updateUserInfo(user);
    }
}
