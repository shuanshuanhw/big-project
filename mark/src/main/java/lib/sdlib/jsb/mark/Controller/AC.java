package lib.sdlib.jsb.mark.Controller;

import com.github.pagehelper.PageHelper;
import lib.sdlib.jsb.mark.common.Result;
import lib.sdlib.jsb.mark.dao.DataSdlibStatiMapper;
import lib.sdlib.jsb.mark.entity.DataSdlibStati;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 类名： AC
 * 描述 TODO：
 * 创建时间： 2022/5/24 10:29
 * 创建人： Administrator
 */
@Controller
@ResponseBody
@RequestMapping("/agv")
public class AC {

    @Autowired
    DataSdlibStatiMapper dataSdlibStatiMapper;

    @GetMapping("/getAll")
    public Result getAll()
    {
        PageHelper.startPage(1,2);
        List<DataSdlibStati> dataSdlibStatis = dataSdlibStatiMapper.selectAll();
        return Result.ok(dataSdlibStatis);
    }
}
