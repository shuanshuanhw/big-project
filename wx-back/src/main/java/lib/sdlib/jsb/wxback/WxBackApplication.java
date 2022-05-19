package lib.sdlib.jsb.wxback;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import lib.sdlib.jsb.wxback.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@SpringBootApplication
@Controller
@ResponseBody
@Slf4j
public class WxBackApplication {
    @Autowired
    private UserMapper userMapper;

    public static void main(String[] args) {
        SpringApplication.run(WxBackApplication.class, args);
    }

    @GetMapping("/test")
    public String test(String code) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建 GET 请求
        HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/sns/jscode2session?appid=wx873d2b29be43c05d&secret=7d0986b8e18192166a80cfe6bfc9da17&js_code="+code+"&grant_type=authorization_code");
        log.info("【发送GET请求】请求地址为：{}", "https://api.weixin.qq.com/sns/jscode2session?appid=wx873d2b29be43c05d&secret=7d0986b8e18192166a80cfe6bfc9da17&js_code=JSCODE&grant_type=authorization_code");
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            log.info("【发送GET请求】成功，相应状态为：{}", httpResponse.getStatusLine());
            if (HttpStatus.SC_OK == httpResponse.getStatusLine().getStatusCode() && null != httpEntity) {
                String result = EntityUtils.toString(httpEntity);
                Map<String, JSONObject> map1 = (Map)JSON.parse(result);
                JSONObject json2 = map1.get("result");
                log.info(map1.toString());
                log.info("【发送GET请求】成功，响应内容为：{}", result);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "test22";
    }
}
