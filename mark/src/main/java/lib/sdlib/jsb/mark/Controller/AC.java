package lib.sdlib.jsb.mark.Controller;

import cn.hutool.core.convert.Convert;
import com.github.pagehelper.PageHelper;
import com.google.code.kaptcha.Constants;
import com.sun.org.apache.regexp.internal.RE;
import lib.sdlib.jsb.mark.common.Result;
import lib.sdlib.jsb.mark.common.ShiroConstants;
import lib.sdlib.jsb.mark.dao.DataSdlibStatiMapper;
import lib.sdlib.jsb.mark.dao.LikeTimesMapper;
import lib.sdlib.jsb.mark.entity.DataSdlibStati;
import lib.sdlib.jsb.mark.entity.LikeTimes;
import lib.sdlib.jsb.mark.entity.User;
import lib.sdlib.jsb.mark.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.*;

/**
 * 类名： AC
 * 描述 TODO：
 * 创建时间： 2022/5/24 10:29
 * 创建人： Administrator
 */
@Controller
@Slf4j
//@ResponseBody
//@RequestMapping("/agv")
public class AC {

    /**
     * 是否开启记住我功能
     */
    @Value("${shiro.rememberMe.enabled: false}")
    private boolean rememberMe;
    @Value("${sys.account.allowRegister}")
    private boolean allowRegister;

    @Value("${shiro.user.captchaEnabled}")
    private boolean captchaEnabled;
    @Value("${shiro.user.captchaType}")
    private String captchaType;

    @Autowired
    DataSdlibStatiMapper dataSdlibStatiMapper;
    @Autowired
    LikeTimesMapper likeTimesMapper;


    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, ModelMap mmap)
    {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }
        // 是否开启记住我
        // 如果rememberMe为true，登陆页面就显示一个记住我的多选框
        // 通过在这里设定了是否显示验证码和验证码类型，在登陆页进行验证
//        request.setAttribute(ShiroConstants.CURRENT_ENABLED, captchaEnabled);
//        request.setAttribute(ShiroConstants.CURRENT_TYPE, captchaType);
        mmap.put(ShiroConstants.CURRENT_ENABLED,captchaEnabled);
        mmap.put(ShiroConstants.CURRENT_TYPE,captchaType);
        mmap.put("isRemembered", rememberMe);
        //  logger.info("rememberMe"+rememberMe);
        // 是否开启用户注册
        // Convert.toBool(configService.getKey("sys.account.registerUser") 将字符串转为布尔值
        // 如果没有字符串，默认值就是false
        // 将配置参数放在一个专门的数据库表上，需要的时候去取
        mmap.put("isAllowRegister", allowRegister);
        return "login";
    }



    /**
     * 登陆
     * @param user
     * @return
     */

    @ResponseBody
    @PostMapping("/agv/login")
    public Result login(@RequestBody @Valid User user, BindingResult errors,HttpServletRequest req)
    {
        // 判断验证码

        Object obj = req.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String code = String.valueOf(obj != null ? obj : "");

        req.getSession().removeAttribute(ShiroConstants.CURRENT_VALIDATECODE);

        if (lib.sdlib.jsb.mark.utils.StringUtils.isEmpty(user.getValidateCode()) || !user.getValidateCode().equalsIgnoreCase(code))
        {
            // equalsIgnoreCase 忽略大小写的比较
            return Result.error("验证码为空或验证码错误");
        }

        if (errors.hasErrors()) {
            return Result.error(errors.getFieldError().getDefaultMessage());
        }
        UsernamePasswordToken token = new UsernamePasswordToken(user.getLogin_name(), user.getPassword(), user.isRememberme());
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return Result.ok();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return Result.error(msg);
        }
    }
    @ResponseBody
    @PostMapping("/agv/submitFruition")
    public Result submitFruition(@RequestBody List<DataSdlibStati> dataArray)
    {
        for(DataSdlibStati data:dataArray)
        {
            if(data.getIfSelect())
            {
                LikeTimes build = LikeTimes.builder().art(data.getAgv_name()).times(1).build();
                likeTimesMapper.insert(build);
            }
        }
        return Result.ok();
    }
    @GetMapping("/agv/getAll")
    @ResponseBody
    public Result getAll()
    {
     //   PageHelper.startPage(1,2);
        List<DataSdlibStati> dataSdlibStatis = dataSdlibStatiMapper.selectAll();
        for(DataSdlibStati data:dataSdlibStatis)
        {
            if(data.getName_implication().length()>100)
                data.setName_implication(data.getName_implication().substring(0,100)+"...");

            data.setIfId("if"+data.getId());

            data.setIfSelect(false);
            data.setIfSelectString("未勾选");
        }
        return Result.ok(dataSdlibStatis);
    }


    @GetMapping("/getTop10")
    @ResponseBody
    public Result getTop10()
    {
        // 1、取出所有数据
        Set<String> newSet = new HashSet();
        List<LikeTimes> likeTimes = likeTimesMapper.selectAll();
        log.info(likeTimesMapper.selectAll().toString());
        log.info("likeTimes",likeTimes);
        for(LikeTimes like:likeTimes)
        {
            // 通过去重
            newSet.add(like.getArt());
        }
        log.info(newSet.toString());
        // 2、排序
        List<DataSdlibStati> list = new ArrayList();
        for(String art:newSet)
        {
            int i = likeTimesMapper.selectCountByArt(art);
            log.info(String.valueOf(i)+art);
            DataSdlibStati dataSdlibStati = dataSdlibStatiMapper.selectByArt(art);
            dataSdlibStati.setScore(i);
            list.add(dataSdlibStati);
        }
        System.out.println(list.toString());

        Collections.sort(list);

        return Result.ok(list);
    }
    /**
     * 根据前台拼接的ids取数据列表,返回
     * @param
     * @return
     */
    @GetMapping("/agv/getAllByIds/{ids}")
    @ResponseBody
    public Result getAllByIds(@PathVariable String ids)
    {
        // 定义一个列表,用来存储分割后的ids
        String[] split = null;
        if(StringUtils.isEmpty(ids))
        {
            Result.error("传入后台的参数不能为空");
        }
        else
        {
            split = ids.split("a");
        }
        List<DataSdlibStati> dataSdlibStatis = dataSdlibStatiMapper.selectAll();
        List<DataSdlibStati> returnData = new ArrayList<>();
        for(DataSdlibStati data:dataSdlibStatis)
        {
            for(int i=0;i<split.length;i++)
            {
                if(data.getId().toString().equals(split[i]))
                {
                    // 一旦存在这个数组中的,整个对象传到前台
                    returnData.add(data);
                }
            }
        }
        if(!returnData.isEmpty())
        {
            for(DataSdlibStati data:returnData) {
                if (data.getName_implication().length() > 100)
                    data.setName_implication(data.getName_implication().substring(0, 100) + "...");
            }
        }

        return Result.ok(returnData);
    }

    @GetMapping("/agv/getAllById/{id}")
    @ResponseBody
    public Result getAllById(@PathVariable Integer id)
    {
        DataSdlibStati dataSdlibStati = dataSdlibStatiMapper.selectByPrimaryKey(id);
        return Result.ok(dataSdlibStati);
    }

    @GetMapping("/xq")
    public String xq(@PathParam("id")Integer id, HttpServletRequest req)
    {
        DataSdlibStati dataSdlibStati = dataSdlibStatiMapper.selectByPrimaryKey(id);
    //    req.setAttribute("data",dataSdlibStati);
        req.setAttribute("id",id);

        return "xq";
    }

    @GetMapping("/jg")
    public String jg(@PathParam("ids")String ids, HttpServletRequest req)
    {
        req.setAttribute("id",ids);
        return "jg";
    }
}
