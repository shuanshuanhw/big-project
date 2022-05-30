package lib.sdlib.jsb.mark.dao;

import java.util.List;
import lib.sdlib.jsb.mark.entity.DataSdlibStati;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface DataSdlibStatiMapper {
    @Delete({
        "delete from data_sdlib_stati",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into data_sdlib_stati (agv_name, clientip, ",
        "create_time, name, ",
        "opinion, phone, ",
        "name_implication)",
        "values (#{agv_name,jdbcType=VARCHAR}, #{clientip,jdbcType=VARCHAR}, ",
        "#{create_time,jdbcType=TIMESTAMP}, #{name,jdbcType=VARCHAR}, ",
        "#{opinion,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, ",
        "#{name_implication,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(DataSdlibStati row);

    @Select({
        "select",
        "id, agv_name, clientip, create_time, name, opinion, phone, name_implication",
        "from data_sdlib_stati",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="agv_name", property="agv_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="clientip", property="clientip", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="create_time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="opinion", property="opinion", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="name_implication", property="name_implication", jdbcType=JdbcType.LONGVARCHAR)
    })
    DataSdlibStati selectByPrimaryKey(Integer id);



    @Select({
            "select",
            "id, agv_name, clientip, create_time, name, opinion, phone, name_implication",
            "from data_sdlib_stati",
            "where agv_name = #{art,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="agv_name", property="agv_name", jdbcType=JdbcType.VARCHAR),
            @Result(column="clientip", property="clientip", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="create_time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="opinion", property="opinion", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="name_implication", property="name_implication", jdbcType=JdbcType.LONGVARCHAR)
    })
    DataSdlibStati selectByArt(String art);


    @Select({
        "select",
        "id, agv_name, clientip, create_time, name, opinion, phone, name_implication",
        "from data_sdlib_stati"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="agv_name", property="agv_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="clientip", property="clientip", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="create_time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="opinion", property="opinion", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="name_implication", property="name_implication", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<DataSdlibStati> selectAll();

    @Update({
        "update data_sdlib_stati",
        "set agv_name = #{agv_name,jdbcType=VARCHAR},",
          "clientip = #{clientip,jdbcType=VARCHAR},",
          "create_time = #{create_time,jdbcType=TIMESTAMP},",
          "name = #{name,jdbcType=VARCHAR},",
          "opinion = #{opinion,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "name_implication = #{name_implication,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(DataSdlibStati row);
}