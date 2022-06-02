package shuanshuan.pay.testpay;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;


@Data
public class Result implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(Result.class);
    private static final long serialVersionUID = 1L;


    private Integer code;
    private Boolean flag;
    private String message;
    private Object data;

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.flag = code == ResultEnum.SUCCESS.getCode() ? true : false;
    }


    public static Result ok() {
        return new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc(), null);
    }

    public static Result ok(Object data) {
        return new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc(), data);
    }


    public static Result ok(String message, Object data) {
        return new Result(ResultEnum.SUCCESS.getCode(), message, data);
    }

    public static Result error(String message) {
        logger.debug("返回错误：code={}, message={}", ResultEnum.ERROR.getCode(), message);
        return new Result(ResultEnum.ERROR.getCode(), message, null);
    }

    public static Result build(int code, String message) {
        logger.debug("返回结果：code={}, message={}", code, message);
        return new Result(code, message, null);
    }

    public static Result build(ResultEnum resultEnum) {
        logger.debug("返回结果：code={}, message={}", resultEnum.getCode(), resultEnum.getDesc());
        return new Result(resultEnum.getCode(), resultEnum.getDesc(), null);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
//
//    public Map<String, Object> responseUserSearchResult(String code, boolean flag, Object obj, String message, int total)
//    {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("code",code);
//        map.put("flag",flag);
//        map.put("message",message);
//        map.put("obj",obj);
//        map.put("total",total);
//        return map;
//    }
//
//
//    public Map<String, Object> responseUserNormalResult(String code,boolean flag,Object obj,String message)
//    {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("code",code);
//        map.put("flag",flag);
//        map.put("message",message);
//        map.put("obj",obj);
//        return map;
//    }
//
//
//    public Map<String, Object> responseUserLandResult(String code,boolean flag,String message)
//    {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("code",code);
//        map.put("flag",flag);
//        map.put("message",message);
//        return map;
//    }
//    public Map<String, Object> responseUserInfoResult(String token, boolean flag)
//    {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("userInfo", userRepository.getByUsernamePart(token));
//        map.put("token",token);
//        map.put("flag", flag);
//        return map;
//    }

}
