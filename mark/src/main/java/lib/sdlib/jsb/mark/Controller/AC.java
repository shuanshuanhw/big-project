package lib.sdlib.jsb.mark.Controller;

import com.github.pagehelper.PageHelper;
import com.sun.org.apache.regexp.internal.RE;
import lib.sdlib.jsb.mark.common.Result;
import lib.sdlib.jsb.mark.dao.DataSdlibStatiMapper;
import lib.sdlib.jsb.mark.entity.DataSdlibStati;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * 类名： AC
 * 描述 TODO：
 * 创建时间： 2022/5/24 10:29
 * 创建人： Administrator
 */
@Controller
//@ResponseBody
//@RequestMapping("/agv")
public class AC {

    @Autowired
    DataSdlibStatiMapper dataSdlibStatiMapper;

    @GetMapping("/agv/getAll")
    @ResponseBody
    public Result getAll()
    {
     //   PageHelper.startPage(1,2);
        List<DataSdlibStati> dataSdlibStatis = dataSdlibStatiMapper.selectAll();
        for(DataSdlibStati data:dataSdlibStatis)
        {
            if(data.getName_implication().length()>100)
                data.setName_implication(data.getName_implication().substring(0,100));

            data.setIfId("if"+data.getId());

            data.setIfSelect(false);
            data.setIfSelectString("未勾选");
        }
        return Result.ok(dataSdlibStatis);
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
        StringBuilder sb = new StringBuilder();
        sb.append("名称：");
        sb.append("<br />");
        sb.append(dataSdlibStati.getAgv_name());
        sb.append("<br />");sb.append("<br />");
        sb.append("名称寓意：");
        sb.append("<br />");
        sb.append(dataSdlibStati.getName_implication());
        sb.append("<br />");sb.append("<br />");
        sb.append("建议:");
        sb.append("<br />");
        sb.append(dataSdlibStati.getOpinion());

        return "xq";
    }
}
