package lib.sdlib.jsb.mark.filter;


import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Constants;
import lib.sdlib.jsb.mark.common.ShiroConstants;
import lib.sdlib.jsb.mark.utils.GetRequestJsonUtils;
import lib.sdlib.jsb.mark.utils.StringUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 验证码过滤器
 *
 * @author ruoyi
 */
@Slf4j
public class CaptchaValidateFilter extends AccessControlFilter
{
    /**
     * 是否开启验证码
     */
    private boolean captchaEnabled = true;

    /**
     * 验证码类型
     */
    private String captchaType = "math";

    public void setCaptchaEnabled(boolean captchaEnabled)
    {
        this.captchaEnabled = captchaEnabled;
    }

    public void setCaptchaType(String captchaType)
    {
        this.captchaType = captchaType;
    }

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception
    {
        // 这里调用一下父方法，父方法就是必须两个有一个为真
        // return this.isAccessAllowed(request, response, mappedValue) || this.onAccessDenied(request, response, mappedValue);
        return super.onPreHandle(request, response, mappedValue);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;


        JSONObject json = GetRequestJsonUtils.getRequestJsonObject(httpServletRequest);
        String username = json.getString("login_name");
        String password = json.getString("password");
        String validateCode = json.getString("validateCode");
        boolean rememberme = json.getBoolean("rememberme");


        log.info("1");
        // 验证码禁用 或不是表单提交 允许访问
        if (captchaEnabled == false || !"post".equals(httpServletRequest.getMethod().toLowerCase()))
        {
            return true;
        }

//        log.info(httpServletRequest.getParameter("login_name"));
//        log.info("前台转来的验证码"+httpServletRequest.getParameter(ShiroConstants.CURRENT_VALIDATECODE));
        // 如果是post并且是控制器login，那就检测一下req里面和session里面的验证码是否是一致的，防止验证码重复使用
        return validateResponse(httpServletRequest, "");
    }

    public boolean validateResponse(HttpServletRequest request, String validateCode)
    {
        Object obj = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        log.info("2");
//        Object obj = request.getSession().getAttribute(ShiroConstants.CURRENT_VALIDATECODE);
        String code = String.valueOf(obj != null ? obj : "");
        // 验证码清除，防止多次使用。
        request.getSession().removeAttribute(ShiroConstants.CURRENT_VALIDATECODE);
        if (StringUtils.isEmpty(validateCode) || !validateCode.equalsIgnoreCase(code))
        {
            // equalsIgnoreCase 忽略大小写的比较
            // 一旦页面上的验证码输入框的值为空，或者 session 里的验证码和页面上传过来的不一致，就跳到拒绝方法执行处理
            log.info("3");
            return false;
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
    {
        // 这里为true才会接下来执行其它的过滤器和servlet，如果为false，就会终止执行
        request.setAttribute(ShiroConstants.CURRENT_CAPTCHA, ShiroConstants.CAPTCHA_ERROR);
        log.info("4");
        return true;


    }
}
