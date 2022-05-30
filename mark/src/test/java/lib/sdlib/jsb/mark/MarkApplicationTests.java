package lib.sdlib.jsb.mark;

import lib.sdlib.jsb.mark.dao.DataSdlibStatiMapper;
import lib.sdlib.jsb.mark.dao.LikeTimesMapper;
import lib.sdlib.jsb.mark.entity.DataSdlibStati;
import lib.sdlib.jsb.mark.entity.LikeTimes;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@Slf4j
@SpringBootTest
class MarkApplicationTests {
    @Autowired
    LikeTimesMapper likeTimesMapper;
    @Autowired
    DataSdlibStatiMapper dataSdlibStatiMapper;
    @Test
    void contextLoads() {

//        log.info(dataSdlibStatiMapper.selectAll().toString());

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

        System.out.println("----------------------------------");
        System.out.println(list.toString());




    }

}
