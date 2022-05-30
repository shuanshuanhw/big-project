package lib.sdlib.jsb.mark.dao;

import java.util.List;

import lib.sdlib.jsb.mark.entity.LikeTimes;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface LikeTimesMapper {
    @Delete({
        "delete from like_times",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into like_times (art, create_time, ",
        "times)",
        "values (#{art,jdbcType=VARCHAR}, #{create_time,jdbcType=TIMESTAMP}, ",
        "#{times,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(LikeTimes row);

    @Select({
        "select",
        "id, art, create_time, times",
        "from like_times",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="art", property="art", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="create_time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="times", property="times", jdbcType=JdbcType.INTEGER)
    })
    LikeTimes selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "id, art, create_time, times",
            "from like_times",
            "where art = #{art,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="art", property="art", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="create_time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="times", property="times", jdbcType=JdbcType.INTEGER)
    })
    LikeTimes selectByArt(String art);

    @Select({
            "select",
            "count(*)",
            "from like_times",
            "where art = #{art,jdbcType=VARCHAR}"
    })
    int selectCountByArt(String art);

    @Select({
        "select",
        "id, art, create_time, times",
        "from like_times"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="art", property="art", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="create_time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="times", property="times", jdbcType=JdbcType.INTEGER)
    })
    List<LikeTimes> selectAll();

    @Update({
        "update like_times",
        "set art = #{art,jdbcType=VARCHAR},",
          "create_time = #{create_time,jdbcType=TIMESTAMP},",
          "times = #{times,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(LikeTimes row);
}